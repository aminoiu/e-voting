package com.electronicvoting.service.votingdata;

import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.domain.dto.VotingDataForMobileDTO;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.exceptions.UserNotFoundException;

import java.util.List;

public interface VotingDataService {
    VotingData findByVotingTitle(String votingTitle);

    void saveVotingSession(VotingData votingData) throws UserNotFoundException;

    String findTitleById(String id);

    List<VotingDataForMobileDTO> getVotingDataByEmail(String email) throws UserNotFoundException;
}
