package com.electronicvoting.repository;

import com.electronicvoting.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {
    Candidate findByEmail(String email);

    Candidate findByName(String name);

    List<Candidate> findAllByVotingId(String votingId);
}
