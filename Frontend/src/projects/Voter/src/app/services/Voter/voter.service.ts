import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IsTempDto} from '../../../../../Candidate/src/app/model/isTemp.dto';
import {PassModel} from '../../../../../Candidate/src/app/model/Password/pass.model';
import {ProfileDto} from "../../../../../Candidate/src/app/model/Profile/profile.dto";
import {CastedVoteDto} from "../../model/CastedVote/casted-vote.dto";

@Injectable({
  providedIn: 'root'
})
export class VoterService {

  constructor(private http: HttpClient) {
  }

  isTempPass(email: string) {
    return this.http.get<IsTempDto>('http://localhost:8080/evoting/voter/temporal-password/' + email);
  }

  updatePassword(email: string, newPass: PassModel) {
    return this.http.put<any>('http://localhost:8080/evoting/voter/update-password/' + email, newPass);
  }

  getCandidatesProfileByVotingCode(votingCode: string) {
    return this.http.get<Array<ProfileDto>>('http://localhost:8080/evoting/voter/voting-candidates/' + votingCode);
  }

  castVote(submittedVote: CastedVoteDto) {
    return this.http.post<any>('http://localhost:8080/evoting/voter/cast-vote', submittedVote);
  }
}
