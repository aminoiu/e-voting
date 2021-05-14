package com.electronicvoting.Service.castedvotes;

import com.electronicvoting.entity.CastedVote;
import com.electronicvoting.repository.CastedVoteRepository;
import com.electronicvoting.service.castedvotes.CastedVotesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class CastedVotesServiceImplTest {
    @InjectMocks
    CastedVotesServiceImpl castedVotesService;

    @Mock
    CastedVoteRepository castedVoteRepository;

    CastedVote castedVote;

    @BeforeEach
    void setUp() {

        castedVote = new CastedVote();
        castedVote.setVoteId("voteId");
        castedVote.setVotingId("votingId");
        castedVote.setCastedVote("aminoiu@inthergroup.com");
        castedVote.setTimestamp(Instant.now());
        castedVote.setVoteType("candidate");
    }

    @Test
    void saveVote() {
        castedVotesService.saveVote(castedVote);
        verify(castedVoteRepository,times(1)).save(castedVote);
    }
}