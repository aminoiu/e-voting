package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.VotingData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    Timestamp startDate;
    Timestamp endDate;
    String status;
    List<String> votersList;
    List<String> candidatesList;

    public static VotingDataDTO toDto(@NotNull VotingData votingData) {
        return VotingDataDTO.builder()
                .votingTitle(votingData.getVotingTitle())
                .votersNumber(votingData.getVotersNumber())
                .votesNumber(votingData.getVotesNumber())
                .votingWinner(votingData.getVotingWinner())
                .candidatesNumber(votingData.getCandidatesNumber())
                .adminId(votingData.getAdminId())
                .startDate(votingData.getStartDate())
                .endDate(votingData.getEndDate())
                .status(votingData.getStatus())
                .votersList(parseToList(votingData.getVotersList()))
                .candidatesList(parseToList(votingData.getCandidatesList()))
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
                .startDate(votingDataDTO.getStartDate())
                .endDate(votingDataDTO.getEndDate())
                .status(votingDataDTO.getStatus())
                .votersList(String.valueOf(votingDataDTO.getVotersList()))
                .candidatesList(String.valueOf(votingDataDTO.getCandidatesList()))
                .build();
    }


    private static List<String> parseToList(String listToParse) {
        String temp;
        List<String> listParsed;

        temp = listToParse.replace("[", "").replace("]", "");
        listParsed = Arrays.asList(temp.split(","));
        return listParsed;
    }
}
