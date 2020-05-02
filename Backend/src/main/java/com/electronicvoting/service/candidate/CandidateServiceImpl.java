package com.electronicvoting.service.candidate;


import com.electronicvoting.domain.dto.CandidateDTO;
import com.electronicvoting.entity.Candidate;
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
    public void saveUserCandidate(CandidateDTO candidateDTO) {
        userRepository.findByEmail(candidateDTO.getEmail()).ifPresentOrElse(users -> candidateDTO.setUserId(users.getId()), () -> {
            throw new RuntimeException("Error: User doesn't exist.");
        });
        candidateDTO.setProfileId("profileId"); //TODO:Add profile entity. After candidate user was created, should be created a default profile
        Candidate candidate = CandidateDTO.dtoToEntity(candidateDTO);
        candidate.setCandidateId(UUID.randomUUID().toString());
        this.candidateRepository.save(candidate);
    }

    @Override
    @Transactional
    public void updateEmail(CandidateDTO candidateDTO, String newEmail) {
        boolean emailExists = candidateRepository.existsByEmail(newEmail);
        if (emailExists) {
            return; //TODO: Create exception for it, and if it exists, throw it
        }
        candidateDTO.setEmail(newEmail);
    }

    @Override
    @Transactional
    public void deleteCandidate(String email) {
        this.candidateRepository.deleteByEmail(email);
    }


}
