import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {VotingDataDto} from '../../model/VotingData/voting-data.dto';
import {ProfileDto} from '../../model/Profile/profile.dto';
import {PassModel} from '../../model/Password/pass.model';
import {IsTempDto} from '../../model/isTemp.dto';

@Injectable({
  providedIn: 'root'
})
export class CandidateService {

  constructor(private http: HttpClient) { }

  isTempPass(email: string){
    return this.http.get<IsTempDto>('http://localhost:8080/evoting/candidates/temporal-password/' + email);
  }
  updatePassword(email: string, newPass: PassModel){
    return this.http.put<any>('http://localhost:8080/evoting/candidates/update-password/' + email,  newPass );
  }
  getProfile(email: string){
    return this.http.get<ProfileDto>('http://localhost:8080/evoting/candidates/profile/' + email);
  }
}
