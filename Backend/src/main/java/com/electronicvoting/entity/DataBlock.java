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
@Table(name = "blocks")
public class DataBlock {
    @Id
    @Column(name="block_id")
    private String blockId;
    @Column(name="hash",nullable = false)
    private String hash;
    @Column(name="version",nullable = false)
    private Integer version;
    @Column (name = "hashPrev",nullable = false)
    private String hashPrev;
    @Column(name = "hashMerkelRoot",nullable = false)
    private String hashMerkelRoot;
}
