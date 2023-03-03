package com.example.demo.persistence.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "country")
public class CountryEntity {

    @Id
    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "country")
    private String country;

    private String slug;

    @Column(name = "new_confirmed")
    private int newConfirmed;

    @Column(name = "total_confirmed")
    private int totalConfirmed;

    @Column(name = "new_deaths")
    private int newDeaths;

    @Column(name = "total_deaths")
    private int totalDeaths;

    @Column(name = "new_recovered")
    private int newRecovered;

    @Column(name = "total_recovered")
    private int totalRecovered;

    private Date date;

    public CountryEntity() {
    }

    public CountryEntity(String countryCode, String country, String slug, int newConfirmed,
                         int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered,
                         int totalRecovered, Date date) {
        this.countryCode = countryCode;
        this.country = country;
        this.slug = slug;
        this.newConfirmed = newConfirmed;
        this.totalConfirmed = totalConfirmed;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.newRecovered = newRecovered;
        this.totalRecovered = totalRecovered;
        this.date = date;
    }

    // getters and setters
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

