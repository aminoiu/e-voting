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
@Table(name = "vote_evidence",schema = "voting_data")
@Builder
public class VoteEvidence {
    @Id
    @Column(name ="id")
    private String id;
    @Column(name ="voting_id",nullable = false)
    private String votingId;
    @Column(name = "voter_email",nullable = false)
    private String voterEmail;
    @Column(name ="device_ip",nullable = false)
    private String deviceIp;

}
