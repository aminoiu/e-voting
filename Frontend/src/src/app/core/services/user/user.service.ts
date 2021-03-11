import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../../models/user.model';
import {AdminDto} from "../../models/register-dto.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly BASE_URL: string = 'http://localhost:8080'
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<User[]>('${config.apiUrl}/users');
  }

  register(admin: AdminDto) {
    return this.http.post(this.BASE_URL + '/evoting/admins/register', admin);
  }

  delete() {
    return this.http.delete(this.BASE_URL + '/users/${id}');
  }
}
