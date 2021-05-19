import {Time} from '@angular/common';
import {DynamicGrid} from '../DynamicGrid/dynamic-grid.model';

// String votingTitle;
// Integer votersNumber;
// Integer votesNumber;
// String votingWinner;
// Integer candidatesNumber;
// String adminId;
// Timestamp startDate;
// Timestamp endDate;
// String status;
// List<String> votersList;
// List<String> candidatesList;
export interface NewVotingDto {
  votingTitle: string;
  votersNumber: number;
  votesNumber: number;
  votingWinner: string;
  candidatesNumber: number;
  adminId: string;
  startDate: string;
  endDate: string;
  startTime: string;
  endTime: string;
  voting_type: string;
  categories: string;
  status: string;
  votersList: Array<string>;
  candidatesList: Array<string>;
}
