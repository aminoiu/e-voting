package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.domain.dto.VotingDataForMobileDTO;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/evoting/admin")
@RequiredArgsConstructor
public class VotingDataController {
    private final VotingDataService votingDataService;

    @PostMapping(path = "/start-new-voting",consumes = "application/json")
    public ResponseEntity<VotingDataDTO> saveVoteData(@RequestBody VotingDataDTO newVotingDTO) throws UserNotFoundException {
//TODO: Adjust this part for newVotingDTO
        votingDataService.saveVotingSession(VotingDataDTO.dtoToEntity(newVotingDTO));
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/voting-data-history/{email}", produces = "application/json")
    public ResponseEntity<List<VotingDataForMobileDTO>> getVoteData(@PathVariable String email) throws UserNotFoundException {
        List<VotingDataForMobileDTO> votingDataDTO=votingDataService.getVotingDataByEmail(email);
        return ResponseEntity.ok(votingDataDTO);
    }
}
