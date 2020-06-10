package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.CandidateDTO;
import com.electronicvoting.domain.dto.MessageDTO;
import com.electronicvoting.domain.dto.SignUpDTO;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.exceptions.EmailExistsException;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.candidate.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/evoting/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;
    private final HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder;
    private final AuthService authService;

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

    @PutMapping(path = "/update/{email}",consumes = "application/json")
    @PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")
    public ResponseEntity<Candidate> updateEmail(@PathVariable String email, @RequestBody CandidateDTO candidateDTO) throws EmailExistsException {
        return ResponseEntity.ok(this.candidateService.updateEmail(CandidateDTO.dtoToEntity(candidateDTO), email));
    }

    @DeleteMapping(path = "{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCandidate(@PathVariable String email) {

        return ResponseEntity.ok(this.candidateService.deleteCandidate(email));
    }
}
