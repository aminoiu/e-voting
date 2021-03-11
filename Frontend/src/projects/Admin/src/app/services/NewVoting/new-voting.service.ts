import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NewVotingDto} from '../../model/NewVoting/new-voting.dto';

@Injectable({
  providedIn: 'root'
})
export class NewVotingService {

  constructor(private http: HttpClient) { }

  startNewVoting(newVotingDto: NewVotingDto){
    return this.http.post<any>('http://localhost:8080/evoting/admin/create-voting-session', newVotingDto);
  }
}
