package com.electronicvoting.service.admin;

import com.electronicvoting.dto.AdminDTO;
import com.electronicvoting.entity.Admin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AdminService {
    Admin findByEmail(String email);

    void saveUserAdmin(AdminDTO adminDTO);


    Authentication getAdminAuthentication();


    boolean isAdminAuthenticated(String email, Authentication authentication);
}
