package com.example.demo.rest;

import com.example.demo.DTOs.CountryDTO;
import com.example.demo.DTOs.SummaryDTO;
import com.example.demo.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    CovidService restService;


    @GetMapping ("/summary/countries")
    public List<CountryDTO> getCountries(){
        return restService.getCountries();
    }
    @GetMapping("/country/{countryCode}")
    public ResponseEntity<CountryDTO> getCountryData(@PathVariable String countryCode) {
        return restService.findByCountryCode(countryCode);
    }
    @PutMapping("/summary")
    public SummaryDTO updateSummary(){
        return restService.updateSummary();
    }
}
