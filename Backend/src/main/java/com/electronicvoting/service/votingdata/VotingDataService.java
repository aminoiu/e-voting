package com.electronicvoting.service.votingdata;

import com.electronicvoting.domain.dto.VotingDataForMobileDTO;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.exceptions.UserNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VotingDataService {
    VotingData findByVotingTitle(String votingTitle);

    VotingData saveVotingSession(VotingData votingData) throws UserNotFoundException;

    @Transactional
    VotingData update(VotingData votingData);

    String findTitleById(String id);

    List<VotingDataForMobileDTO> getVotingDataByEmail(String email) throws UserNotFoundException;

    List<VotingDataForMobileDTO> getVotingDataByCandidateEmail(String email);

    List<VotingDataForMobileDTO> getVotingDataByVoterEmail(String email);

    VotingData getVotingDataByVotingCode(String votingCode);

    List<VotingData> getVotingDataByStatus(String status);
}
