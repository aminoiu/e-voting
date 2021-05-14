package com.electronicvoting.repository;

import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Chains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChainsRepository extends JpaRepository<Chains, String> {


}
