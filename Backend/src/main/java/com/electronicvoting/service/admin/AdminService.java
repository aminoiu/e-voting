package com.electronicvoting.service.admin;

import com.electronicvoting.domain.dto.AdminDTO;
import com.electronicvoting.domain.dto.AdminDTOForMobile;
import com.electronicvoting.entity.Admin;
import com.electronicvoting.exceptions.UserNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AdminService {
    Admin findByEmail(String email) throws UserNotFoundException;

    void saveUserAdmin(AdminDTO adminDTO);


    Authentication getAdminAuthentication();


    boolean isAdminAuthenticated(String email, Authentication authentication);

    Optional<Admin> findById(String id);

    AdminDTOForMobile getAdminByEmail(String email) throws UserNotFoundException;
}
