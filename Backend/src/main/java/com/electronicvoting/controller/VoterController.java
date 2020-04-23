package com.electronicvoting.controller;

import com.electronicvoting.dto.SignUpDTO;
import com.electronicvoting.dto.VoterDto;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.service.voter.VoterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

@RestController
@RequestMapping(value = "/evoting/voter")
public class VoterController {
    private final VoterService voterService;
    AuthController authController;
    HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder;

    public VoterController(VoterService voterService, HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder, AuthController authController
    ) {
        this.voterService = voterService;
        this.hashPasswordWithSaltEncoder = hashPasswordWithSaltEncoder;
        this.authController = authController;
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
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> createVoter(@RequestBody VoterDto voterDto) {
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setEmail(voterDto.getEmail());
        signUpDTO.setPassword(voterDto.getPassword());
        signUpDTO.setUsername(voterDto.getEmail());
        signUpDTO.setRole(Set.of("CANDIDATE"));

        ResponseEntity responseEntity = authController.registerUser(signUpDTO);
        if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
            this.voterService.saveUserVoter(voterDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return responseEntity;
    }
}
