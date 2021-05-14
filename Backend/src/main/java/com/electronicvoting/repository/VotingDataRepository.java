package com.electronicvoting.repository;

import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.entity.VotingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface VotingDataRepository extends JpaRepository<VotingData, String> {
    VotingData findByVotingTitle(String votingTitle);

    Optional<VotingData> findByVotingId(String id);

    List<VotingData> findByAdminId(String id);
    @Query(
            value = "select * from voting_data.votings_data where candidates_list like %?1% \n",
            nativeQuery = true)
    List<VotingData> findByCandidateId(String id);

    int countByVoteCode(String voteCode);

    @Query(
            value = "select * from voting_data.votings_data where voters_list like %?1% \n",
            nativeQuery = true)
    List<VotingData> findByVoterId(String id);

    VotingData findByVoteCode(String votingCode);

}
