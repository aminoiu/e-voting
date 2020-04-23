package com.electronicvoting.service.admin;

import com.electronicvoting.dto.AdminDTO;
import com.electronicvoting.entity.Admin;
import com.electronicvoting.repository.AdminRepository;
import com.electronicvoting.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Admin findByEmail(String email) {
        log.info("Find admin by e-mail[{}]", email);
        return adminRepository.findByEmail(email);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUserAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setAdminId(UUID.randomUUID().toString());
        admin.setCity(adminDTO.getCity());
        admin.setName(adminDTO.getName());
        admin.setEmail(adminDTO.getEmail());
        admin.setWorkPlace(adminDTO.getWorkPlace());
        admin.setCountry(adminDTO.getCountry());
        admin.setStreet(adminDTO.getStreet());
        admin.setPhoneNumber(adminDTO.getPhoneNumber());
        userRepository.findByEmail(admin.getEmail()).ifPresent(user -> admin.setUser_id(user.getId()));
        this.adminRepository.save(admin);
    }

    @Override
    public boolean isAdminAuthenticated(String email, Authentication authentication) {
        if (authentication == null) {
            return getAdminAuthentication().getName().equals(email);
        } else {
            return authentication.getName().equals(email);
        }
    }

    @Override
    public Authentication getAdminAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
