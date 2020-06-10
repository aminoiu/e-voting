package com.electronicvoting.Service.voter;

import com.electronicvoting.entity.Role;
import com.electronicvoting.entity.Users;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.repository.VoterRepository;
import com.electronicvoting.service.voter.VoterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoterServiceImplTest {
    @InjectMocks
    private VoterServiceImpl voterService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private VoterRepository voterRepository;

    private Voter voter;
    private Users user;
    private Role role;

    @BeforeEach
    void setUp() {
        voterService = new VoterServiceImpl(voterRepository, userRepository);
        user = new Users();
        user.setId(102);
        user.setUsername("voter@test.com");
        user.setEmail("voter@test.com");
        user.setPassword("voter");

        role = new Role();
        role.setId(103);
        role.setName("VOTER");

        voter = new Voter();
        voter.setVoterId("112");
        voter.setEmail("voter@test.com");
        voter.setName("voter");
        voter.setUserId(user.getId());
    }

    @Test
    void findByEmail_whenInvoked_returnCandidate() {
        when(voterService.findByEmail(voter.getEmail())).thenReturn(voter);
        Voter voter2 = voterService.findByEmail(voter.getEmail());
        assertThat(voter).isNotNull();
        assertThat(voter.getEmail()).isEqualTo(voter2.getEmail());
    }

    @Test
    void saveUserCandidate_whenInvoked_doesNotThrowAnyExceptions() {
        when(userRepository.findByEmail(voter.getEmail())).thenReturn(Optional.of(user));
        voterService.saveUserVoter(voter);
        verify(voterRepository, times(1)).save(voter);
    }
}