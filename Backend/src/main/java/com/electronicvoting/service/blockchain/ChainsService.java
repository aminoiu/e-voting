package com.electronicvoting.service.blockchain;

import com.electronicvoting.entity.Chains;

import java.util.Map;

public interface ChainsService {
    void createBlockchainForVotingSession(Chains chain);

    Integer getNumberOfBlocksInChain(String votingTitle);


    String computeTransactionHas(Map<String, String> castedVoteMap);
}
