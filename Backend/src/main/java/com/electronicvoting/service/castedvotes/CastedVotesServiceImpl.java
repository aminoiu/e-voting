package com.electronicvoting.service.castedvotes;

import com.electronicvoting.dto.CastedVoteDTO;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.CastedVote;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.repository.CastedVoteRepository;
import com.electronicvoting.repository.VoterRepository;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.voter.VoterService;
import com.electronicvoting.service.votingdata.VotingDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CastedVotesServiceImpl implements CastedVotesService {
    VoterService voterService;
    CandidateService candidateService;
    VotingDataService votingDataService;

    CastedVoteRepository castedVoteRepository;


    CastedVotesServiceImpl(CastedVoteRepository castedVoteRepository) {
        this.castedVoteRepository = castedVoteRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveVote(CastedVoteDTO castedVoteDTO) {
        Voter voter = voterService.findByEmail(castedVoteDTO.getVoterEmail());
        Candidate candidate = candidateService.findByEmail(castedVoteDTO.getVote());//the vote will represent the candidate email
        VotingData votingData = votingDataService.findByVotingTitle(castedVoteDTO.getVotingTitle());
        CastedVote castedVote = new CastedVote();
        castedVote.setVoteId(UUID.randomUUID().toString());
        castedVote.setDeviceIp(castedVoteDTO.getDeviceIp());
        castedVote.setVoterId(voter.getVoterId());
        castedVote.setVotingId(votingData.getVotingId());
        castedVote.setCandidateId(candidate.getCandidateId());
        castedVote.setTimestamp(castedVoteDTO.getTimestamp());
        castedVoteRepository.save(castedVote);

    }
}
