import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  http = inject(HttpClient);

  checkUsernameExists(username: string): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8080/api/user/exists/username?username=${encodeURIComponent(username)}`);
  }

  checkEmailExists(email: string): Observable<boolean> {
      return this.http.get<boolean>(`http://localhost:8080/api/user/exists/email?email=${encodeURIComponent(email)}`);
    }

}
