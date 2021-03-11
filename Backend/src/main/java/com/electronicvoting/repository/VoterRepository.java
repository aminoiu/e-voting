package com.electronicvoting.repository;

import com.electronicvoting.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Voter, String> {
    Voter findByEmail(String email);

    Voter findByName(String name);

    Boolean existsByEmail(String email);

}
