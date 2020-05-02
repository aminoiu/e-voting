package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.MessageDTO;
import com.electronicvoting.domain.dto.SignUpDTO;
import com.electronicvoting.domain.dto.VoterDto;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.voter.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/evoting/voter")
@RequiredArgsConstructor
public class VoterController {
    private final VoterService voterService;
    private AuthService authService;

    @GetMapping(value = "/{email}", produces = "application/json")
    public ResponseEntity<Voter> getByEmail(@PathVariable String email) {
        Voter voter = this.voterService.findByEmail(email);
        if (voter != null) {

            return ResponseEntity.ok(voter);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/register",consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageDTO> createVoter(@RequestBody VoterDto voterDto) {
        SignUpDTO signUpDTO = SignUpDTO.voterDtoToSignUpDto(voterDto);

        ResponseEntity<MessageDTO> responseEntity = authService.registerUser(signUpDTO);
        if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
            this.voterService.saveUserVoter(voterDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return responseEntity;
    }
}
