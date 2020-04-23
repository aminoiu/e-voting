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
@Table(name = "voter",schema = "voting_data")
public class Voter {
    @Id
    @Column(name = "voter_id")
    private String voterId;
    @Column(name = "name", nullable = false)
    private String name;
    @Email
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "voting_id", nullable = false)
    private String votingId;
    @Column(name = "user_id")
    private long user_id;

}
