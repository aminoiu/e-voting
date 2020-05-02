package com.electronicvoting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "casted_vote",schema = "voting_data")
@Builder
public class CastedVote {
    @Id
    @Column(name ="vote_id")
    private String voteId;
    @Column(name ="voting_id",nullable = false)
    private String votingId;
    @Column(name ="voter_email",nullable = false)
    private String voterEmail;
    @Column(name = "timestamp",nullable = false)
    private Instant timestamp;
    @Column(name ="device_ip",nullable = false)
    private String deviceIp;
    @Column(name ="candidate_email",nullable = false)
    private String candidateEmail;

}
