package com.electronicvoting.service.admin;

import com.electronicvoting.entity.Admin;
import com.electronicvoting.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findByEmail(String email) {
        log.info("Find admin by e-mail[{}]",email);
        return adminRepository.findByEmail(email);
    }

    @Override
    public void saveUserAdmin(Admin admin) {
        this.adminRepository.save(admin);
    }

    @Override
    public String getHashPass(Admin admin) {
       return admin.getHashPassword();
    }

    @Override
    public void setHashPass(Admin admin,String hashedPass) {

    }
}
