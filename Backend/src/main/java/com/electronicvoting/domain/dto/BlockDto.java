package com.electronicvoting.domain.dto;

import com.electronicvoting.entity.DataBlock;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class BlockDto {
    String chainId;
    String hash;
    Integer version;
    String hashMerkleRoot;
    String hashPrev;
    List<String> transactionList;


    public static BlockDto toDto(@NotNull DataBlock dataBlock) {
        String[] transactions =dataBlock.getTransactionList().split(",");
        List<String> transactionsList;
        transactionsList = Arrays.asList(transactions);
        return BlockDto.builder()
                .chainId(dataBlock.getChainId())
                .hash(dataBlock.getHash())
                .version(dataBlock.getVersion())
                .hashMerkleRoot(dataBlock.getHashMerkelroot())
                .hashPrev(dataBlock.getHashPrev())
                .transactionList(transactionsList)
                .build();

    }
}
