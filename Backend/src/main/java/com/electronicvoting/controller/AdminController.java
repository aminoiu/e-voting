package com.electronicvoting.controller;

import com.electronicvoting.dto.AdminDTO;
import com.electronicvoting.dto.SignUpDTO;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

@RestController
@RequestMapping(value = "/evoting/admins")
public class AdminController {

    private final AdminService adminService;
    AuthController authController;
    HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder;

    public AdminController(AdminService adminService, HashPasswordWithSaltEncoder hashPasswordWithSaltEncoder, AuthController authController) {
        this.adminService = adminService;
        this.hashPasswordWithSaltEncoder = hashPasswordWithSaltEncoder;
        this.authController = authController;
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity<?> createAdmin(@RequestBody AdminDTO adminDTO) {
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setEmail(adminDTO.getEmail());
        signUpDTO.setPassword(adminDTO.getPassword());
        signUpDTO.setUsername(adminDTO.getEmail());
        signUpDTO.setRole(Set.of("ADMIN"));

        ResponseEntity responseEntity = authController.registerUser(signUpDTO);
        if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
           this.adminService.saveUserAdmin(adminDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return responseEntity;
    }
}
