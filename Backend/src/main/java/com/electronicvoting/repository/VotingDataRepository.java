package com.electronicvoting.repository;

import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.entity.VotingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotingDataRepository extends JpaRepository<VotingData, String> {
    VotingData findByVotingTitle(String votingTitle);

    Optional<VotingData> findByVotingId(String id);

    List<VotingData> findByAdminId(String id);
}
