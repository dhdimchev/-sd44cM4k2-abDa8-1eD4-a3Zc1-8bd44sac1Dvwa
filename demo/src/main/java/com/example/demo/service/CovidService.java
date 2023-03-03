package com.example.demo.service;

import com.example.demo.DTOs.CountryDTO;
import com.example.demo.DTOs.GlobalCovidDTO;
import com.example.demo.DTOs.SummaryDTO;
import com.example.demo.persistence.CountryRepo;
import com.example.demo.persistence.GlobalCovidRepo;
import com.example.demo.persistence.entities.CountryEntity;
import com.example.demo.persistence.entities.GlobalCovidEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CovidService {
    @Autowired
    RabitMQ rabit;
    @Autowired
    CountryRepo countryRepo;
    @Autowired
    GlobalCovidRepo globalCovidRepo;
    private String url = "https://api.covid19api.com/summary";

    @PostConstruct
    public void updateDatabaseWithLatestInfo(){
        saveData(updateSummary());
    }


    public List<CountryDTO> getCountries(){
        return getAllCountryData();
    }

    public SummaryDTO updateSummary() {
        var summary = rabit.getRestTemp().getForObject(url, SummaryDTO.class);
        saveData(summary);
        return summary;
    }
    public ResponseEntity<CountryDTO>  findByCountryCode(String code){
        Optional<CountryEntity> optionalCountry = countryRepo.findByCountryCode(code.toUpperCase());

        if (optionalCountry.isPresent()) {
            CountryEntity countryEntity = optionalCountry.get();
            CountryDTO countryDto = new CountryDTO(
                    countryEntity.getCountryCode(),
                    countryEntity.getCountry(),
                    countryEntity.getSlug(),
                    countryEntity.getNewConfirmed(),
                    countryEntity.getTotalConfirmed(),
                    countryEntity.getNewDeaths(),
                    countryEntity.getTotalDeaths(),
                    countryEntity.getNewRecovered(),
                    countryEntity.getTotalRecovered(),
                    LocalDateTime.ofInstant(countryEntity.getDate().toInstant(),ZoneOffset.ofHours(0))
            );
            return ResponseEntity.ok(countryDto);
        }
        return ResponseEntity.notFound().build();

    }

    @Transactional
    private List<CountryDTO> getAllCountryData(){
        return countryRepo.findAll()
                .stream()
                .map((CountryEntity country)->{
                    return new CountryDTO(
                            country.getCountryCode(),
                            country.getCountry(),
                            country.getSlug(),
                            country.getNewConfirmed(),
                            country.getTotalConfirmed(),
                            country.getNewDeaths(),
                            country.getTotalDeaths(),
                            country.getNewRecovered(),
                            country.getTotalRecovered(),
                            LocalDateTime.ofInstant(country.getDate().toInstant(),ZoneOffset.ofHours(0)));})
                .collect(Collectors.toList());
    }

    @Transactional
    private void saveData(SummaryDTO summary) {
        // Save the global data
        GlobalCovidDTO globalDto = summary.getGlobal();
        GlobalCovidEntity globalEntity = new GlobalCovidEntity(
                globalDto.getNewConfirmed(),
                globalDto.getTotalConfirmed(),
                globalDto.getNewDeaths(),
                globalDto.getTotalDeaths(),
                globalDto.getNewRecovered(),
                globalDto.getTotalRecovered(),
                Date.valueOf(LocalDate.now())
        );
        globalCovidRepo.save(globalEntity);

        // Save the country data
        List<CountryDTO> countryData = summary.getCountries();
        for (CountryDTO countryDto : countryData) {
            CountryEntity countryEntity = new CountryEntity(
                    countryDto.getCountryCode(),
                    countryDto.getCountry(),
                    countryDto.getSlug(),
                    countryDto.getNewConfirmed(),
                    countryDto.getTotalConfirmed(),
                    countryDto.getNewDeaths(),
                    countryDto.getTotalDeaths(),
                    countryDto.getNewRecovered(),
                    countryDto.getTotalRecovered(),
                    Date.from(countryDto.getDate().toInstant(ZoneOffset.ofHours(0)))
            );
            countryRepo.save(countryEntity);
        }
    }



}
