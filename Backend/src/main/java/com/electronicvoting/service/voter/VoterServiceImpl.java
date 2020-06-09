package com.electronicvoting.service.voter;

import com.electronicvoting.domain.dto.VoterDto;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Voter findByEmail(String email) {
        log.info("Find candidate by e-mail[{}]", email);
        return voterRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void saveUserVoter(Voter voter) {
        userRepository.findByEmail(voter.getEmail()).ifPresentOrElse(users -> voter.setUserId(users.getId()), () -> new UserNotFoundException("Error: User doesn't exist."));

        voter.setVoterId(UUID.randomUUID().toString());
        this.voterRepository.save(voter);
    }


}
