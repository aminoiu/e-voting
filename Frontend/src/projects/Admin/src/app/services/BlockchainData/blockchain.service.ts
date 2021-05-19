import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BlockDto} from "../../model/Blockchain/blockchain.dto";

@Injectable({
  providedIn: 'root'
})
export class BlockchainService {

  constructor(private http: HttpClient) { }

  getBlockchainContent(chainId: string){
    return this.http.get<Array<BlockDto>>('http://localhost:8080/evoting/admin/blockchain/' + chainId);
  }
}
