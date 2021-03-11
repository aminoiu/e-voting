package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.*;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Profile;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.repository.VotingDataRepository;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.castedvotes.CastedVotesService;
import com.electronicvoting.service.profile.ProfileService;
import com.electronicvoting.service.voter.VoterService;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/evoting/voter")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VoterController {
    private final VoterService voterService;
    private AuthService authService;
    private final VotingDataService votingDataService;
    private final CandidateService candidateService;
    private final ProfileService profileService;
    private final VotingDataRepository votingDataRepository;
private final CastedVotesService castedVotesService;

    @GetMapping(value = "/{email}", produces = "application/json")
    public ResponseEntity<Voter> getByEmail(@PathVariable String email) {
        Voter voter = this.voterService.findByEmail(email);
        if (voter != null) {

            return ResponseEntity.ok(voter);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/register", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageDTO> createVoter(@RequestBody VoterDto voterDto) {
        SignUpDTO signUpDTO = SignUpDTO.voterDtoToSignUpDto(voterDto);

        ResponseEntity<MessageDTO> responseEntity = authService.registerUser(signUpDTO);
        if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
            this.voterService.saveUserVoter(VoterDto.dtoToEntity(voterDto));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return responseEntity;
    }

    @GetMapping(value = "/temporal-password/{email}", produces = "application/json")
    public ResponseEntity<IsTempDto> getIsTemporalPasswordByEmail(@PathVariable String email) {
        Voter voter = this.voterService.findByEmail(email);
        if (voter != null) {
            IsTempDto isTempDto = new IsTempDto();
            isTempDto.setTemp(voter.isTemporarPassword());
            return ResponseEntity.ok(isTempDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/update-password/{email}", consumes = "application/json")
    public ResponseEntity updatePassword(@PathVariable String email, @RequestBody PassDTO passDTO) {
        return ResponseEntity.ok(this.voterService.updatePass(email, passDTO));
    }

    @GetMapping(path = "/voting-data-history/{email}", produces = "application/json")
    public ResponseEntity<List<VotingDataForMobileDTO>> getVoteData(@PathVariable String email) throws UserNotFoundException {
        List<VotingDataForMobileDTO> votingDataDTO = votingDataService.getVotingDataByVoterEmail(email);
        return ResponseEntity.ok(votingDataDTO);
    }

    @GetMapping(path = "/voting-candidates/{votingCode}", produces = "application/json")
    public ResponseEntity<List<ProfileDTO>> getCandidatesProfileByVotingCode(@PathVariable String votingCode) throws UserNotFoundException {
        VotingData votingData = votingDataService.getVotingDataByVotingCode(votingCode);
        List<ProfileDTO> candidatesProfiles = new ArrayList<>();
        List<String> candidatesList = VotingDataDTO.parseToList(votingData.getCandidatesList());
        candidatesList.forEach(s -> {
            String t=s.trim();
            Candidate c = candidateService.findProfileIdByEmail(t);
            Profile profile = profileService.findById(c.getProfileId());
            candidatesProfiles.add(ProfileDTO.toDto(profile));
        });
        return ResponseEntity.ok(candidatesProfiles);
    }

    @PostMapping(path = "/cast-vote", consumes = "application/json")
    public ResponseEntity castVote(@RequestBody SavedVoteDTO savedVoteDTO) {
        VotingData votingData= votingDataService.getVotingDataByVotingCode(savedVoteDTO.getVotingCode());
        int votesNr=votingData.getVotesNumber();
        votingData.setVotesNumber(votesNr+1);
        votingDataRepository.save(votingData);
        CastedVoteDTO castedVoteDTO=new CastedVoteDTO(savedVoteDTO.getVoterEmail(),savedVoteDTO.getCastedVote(),Instant.now(),votingData.getVotingTitle(),"0");

        return ResponseEntity.ok(this.castedVotesService.saveVote(CastedVoteDTO.dtoToEntity(castedVoteDTO)));
    }
}
