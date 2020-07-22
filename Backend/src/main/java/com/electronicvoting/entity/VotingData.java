package com.electronicvoting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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
    @Column(name ="start_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Timestamp startDate;
    @Column(name="end_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone = "Europe/Chisinau")
    private Timestamp endDate;
    @Column(name = "status")
    private String status;
    @Column(name="voters_list")
    private String votersList;
    @Column(name="candidates_list")
    private String candidatesList;
}
