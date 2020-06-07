import {Time} from "@angular/common";
import {DynamicGrid} from "../DynamicGrid/dynamic-grid.model";

export interface NewVotingDto {
  title:string;
  voting_type:string;
  categories:string;
  voting_start_date:Date;
  voting_start_time:Time;
  voting_end_date:Date;
  voting_end_time:Time;
  voters:Array<DynamicGrid>;
  candidates:Array<DynamicGrid>;
}
