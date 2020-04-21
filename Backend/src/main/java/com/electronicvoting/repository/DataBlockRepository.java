package com.electronicvoting.repository;

import com.electronicvoting.entity.DataBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBlockRepository extends JpaRepository<DataBlock, String> {
}
