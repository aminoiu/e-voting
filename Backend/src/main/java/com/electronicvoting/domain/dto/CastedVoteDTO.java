package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.CastedVote;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
public class CastedVoteDTO {
    String voterEmail;
    String vote;
    Instant timestamp;
    String votingTitle;
    String deviceIp;

    public static CastedVoteDTO toDto(CastedVote castedVote) {
        return CastedVoteDTO.builder()
                .voterEmail(castedVote.getVoterEmail())
                .vote(castedVote.getCandidateEmail())
                .timestamp(castedVote.getTimestamp())
                .votingTitle(castedVote.getVotingId())
                .deviceIp(castedVote.getDeviceIp())
                .build();
    }

    public static CastedVote dtoToEntity(CastedVoteDTO castedVoteDTO) {
        return CastedVote.builder()
                .voteId(castedVoteDTO.getVotingTitle())
                .voterEmail(castedVoteDTO.getVoterEmail())
                .timestamp(castedVoteDTO.getTimestamp())
                .deviceIp(castedVoteDTO.getDeviceIp())
                .candidateEmail(castedVoteDTO.getVote())
                .build();
    }

}
