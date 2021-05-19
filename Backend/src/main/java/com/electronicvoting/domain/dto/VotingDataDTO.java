package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.VotingData;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    String startDate;
    String endDate;
    String startTime;
    String endTime;
    String voting_type;
    String categories;
    String status;
    List<String> votersList;
    List<String> candidatesList;
    String voteCode;

    public static VotingDataDTO toDto(@NotNull VotingData votingData) {

        Date sdate = new Date();
        sdate.setTime(votingData.getStartDate().getTime());
        String startDateString = new SimpleDateFormat("yyyy-MM-dd").format(sdate);
        String startTimeString = new SimpleDateFormat("hh:mm").format(sdate);

        Date edate = new Date();
        edate.setTime(votingData.getEndDate().getTime());
        String endDateString = new SimpleDateFormat("yyyy-MM-dd").format(edate);
        String endTimeString = new SimpleDateFormat("hh:mm").format(edate);

        return VotingDataDTO.builder()
                .votingTitle(votingData.getVotingTitle())
                .votersNumber(votingData.getVotersNumber())
                .votesNumber(votingData.getVotesNumber())
                .votingWinner(votingData.getVotingWinner())
                .candidatesNumber(votingData.getCandidatesNumber())
                .adminId(votingData.getAdminId())
                .startDate(startDateString)
                .startTime(startTimeString)
                .endDate(endDateString)
                .endTime(endTimeString)
                .status(votingData.getStatus())
                .votersList(parseToList(votingData.getVotersList()))
                .candidatesList(parseToList(votingData.getCandidatesList()))
                .voteCode(votingData.getVoteCode())
                .build();
    }


    public static VotingData dtoToEntity(@NotNull VotingDataDTO votingDataDTO) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String startDateAndTime = votingDataDTO.startDate + " " + votingDataDTO.startTime;
        Date startDateTime = dateFormat.parse(startDateAndTime);
        String endDateAndTime = votingDataDTO.endDate + " " + votingDataDTO.endTime;
        Date endDateTime = dateFormat.parse(endDateAndTime);

        Timestamp startTimestamp = new Timestamp(startDateTime.getTime());
        Timestamp endTimestamp = new Timestamp(endDateTime.getTime());

        return VotingData.builder()
                .votingTitle(votingDataDTO.getVotingTitle())
                .votersNumber(votingDataDTO.getVotersNumber())
                .votesNumber(votingDataDTO.getVotesNumber())
                .votingWinner(votingDataDTO.getVotingWinner())
                .candidatesNumber(votingDataDTO.getCandidatesNumber())
                .adminId(votingDataDTO.getAdminId())
                .startDate(startTimestamp)
                .endDate(endTimestamp)
                .status(votingDataDTO.getStatus())
                .votersList(String.valueOf(getEmailsListOnly(votingDataDTO.getVotersList())))
                .candidatesList(String.valueOf(getEmailsListOnly(votingDataDTO.getCandidatesList())))
                .voteCode(votingDataDTO.getVoteCode())
                .build();
    }


    private static List getEmailsListOnly(List<String> votersList) {
        String[] temp;
        List<String> listParsed = new ArrayList<>();
        for (String u : votersList) {
            temp = u.split(",");
            listParsed.add(temp[1]);
        }
        return listParsed;

    }


    public static List<String> parseToList(String listToParse) {
        String temp;
        List<String> listParsed;

        temp = listToParse.replace("[", "").replace("]", "").replace(" ","");
        listParsed = Arrays.asList(temp.trim().split(","));
        return listParsed;
    }
}
