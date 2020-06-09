package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.CastedVoteDTO;
import com.electronicvoting.service.castedvotes.CastedVotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/evoting/voter/cast-vote")
@RequiredArgsConstructor
public class CastedVoteController {
    private final CastedVotesService castedVotesService;

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('VOTER')")
    public ResponseEntity<CastedVoteDTO> saveVote(@RequestBody CastedVoteDTO castedVoteDTO) {
        castedVotesService.saveVote(CastedVoteDTO.dtoToEntity(castedVoteDTO));
        return ResponseEntity.accepted().build();
    }
}
