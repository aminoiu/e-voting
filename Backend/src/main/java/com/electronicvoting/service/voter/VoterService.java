package com.electronicvoting.service.voter;

import com.electronicvoting.entity.Voter;
import org.springframework.stereotype.Service;

public interface VoterService {
    Voter findByEmail(String email);

    void saveUserVoter(Voter voter);

    String getHashPass(Voter voter);
}
