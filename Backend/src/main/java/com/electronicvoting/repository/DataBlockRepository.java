package com.electronicvoting.repository;

import com.electronicvoting.entity.DataBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataBlockRepository extends JpaRepository<DataBlock, String> {
    @Query(
            value = "select * from blockchain.blocks b where chain_id=?1 and order_nr=?2",
            nativeQuery = true)
    DataBlock findByChainIdAndAndOrderNr(String chainId, Integer orderNr);

    List<DataBlock> findAllByChainId(String chainId);
}
