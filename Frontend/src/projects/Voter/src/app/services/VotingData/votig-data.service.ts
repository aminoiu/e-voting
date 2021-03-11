import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {VotingDataDto} from '../../model/VotingData/voting-data.dto';

@Injectable({
  providedIn: 'root'
})
export class VotigDataService {

  constructor(private http: HttpClient) { }

  getVotingData(email: string){
    return this.http.get<Array<VotingDataDto>>('http://localhost:8080/evoting/voter/voting-data-history/' + email);
  }

}
