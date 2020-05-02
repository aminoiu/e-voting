package com.electronicvoting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voting_data",schema = "voting_data")
@Builder
public class VotingData {

    @Id
    @Column(name = "voting_id")
    private String votingId;
    @Column(name="voting_name",nullable = false)
    private String votingTitle; //votingTitle=votingId (the same string)
    @Column(name = "admin_id",nullable = false)
    private String adminId;
    @Column(name = "voters_number",nullable = false)
    private Integer votersNumber;
    @Column(name = "candidates_number",nullable = false)
    private Integer candidatesNumber;
    @Column(name = "votes_number")
    private Integer votesNumber;
    @Column(name = "voting_winner")
    private String votingWinner;
}
