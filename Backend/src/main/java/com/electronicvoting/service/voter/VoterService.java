package com.electronicvoting.service.voter;

import com.electronicvoting.domain.dto.VoterDto;
import com.electronicvoting.entity.Voter;

public interface VoterService {
    Voter findByEmail(String email);

    void saveUserVoter(VoterDto voterDto);
}
