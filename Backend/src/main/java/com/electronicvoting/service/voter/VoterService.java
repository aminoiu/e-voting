package com.electronicvoting.service.voter;

import com.electronicvoting.domain.dto.PassDTO;
import com.electronicvoting.domain.dto.VoterDto;
import com.electronicvoting.entity.Voter;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface VoterService {
    Voter findByEmail(String email);

    void saveUserVoter(Voter voter);

    Map<String,String> createVotersAccounts(List<String> votersList);

    ResponseEntity updatePass(String email, PassDTO passDTO);
}
