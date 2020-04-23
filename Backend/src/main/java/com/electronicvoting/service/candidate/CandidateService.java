package com.electronicvoting.service.candidate;

import com.electronicvoting.dto.CandidateDTO;
import com.electronicvoting.entity.Candidate;

public interface CandidateService {
    Candidate findByEmail(String email);

    void saveUserCandidate(CandidateDTO candidateDTO);
    void updateEmail(Candidate candidate, String newEmail);
    void deleteCandidate(String email);
}
