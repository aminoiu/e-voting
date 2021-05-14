package com.electronicvoting.service.castedvotes;

import com.electronicvoting.domain.dto.CastedVoteDTO;
import com.electronicvoting.entity.CastedVote;
import com.electronicvoting.entity.VoteEvidence;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.repository.CastedVoteRepository;
import com.electronicvoting.repository.VoteEvidenceRepository;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.voter.VoterService;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CastedVotesServiceImpl implements CastedVotesService {
    private final VoterService voterService;
    private final CandidateService candidateService;
    private final VotingDataService votingDataService;
    private final CastedVoteRepository castedVoteRepository;
    private final VoteEvidenceRepository voteEvidenceRepository;



    @Override
    @Transactional
    public ResponseEntity saveVote(CastedVote castedVote) {
        castedVote.setVoteId(UUID.randomUUID().toString());
        castedVoteRepository.save(castedVote);
        return ResponseEntity.ok().build();

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean hasVoterAlreadySubmittedVote(String votingCode, String voterEmail) {
        VotingData votingDataByCode = votingDataService.getVotingDataByVotingCode(votingCode);
        if (votingDataByCode != null) {
            List<VoteEvidence>  voteEvidenceList= voteEvidenceRepository.findAllByVotingIdAndVoterEmail(votingDataService.getVotingDataByVotingCode(votingCode).getVotingId(), voterEmail);
            if (voteEvidenceList == null || voteEvidenceList.isEmpty()) {
                return false;
            }
            return true;
        }
        return false;
    }
}
