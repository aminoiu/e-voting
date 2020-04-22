package com.electronicvoting.service.candidate;


import com.electronicvoting.entity.Candidate;
import com.electronicvoting.repository.CandidateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Candidate findByEmail(String email) {
        log.info("Find candidate by e-mail[{}]", email);
        return candidateRepository.findByEmail(email);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUserCandidate(Candidate candidate) {
        this.candidateRepository.save(candidate);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateEmail(Candidate candidate, String newEmail) {
        boolean emailExists = candidateRepository.existsByEmail(newEmail);
        if (emailExists) {
            return; //TODO: Create exception for it, and if it exists, throw it
        }
        candidate.setEmail(newEmail);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCandidate(String email) {
        this.candidateRepository.deleteByEmail(email);
    }


}
