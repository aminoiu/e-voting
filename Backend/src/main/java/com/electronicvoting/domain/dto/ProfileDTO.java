package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.Profile;
import com.electronicvoting.entity.Voter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class ProfileDTO {
    String name;
    String email;
    String position;
    String education;
    String selfDescription;


    public static ProfileDTO toDto(@NotNull Profile profile) {
        return ProfileDTO.builder()
                .name(profile.getName())
                .email(profile.getEmail())
                .position(profile.getPosition())
                .education(profile.getEducation())
                .selfDescription(profile.getSelfDescription())
                .build();
    }

    public static Profile dtoToEntity(@NotNull ProfileDTO profileDTO) {
        return Profile.builder()
                .name(profileDTO.getName())
                .email(profileDTO.getEmail())
                .position(profileDTO.getPosition())
                .education(profileDTO.getEducation())
                .selfDescription(profileDTO.getSelfDescription())
                .build();
    }
}
