package com.electronicvoting.Controller;

import com.electronicvoting.controller.CandidateController;
import com.electronicvoting.entity.CastedVote;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.security.jwt.JwtUtils;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.user.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CandidateController.class)
class CastedVoteControllerTest {
    @MockBean
    HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder;
    @MockBean
    AuthService authService;
    @MockBean
    UserDetailsServiceImpl userDetailsService;
    @MockBean
    JwtUtils jwtUtils;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CandidateService candidateService;
    private CastedVote castedVote;

    @BeforeEach
    void setUp() {
        castedVote = new CastedVote();
        castedVote.setVoteId("voteId");
        castedVote.setCandidateEmail("candidate2test.com");
        castedVote.setVoterEmail("voter@test.com");
        castedVote.setTimestamp(Instant.now());
        castedVote.setVotingId("votingId");
        castedVote.setDeviceIp("deviceIp");
    }

    @Test
    void saveVote() throws Exception {
        mockMvc.perform(put("/evoting/voter/cast-vote")
                .contentType("application/json")
                .content(String.valueOf(castedVote))
        ).andExpect(status().is(HttpStatus.ACCEPTED.value()));
    }
}