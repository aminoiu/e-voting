package com.electronicvoting.service.blockchain;

import com.electronicvoting.entity.DataBlock;

public interface DataBlockService {
    void saveBlock(DataBlock block);

    DataBlock getLastBlock(String votingTitle, Integer nrOfBlocksInChain);
}
