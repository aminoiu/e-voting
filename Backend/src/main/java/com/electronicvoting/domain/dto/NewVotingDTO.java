package com.electronicvoting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewVotingDTO {
    String votingTitle;
    String adminId;
    Map<String,String> newVotingVoters=new HashMap<>();
    Map<String,String> newVotingCandidates=new HashMap<>();
}
