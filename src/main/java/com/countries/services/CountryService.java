package com.countries.services;

import com.countries.jpa.entity.Country;
import com.countries.model.request.CountryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CountryService {

    Country createCountry(CountryRequest request);

    Page<Country> findAllCountries(Pageable pageable);

    Country updateCountry(CountryRequest request, Long countryId);

    Optional<Country> findCountryByName(String name);

    void deleteCountryById(Long countryId);

}
