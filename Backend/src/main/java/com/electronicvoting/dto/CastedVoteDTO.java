package com.electronicvoting.dto;

import com.electronicvoting.entity.CastedVote;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
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
