package com.electronicvoting.service.votingdata;

import com.electronicvoting.domain.dto.VotingDataForMobileDTO;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.helper.RandomString;
import com.electronicvoting.repository.VotingDataRepository;
import com.electronicvoting.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class VotingDataServiceImpl implements VotingDataService {
    private final VotingDataRepository votingDataRepository;
    private final AdminService adminService;


    @Override
    @Transactional(readOnly = true)
    public VotingData findByVotingTitle(String votingTitle) {
        return votingDataRepository.findByVotingTitle(votingTitle);
    }

    @Override
    @Transactional
    public VotingData saveVotingSession(VotingData votingData) throws UserNotFoundException {

        String adminUUID = adminService.findByEmail(votingData.getAdminId()).getAdminId();
        votingData.setAdminId(adminUUID);
        votingData.setVotingId(votingData.getVotingTitle());
        votingData.setVotingWinner(null);
        votingData.setVoteCode(getVoteCode());

        return votingDataRepository.save(votingData);
    }

    @Override
    @Transactional
    public VotingData update(VotingData votingData) {
        return votingDataRepository.save(votingData);
    }

    private String getVoteCode() {
        String voteCodeRandom = null;
        boolean existsCode = true;
        while (existsCode) {
            voteCodeRandom = RandomString.getAlphaNumericString(6);
            if (votingDataRepository.countByVoteCode(voteCodeRandom) == 0) existsCode = false;
        }
        return voteCodeRandom;
    }

    @Override
    public String findTitleById(String id) {
        VotingData votingData = votingDataRepository.findByVotingId(id).orElseThrow(() ->
                new RuntimeException("Error: Voting data not found."));
        return votingData.getVotingTitle();
    }

    @Override
    public List<VotingDataForMobileDTO> getVotingDataByEmail(String email) throws UserNotFoundException {
        String id = adminService.findByEmail(email).getAdminId();
        List<VotingDataForMobileDTO> votingDataDTOS = new ArrayList<>();
        votingDataRepository.findByAdminId(id).forEach(votingData -> {

            adminService.findById(id).ifPresent(admin -> votingData.setAdminId(admin.getEmail()));
            votingDataDTOS.add(VotingDataForMobileDTO.toDto(votingData));
        });
        return votingDataDTOS;
    }

    @Override
    public List<VotingDataForMobileDTO> getVotingDataByCandidateEmail(String email) {
        List<VotingDataForMobileDTO> votingDataForMobileDTOS = new ArrayList<>();
        votingDataRepository.findByCandidateId(email).forEach(votingData -> {

            adminService.findById(votingData.getAdminId()).ifPresent(admin -> votingData.setAdminId(admin.getEmail()));
            votingDataForMobileDTOS.add(VotingDataForMobileDTO.toDto(votingData));
        });
        return votingDataForMobileDTOS;
    }

    @Override
    public List<VotingDataForMobileDTO> getVotingDataByVoterEmail(String email) {
        List<VotingDataForMobileDTO> votingDataForMobileDTOS = new ArrayList<>();
        votingDataRepository.findByVoterId(email).forEach(votingData -> {

            adminService.findById(votingData.getAdminId()).ifPresent(admin -> votingData.setAdminId(admin.getEmail()));
            votingDataForMobileDTOS.add(VotingDataForMobileDTO.toDto(votingData));
        });
        return votingDataForMobileDTOS;
    }

    @Override
    public VotingData getVotingDataByVotingCode(String votingCode) {
        return votingDataRepository.findByVoteCode(votingCode);
    }

    @Override
    public List<VotingData> getVotingDataByStatus(String status) {
        return votingDataRepository.findByStatus(status);
    }
}
