package com.electronicvoting.service.candidate;

import com.electronicvoting.entity.Candidate;

public interface CandidateService {
    Candidate findByEmail(String email);

    void saveUserCandidate(Candidate candidate);
    void updateEmail(Candidate candidate, String newEmail);
    void deleteCandidate(String email);
}
