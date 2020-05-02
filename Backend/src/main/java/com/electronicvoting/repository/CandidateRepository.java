package com.electronicvoting.repository;

import com.electronicvoting.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {
    Candidate findByEmail(String email);

    Candidate findByName(String name);
    Boolean existsByEmail(String email);

    void deleteByEmail(String email);
}
