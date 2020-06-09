package com.electronicvoting.service.candidate;


import com.electronicvoting.domain.dto.CandidateDTO;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.exceptions.EmailExistsException;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.repository.CandidateRepository;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private VotingDataService votingDataService;

    @Override
    @Transactional(readOnly = true)
    public Candidate findByEmail(String email) {
        log.info("Find candidate by e-mail[{}]", email);
        return candidateRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void saveUserCandidate(Candidate candidate) {
        userRepository.findByEmail(candidate.getEmail()).ifPresentOrElse(users -> candidate.setUserId(users.getId()), () -> new UserNotFoundException("Error: User doesn't exist."));
        candidate.setCandidateId(UUID.randomUUID().toString());
        this.candidateRepository.save(candidate);
    }

    @Override
    @Transactional
    public void updateEmail(Candidate candidate, String newEmail) throws EmailExistsException {
        boolean emailExists = candidateRepository.existsByEmail(newEmail);
        if (emailExists) {
            throw new EmailExistsException("Error: This e-mail is already reserved");
        }
        candidate.setEmail(newEmail);
        candidateRepository.save(candidate);
    }

    @Override
    @Transactional
    public void deleteCandidate(String email) {
        this.candidateRepository.deleteByEmail(email);
    }


}
