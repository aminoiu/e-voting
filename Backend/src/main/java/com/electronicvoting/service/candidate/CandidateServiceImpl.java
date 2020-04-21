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
        log.info("Find candidate by e-mail[{}]", email);
        return candidateRepository.findByEmail(email);
    }


    @Override
    public void saveUserCandidate(Candidate candidate) {
        this.candidateRepository.save(candidate);
    }

    @Override
    public void updateEmail(Candidate candidate, String newEmail) {
        boolean emailExists=candidateRepository.existsByEmail(newEmail);
        if(emailExists) {
            return; //TODO: Create exception for it, and if it exists, throw it
        }
        candidate.setEmail(newEmail);
    }

    @Override
    public void deleteCandidate(String email) {
this.candidateRepository.deleteByEmail(email);
    }


}
