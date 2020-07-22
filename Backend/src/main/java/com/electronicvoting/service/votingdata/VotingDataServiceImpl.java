package com.electronicvoting.service.votingdata;

import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.domain.dto.VotingDataForMobileDTO;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.repository.AdminRepository;
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
    public void saveVotingSession(VotingData votingData) throws UserNotFoundException {

        String adminUUID = adminService.findByEmail(votingData.getAdminId()).getAdminId();
        votingData.setAdminId(adminUUID);
        votingData.setVotingId(votingData.getVotingTitle());
        votingDataRepository.save(votingData);
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
}
