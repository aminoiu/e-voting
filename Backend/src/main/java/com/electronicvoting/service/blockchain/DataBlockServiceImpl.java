package com.electronicvoting.service.blockchain;

import com.electronicvoting.domain.dto.BlockDto;
import com.electronicvoting.entity.DataBlock;
import com.electronicvoting.repository.DataBlockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<BlockDto> getBlockByChainId(String chaiId) {
        List<BlockDto> blockDtoList = new ArrayList<>();
        dataBlockRepository.findAllByChainId(chaiId).forEach(dataBlock -> {
            blockDtoList.add(BlockDto.toDto(dataBlock));
        });
        return blockDtoList;
    }
}
