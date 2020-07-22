package com.electronicvoting.service.admin;

import com.electronicvoting.domain.dto.AdminDTO;
import com.electronicvoting.domain.dto.AdminDTOForMobile;
import com.electronicvoting.entity.Admin;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.repository.AdminRepository;
import com.electronicvoting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public Admin findByEmail(String email) throws UserNotFoundException {
        Admin admin;
        if (email == null) {
            throw new UserNotFoundException("E-mail is not specified.");
        }
        log.info("Find admin by e-mail[{}]", email);
        admin = adminRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("Error: Admin is not found.", email));
        return admin;
    }

    @Override
    @Transactional
    public void saveUserAdmin(@NotNull AdminDTO adminDTO) {
        userRepository.findByEmail(adminDTO.getEmail()).ifPresentOrElse(users -> adminDTO.setUserId(users.getId()), () -> {
            throw new RuntimeException("Error: User doesn't exist.");
        });

        Admin admin = AdminDTO.dtoToEntity(adminDTO);
        admin.setAdminId(UUID.randomUUID().toString());
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
    public Optional<Admin> findById(String id) {
        return adminRepository.findById(id);
    }

    @Override
    public AdminDTOForMobile getAdminByEmail(String email) throws UserNotFoundException {
        return AdminDTOForMobile.toDto(adminRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("Error: Admin is not found.", email)));
    }


    @Override
    public Authentication getAdminAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
