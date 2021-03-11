package com.electronicvoting.service.voter;

import com.electronicvoting.domain.dto.*;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Users;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.helper.RandomString;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.repository.VoterRepository;
import com.electronicvoting.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final HashPasswordWithSaltEncoder encoder;


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

    @Override
    @Transactional
    public Map<String, String> createVotersAccounts(List<String> votersList) {
        Map<String, String> voterTempPass = new HashMap<>();
        for (String voter : votersList) {
            if (!voterRepository.existsByEmail(voter)) {
                String temporarPassword = RandomString.getAlphaNumericString(8);
                VoterDto voterDto = new VoterDto(voter.split(",")[0], voter.split(",")[1], temporarPassword, 0, true);
                SignUpDTO signUpDTO = SignUpDTO.voterDtoToSignUpDto(voterDto);
                voterTempPass.put(voterDto.getEmail(), temporarPassword);
                ResponseEntity<MessageDTO> responseEntity = authService.registerUser(signUpDTO);
                if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
                    saveUserVoter(VoterDto.dtoToEntity(voterDto));
                } else {
                    log.error("Voter user with e-mail {} wasn't registered successfully", voterDto.getEmail());
                }
            } else {
                log.info("Voter with e-mail {} already was registered", voter);
            }
        }
        return voterTempPass;
    }

    @Override
    public ResponseEntity<Users> updatePass(String email, PassDTO passDTO) {
        final Users[] temp = new Users[1];
        Optional<Users> user=userRepository.findByEmail(email);
        user.ifPresent(users -> temp[0] =users);
        Users oldUser=temp[0];
        oldUser.setPassword(encoder.encode(passDTO.getNewPass()));
        userRepository.save(oldUser);

        Voter voter=voterRepository.findByEmail(email);
        voter.setTemporarPassword(false);
        voterRepository.save(voter);
        return ResponseEntity.ok().build();
    }
}
