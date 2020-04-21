package com.electronicvoting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "voting_data")
public class VotingData {

    @Id
    @Column(name = "voting_id")
    private String votingId;
    @Column(name="voting_name",nullable = false)
    private String votingTitle;
    @Column(name = "admin_id",nullable = false)
    private String adminId;
    @Column(name = "voters_number",nullable = false)
    private Integer votersNumber;
    @Column(name = "candidates_number",nullable = false)
    private Integer candidatesNumber;
    @Column(name = "votes_number",nullable = false)
    private Integer votesNumber;
    @Column(name = "voting_winner",nullable = false)
    private String votingWinner;
}
