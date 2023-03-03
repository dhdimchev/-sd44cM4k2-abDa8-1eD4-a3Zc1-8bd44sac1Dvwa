package com.example.demo.persistence;

import com.example.demo.persistence.entities.CountryEntity;
import com.example.demo.persistence.entities.GlobalCovidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<CountryEntity,Long> {
    Optional<CountryEntity> findByCountryCode(String countryCode);
}
