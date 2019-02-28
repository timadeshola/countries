package com.countries.resources;

import com.countries.core.utils.AppUtils;
import com.countries.model.request.CountryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createCountryEndpointTest() throws Exception {
        CountryRequest request = CountryRequest.builder().name("Mali").continent("Africa").build();
        String requestJson = AppUtils.toJSON(request);
        mockMvc.perform(post("/api/v1/countries")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzYxNjQ1LCJleHAiOjE1NTEzNjUyNDV9.j8i59ppO_kBdYMRwdp3SlJtmbG17YgspCHl7OzcuOArh2GXmsenFVTEGNf5SemCBtaiqBcIXpvW3xIte5iDV2A")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCountryEndpointTest() throws Exception {
        CountryRequest request = CountryRequest.builder().name("Mali").continent("Africa").build();
        String requestJson = AppUtils.toJSON(request);
        mockMvc.perform(put("/api/v1/countries/{countryId}", "1")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzYxNjQ1LCJleHAiOjE1NTEzNjUyNDV9.j8i59ppO_kBdYMRwdp3SlJtmbG17YgspCHl7OzcuOArh2GXmsenFVTEGNf5SemCBtaiqBcIXpvW3xIte5iDV2A")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCountryEndpointTest() throws Exception {
        mockMvc.perform(delete("/api/v1/countries/{countryId}", "1")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzYxNjQ1LCJleHAiOjE1NTEzNjUyNDV9.j8i59ppO_kBdYMRwdp3SlJtmbG17YgspCHl7OzcuOArh2GXmsenFVTEGNf5SemCBtaiqBcIXpvW3xIte5iDV2A")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllCountriesEndpointTest() throws Exception {
        mockMvc.perform(get("/api/v1/countries")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzYxNjQ1LCJleHAiOjE1NTEzNjUyNDV9.j8i59ppO_kBdYMRwdp3SlJtmbG17YgspCHl7OzcuOArh2GXmsenFVTEGNf5SemCBtaiqBcIXpvW3xIte5iDV2A")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findCountryByNameEndpointTest() throws Exception {
        mockMvc.perform(get("/api/v1/countries/{name}", "Nigeria")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzYxNjQ1LCJleHAiOjE1NTEzNjUyNDV9.j8i59ppO_kBdYMRwdp3SlJtmbG17YgspCHl7OzcuOArh2GXmsenFVTEGNf5SemCBtaiqBcIXpvW3xIte5iDV2A")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
