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
@Table(name = "profile",schema = "voting_data")
@Builder
public class Profile {
    @Id
    @Column(name = "profile_id")
    private String profileId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "position")
    private String position;
    @Column(name = "education")
    private String education;
    @Column(name = "self_description")
    private String selfDescription;
}
