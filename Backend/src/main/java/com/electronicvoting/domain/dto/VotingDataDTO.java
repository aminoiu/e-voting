package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.VotingData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotingDataDTO {
    String votingTitle;
    Integer votersNumber;
    Integer votesNumber;
    String votingWinner;
    Integer candidatesNumber;
    String adminId;

    public static VotingDataDTO toDto(@NotNull VotingData votingData) {
        return VotingDataDTO.builder()
                .votingTitle(votingData.getVotingTitle())
                .votersNumber(votingData.getVotersNumber())
                .votesNumber(votingData.getVotesNumber())
                .votingWinner(votingData.getVotingWinner())
                .candidatesNumber(votingData.getCandidatesNumber())
                .adminId(votingData.getAdminId())
                .build();
    }

    public static VotingData dtoToEntity(@NotNull VotingDataDTO votingDataDTO) {
        return VotingData.builder()
                .votingTitle(votingDataDTO.getVotingTitle())
                .votersNumber(votingDataDTO.getVotersNumber())
                .votesNumber(votingDataDTO.getVotesNumber())
                .votingWinner(votingDataDTO.getVotingWinner())
                .candidatesNumber(votingDataDTO.getCandidatesNumber())
                .adminId(votingDataDTO.getAdminId())
                .build();
    }
}
