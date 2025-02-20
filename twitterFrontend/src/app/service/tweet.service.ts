import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  http = inject(HttpClient);


  post(text: string) {
    return this.http.post('http://localhost:8080/api/tweet', {text}, {withCredentials: true});
  }
}
