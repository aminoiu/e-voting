package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.*;
import com.electronicvoting.domain.enums.Roles;
import com.electronicvoting.entity.Role;
import com.electronicvoting.service.admin.AdminService;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.user.UserDetailsServiceImpl;
import com.electronicvoting.service.voter.VoterService;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/evoting/users")
@RequiredArgsConstructor
public class UserController {


    private final UserDetailsServiceImpl userDetailsService;

    private final AuthService authService;

    @GetMapping(value = "/login/{email}", produces = "application/json")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {

       /* List<String> roles = userDetailsService.loadUserByUsername(email).getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());*/
        Set<Role> roles = userDetailsService.loadUserByEmail(email).getRoles();
            if (!roles.isEmpty()) {
                if (roles.toString().contains(Roles.ADMIN.toString())) {
                    return ResponseEntity.ok(Roles.ADMIN.toString());
                } else if (roles.toString().contains(Roles.CANDIDATE.toString())) {
                    return ResponseEntity.ok(Roles.CANDIDATE.toString());
                } else if (roles.toString().contains(Roles.VOTER.toString())) {
                    return ResponseEntity.ok(Roles.VOTER.toString());
                }
            }
            return ResponseEntity.notFound().build();

    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {

        ResponseEntity<LoginResponseDTO> responseEntity = authService.authenticateUser(loginDTO);

        return  responseEntity;
    }
}
