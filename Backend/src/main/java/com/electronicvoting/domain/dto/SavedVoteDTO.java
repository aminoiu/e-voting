package com.electronicvoting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedVoteDTO {
    String voterEmail;
    String castedVote;
    String votingCode;
}
