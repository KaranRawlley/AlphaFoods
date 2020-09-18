import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
const Auth_api ='http://localhost:9902//signin';
const Authx_api='http://localhost:9902/api/signup';
const httpOptions ={
  headers: new HttpHeaders({ 'content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post(Auth_api + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
   }
   register(user): Observable<any> {
    return this.http.post(Authx_api + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password,
      mobile:user.mobile,
      confirmPassword: user.confirmPassword
    }, httpOptions);
  }
}
