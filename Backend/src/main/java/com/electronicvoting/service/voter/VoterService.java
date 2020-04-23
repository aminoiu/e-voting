package com.electronicvoting.service.voter;

import com.electronicvoting.dto.VoterDto;
import com.electronicvoting.entity.Voter;
import org.springframework.stereotype.Service;

public interface VoterService {
    Voter findByEmail(String email);

    void saveUserVoter(VoterDto voterDto);
}
