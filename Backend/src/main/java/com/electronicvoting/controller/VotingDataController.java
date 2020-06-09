package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.NewVotingDTO;
import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.entity.VotingData;
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

    @PostMapping(path = "/",consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VotingDataDTO> saveVoteData(@RequestBody NewVotingDTO newVotingDTO) {

        VotingDataDTO votingDataDTO=new VotingDataDTO();
        votingDataService.saveVotingSession(VotingDataDTO.dtoToEntity(votingDataDTO));
        return ResponseEntity.accepted().build();
    }
}
