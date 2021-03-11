package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.*;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Profile;
import com.electronicvoting.exceptions.EmailExistsException;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.profile.ProfileService;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/evoting/candidates")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CandidateController {

    private final CandidateService candidateService;
    private final ProfileService profileService;
    private final HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder;
    private final AuthService authService;
    private final VotingDataService votingDataService;

    @GetMapping(value = "/{email}", produces = "application/json")
    public ResponseEntity<Candidate> getByEmail(@PathVariable String email) {
        Candidate candidate = this.candidateService.findByEmail(email);
        if (candidate != null) {

            return ResponseEntity.ok(candidate);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/register", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageDTO> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        SignUpDTO signUpDTO = SignUpDTO.candidateDtoToSignUpDto(candidateDTO);

        ResponseEntity<MessageDTO> responseEntity = authService.registerUser(signUpDTO);
        if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
            candidateDTO.setProfileId("profileId"); //TODO:Add profile entity. After candidate user was created, should be created a default profile
            Candidate candidate = CandidateDTO.dtoToEntity(candidateDTO);
            this.candidateService.saveUserCandidate(candidate);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return responseEntity;
    }

    @PutMapping(path = "/update/{email}", consumes = "application/json")
    public ResponseEntity<Candidate> updateEmail(@PathVariable String email, @RequestBody CandidateDTO candidateDTO) throws EmailExistsException {
        return ResponseEntity.ok(this.candidateService.updateEmail(CandidateDTO.dtoToEntity(candidateDTO), email));
    }

    @DeleteMapping(path = "{email}")
    public ResponseEntity<String> deleteCandidate(@PathVariable String email) {

        return ResponseEntity.ok(this.candidateService.deleteCandidate(email));
    }

    @PutMapping(path = "/update-profile/{email}", consumes = "application/json")
    public ResponseEntity<Profile> updateProfile(@PathVariable String email, @RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(this.profileService.updateProfile(email, profileDTO));
    }

    @GetMapping(value = "/profile/{email}", produces = "application/json")
    public ResponseEntity<ProfileDTO> getProfileByEmail(@PathVariable String email) {
        Profile profile = this.profileService.findByEmail(email);
        if (profile != null) {
            return ResponseEntity.ok(ProfileDTO.toDto(profile));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/temporal-password/{email}", produces = "application/json")
    public ResponseEntity<IsTempDto> getIsTemporalPasswordByEmail(@PathVariable String email) {
        Candidate candidate = this.candidateService.findByEmail(email);
        if (candidate != null) {
            IsTempDto isTempDto=new IsTempDto();
            isTempDto.setTemp(candidate.isTemporarPassword());
            return ResponseEntity.ok(isTempDto);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping(path = "/update-password/{email}", consumes = "application/json")
    public ResponseEntity updatePassword(@PathVariable String email, @RequestBody PassDTO passDTO) {
        return ResponseEntity.ok(this.candidateService.updatePass(email, passDTO));
    }
    @GetMapping(path = "/voting-data-history/{email}", produces = "application/json")
    public ResponseEntity<List<VotingDataForMobileDTO>> getVoteData(@PathVariable String email) throws UserNotFoundException {
        List<VotingDataForMobileDTO> votingDataDTO = votingDataService.getVotingDataByCandidateEmail(email);
        return ResponseEntity.ok(votingDataDTO);
    }
}
