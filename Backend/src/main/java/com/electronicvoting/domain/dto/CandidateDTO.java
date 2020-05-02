package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.Candidate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CandidateDTO {
    String name;
    String email;
    String password;
    String profileId;
    long userId;


    public static CandidateDTO toDto(@NotNull Candidate candidate) {
        return CandidateDTO.builder()
                .name(candidate.getName())
                .email(candidate.getEmail())
                .profileId(candidate.getProfileId())
                .userId(candidate.getUserId())
                .build();
    }

    public static Candidate dtoToEntity(@NotNull CandidateDTO candidateDTO) {
        return Candidate.builder()
                .name(candidateDTO.getName())
                .profileId(candidateDTO.getProfileId())
                .email(candidateDTO.getEmail())
                .userId(candidateDTO.getUserId())
                .build();
    }

}
