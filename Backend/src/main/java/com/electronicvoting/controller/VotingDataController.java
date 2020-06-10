package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/evoting/admin/create-voting-session")
@RequiredArgsConstructor
public class VotingDataController {
    private final VotingDataService votingDataService;

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VotingDataDTO> saveVoteData(@RequestBody VotingDataDTO newVotingDTO) {
//TODO: Adjust this part for newVotingDTO
        votingDataService.saveVotingSession(VotingDataDTO.dtoToEntity(newVotingDTO));
        return ResponseEntity.accepted().build();
    }
}
