package com.electronicvoting.controller;

import com.electronicvoting.dto.CandidateDTO;
import com.electronicvoting.dto.SignUpDTO;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.service.candidate.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

@RestController
@RequestMapping(value = "/evoting/candidate")
public class CandidateController {
    private final CandidateService candidateService;
    HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder;
    AuthController authController;


    public CandidateController(CandidateService candidateService,HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder,AuthController authController) {
        this.candidateService = candidateService;
        this.hashPasswordWithSaltEncoder=hashPasswordWithSaltEncoder;
        this.authController=authController;
    }


    @GetMapping(value = "/{email}", produces = "application/json")
    ResponseEntity<Candidate> getByEmail(@PathVariable String email) {
        Candidate candidate = this.candidateService.findByEmail(email);
        if (candidate != null) {

            return ResponseEntity.ok(candidate);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setEmail(candidateDTO.getEmail());
        signUpDTO.setPassword(candidateDTO.getPassword());
        signUpDTO.setUsername(candidateDTO.getEmail());
        signUpDTO.setRole(Set.of("CANDIDATE"));

        ResponseEntity responseEntity = authController.registerUser(signUpDTO);
        if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
            this.candidateService.saveUserCandidate(candidateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return responseEntity;
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")
    ResponseEntity<Candidate> updateEmail(@RequestParam(name = "email") String newEmail, Candidate candidate) {
        this.candidateService.updateEmail(candidate, newEmail);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity deleteCandidate(@RequestParam(name = "email") String email){
        this.candidateService.deleteCandidate(email);
        return ResponseEntity.noContent().build();
    }
}
