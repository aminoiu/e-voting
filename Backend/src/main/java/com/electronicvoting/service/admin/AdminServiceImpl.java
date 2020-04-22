package com.electronicvoting.service.admin;

import com.electronicvoting.entity.Admin;
import com.electronicvoting.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Admin findByEmail(String email) {
        log.info("Find admin by e-mail[{}]", email);
        return adminRepository.findByEmail(email);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUserAdmin(Admin admin) {
        this.adminRepository.save(admin);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String getHashPass(Admin admin) {
        return admin.getHashPassword();
    }


}
