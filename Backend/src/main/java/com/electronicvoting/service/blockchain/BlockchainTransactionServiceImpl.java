package com.electronicvoting.service.blockchain;

import com.electronicvoting.entity.BlockchainTransaction;
import com.electronicvoting.repository.BlockchainTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlockchainTransactionServiceImpl implements BlockchainTransactionService{
    private final BlockchainTransactionRepository blockchainTransactionRepository;
    @Override
    public BlockchainTransaction saveNewTransaction(BlockchainTransaction blockchainTransaction) {
        blockchainTransactionRepository.save(blockchainTransaction);
       return blockchainTransaction;
    }
}
