package com.electronicvoting.service.votingdata;

import com.electronicvoting.dto.VotingDataDTO;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.repository.VotingDataRepository;
import com.electronicvoting.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class VotingDataServiceImpl implements VotingDataService {
    VotingDataRepository votingDataRepository;
    AdminService adminService;

    VotingDataServiceImpl(VotingDataRepository votingDataRepository) {
        this.votingDataRepository = votingDataRepository;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public VotingData findByVotingTitle(String votingTitle) {
        return votingDataRepository.findByVotingTitle(votingTitle);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveVotingSession(VotingDataDTO votingDataDTO) {
        VotingData votingData = new VotingData();
        votingData.setVotingId(UUID.randomUUID().toString());
        votingData.setAdminId(adminService.findByEmail(votingDataDTO.getAdminEmail()).getAdminId());
        votingData.setVotersNumber(votingDataDTO.getVotersNumber());
        votingData.setCandidatesNumber(votingDataDTO.getCandidatesNumber());
        votingData.setVotingTitle(votingDataDTO.getVotingTitle());
        votingDataRepository.save(votingData);
    }
}
