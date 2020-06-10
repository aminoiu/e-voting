package com.electronicvoting.Service.votingdata;

import com.electronicvoting.entity.*;
import com.electronicvoting.repository.VotingDataRepository;
import com.electronicvoting.service.votingdata.VotingDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotingDataServiceImplTest {
    @InjectMocks
    VotingDataServiceImpl votingDataService;
    @Mock
    VotingDataRepository votingDataRepository;

    VotingData votingData;
    Candidate candidate;
    Admin admin;
    Users user;
    Users user1;
    Role role;
    Role role1;


    @BeforeEach
    void setUp() {
        votingDataService = new VotingDataServiceImpl(votingDataRepository);

        user = new Users();
        user.setId(101);
        user.setUsername("candidate@test.com");
        user.setEmail("candidate@test.com");
        user.setPassword("candidate");

        role = new Role();
        role.setId(102);
        role.setName("CANDIDATE");

        candidate = new Candidate();
        candidate.setCandidateId("111");
        candidate.setEmail("candidate@test.com");
        candidate.setProfileId("1");
        candidate.setName("candidate");
        candidate.setUserId(user.getId());

        user1 = new Users();
        user1.setId(100);
        user1.setUsername("test@test.com");
        user1.setEmail("test@test.com");
        user1.setPassword("test");

        role1 = new Role();
        role1.setId(101);
        role1.setName("ADMIN");

        admin = new Admin();
        admin.setAdminId("1000");
        admin.setUserId(user1.getId());
        admin.setPhoneNumber("123456");
        admin.setStreet("test");
        admin.setCountry("test");
        admin.setWorkPlace("Test");
        admin.setEmail("test@test.com");
        admin.setName("Test Test");
        admin.setCity("Testtt");
        admin.setBirthDate(new Date(Calendar.getInstance().getTime().getTime()));

        votingData = new VotingData();
        votingData.setVotingId("VotingId");
        votingData.setVotingTitle("Voting Title");
        votingData.setCandidatesNumber(20);
        votingData.setVotersNumber(100);
        votingData.setAdminId(admin.getAdminId());
        votingData.setVotesNumber(70);
        votingData.setVotingWinner(candidate.getCandidateId());
    }

    @Test
    void findByVotingTitle_whenInvoked_returnVotingData() {
        when(votingDataRepository.findByVotingTitle(votingData.getVotingTitle())).thenReturn(votingData);
        VotingData votingData1 = votingDataService.findByVotingTitle(votingData.getVotingTitle());
        assertThat(votingData.getVotingId()).isEqualTo(votingData1.getVotingId());
    }

    @Test
    void saveVotingSession_whenInvoked_saveOnce() {
        votingDataService.saveVotingSession(votingData);
        verify(votingDataRepository, times(1)).save(votingData);
    }

    @Test
    void findTitleById_whenInvoked_returnVotingTitle() {
        when(votingDataRepository.findByVotingId(votingData.getVotingId())).thenReturn(Optional.ofNullable(votingData));
        String title = votingDataService.findTitleById(votingData.getVotingId());
        assertThat(votingData.getVotingTitle()).isEqualTo(title);
    }
}