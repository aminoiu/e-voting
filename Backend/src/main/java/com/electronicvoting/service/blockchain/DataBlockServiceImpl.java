package com.electronicvoting.service.blockchain;

import com.electronicvoting.entity.DataBlock;
import com.electronicvoting.repository.DataBlockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataBlockServiceImpl implements DataBlockService {
    private final DataBlockRepository dataBlockRepository;


    @Override
    public void saveBlock(DataBlock block) {
        dataBlockRepository.save(block);
    }

    @Override
    public DataBlock getLastBlock(String votingTitle, Integer nrOfBlocksInChain) {
        return dataBlockRepository.findByChainIdAndAndOrderNr(votingTitle, nrOfBlocksInChain);
    }
}
