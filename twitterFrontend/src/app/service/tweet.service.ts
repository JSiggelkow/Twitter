import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TweetModel} from '../model/tweet-model';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  http = inject(HttpClient);


  post(text: string) {
    return this.http.post<TweetModel>('http://localhost:8080/api/tweet', {text}, {withCredentials: true});
  }

  newest(limit: number) {
    return this.http.get<TweetModel[]>(`http://localhost:8080/api/tweet/newest?limit=${limit}`, {withCredentials: true});
  }

  before(createdAd: string) {
    return this.http.get<TweetModel[]>(`http://localhost:8080/api/tweet/before?createdAt=${createdAd}`, {withCredentials: true});
  }
}

