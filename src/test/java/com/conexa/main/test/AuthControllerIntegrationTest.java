package com.conexa.main.test;

import com.conexa.main.controllers.AuthController;
import com.conexa.main.model.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String INVALID_PASSWORD = "wrongpass";

    @Test
    void login_WithValidAdminCredentials_ReturnsToken() throws Exception {
        AuthRequest request = new AuthRequest(ADMIN_USERNAME, ADMIN_PASSWORD);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void login_WithInvalidCredentials_ReturnsForbidden() throws Exception {
        AuthRequest request = new AuthRequest(ADMIN_USERNAME, INVALID_PASSWORD);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    void login_WithEmptyCredentials_ReturnsForbidden() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void authenticatedEndpoint_WithValidToken_ReturnsOk() throws Exception {
        String token = performAdminLoginAndGetToken();

        mockMvc.perform(get("/people")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void whenAlreadyAuthenticated_thenKeepsExistingAuthentication() throws Exception {
        String token = performAdminLoginAndGetToken();

        mockMvc.perform(get("/people")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        var firstAuth = SecurityContextHolder.getContext().getAuthentication();

        mockMvc.perform(get("/people")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        var secondAuth = SecurityContextHolder.getContext().getAuthentication();
        assertEquals(firstAuth, secondAuth);
    }

    private String performAdminLoginAndGetToken() throws Exception {
        AuthRequest request = new AuthRequest(ADMIN_USERNAME, ADMIN_PASSWORD);

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readValue(response, AuthController.AuthResponse.class).token();
    }
}