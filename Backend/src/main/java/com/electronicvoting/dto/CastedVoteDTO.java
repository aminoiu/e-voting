package com.electronicvoting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
public class CastedVoteDTO {
    String voteId;
    String votingId;
    Date timestamp;
}
