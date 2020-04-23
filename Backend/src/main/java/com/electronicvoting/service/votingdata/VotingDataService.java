package com.electronicvoting.service.votingdata;

import com.electronicvoting.dto.VotingDataDTO;
import com.electronicvoting.entity.VotingData;

public interface VotingDataService {
    VotingData findByVotingTitle(String votingTitle);

    void saveVotingSession(VotingDataDTO votingDataDTO);
}
