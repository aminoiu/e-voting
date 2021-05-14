package com.electronicvoting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginResponseDTO {
    private String token;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

}
