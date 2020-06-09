package com.electronicvoting.Service.candidate;

import com.electronicvoting.domain.dto.CandidateDTO;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Role;
import com.electronicvoting.entity.Users;
import com.electronicvoting.exceptions.EmailExistsException;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.repository.CandidateRepository;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.service.candidate.CandidateServiceImpl;
import com.electronicvoting.service.votingdata.VotingDataService;
import liquibase.pro.packaged.A;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateServiceImplTest {

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private VotingDataService votingDataService;

    private Candidate candidate;
    private Users user;
    private Role role;
    private CandidateDTO candidateDTO;


    @BeforeEach
    void setUp() {
        candidateService = new CandidateServiceImpl(candidateRepository, userRepository);
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

    }

    @Test
    void findByEmail_whenInvoked_returnCandidate() {
        when(candidateService.findByEmail(candidate.getEmail())).thenReturn(candidate);
        Candidate candidate2 = candidateService.findByEmail(candidate.getEmail());
        assertThat(candidate).isNotNull();
        assertThat(candidate.getEmail()).isEqualTo(candidate2.getEmail());
    }

    @Test
    void saveUserCandidate_whenInvoked_doesNotThrowAnyExceptions() {
        when(userRepository.findByEmail(candidate.getEmail())).thenReturn(Optional.of(user));
        candidateService.saveUserCandidate(candidate);
        verify(candidateRepository, times(1)).save(candidate);
    }


    @Test
    void updateEmail_whenInvoked_doesNotThrowAnyExceptions() {
        when(candidateRepository.existsByEmail("candidate3@test.com")).thenReturn(false);
        assertThatCode(() -> candidateService.updateEmail(candidate, "candidate3@test.com")).doesNotThrowAnyException();
    }

    @Test
    void updateEmail_withAnExistingEmail_throwEmailExistsException() {
        when(candidateRepository.existsByEmail("candidate3@test.com")).thenReturn(true);
        assertThrows(EmailExistsException.class,()->candidateService.updateEmail(candidate,"candidate3@test.com"));
    }


    @Test
    void deleteCandidate_whenInvoked_doesNotThrowAnyException() {
        assertThatCode(() -> candidateService.deleteCandidate( "candidate@test.com")).doesNotThrowAnyException();
    }
}