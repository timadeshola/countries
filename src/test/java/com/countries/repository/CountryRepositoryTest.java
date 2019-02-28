package com.countries.repository;

import com.countries.jpa.entity.Country;
import com.countries.jpa.entity.Role;
import com.countries.jpa.repository.CountryRepository;
import org.assertj.core.api.Java6Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    private Country country;

    @Before
    public void setUp() {
        country = new Country();
    }

    @Test
    public void tesCreatetCountryRepository() {
        country = Country.builder().id(1L).name("Nigeria").continent("Africa").dateCreated(new Timestamp(System.currentTimeMillis())).build();
        country = countryRepository.save(country);
        assertThat(country.getName()).isEqualTo("Nigeria");
    }

    @Test
    public void testUpdateCountryRepository() {
        country = Country.builder().id(1L).name("Nigeria").continent("Africa").dateCreated(new Timestamp(System.currentTimeMillis())).build();
        country = countryRepository.save(country);
        assertThat(country.getName()).isEqualTo("Nigeria");
        Optional<Country> countryOptional = countryRepository.findByName("Nigeria");
        country = countryOptional.get();
        country.setName("Algeria");
        country = countryRepository.save(country);
        assertThat(country.getName()).isEqualTo("Algeria");
    }

    @Test
    public void fetchFromCountryRepository() {
        Optional<Country> optionalCountry = countryRepository.findByName("Nigeria");
        if(optionalCountry.isPresent()) {
            country = optionalCountry.get();
            assertThat(country.getName()).isEqualTo("ADMIN");
            assertThat(country.getDateCreated()).isNotNull();
        }
    }

    @Test
    public void deleteCountryRepository() {
        Optional<Country> optionalCountry = countryRepository.findByName("Nigeria");
        if(optionalCountry.isPresent()) {
            country = optionalCountry.get();
            countryRepository.delete(country);
            Java6Assertions.assertThat(country).isNotNull();
        }
    }
}
