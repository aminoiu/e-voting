package com.electronicvoting.service.votingdata;

import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.repository.VotingDataRepository;
import com.electronicvoting.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class VotingDataServiceImpl implements VotingDataService {
    private final VotingDataRepository votingDataRepository;
//    private final AdminService adminService;

    @Override
    @Transactional(readOnly = true)
    public VotingData findByVotingTitle(String votingTitle) {
        return votingDataRepository.findByVotingTitle(votingTitle);
    }

    @Override
    @Transactional
    public void saveVotingSession(VotingData votingData) {
        votingData.setVotingId(votingData.getVotingTitle());
        votingDataRepository.save(votingData);
    }

    @Override
    public String findTitleById(String id) {
        VotingData votingData=votingDataRepository.findByVotingId(id).orElseThrow(() ->
                new RuntimeException("Error: Voting data not found."));
        return votingData.getVotingTitle();
    }
}
