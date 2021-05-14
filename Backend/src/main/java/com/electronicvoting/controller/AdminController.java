package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.*;
import com.electronicvoting.entity.ConfirmationToken;
import com.electronicvoting.entity.Users;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.helper.ApplicationContextProvider;
import com.electronicvoting.helper.HashPasswordWithSaltEncoder;
import com.electronicvoting.repository.ConfirmationTokenRepository;
import com.electronicvoting.repository.UserRepository;
import com.electronicvoting.service.admin.AdminService;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping(value = "/evoting/admins")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;
    private final AuthService authService;
    private final HashPasswordWithSaltEncoder encoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    @Autowired
    private ApplicationContextProvider applicationContextProvider;
    @Autowired
    private Environment env;


    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<MessageDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        SignUpDTO signUpDTO = SignUpDTO.adminDtoToSignUpDto(adminDTO);
        ResponseEntity<MessageDTO> responseEntity = authService.registerUser(signUpDTO);
        if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
            this.adminService.saveUserAdmin(adminDTO);
            sendConfirmationEmail(signUpDTO);
        }
        return responseEntity;
    }

    private void sendConfirmationEmail(SignUpDTO signUpDTO) {
        Optional<Users> tempUsers = userRepository.findByEmail(signUpDTO.getEmail());

        Users users = tempUsers.orElse(new Users());

        ConfirmationToken confirmationToken = new ConfirmationToken(users);
        confirmationTokenRepository.save(confirmationToken);

        Mail mailMessage = new Mail();
        mailMessage.setMailTo(users.getEmail());
        mailMessage.setMailSubject("EVoting Complete Registration!");
        mailMessage.setMailFrom(env.getProperty("spring.mail.username"));
        mailMessage.setMailContent("To confirm your account, please click here : "
                + "http://localhost:8080/evoting/admins/confirm-account?token=" + confirmationToken.getConfirmationToken());

        MailService mailService = (MailService) applicationContextProvider.getApplicationContext().getBean("mailService");
        mailService.sendEmail(mailMessage);

    }

    @GetMapping(path = "/user-details/{email}", produces = "application/json")
    public ResponseEntity<AdminDTOForMobile> getUserDetails(@PathVariable String email) throws UserNotFoundException {
        AdminDTOForMobile votingDataDTO = adminService.getAdminByEmail(email);
        return ResponseEntity.ok(votingDataDTO);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public void confirmUserAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            Users userFromToken = userRepository.findById(token.getUser_id());
            Optional<Users> users = userRepository.findByEmail(userFromToken.getEmail());
            Users u = users.orElse(new Users());
            u.setEnabled(true);
            userRepository.save(u);
        } else {
//            return ResponseEntity.ok(new MessageDTO("The link is invalid or broken!"));
        }
//        return ResponseEntity.ok(new MessageDTO("Admin account confirmed!"));
    }
}
