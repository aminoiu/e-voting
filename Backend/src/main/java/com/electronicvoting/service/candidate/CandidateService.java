package com.electronicvoting.service.candidate;

import com.electronicvoting.entity.Admin;
import com.electronicvoting.entity.Candidate;

public interface CandidateService {
    Candidate findByEmail(String email);

    void saveUserCandidate(Candidate candidate);

   String getHashPass(Candidate candidate);

   void setHashPass(Candidate candidate,String hashedPass);

}
