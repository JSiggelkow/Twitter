import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  //service so other components can refresh the feed --> feed has subscribed an refreshes on update

  private readonly refreshFeedSubject = new BehaviorSubject<void>(undefined);
  refreshFeed$ = this.refreshFeedSubject.asObservable();

  refreshFeed(){
    this.refreshFeedSubject.next();
  }
}
