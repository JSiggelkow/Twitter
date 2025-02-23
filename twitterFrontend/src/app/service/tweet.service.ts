import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TweetModel} from '../model/tweet-model';
import {CreateTweetModel} from '../model/create-tweet-model';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  http = inject(HttpClient);


  post(createTweet: CreateTweetModel) {
    return this.http.post<TweetModel>('http://localhost:8080/api/post', createTweet, {withCredentials: true});
  }

  newest(limit: number) {
    return this.http.get<TweetModel[]>(`http://localhost:8080/api/post/newest?limit=${limit}`, {withCredentials: true});
  }

  before(createdAd: string) {
    return this.http.get<TweetModel[]>(`http://localhost:8080/api/post/before?createdAt=${createdAd}`, {withCredentials: true});
  }

  toggleLike(tweetId: string) {
    return this.http.post(`http://localhost:8080/api/post/like?postId=${tweetId}`, {}, {withCredentials: true});
  }

  toggleRetweet(tweetId: string) {
    return this.http.post(`http://localhost:8080/api/post/retweet?postId=${tweetId}`, {}, {withCredentials: true});
  }
}

