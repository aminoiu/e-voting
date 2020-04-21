package com.electronicvoting.service.admin;

import com.electronicvoting.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin findByEmail(String email);

    void saveUserAdmin(Admin admin);

   String getHashPass(Admin admin);

   void setHashPass(Admin admin,String hashedPass);

}
