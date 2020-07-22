package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.*;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.service.admin.AdminService;
import com.electronicvoting.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/evoting/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AuthService authService;

    @PostMapping(path = "/register",consumes = "application/json")
    public ResponseEntity<MessageDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        SignUpDTO signUpDTO = SignUpDTO.adminDtoToSignUpDto(adminDTO);

        ResponseEntity<MessageDTO> responseEntity = authService.registerUser(signUpDTO);
        if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
            this.adminService.saveUserAdmin(adminDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return responseEntity;
    }

    @GetMapping(path = "/user-details/{email}", produces = "application/json")
    public ResponseEntity<AdminDTOForMobile> getUserDetails(@PathVariable String email) throws UserNotFoundException {
        AdminDTOForMobile votingDataDTO=adminService.getAdminByEmail(email);
        return ResponseEntity.ok(votingDataDTO);
    }
}
