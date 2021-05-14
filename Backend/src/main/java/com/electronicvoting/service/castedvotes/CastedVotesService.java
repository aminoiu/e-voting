package com.electronicvoting.service.castedvotes;

import com.electronicvoting.domain.dto.CastedVoteDTO;
import com.electronicvoting.entity.CastedVote;
import org.springframework.http.ResponseEntity;

public interface CastedVotesService {
    ResponseEntity saveVote(CastedVote castedVote);
    boolean hasVoterAlreadySubmittedVote(String votingCode, String voterEmail);
}
