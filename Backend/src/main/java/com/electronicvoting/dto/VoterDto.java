package com.electronicvoting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoterDto {
    String voterId;
    String name;
    String email;
}
