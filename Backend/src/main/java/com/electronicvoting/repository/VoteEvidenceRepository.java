package com.electronicvoting.repository;

import com.electronicvoting.entity.CastedVote;
import com.electronicvoting.entity.VoteEvidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteEvidenceRepository extends JpaRepository<VoteEvidence, String> {
    List<VoteEvidence> findAllByVotingIdAndVoterEmail(String voteId, String voterEmail);

}
