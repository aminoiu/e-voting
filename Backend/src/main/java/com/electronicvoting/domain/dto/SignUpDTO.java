package com.electronicvoting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public static SignUpDTO candidateDtoToSignUpDto(CandidateDTO candidateDTO) {
        return SignUpDTO.builder()
                .username(candidateDTO.getEmail())
                .email(candidateDTO.getEmail())
                .password(candidateDTO.getPassword())
                .role(Set.of("CANDIDATE"))
                .build();
    }

    public static SignUpDTO adminDtoToSignUpDto(AdminDTO adminDTO) {
        return SignUpDTO.builder()
                .username(adminDTO.getEmail())
                .email(adminDTO.getEmail())
                .password(adminDTO.getPassword())
                .role(Set.of("ADMIN"))
                .build();
    }

    public static SignUpDTO voterDtoToSignUpDto(VoterDto voterDto) {
        return SignUpDTO.builder()
                .username(voterDto.getEmail())
                .email(voterDto.getEmail())
                .password(voterDto.getPassword())
                .role(Set.of("VOTER"))
                .build();
    }
}
