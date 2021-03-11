package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.VotingData;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;

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
    Date startDate;
    Date endDate;
    String voting_type;
    String categories;
    String status;
    List<String> votersList;
    List<String> candidatesList;
    String voteCode;

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
                .voteCode(votingData.getVoteCode())
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
                .startDate((Timestamp) votingDataDTO.getStartDate())
                .endDate((Timestamp) votingDataDTO.getEndDate())
                .status(votingDataDTO.getStatus())
                .votersList(String.valueOf(getEmailsListOnly(votingDataDTO.getVotersList())))
                .candidatesList(String.valueOf(getEmailsListOnly(votingDataDTO.getCandidatesList())))
                .voteCode(votingDataDTO.getVoteCode())
                .build();
    }



    private static List getEmailsListOnly(List<String> votersList) {
        String[] temp;
        List<String> listParsed = new ArrayList<>();
        for(String u:votersList){
            temp=u.split(",");
            listParsed.add(temp[1]);
        }
        return listParsed;

    }


    public static List<String> parseToList(String listToParse) {
        String temp;
        List<String> listParsed;

        temp = listToParse.replace("[", "").replace("]", "");
        listParsed = Arrays.asList(temp.trim().split(","));
        return listParsed;
    }
}
