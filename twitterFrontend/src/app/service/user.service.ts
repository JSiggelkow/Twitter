import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {UserModel} from '../model/user-model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  http = inject(HttpClient);

  private readonly userSubject = new BehaviorSubject<UserModel | null>(null);
  user$ = this.userSubject.asObservable();

  checkUsernameExists(username: string): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8080/api/user/exists/username?username=${encodeURIComponent(username)}`);
  }

  checkEmailExists(email: string): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8080/api/user/exists/email?email=${encodeURIComponent(email)}`);
  }

  fetchUserInfo(){
    this.http.get<UserModel>('http://localhost:8080/api/user/info', {withCredentials: true}).subscribe({
      next: (userModel: UserModel) => {
        this.setUser(userModel);
      },
      error: () => {
        this.clearUserData()
      }
    })
  }

  setUser(userModel: UserModel){
    this.userSubject.next(userModel);
  }

  clearUserData(){
    this.userSubject.next(null);
  }

}
