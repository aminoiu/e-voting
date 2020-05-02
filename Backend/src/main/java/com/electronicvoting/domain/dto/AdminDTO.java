package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.Admin;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Builder
public class AdminDTO {
    String name;
    String workPlace;
    String email;
    String password;
    String country;
    String city;
    String street;
    String phoneNumber;
    Date birthDate;
    Long userId;

    public static AdminDTO toDto(@NotNull Admin admin) {
        return AdminDTO.builder()
                .name(admin.getName())
                .workPlace(admin.getWorkPlace())
                .email(admin.getEmail())
                .country(admin.getCountry())
                .city(admin.getCity())
                .street(admin.getStreet())
                .phoneNumber(admin.getPhoneNumber())
                .birthDate(admin.getBirthDate())
                .userId(admin.getUserId())
                .build();
    }

    public static Admin dtoToEntity(AdminDTO adminDTO) {
        return Admin.builder()
                .name(adminDTO.getName())
                .email(adminDTO.getEmail())
                .workPlace(adminDTO.getWorkPlace())
                .country(adminDTO.getCountry())
                .city(adminDTO.getCity())
                .street(adminDTO.getStreet())
                .birthDate(adminDTO.getBirthDate())
                .phoneNumber(adminDTO.getPhoneNumber())
                .userId(adminDTO.getUserId())
                .build();
    }

}
