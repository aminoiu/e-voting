package com.electronicvoting.repository;

import com.electronicvoting.entity.VotingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingDataRepository extends JpaRepository<VotingData, String> {
}
