package com.electronicvoting.service.blockchain;

import com.electronicvoting.domain.dto.BlockDto;
import com.electronicvoting.entity.Chains;
import com.electronicvoting.helper.SHA256;
import com.electronicvoting.repository.ChainsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChainsServiceImpl implements ChainsService {
    private final ChainsRepository chainsRepository;
    @Override
    public void createBlockchainForVotingSession(Chains chain) {
        chainsRepository.save(chain);
    }

    @Override
    public Integer getNumberOfBlocksInChain(String votingTitle) {
        AtomicReference<Integer> nrOfBlocks= new AtomicReference<>(0);
        Optional<Chains> chain= chainsRepository.findById(votingTitle);
         chain.ifPresent(c -> {
             nrOfBlocks.set(c.getNumberBlocks());});
         return nrOfBlocks.get();
    }


    @Override
    public String computeTransactionHas(Map<String, String> transactionInfo) {
       return  SHA256.generateHash(transactionInfo);

    }



}
