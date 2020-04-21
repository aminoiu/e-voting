package com.electronicvoting.controller;

import com.electronicvoting.entity.Admin;
import com.electronicvoting.helper.GenerateHashPasswordWithSalt;
import com.electronicvoting.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@RestController
@RequestMapping(value = "admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping(value = "/{email}", produces = "application/json")
    ResponseEntity<Admin> getByEmail(@PathVariable String email) {
        Admin admin = this.adminService.findByEmail(email);
        if (admin != null) {

            return ResponseEntity.ok(admin);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity<Admin> saveUserAdmin(@RequestBody Admin admin) throws InvalidKeySpecException, NoSuchAlgorithmException {
        admin.setAdminId(UUID.randomUUID().toString());
        admin.setHashPassword(GenerateHashPasswordWithSalt.generateStrongPasswordHash(admin.getHashPassword()));
        this.adminService.saveUserAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
