package com.electronicvoting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDTO {
    String adminId;
    String name;
    String workPlace;
    String email;
}
