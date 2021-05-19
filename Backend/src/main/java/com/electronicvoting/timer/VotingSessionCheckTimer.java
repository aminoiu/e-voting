package com.electronicvoting.timer;


import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.domain.enums.Statuses;
import com.electronicvoting.entity.Admin;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.helper.SendMailSMTP;
import com.electronicvoting.repository.AdminRepository;
import com.electronicvoting.repository.CastedVoteRepository;
import com.electronicvoting.repository.VotingDataRepository;
import com.electronicvoting.service.votingdata.VotingDataService;
import com.electronicvoting.service.votingdata.VotingDataServiceImpl;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.sql.Timestamp;
import java.util.*;

@Named
@RequiredArgsConstructor
public class VotingSessionCheckTimer extends TimerTask {
    private final VotingDataService votingDataService;
    private final SendMailSMTP sendMailSMTP;
    private final AdminRepository adminRepository;

    private final CastedVoteRepository castedVoteRepository;


    @Override
    public void run() {
        startInitializedSessions();
        finishSessionWithSpecificStatus();
    }

    private void startInitializedSessions() {
        List<VotingData> initialisedVotingData = votingDataService.getVotingDataByStatus(Statuses.INITIALISED_STATUS);
        initialisedVotingData.forEach(votingData -> {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            int compareTimestamps = currentTimestamp.compareTo(votingData.getStartDate());
            if (compareTimestamps == 0 || compareTimestamps > 0) {
                votingData.setStatus(Statuses.IN_PROGRESS_STATUS);
                votingDataService.update(votingData);
            }

        });
    }

    private void finishSessionWithSpecificStatus() {
        List<VotingData> inProgressVotingData = votingDataService.getVotingDataByStatus(Statuses.IN_PROGRESS_STATUS);

        inProgressVotingData.forEach(votingData -> {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            int compareTimestamps = currentTimestamp.compareTo(votingData.getEndDate());
            if (compareTimestamps == 0 || compareTimestamps > 0 || votingData.getVotesNumber().equals(votingData.getVotersNumber())) {
                votingData.setStatus(Statuses.FINISHED_STATUS);
                votingData = calculateVoteSessionWinner(votingData);
                sendEmails(votingData);
                votingDataService.update(votingData);

            }
        });
    }

    private void sendEmails(VotingData votingData) {
        List<String> candidatesList = VotingDataDTO.parseToList(votingData.getCandidatesList());

        List<String> votersList = VotingDataDTO.parseToList(votingData.getVotersList());

        Optional<Admin> admin = adminRepository.findById(votingData.getAdminId());
        List<String> adminEmail = new ArrayList<>();
        admin.ifPresent(admin1 -> {
            adminEmail.add(admin1.getEmail());
        });
        Long score = castedVoteRepository.countByCastedVote(votingData.getVotingWinner());
        sendMailSMTP.sendEmailToTheWinner(votingData.getVotingWinner(), votingData.getVotingTitle(), votingData.getStartDate(), votingData.getEndDate(), adminEmail.get(0), score, votingData.getVotersNumber());

        candidatesList.forEach(candidate -> {
            if (!candidate.equals(votingData.getVotingWinner())) {
                sendMailSMTP.sendEmailToAllCandidates(candidate, votingData.getVotingTitle(), votingData.getStartDate(), votingData.getEndDate(), adminEmail.get(0), score, votingData.getVotersNumber(), votingData.getVotingWinner());
            }
        });

        votersList.forEach(voter -> {
            sendMailSMTP.sendEmailToAllVoters(voter, votingData.getVotingTitle(), votingData.getStartDate(), votingData.getEndDate(), adminEmail.get(0), score, votingData.getVotersNumber(), votingData.getVotingWinner());

        });

    }

    private VotingData calculateVoteSessionWinner(VotingData votingData) {
        Map<String, Long> score = new HashMap<>();
        List<String> candidatesList = VotingDataDTO.parseToList(votingData.getCandidatesList());
        candidatesList.forEach(candidateEmail -> {
            score.put(candidateEmail, castedVoteRepository.countByCastedVote(candidateEmail));
        });
        String winner = findWithHighestScore(score);
        if (winner != null) {
            votingData.setVotingWinner(winner.replace(" ", ""));
        }
        return votingData;

    }

    private String findWithHighestScore(Map<String, Long> score) {
        Long maxValueInMap = (Collections.max(score.values()));
        for (Map.Entry<String, Long> entry : score.entrySet()) {
            if (entry.getValue().equals(maxValueInMap)) {
                return entry.getKey();
            }
        }
        return null;
    }


}
