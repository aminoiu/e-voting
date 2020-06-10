package com.electronicvoting.Service.Admin;

import com.electronicvoting.entity.Admin;
import com.electronicvoting.entity.Role;
import com.electronicvoting.entity.Users;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.repository.AdminRepository;
import com.electronicvoting.repository.RoleRepository;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.security.jwt.JwtUtils;
import com.electronicvoting.service.admin.AdminServiceImpl;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.user.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {
    @InjectMocks
    AdminServiceImpl adminService;
    List<String> roles;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthService authService;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private HashPasswordWithSaltEncoder encoder;
    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private AuthenticationManager authenticationManager;
    private Users user;
    private Admin admin;
    private Role role;
    private Authentication authMock;

    @BeforeEach
    void setUp() {
        adminService = new AdminServiceImpl(adminRepository, userRepository);
        userDetailsService = new UserDetailsServiceImpl(userRepository);

        user = new Users();
        user.setId(100);
        user.setUsername("test@test.com");
        user.setEmail("test@test.com");
        user.setPassword("test");

        role = new Role();
        role.setId(101);
        role.setName("ADMIN");

        admin = new Admin();
        admin.setAdminId("1000");
        admin.setUserId(user.getId());
        admin.setPhoneNumber("123456");
        admin.setStreet("test");
        admin.setCountry("test");
        admin.setWorkPlace("Test");
        admin.setEmail("test@test.com");
        admin.setName("Test Test");
        admin.setCity("Testtt");
        admin.setBirthDate(new Date(Calendar.getInstance().getTime().getTime()));

        authMock=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(role.getName())));
        SecurityContextHolder.getContext().setAuthentication(authMock);
    }

    @Test
    void loadByEmail_whenInvoked_returnAdminDetails() {
        when(userRepository.findByEmail("test@test.com")).thenReturn(java.util.Optional.ofNullable(user));
        UserDetails user = userDetailsService.loadUserByUsername(admin.getEmail());
        assertThat(user.getUsername()).isEqualTo(admin.getEmail());
    }
    @Test
    void loadByUsername_withNullOrWrongUser_throwUserNotFoundException() {
        when(userRepository.findByEmail(admin.getEmail())).thenThrow(UsernameNotFoundException.class);
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(admin.getEmail()));
    }



}
