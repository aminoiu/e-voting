package com.electronicvoting.controller;

import com.electronicvoting.domain.enums.Roles;
import com.electronicvoting.entity.Admin;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Role;
import com.electronicvoting.entity.Voter;
import com.electronicvoting.repository.AdminRepository;
import com.electronicvoting.service.admin.AdminService;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.user.UserDetailsServiceImpl;
import com.electronicvoting.service.voter.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/evoting/users")
@RequiredArgsConstructor
public class UserController {

    private final CandidateService candidateService;
    private final VoterService voterService;
    private final AdminService adminService;
    private final UserDetailsServiceImpl userDetailsService;


    @GetMapping(value = "/{email}", produces = "application/json")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {

        List<String> roles = userDetailsService.loadUserByUsername(email).getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            if (!roles.isEmpty()) {
                if (roles.contains(Roles.ADMIN)) {
                    return ResponseEntity.ok(Roles.ADMIN);
                } else if (roles.contains(Roles.CANDIDATE)) {
                    return ResponseEntity.ok(Roles.CANDIDATE);
                } else if (roles.contains(Roles.VOTER)) {
                    return ResponseEntity.ok(Roles.VOTER);
                }
            }
            return ResponseEntity.notFound().build();

    }
}
