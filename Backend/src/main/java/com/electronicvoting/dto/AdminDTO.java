package com.electronicvoting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDTO {
    String name;
    String workPlace;
    String email;
    String password;
    String country;
    String city;
    String street;
    String phoneNumber;


}
