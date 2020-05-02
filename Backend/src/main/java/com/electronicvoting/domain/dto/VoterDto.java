package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.Voter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoterDto {

    String name;
    String email;
    String password;
    long userId;

    public static VoterDto toDto(@NotNull Voter voter) {
        return VoterDto.builder()
                .name(voter.getName())
                .email(voter.getEmail())
                .userId(voter.getUserId())
                .build();
    }

    public static Voter dtoToEntity(@NotNull VoterDto voterDto) {
        return Voter.builder()
                .name(voterDto.getName())
                .email(voterDto.getEmail())
                .userId(voterDto.getUserId())
                .build();
    }
}
