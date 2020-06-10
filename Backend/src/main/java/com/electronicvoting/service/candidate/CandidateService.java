package com.electronicvoting.service.candidate;

import com.electronicvoting.domain.dto.CandidateDTO;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.exceptions.EmailExistsException;
import com.electronicvoting.exceptions.UserNotFoundException;

public interface CandidateService {
    Candidate findByEmail(String email);

    Candidate saveUserCandidate(Candidate candidate);
    Candidate updateEmail(Candidate candidate, String newEmail) throws EmailExistsException;
    String deleteCandidate(String email);
}
