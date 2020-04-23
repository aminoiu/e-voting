package com.electronicvoting.controller;

import com.electronicvoting.dto.VotingDataDTO;
import com.electronicvoting.service.votingdata.VotingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/evoting/admin/createVotingSession")
public class VotingDataController {
    private final VotingDataService votingDataService;

    @Autowired
    VotingDataController(VotingDataService votingDataService) {
        this.votingDataService = votingDataService;
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity saveVoteData(@RequestBody VotingDataDTO votingDataDTO) {
        votingDataService.saveVotingSession(votingDataDTO);
        return ResponseEntity.accepted().build();
    }
}
