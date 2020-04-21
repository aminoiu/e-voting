package com.electronicvoting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "casted_vote")
public class CastedVote {
    @Id
    @Column(name ="vote_id")
    private String voteId;
    @Column(name ="voting_id",nullable = false)
    private String votingId;
    @Column(name ="voter_id",nullable = false)
    private String voterId;
    @Column(name = "timestamp",nullable = false)
    private Instant timestamp;
    @Column(name ="device_ip",nullable = false)
    private String deviceIp;

}
