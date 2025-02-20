import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TweetModel} from '../model/tweet-model';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  http = inject(HttpClient);


  post(text: string) {
    return this.http.post('http://localhost:8080/api/tweet', {text}, {withCredentials: true});
  }

  getAll() {
    return this.http.get<TweetModel[]>('http://localhost:8080/api/tweet', {withCredentials: true});
  }
}

