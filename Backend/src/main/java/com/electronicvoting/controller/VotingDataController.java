package com.electronicvoting.controller;

import com.electronicvoting.domain.dto.VotingDataDTO;
import com.electronicvoting.domain.dto.VotingDataForMobileDTO;
import com.electronicvoting.entity.Admin;
import com.electronicvoting.entity.Chains;
import com.electronicvoting.entity.VotingData;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.helper.SendMailSMTP;
import com.electronicvoting.repository.AdminRepository;
import com.electronicvoting.service.blockchain.ChainsService;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.voter.VoterService;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/evoting/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VotingDataController {
    private final VotingDataService votingDataService;
    private final SendMailSMTP sendMailSMTP;
    private final CandidateService candidateService;
    private final VoterService voterService;
    private final AdminRepository adminRepository;
    private final ChainsService chainsService;

    @PostMapping(path = "/create-voting-session", consumes = "application/json")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VotingDataDTO> saveVoteData(@RequestBody VotingDataDTO newVotingDTO) throws UserNotFoundException {
        List<String> votersList=newVotingDTO.getVotersList();
        List<String> candidatesList=newVotingDTO.getCandidatesList();

        VotingData savedVotingData=votingDataService.saveVotingSession(VotingDataDTO.dtoToEntity(newVotingDTO));

        Map<String,String> candPassTemp=candidateService.createCandidatesAccounts(candidatesList);
        Map<String,String> voterPassTemp=voterService.createVotersAccounts(votersList);

        for (String candidate:candidatesList){
            Optional<Admin> admin=adminRepository.findById(savedVotingData.getAdminId());
            List<String> adminEmail = new ArrayList<>();
            admin.ifPresent(admin1 -> {
                adminEmail.add(admin1.getEmail());
            });
            String candidateEmail=candidate.split(",")[1];
            sendMailSMTP.sendEmailToCandidateStart(candidateEmail,savedVotingData.getVotingTitle(),savedVotingData.getStartDate(),savedVotingData.getEndDate(),adminEmail.get(0),candPassTemp.get(candidateEmail));
        }
        for (String voter:votersList){
            Optional<Admin> admin=adminRepository.findById(savedVotingData.getAdminId());
            List<String> adminEmail = new ArrayList<>();
            admin.ifPresent(admin1 -> {
                adminEmail.add(admin1.getEmail());
            });
            String voterEmail=voter.split(",")[1];
            sendMailSMTP.sendEmailToVoterStart(voterEmail,savedVotingData.getVotingTitle(),savedVotingData.getVoteCode(),savedVotingData.getStartDate(),savedVotingData.getEndDate(),adminEmail.get(0),voterPassTemp.get(voterEmail));
        }

        Chains chain=new Chains();
        chain.setChainId(newVotingDTO.getVotingTitle());
        chain.setNumberBlocks(0);
        chain.setVotingTitle(newVotingDTO.getVotingTitle());
        chain.setAdminEmail(adminRepository.findByEmail(newVotingDTO.getAdminId()).get().getEmail());
        chain.setGendate(Instant.now());
        chainsService.createBlockchainForVotingSession(chain);

        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/voting-data-history/{email}", produces = "application/json")
    public ResponseEntity<List<VotingDataForMobileDTO>> getVoteData(@PathVariable String email) throws UserNotFoundException {
        List<VotingDataForMobileDTO> votingDataDTO = votingDataService.getVotingDataByEmail(email);
        return ResponseEntity.ok(votingDataDTO);
    }
}
