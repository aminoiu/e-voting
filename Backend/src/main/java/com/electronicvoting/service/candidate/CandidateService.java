package com.electronicvoting.service.candidate;

import com.electronicvoting.domain.dto.CandidateDTO;
import com.electronicvoting.domain.dto.PassDTO;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.exceptions.EmailExistsException;
import com.electronicvoting.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CandidateService {
    Candidate findByEmail(String email);
    Candidate saveUserCandidate(Candidate candidate);
    Candidate updateEmail(Candidate candidate, String newEmail) throws EmailExistsException;
    String deleteCandidate(String email);
    Map<String,String> createCandidatesAccounts(List<String> candidatesList);

    ResponseEntity updatePass(String email, PassDTO passDTO);

    Candidate findProfileIdByEmail(String s);
}
