package com.wrestling_moves;

import com.wrestling_moves.service.WrestlerService;
import com.wrestling_moves.service.WrestlerUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest()
@AutoConfigureMockMvc()
public class SecurityTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void cantAccessDashboardWithoutAuthenticatedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/dashboard"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void accessDashboardWithAuthenticatedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/dashboard"))
                .andExpect(status().isOk());
    }

}

