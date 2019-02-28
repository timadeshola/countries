package com.countries.resources;

import com.countries.core.utils.AppUtils;
import com.countries.jpa.entity.User;
import com.countries.model.request.UpdateUserRequest;
import com.countries.model.request.UserRequest;
import com.countries.services.UserService;
import org.junit.Before;
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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUserEndpointTest() throws Exception {
        UserRequest request = UserRequest.builder().firstName("John").lastName("Adeshola").email("codemaster@gmail.com").username("codemaster").password("Passw0rd@2019").roleIds(Collections.singletonList(1L)).build();
        String requestJson = AppUtils.toJSON(request);
        mockMvc.perform(post("/api/v1/users")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU2MTc4LCJleHAiOjE1NTEzNTk3Nzh9.LbloTOQA_u8BAp7bKIQRo4FWMSFyO81TTu2TwWUJNsS2ivS7XwSK6UkIp3rwK1CIqMKshJ5vbKAV131dTNdmyQ")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateUserEndpointTest() throws Exception {
        UpdateUserRequest request = UpdateUserRequest.builder().id(1L).firstName("John").lastName("Adeshola").email("codemaster@gmail.com").roleIds(Collections.singletonList(1L)).build();
        String requestJson = AppUtils.toJSON(request);
        mockMvc.perform(put("/api/v1/users")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU2MTc4LCJleHAiOjE1NTEzNTk3Nzh9.LbloTOQA_u8BAp7bKIQRo4FWMSFyO81TTu2TwWUJNsS2ivS7XwSK6UkIp3rwK1CIqMKshJ5vbKAV131dTNdmyQ")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserEndpointTest() throws Exception {
        mockMvc.perform(delete("/api/v1/users")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU2MTc4LCJleHAiOjE1NTEzNTk3Nzh9.LbloTOQA_u8BAp7bKIQRo4FWMSFyO81TTu2TwWUJNsS2ivS7XwSK6UkIp3rwK1CIqMKshJ5vbKAV131dTNdmyQ")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsersEndpointTest() throws Exception {
        mockMvc.perform(get("/api/v1/users")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU2MTc4LCJleHAiOjE1NTEzNTk3Nzh9.LbloTOQA_u8BAp7bKIQRo4FWMSFyO81TTu2TwWUJNsS2ivS7XwSK6UkIp3rwK1CIqMKshJ5vbKAV131dTNdmyQ")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserByUsernameEndpointTest() throws Exception {
        mockMvc.perform(get("/api/v1/users/{username}", "timadeshola")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU5ODQ1LCJleHAiOjE1NTEzNjM0NDV9.y0SMu5_vaD4cm8Su3h0WvEcTydWkNS9b3287t4V50RjBkS84fkV7j0SziGsfyNQF4WAKh5keCkONHOOkszFUfA")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void toggleUserStatusEndpointTest() throws Exception {
        mockMvc.perform(put("/api/v1/users/{userId}", "2")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU5ODQ1LCJleHAiOjE1NTEzNjM0NDV9.y0SMu5_vaD4cm8Su3h0WvEcTydWkNS9b3287t4V50RjBkS84fkV7j0SziGsfyNQF4WAKh5keCkONHOOkszFUfA")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
