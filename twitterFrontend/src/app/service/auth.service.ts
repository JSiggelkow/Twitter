import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, map, of} from 'rxjs';
import {LoginModel} from '../model/login-model';
import {SignupModel} from '../model/signup-model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private readonly http: HttpClient) { }

  login(loginModel: LoginModel) {
    return this.http.post('http://localhost:8080/api/auth/login', loginModel, {withCredentials: true});
  }

  signup(signupModel: SignupModel) {
    return this.http.post('http://localhost:8080/api/user/signup', signupModel, {withCredentials: true});
  }

  check() {
    return this.http.get('http://localhost:8080/api/auth', {withCredentials: true}).pipe(
      map(() => true),
      catchError(() => of(false))
    );
  }
}
