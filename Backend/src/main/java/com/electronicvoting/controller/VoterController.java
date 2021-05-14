package com.electronicvoting.controller;

import com.electronicvoting.blockchain.Block;
import com.electronicvoting.blockchain.Transaction;
import com.electronicvoting.blockchain.Tx;
import com.electronicvoting.domain.dto.*;
import com.electronicvoting.entity.*;
import com.electronicvoting.exceptions.UserNotFoundException;
import com.electronicvoting.repository.VotingDataRepository;
import com.electronicvoting.service.auth.AuthService;
import com.electronicvoting.service.blockchain.BlockchainTransactionService;
import com.electronicvoting.service.blockchain.ChainsService;
import com.electronicvoting.service.blockchain.DataBlockService;
import com.electronicvoting.service.candidate.CandidateService;
import com.electronicvoting.service.castedvotes.CastedVotesService;
import com.electronicvoting.service.castedvotes.VoteEvidenceService;
import com.electronicvoting.service.profile.ProfileService;
import com.electronicvoting.service.voter.VoterService;
import com.electronicvoting.service.votingdata.VotingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping(value = "/evoting/voter")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VoterController {
    private final VoterService voterService;
    private AuthService authService;
    private final VotingDataService votingDataService;
    private final CandidateService candidateService;
    private final ProfileService profileService;
    private final VotingDataRepository votingDataRepository;
    private final CastedVotesService castedVotesService;
    private final DataBlockService dataBlockService;
    private final ChainsService chainsService;
    private final BlockchainTransactionService blockchainTransactionService;
    private final VoteEvidenceService voteEvidenceService;

    @Value("${blockchain.block.maximum.transactions}")
    private int blockMaxTransactions;


    @GetMapping(value = "/{email}", produces = "application/json")
    public ResponseEntity<Voter> getByEmail(@PathVariable String email) {
        Voter voter = this.voterService.findByEmail(email);
        if (voter != null) {

            return ResponseEntity.ok(voter);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/register", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageDTO> createVoter(@RequestBody VoterDto voterDto) {
        SignUpDTO signUpDTO = SignUpDTO.voterDtoToSignUpDto(voterDto);

        ResponseEntity<MessageDTO> responseEntity = authService.registerUser(signUpDTO);
        if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
            this.voterService.saveUserVoter(VoterDto.dtoToEntity(voterDto));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return responseEntity;
    }

    @GetMapping(value = "/temporal-password/{email}", produces = "application/json")
    public ResponseEntity<IsTempDto> getIsTemporalPasswordByEmail(@PathVariable String email) {
        Voter voter = this.voterService.findByEmail(email);
        if (voter != null) {
            IsTempDto isTempDto = new IsTempDto();
            isTempDto.setTemp(voter.isTemporarPassword());
            return ResponseEntity.ok(isTempDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/update-password/{email}", consumes = "application/json")
    public ResponseEntity updatePassword(@PathVariable String email, @RequestBody PassDTO passDTO) {
        return ResponseEntity.ok(this.voterService.updatePass(email, passDTO));
    }

    @GetMapping(path = "/voting-data-history/{email}", produces = "application/json")
    public ResponseEntity<List<VotingDataForMobileDTO>> getVoteData(@PathVariable String email) throws UserNotFoundException {
        List<VotingDataForMobileDTO> votingDataDTO = votingDataService.getVotingDataByVoterEmail(email);
        return ResponseEntity.ok(votingDataDTO);
    }

    @PutMapping(path = "/voting-candidates/{votingCode}", produces = "application/json")
    public ResponseEntity<Object> getCandidatesProfileByVotingCode(@PathVariable String votingCode, @RequestBody String voterEmail) throws UserNotFoundException {
        if (!castedVotesService.hasVoterAlreadySubmittedVote(votingCode, voterEmail)) {
            VotingData votingData = votingDataService.getVotingDataByVotingCode(votingCode);
            List<ProfileDTO> candidatesProfiles = new ArrayList<>();
            List<String> candidatesList = VotingDataDTO.parseToList(votingData.getCandidatesList());
            candidatesList.forEach(s -> {
                String t = s.trim();
                Candidate c = candidateService.findProfileIdByEmail(t);
                Profile profile = profileService.findById(c.getProfileId());
                candidatesProfiles.add(ProfileDTO.toDto(profile));
            });
            return ResponseEntity.ok(candidatesProfiles);
        } else {
            return ResponseEntity.badRequest().body("Vote already submitted");
        }
    }

    @PostMapping(path = "/cast-vote", consumes = "application/json")
    public ResponseEntity castVote(@RequestBody SavedVoteDTO savedVoteDTO) throws UnknownHostException {
        VotingData votingData = votingDataService.getVotingDataByVotingCode(savedVoteDTO.getVotingCode());
        int votesNr = votingData.getVotesNumber();
        votingData.setVotesNumber(votesNr + 1);
        votingDataRepository.save(votingData);
        CastedVoteDTO castedVoteDTO = new CastedVoteDTO("Candidate", Instant.now(), votingData.getVotingTitle(), savedVoteDTO.getCastedVote());


        Map<String, String> castedVoteMap = computeCastedVoteMap(savedVoteDTO, votingData);


        BlockchainTransaction blockchainTransaction = new BlockchainTransaction();
        blockchainTransaction.setTimestamp(Instant.now());
        blockchainTransaction.setVersion(0);
        blockchainTransaction.setTransactionId(UUID.randomUUID().toString());
        blockchainTransaction.setTxHash(chainsService.computeTransactionHas(castedVoteMap));
        blockchainTransactionService.saveNewTransaction(blockchainTransaction);

        Integer nrOfBlocksInChain = chainsService.getNumberOfBlocksInChain(votingData.getVotingTitle());

        if (nrOfBlocksInChain == 0) {
            List<String> transactionsList = new ArrayList<>();
            transactionsList.add(blockchainTransaction.getTransactionId());

            Transaction tempTransaction = new Transaction(castedVoteMap);
            Block<Tx> bl = new Block();
            bl.add(tempTransaction);

            DataBlock block = new DataBlock();
            block.setBlockId(UUID.randomUUID().toString());
            block.setChainId(votingData.getVotingTitle());
            block.setHashMerkelroot(bl.getMerkleRoot());
            block.setHash(bl.getHash());
            block.setHashPrev("root");
            block.setVersion(0);
            block.setOrderNr(1);
            block.setTransactionList(String.join(",", transactionsList));
            dataBlockService.saveBlock(block);

        } else if (nrOfBlocksInChain < blockMaxTransactions) {
            DataBlock lastBlock = dataBlockService.getLastBlock(votingData.getVotingTitle(), nrOfBlocksInChain);
            List<String> transactionsList = new ArrayList<>(Arrays.asList(lastBlock.getTransactionList().split(",")));
            transactionsList.add(blockchainTransaction.getTransactionId());

            Transaction tempTransaction = new Transaction(castedVoteMap);
            Block<Tx> bl = new Block();
            bl.add(tempTransaction);

            lastBlock.setHashMerkelroot(bl.getMerkleRoot());
            lastBlock.setHash(bl.getHash());
            lastBlock.setTransactionList(String.join(",", transactionsList));
            lastBlock.setVersion(lastBlock.getVersion() + 1);
            dataBlockService.saveBlock(lastBlock);
        } else if (nrOfBlocksInChain == blockMaxTransactions) {
            DataBlock lastBlock = dataBlockService.getLastBlock(votingData.getVotingTitle(), nrOfBlocksInChain);
            String prevBlockHash = lastBlock.getHash();

            List<String> transactionsList = new ArrayList<>();
            transactionsList.add(blockchainTransaction.getTransactionId());

            Transaction tempTransaction = new Transaction(castedVoteMap);
            Block<Tx> bl = new Block();
            bl.add(tempTransaction);

            DataBlock block = new DataBlock();
            block.setBlockId(UUID.randomUUID().toString());
            block.setChainId(votingData.getVotingTitle());
            block.setHashMerkelroot(bl.getMerkleRoot());
            block.setHash(bl.getHash());
            block.setHashPrev(prevBlockHash);
            block.setVersion(0);
            block.setOrderNr(lastBlock.getOrderNr() + 1);
            block.setTransactionList(String.join(",", transactionsList));
            dataBlockService.saveBlock(block);
        }
VoteEvidence voteEvidence= new VoteEvidence();
        voteEvidence.setId(UUID.randomUUID().toString());
        voteEvidence.setVoterEmail(savedVoteDTO.getVoterEmail());
        voteEvidence.setVotingId(votingData.getVotingTitle());
        voteEvidence.setDeviceIp(InetAddress.getLocalHost().toString());
voteEvidenceService.saveEvidence(voteEvidence);
        return ResponseEntity.ok(this.castedVotesService.saveVote(CastedVoteDTO.dtoToEntity(castedVoteDTO)));
    }


    private Map<String, String> computeCastedVoteMap(SavedVoteDTO savedVoteDTO, VotingData votingData) throws UnknownHostException {
        Map<String, String> castedVoteMap = new HashMap<>();
        castedVoteMap.put("VoterEmail", savedVoteDTO.getVoterEmail());
        castedVoteMap.put("CastedVote", savedVoteDTO.getCastedVote());
        castedVoteMap.put("VotingTitle", votingData.getVotingTitle());
        castedVoteMap.put("Timestamp", String.valueOf(Instant.now()));
        castedVoteMap.put("VoterIp", InetAddress.getLocalHost().toString());
        return castedVoteMap;
    }
}
