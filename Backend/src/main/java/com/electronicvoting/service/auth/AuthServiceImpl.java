package com.electronicvoting.service.auth;

import com.electronicvoting.domain.dto.LoginDTO;
import com.electronicvoting.domain.dto.LoginResponseDTO;
import com.electronicvoting.domain.dto.MessageDTO;
import com.electronicvoting.domain.dto.SignUpDTO;
import com.electronicvoting.domain.enums.Roles;
import com.electronicvoting.entity.Role;
import com.electronicvoting.entity.Users;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.repository.RoleRepository;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.security.jwt.JwtUtils;
import com.electronicvoting.service.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashPasswordWithSaltEncoder encoder;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<LoginResponseDTO> authenticateUser(@Valid LoginDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new LoginResponseDTO(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public UserDetails authenticateUsers(@Valid LoginDTO login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails;
    }

    @Override
    public ResponseEntity<MessageDTO> registerUser(@Valid SignUpDTO signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageDTO("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageDTO("Error: Email is already in use!"));
        }
        // Create new users's account
        Users users = new Users(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) throw new RuntimeException("Error: Role is not found.");
        else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(Roles.ADMIN.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "CANDIDATE":
                        Role canRole = roleRepository.findByName(Roles.CANDIDATE.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(canRole);

                        break;
                    case "VOTER":
                        Role votRole = roleRepository.findByName(Roles.VOTER.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(votRole);

                        break;
                    default:
                        throw new RuntimeException("Error: Role is not found.");

                }
            });
        }
        users.setRoles(roles);
        users.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        userRepository.save(users);
        return ResponseEntity.ok(new MessageDTO("Users registered successfully!"));
    }
}
