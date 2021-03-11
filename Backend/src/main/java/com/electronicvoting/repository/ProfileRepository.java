package com.electronicvoting.repository;

import com.electronicvoting.entity.Profile;
import com.electronicvoting.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Profile findByEmail(String email);
    Profile findByProfileId(String id);

}
