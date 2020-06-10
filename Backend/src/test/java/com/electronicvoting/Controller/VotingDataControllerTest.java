package com.electronicvoting.Controller;

import com.electronicvoting.controller.CandidateController;
import com.electronicvoting.controller.VotingDataController;
import com.electronicvoting.domain.dto.CandidateDTO;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.security.jwt.JwtUtils;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.user.UserDetailsServiceImpl;
import com.electronicvoting.service.votingdata.VotingDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VotingDataController.class)
class VotingDataControllerTest {
    @MockBean
    UserDetailsServiceImpl userDetailsService;
    @MockBean
    JwtUtils jwtUtils;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VotingDataServiceImpl votingDataService;
    private VotingData votingData;

    @BeforeEach
    void setUp() {
        votingData = new VotingData();
        votingData.setVotingId("VotingId");
        votingData.setVotingTitle("Voting Title");
        votingData.setCandidatesNumber(20);
        votingData.setVotersNumber(100);
        votingData.setAdminId("adminID");
        votingData.setVotesNumber(70);
        votingData.setVotingWinner("candidateId");
    }

    @Test
    @WithMockUser(username = "test@test.com", roles = "ADMIN")
    void saveVoteData_whenInvoked_return202() throws Exception {
        mockMvc.perform(post("/evoting/admin/create-voting-session")
                .contentType("application/json")
                .content(String.valueOf(votingData))
        ).andExpect(status().is(HttpStatus.ACCEPTED.value()));
    }
}