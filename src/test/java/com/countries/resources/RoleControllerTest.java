package com.countries.resources;

import com.countries.core.utils.AppUtils;
import com.countries.model.request.RoleRequest;
import com.countries.model.request.UpdateRoleRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createRoleEndpointTest() throws Exception {
        RoleRequest request = RoleRequest.builder().name("Manager").build();
        String requestJson = AppUtils.toJSON(request);
        mockMvc.perform(post("/api/v1/roles")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU5ODQ1LCJleHAiOjE1NTEzNjM0NDV9.y0SMu5_vaD4cm8Su3h0WvEcTydWkNS9b3287t4V50RjBkS84fkV7j0SziGsfyNQF4WAKh5keCkONHOOkszFUfA")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateRoleEndpointTest() throws Exception {
        UpdateRoleRequest request = UpdateRoleRequest.builder().roleId(1L).name("Supervisor").build();
        String requestJson = AppUtils.toJSON(request);
        mockMvc.perform(put("/api/v1/roles")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU5ODQ1LCJleHAiOjE1NTEzNjM0NDV9.y0SMu5_vaD4cm8Su3h0WvEcTydWkNS9b3287t4V50RjBkS84fkV7j0SziGsfyNQF4WAKh5keCkONHOOkszFUfA")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRoleEndpointTest() throws Exception {
        mockMvc.perform(delete("/api/v1/roles")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzY5NDAxLCJleHAiOjE1NTE0MDU0MDF9.vrZfwTnFZ9WIoF92-PJNn0b880DEeeJkOBZKoQNRdKxZDc6BMAzdgqZe1t1T3RSaULd8MPDWsnX-fNFivclMiA")
                .contentType(MediaType.APPLICATION_JSON)
                .param("roleId", "2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllRolesEndpointTest() throws Exception {
        mockMvc.perform(get("/api/v1/roles")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU5ODQ1LCJleHAiOjE1NTEzNjM0NDV9.y0SMu5_vaD4cm8Su3h0WvEcTydWkNS9b3287t4V50RjBkS84fkV7j0SziGsfyNQF4WAKh5keCkONHOOkszFUfA")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findRoleByNameEndpointTest() throws Exception {
        mockMvc.perform(get("/api/v1/roles/{name}", "ADMIN")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU5ODQ1LCJleHAiOjE1NTEzNjM0NDV9.y0SMu5_vaD4cm8Su3h0WvEcTydWkNS9b3287t4V50RjBkS84fkV7j0SziGsfyNQF4WAKh5keCkONHOOkszFUfA")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void toggleRoleStatusEndpointTest() throws Exception {
        mockMvc.perform(put("/api/v1/roles/{roleId}", "2")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aW1hZGVzaG9sYSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJHVUVTVCJ9LHsiYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaWF0IjoxNTUxMzU5ODQ1LCJleHAiOjE1NTEzNjM0NDV9.y0SMu5_vaD4cm8Su3h0WvEcTydWkNS9b3287t4V50RjBkS84fkV7j0SziGsfyNQF4WAKh5keCkONHOOkszFUfA")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
