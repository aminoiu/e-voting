package com.electronicvoting.Controller;

import com.electronicvoting.controller.CandidateController;
import com.electronicvoting.domain.dto.CandidateDTO;
import com.electronicvoting.domain.dto.MessageDTO;
import com.electronicvoting.domain.dto.SignUpDTO;
import com.electronicvoting.entity.Candidate;

import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.security.jwt.JwtUtils;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.candidate.CandidateServiceImpl;
import com.electronicvoting.service.user.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CandidateController.class)
class CandidateControllerTest {
    @MockBean
    HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder;
    @MockBean
    AuthService authService;
    @MockBean
    UserDetailsServiceImpl userDetailsService;
    @MockBean
    JwtUtils jwtUtils;
    Candidate candidate;
    @MockBean
    private CandidateServiceImpl candidateService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        candidate = new Candidate();
        candidate.setCandidateId("111");
        candidate.setEmail("candidate@test.com");
        candidate.setProfileId("1");
        candidate.setName("candidate");
        candidate.setUserId(101);
    }

    @Test
    void getByEmail_whenInvoked_return200() throws Exception {
        when(candidateService.findByEmail(candidate.getEmail())).thenReturn(candidate);
        mockMvc.perform(get("/evoting/candidates/{email}", candidate.getEmail()).contentType("application/json"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @WithMockUser(username = "test@test.com", roles = "ADMIN")
    void createCandidate_whenInvoked_return201() throws Exception {

        SignUpDTO signUpDTO = SignUpDTO.candidateDtoToSignUpDto(CandidateDTO.toDto(candidate));

        when(authService.registerUser(signUpDTO)).thenReturn(ResponseEntity.ok(new MessageDTO("Users registered successfully!")));
        when(candidateService.saveUserCandidate(candidate)).thenReturn(candidate);

        mockMvc.perform(post("/evoting/candidates")
                .contentType("application/json")
                .content(String.valueOf(CandidateDTO.toDto(candidate))))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    void updateEmail_whenInvoked_return200() throws Exception {
        String newEmail = "cantidate2@test.com";
        when(candidateService.updateEmail(candidate, newEmail)).thenReturn(candidate);
        mockMvc.perform(put("/evoting/candidates/update/{email}", newEmail)
                .contentType("application/json")
                .content(String.valueOf(CandidateDTO.toDto(candidate)))
        ).andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @WithMockUser(username = "test@test.com", roles = "ADMIN")
    void deleteCandidate_whenInvoked_return200() throws Exception {
        when(candidateService.deleteCandidate(candidate.getEmail())).thenReturn("Deleted");

        mockMvc.perform(delete("/evoting/candidates/{email}", candidate.getEmail()))
                .andExpect(status().isOk());
    }
}