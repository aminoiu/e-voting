package com.electronicvoting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CandidateDTO {
    String candidateId;
    String name;
    String email;
}
