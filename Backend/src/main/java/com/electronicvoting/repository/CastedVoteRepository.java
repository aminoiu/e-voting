package com.electronicvoting.repository;

import com.electronicvoting.entity.CastedVote;
import com.electronicvoting.entity.VotingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastedVoteRepository extends JpaRepository<CastedVote, String>{
}
