package com.electronicvoting.service.castedvotes;

import com.electronicvoting.domain.dto.CastedVoteDTO;
import com.electronicvoting.entity.CastedVote;
import com.electronicvoting.repository.CastedVoteRepository;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.voter.VoterService;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CastedVotesServiceImpl implements CastedVotesService {
    private final VoterService voterService;
    private final CandidateService candidateService;
    private final VotingDataService votingDataService;
    private final CastedVoteRepository castedVoteRepository;

    @Override
    @Transactional
    public void saveVote(CastedVote castedVote) {
        castedVote.setVoteId(UUID.randomUUID().toString());
        castedVoteRepository.save(castedVote);

    }
}
