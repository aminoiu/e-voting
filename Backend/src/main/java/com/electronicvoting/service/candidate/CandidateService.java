package com.electronicvoting.service.candidate;

import com.electronicvoting.domain.dto.CandidateDTO;
import com.electronicvoting.entity.Candidate;

public interface CandidateService {
    Candidate findByEmail(String email);

    void saveUserCandidate(CandidateDTO candidateDTO);
    void updateEmail(CandidateDTO candidateDTO, String newEmail);
    void deleteCandidate(String email);
}
