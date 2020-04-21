package com.electronicvoting.repository;

import com.electronicvoting.entity.BlockchainTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockchainTransactionRepository extends JpaRepository<BlockchainTransaction,String> {
}
