package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.AdminDTO;
import com.electronicvoting.domain.dto.MessageDTO;
import com.electronicvoting.domain.dto.SignUpDTO;
import com.electronicvoting.service.admin.AdminService;
import com.electronicvoting.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
