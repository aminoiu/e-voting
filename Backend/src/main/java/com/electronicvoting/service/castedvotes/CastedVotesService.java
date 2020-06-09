package com.electronicvoting.service.castedvotes;

import com.electronicvoting.domain.dto.CastedVoteDTO;
import com.electronicvoting.entity.CastedVote;

public interface CastedVotesService {
    void saveVote(CastedVote castedVote);
}
