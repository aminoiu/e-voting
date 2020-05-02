package com.electronicvoting.service.admin;

import com.electronicvoting.domain.dto.AdminDTO;
import com.electronicvoting.entity.Admin;
import org.springframework.security.core.Authentication;

public interface AdminService {
    Admin findByEmail(String email);

    void saveUserAdmin(AdminDTO adminDTO);


    Authentication getAdminAuthentication();


    boolean isAdminAuthenticated(String email, Authentication authentication);
}
