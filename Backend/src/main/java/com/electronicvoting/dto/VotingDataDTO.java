package com.electronicvoting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VotingDataDTO {
    String votingId;
    String votingName;
    String voters_number;
    String votes_Number;
    String votingWinner;
    String candidatesNumber;
}
