package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.BlockDto;
import com.electronicvoting.domain.dto.VotingDataForMobileDTO;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.service.blockchain.ChainsService;
import com.electronicvoting.service.blockchain.DataBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/evoting/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BlockchainController {
    private final DataBlockService dataBlockService;

    @GetMapping(path = "/blockchain/{chainId}", produces = "application/json")
    public ResponseEntity<List<BlockDto>> getVoteData(@PathVariable String chainId){
        List<BlockDto> blockDtoList = dataBlockService.getBlockByChainId(chainId);
        return ResponseEntity.ok(blockDtoList);
    }
}
