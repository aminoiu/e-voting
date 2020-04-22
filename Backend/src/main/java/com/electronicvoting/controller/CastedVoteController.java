package com.electronicvoting.controller;

import com.electronicvoting.dto.CastedVoteDTO;
import com.electronicvoting.service.castedvotes.CastedVotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "voter")
public class CastedVoteController {
    private final CastedVotesService castedVotesService;

    @Autowired
    CastedVoteController(CastedVotesService castedVotesService) {
        this.castedVotesService = castedVotesService;
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity saveVote(@RequestBody CastedVoteDTO castedVoteDTO) {
        castedVotesService.saveVote(castedVoteDTO);
        return ResponseEntity.accepted().build();
    }
}
