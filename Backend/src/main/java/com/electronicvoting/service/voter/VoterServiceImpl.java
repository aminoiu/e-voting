package com.electronicvoting.service.voter;

import com.electronicvoting.dto.VoterDto;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.repository.VoterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class VoterServiceImpl implements VoterService {
    VoterRepository voterRepository;

    public VoterServiceImpl(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Voter findByEmail(String email) {
        log.info("Find candidate by e-mail[{}]", email);
        return voterRepository.findByEmail(email);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUserVoter(VoterDto voterDto) {
        Voter voter=new Voter();
        //TODO implement
        this.voterRepository.save(voter);
    }




}
