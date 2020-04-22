package com.electronicvoting.service.votingdata;

import com.electronicvoting.entity.VotingData;
import com.electronicvoting.repository.VotingDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class VotingDataServiceImpl implements VotingDataService{
    VotingDataRepository votingDataRepository;

    VotingDataServiceImpl(VotingDataRepository votingDataRepository){
        this.votingDataRepository=votingDataRepository;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public VotingData findByVotingTitle(String votingTitle) {
        return votingDataRepository.findByVotingTitle(votingTitle);
    }
}
