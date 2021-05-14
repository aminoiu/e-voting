package com.electronicvoting.service.blockchain;

import com.electronicvoting.entity.BlockchainTransaction;

public interface BlockchainTransactionService {

    BlockchainTransaction saveNewTransaction(BlockchainTransaction blockchainTransaction);
}
