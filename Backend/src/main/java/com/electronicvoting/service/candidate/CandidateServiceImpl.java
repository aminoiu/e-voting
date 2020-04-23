package com.electronicvoting.service.candidate;


import com.electronicvoting.dto.CandidateDTO;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.repository.CandidateRepository;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    VotingDataService votingDataService;

    public CandidateServiceImpl(CandidateRepository candidateRepository, UserRepository userRepository, VotingDataService votingDataService) {
        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
        this.votingDataService = votingDataService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Candidate findByEmail(String email) {
        log.info("Find candidate by e-mail[{}]", email);
        return candidateRepository.findByEmail(email);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUserCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = new Candidate();
        candidate.setCandidateId(UUID.randomUUID().toString());
        candidate.setName(candidate.getName());
        candidate.setProfileId("profileId"); //TODO:Add profile entity. After candidate user was created, should be created a default profile
        candidate.setEmail(candidateDTO.getEmail());
        candidate.setVotingId(votingDataService.findByVotingTitle(candidateDTO.getVotingTitle()).getVotingId());
        userRepository.findByEmail(candidate.getEmail()).ifPresent(user -> candidate.setUser_id(user.getId()));
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
