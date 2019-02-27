package com.countries.services;

import com.countries.jpa.entity.Country;
import com.countries.model.request.CountryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

    Country createCountry(CountryRequest request);

    Page<Country> findAllCountries(Pageable pageable);

    Country updateCountry(CountryRequest request);

    void deleteCountryById(Long countryId);

}
