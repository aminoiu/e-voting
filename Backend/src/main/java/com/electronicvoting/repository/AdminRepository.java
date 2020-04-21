package com.electronicvoting.repository;

import com.electronicvoting.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByEmail(String email);

    Admin findByName(String name);

    List<Admin> findAllByWorkPlace(String votingId);

}
