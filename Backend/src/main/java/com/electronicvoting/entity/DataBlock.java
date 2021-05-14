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
@Table(name = "blocks",schema = "blockchain")
@Builder
public class DataBlock {
    @Id
    @Column(name="block_id")
    private String blockId;
    @Column(name="hash",nullable = false)
    private String hash;
    @Column(name="version",nullable = false)
    private Integer version;
    @Column (name = "hash_prev",nullable = false)
    private String hashPrev;
    @Column(name = "hash_merkelroot",nullable = false)
    private String hashMerkelroot;
    @Column(name="order_nr", nullable = false, columnDefinition = "integer default 0")
    private Integer orderNr;
    @Column(name="transactions_list")
    private String transactionList;
    @Column(name="chain_id")
    private String chainId;
}
