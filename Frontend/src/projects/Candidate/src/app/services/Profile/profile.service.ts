import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {VotingDataDto} from '../../model/VotingData/voting-data.dto';
import {ProfileDto} from '../../model/Profile/profile.dto';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }

  getVotingData(email: string){
    return this.http.get<Array<VotingDataDto>>('http://localhost:8080/evoting/candidates/voting-data-history/' + email);
  }
  updateProfile(email: string, profileDto: ProfileDto){
    return this.http.put<any>('http://localhost:8080/evoting/candidates/update-profile/' + email, profileDto );
  }
  getProfile(email: string){
    return this.http.get<ProfileDto>('http://localhost:8080/evoting/candidates/profile/' + email);
  }
}
