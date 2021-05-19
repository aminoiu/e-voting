package com.electronicvoting.service.blockchain;

import com.electronicvoting.domain.dto.BlockDto;
import com.electronicvoting.entity.DataBlock;

import java.util.List;

public interface DataBlockService {
    void saveBlock(DataBlock block);

    DataBlock getLastBlock(String votingTitle, Integer nrOfBlocksInChain);

    List<BlockDto> getBlockByChainId(String chaiId);

}
