package com.electronicvoting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@NoArgsConstructor
public class CastedVoteDTO {
    String voterEmail;
    String vote;
    Instant timestamp;
    String votingTitle;
    String deviceIp;

}
