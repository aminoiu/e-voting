package com.electronicvoting.controller;

import com.electronicvoting.entity.Admin;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.helper.GenerateHashPasswordWithSalt;
import com.electronicvoting.service.admin.AdminService;
import com.electronicvoting.service.candidate.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@RestController
@RequestMapping(value = "candidate")
public class CandidateController {
    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
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
    ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) throws InvalidKeySpecException, NoSuchAlgorithmException {
        candidate.setCandidateId(UUID.randomUUID().toString());
        candidate.setHashPass(GenerateHashPasswordWithSalt.generateStrongPasswordHash(candidate.getHashPass()));
        this.candidateService.saveUserCandidate(candidate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    ResponseEntity<Candidate> updateEmail(@RequestParam(name = "email") String newEmail, Candidate candidate) {
        this.candidateService.updateEmail(candidate, newEmail);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    ResponseEntity deleteCandidate(@RequestParam(name = "email") String email){
        this.candidateService.deleteCandidate(email);
        return ResponseEntity.noContent().build();
    }
}
