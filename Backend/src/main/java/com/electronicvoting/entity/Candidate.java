package com.electronicvoting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@Entity
@Table(name = "candidate",schema = "voting_data")
public class Candidate {
    @Id
    @Column(name = "candidate_id")
    private String candidateId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "profile_id", nullable = false)
    private String profileId;
    @Email
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "cand_type_id", nullable = false)
    private String candTypeId;
    @Column(name = "voting_id", nullable = false)
    private String votingId;
    @Column(name = "user_id")
    private long user_id;

}
