package com.electronicvoting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions",schema = "blockchain")
@Builder
public class BlockchainTransaction {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "hashBlock",nullable = false)
    private String hashBlock;
    @Column(name="version",nullable = false)
    private String version;
    @Column(name="timestamp",nullable = false)
    private Instant timestamp;
}
