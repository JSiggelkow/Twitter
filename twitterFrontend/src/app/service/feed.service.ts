import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  private readonly refreshFeedSubject = new BehaviorSubject<void>(undefined);
  refreshFeed$ = this.refreshFeedSubject.asObservable();

  refreshFeed(){
    this.refreshFeedSubject.next();
  }
}
