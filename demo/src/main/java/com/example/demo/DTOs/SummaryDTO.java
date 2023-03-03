package com.example.demo.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SummaryDTO {
        @JsonProperty("Global")
        private GlobalCovidDTO global;
        @JsonProperty("Countries")
        private List<CountryDTO> countries;

        public SummaryDTO() {
        }

        public SummaryDTO(GlobalCovidDTO global, List<CountryDTO> countries) {
            this.global = global;
            this.countries = countries;
        }

        public GlobalCovidDTO getGlobal() {
            return global;
        }

        public void setGlobal(GlobalCovidDTO global) {
            this.global = global;
        }

        public List<CountryDTO> getCountries() {
            return countries;
        }

        public void setCountries(List<CountryDTO> countries) {
            this.countries = countries;
        }

}
