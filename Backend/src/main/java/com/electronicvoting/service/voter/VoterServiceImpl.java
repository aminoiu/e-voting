package com.electronicvoting.service.voter;

import com.electronicvoting.entity.Voter;
import com.electronicvoting.repository.CandidateRepository;
import com.electronicvoting.repository.VoterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VoterServiceImpl implements VoterService {
    VoterRepository voterRepository;

    public VoterServiceImpl(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }


    @Override
    public Voter findByEmail(String email) {
        log.info("Find candidate by e-mail[{}]", email);
        return voterRepository.findByEmail(email);
    }

    @Override
    public void saveUserVoter(Voter voter) {
        this.voterRepository.save(voter);
    }

    @Override
    public String getHashPass(Voter voter) {
        return voter.getHashPassword();
    }


}
