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
    @Column (name = "hashPrev",nullable = false)
    private String hashPrev;
    @Column(name = "hashMerkelRoot",nullable = false)
    private String hashMerkelRoot;
}
