package com.countries.model;

import com.countries.jpa.entity.Country;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class CountryTest {

    private Country country;

    @Before
    public void setUp() {
        country =new Country();
    }

    @Test
    public void testCountry() {
        country = Country.builder().id(1L).name("Nigeria").continent("Africa").dateCreated(new Timestamp(System.currentTimeMillis())).build();

        assertEquals("Africa", country.getContinent());
    }
}
