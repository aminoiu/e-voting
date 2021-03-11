package com.electronicvoting.service.auth;

import com.electronicvoting.domain.dto.LoginDTO;
import com.electronicvoting.domain.dto.LoginResponseDTO;
import com.electronicvoting.domain.dto.MessageDTO;
import com.electronicvoting.domain.dto.SignUpDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthService {
     ResponseEntity<LoginResponseDTO> authenticateUser(@Valid @RequestBody LoginDTO loginRequest);
     UserDetails authenticateUsers(@Valid @RequestBody LoginDTO loginRequest);
     ResponseEntity<MessageDTO> registerUser(@Valid @RequestBody SignUpDTO signUpRequest);

}
