package com.electronicvoting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
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
