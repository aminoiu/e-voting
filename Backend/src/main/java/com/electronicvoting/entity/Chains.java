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
@Table(name = "chains",schema = "blockchain")
@Builder
public class Chains {
    @Id
    @Column(name = "chain_id")
    private String chainId;
    @Column(name = "voting_title",nullable = false)
    private String votingTitle;
    @Column(name = "number_blocks",nullable = false)
    private Integer numberBlocks;
    @Column(name = "admin_email",nullable = false)
    private String adminEmail;
    @Column(name = "gendate",nullable = false)
    private Instant gendate;
}
