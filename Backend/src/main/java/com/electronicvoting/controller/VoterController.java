package com.electronicvoting.controller;

import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.helper.GenerateHashPasswordWithSalt;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.voter.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@RestController
@RequestMapping(value = "voter")
public class VoterController {
    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }


    @GetMapping(value = "/{email}", produces = "application/json")
    ResponseEntity<Voter> getByEmail(@PathVariable String email) {
        Voter voter = this.voterService.findByEmail(email);
        if (voter != null) {

            return ResponseEntity.ok(voter);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity<Candidate> createVoter(@RequestBody Voter voter) throws InvalidKeySpecException, NoSuchAlgorithmException {
        voter.setVoterId(UUID.randomUUID().toString());
        voter.setHashPassword(GenerateHashPasswordWithSalt.generateStrongPasswordHash(voter.getHashPassword()));
        this.voterService.saveUserVoter(voter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
