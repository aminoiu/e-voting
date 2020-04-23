package com.electronicvoting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VotingDataDTO {
    String votingTitle;
    Integer votersNumber;
    Integer votesNumber;
    String votingWinner;
    Integer candidatesNumber;
    String adminEmail;
}
