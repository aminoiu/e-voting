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
    @Column(name = "timestamp",nullable = false)
    private Instant timestamp;
    @Column(name ="casted_vote",nullable = false)
    private String castedVote;
    @Column(name ="vote_type",nullable = false)
    private String voteType;
}
