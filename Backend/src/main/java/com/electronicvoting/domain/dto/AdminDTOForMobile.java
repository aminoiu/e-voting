package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.Admin;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Builder
public class AdminDTOForMobile {
    String name;
    String email;
    String workPlace;
    String country;
    String street;
    Date birthDate;
    String phoneNumber;

    public static AdminDTOForMobile toDto(@NotNull Admin admin) {
        return AdminDTOForMobile.builder()
                .name(admin.getName())
                .workPlace(admin.getWorkPlace())
                .email(admin.getEmail())
                .country(admin.getCountry())
                .street(admin.getStreet())
                .phoneNumber(admin.getPhoneNumber())
                .birthDate(admin.getBirthDate())
                .build();
    }


}


