package com.electronicvoting.service.candidate;


import com.electronicvoting.entity.Candidate;
import com.electronicvoting.repository.CandidateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

        public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate findByEmail(String email) {
        log.info("Find admin by e-mail[{}]",email);
        return candidateRepository.findByEmail(email);
    }


    @Override
    public void saveUserCandidate(Candidate candidate) {
        this.candidateRepository.save(candidate);
    }

    @Override
    public String getHashPass(Candidate candidate) {
        return candidate.getHashPass();
    }

    @Override
    public void setHashPass(Candidate candidate,String hashedPass) {

    }
}
