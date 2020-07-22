package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.VotingData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotingDataForMobileDTO {
    String votingTitle;
    String votersNumber;
    String votesNumber;
    String votingWinner;
    String candidatesNumber;
    String adminId;
    String startDate;
    String endDate;
    String status;

    public static VotingDataForMobileDTO toDto(@NotNull VotingData votingData) {
        return VotingDataForMobileDTO.builder()
                .votingTitle(votingData.getVotingTitle())
                .votersNumber(votingData.getVotersNumber() != null ? votingData.getVotersNumber().toString() : "0")
                .votesNumber(votingData.getVotesNumber() != null ? votingData.getVotesNumber().toString() : "0")
                .votingWinner(votingData.getVotingWinner()!=null?votingData.getVotingWinner():"-")
                .candidatesNumber(votingData.getCandidatesNumber() != null ? votingData.getCandidatesNumber().toString() : "0")
                .adminId(votingData.getAdminId())
                .startDate(votingData.getStartDate() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(votingData.getStartDate()) : "-")
                .endDate(votingData.getEndDate() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(votingData.getEndDate()) : "-")
                .status(votingData.getStatus())
                .build();
    }


    public static VotingDataForMobileDTO dtoToEntity(@NotNull VotingDataForMobileDTO votingDataDTO) {
        return VotingDataForMobileDTO.builder()
                .votingTitle(votingDataDTO.getVotingTitle())
                .votersNumber(votingDataDTO.getVotersNumber())
                .votesNumber(votingDataDTO.getVotesNumber())
                .votingWinner(votingDataDTO.getVotingWinner())
                .candidatesNumber(votingDataDTO.getCandidatesNumber())
                .adminId(votingDataDTO.getAdminId())
                .startDate(votingDataDTO.getStartDate())
                .endDate(votingDataDTO.getEndDate())
                .status(votingDataDTO.getStatus())
                .build();
    }


}
