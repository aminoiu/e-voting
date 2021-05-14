package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.CastedVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class CastedVoteDTO {
    String voteType;
    Instant timestamp;
    String votingTitle;
    String castedVote;

    public static CastedVoteDTO toDto(CastedVote castedVote) {
        return CastedVoteDTO.builder()
                .timestamp(castedVote.getTimestamp())
                .votingTitle(castedVote.getVotingId())
                .castedVote(castedVote.getCastedVote())
                .voteType(castedVote.getVoteType())
                .build();
    }

    public static CastedVote dtoToEntity(CastedVoteDTO castedVoteDTO) {
        return CastedVote.builder()
                .voteId(castedVoteDTO.getVotingTitle())
                .timestamp(castedVoteDTO.getTimestamp())
                .votingId(castedVoteDTO.getVotingTitle())
                .castedVote(castedVoteDTO.getCastedVote())
                .voteType(castedVoteDTO.getVoteType())
                .build();
    }

}
