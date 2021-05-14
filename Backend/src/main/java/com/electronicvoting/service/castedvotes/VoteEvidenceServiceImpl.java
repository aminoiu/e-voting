package com.electronicvoting.service.castedvotes;

import com.electronicvoting.entity.VoteEvidence;
import com.electronicvoting.repository.VoteEvidenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteEvidenceServiceImpl implements VoteEvidenceService{
    private final VoteEvidenceRepository voteEvidenceRepository;
    @Override
    public void saveEvidence(VoteEvidence voteEvidence) {
        voteEvidenceRepository.save(voteEvidence);
    }
}
