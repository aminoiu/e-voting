
export interface BlockDto {
  chainId: string;
  hash: string;
  version: number;
  hashMerkleRoot: string;
  hashPrev: string;
  transactionList: Array<string>;
  }
