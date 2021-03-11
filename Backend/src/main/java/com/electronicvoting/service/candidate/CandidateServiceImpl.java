package com.electronicvoting.service.candidate;


import com.electronicvoting.domain.dto.*;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Profile;
import com.electronicvoting.entity.Users;
import com.electronicvoting.exceptions.EmailExistsException;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.helper.RandomString;
import com.electronicvoting.repository.CandidateRepository;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.profile.ProfileService;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.NoArgsConstructor;
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
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private final VotingDataService votingDataService;
    private final AuthService authService;
    private final ProfileService profileService;
    private final HashPasswordWithSaltEncoder encoder;


    @Override
    @Transactional(readOnly = true)
    public Candidate findByEmail(String email) {
        log.info("Find candidate by e-mail[{}]", email);
        return candidateRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Candidate saveUserCandidate(Candidate candidate) {
        userRepository.findByEmail(candidate.getEmail()).ifPresentOrElse(users -> candidate.setUserId(users.getId()), () -> new UserNotFoundException("Error: User doesn't exist."));
        candidate.setCandidateId(UUID.randomUUID().toString());
        this.candidateRepository.save(candidate);
        return candidate;
    }

    @Override
    @Transactional
    public Candidate updateEmail(Candidate candidate, String newEmail) throws EmailExistsException {
        boolean emailExists = candidateRepository.existsByEmail(newEmail);
        if (emailExists) {
            throw new EmailExistsException("Error: This e-mail is already reserved");
        }
        candidate.setEmail(newEmail);
        candidateRepository.save(candidate);
        return candidate;
    }

    @Override
    @Transactional
    public String deleteCandidate(String email) {
        String errorMessage;
        String okMessage;

        try {
            this.candidateRepository.deleteByEmail(email);
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
            log.error(errorMessage);
        }
        okMessage = "Candidate with email [" + email + "] deleted.";
        return okMessage;

    }

    @Override
    @Transactional
    public Map<String, String> createCandidatesAccounts(List<String> candidatesList) {
        Map<String, String> candidateTempPass = new HashMap<>();
        for (String candidate : candidatesList) {

            if (!candidateRepository.existsByEmail(candidate)) {
                String temporarPassword = RandomString.getAlphaNumericString(8);
                CandidateDTO candidateDTO = new CandidateDTO(candidate.split(",")[0], candidate.split(",")[1], temporarPassword, "", 0, true);
                SignUpDTO signUpDTO = SignUpDTO.candidateDtoToSignUpDto(candidateDTO);
                candidateTempPass.put(candidateDTO.getEmail(), temporarPassword);
                ResponseEntity<MessageDTO> responseEntity = authService.registerUser(signUpDTO);
                if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
                    ProfileDTO profileDTO = new ProfileDTO(candidateDTO.getName(), candidateDTO.getEmail(), "", "", "");
                    String profileId = profileService.saveProfile(ProfileDTO.dtoToEntity(profileDTO)).getProfileId();
                    candidateDTO.setProfileId(profileId);
                    Candidate candidateUser = CandidateDTO.dtoToEntity(candidateDTO);
                    saveUserCandidate(candidateUser);

                } else
                    log.error("Candidate user with e-mail {} wasn't registered successfully", candidateDTO.getEmail());
            } else {
                log.info("Candidate with e-mail {} already was registered", candidate);
            }
        }
        return candidateTempPass;
    }

    @Override
    public ResponseEntity<Users> updatePass(String email, PassDTO passDTO) {
        final Users[] temp = new Users[1];
        Optional<Users> user=userRepository.findByEmail(email);
        user.ifPresent(users -> temp[0] =users);
        Users oldUser=temp[0];
        oldUser.setPassword(encoder.encode(passDTO.getNewPass()));
        userRepository.save(oldUser);

        Candidate candidate=candidateRepository.findByEmail(email);
        candidate.setTemporarPassword(false);
        candidateRepository.save(candidate);
        return ResponseEntity.ok().build();
    }

    @Override
    public Candidate findProfileIdByEmail(String s) {
        return candidateRepository.findProfileIdByEmail(s);
    }


}
