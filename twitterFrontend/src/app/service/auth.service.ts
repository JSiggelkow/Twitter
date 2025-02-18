import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, map, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private readonly http: HttpClient) { }

  login(username: string, password: string) {
    return this.http.post('http://localhost:8080/api/auth/login', {username, password}, {withCredentials: true});
  }

  check() {
    return this.http.get('http://localhost:8080/api/auth', {withCredentials: true}).pipe(
      map(() => true),
      catchError(() => of(false))
    );
  }
}
