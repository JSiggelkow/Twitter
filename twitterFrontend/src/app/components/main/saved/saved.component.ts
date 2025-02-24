import {Component, inject, OnInit} from '@angular/core';
import {RouteBackHeaderComponent} from '../../shared/route-back-header/route-back-header.component';
import {TweetService} from '../../../service/tweet.service';
import {TweetModel} from '../../../model/tweet-model';
import {TweetComponent} from '../../shared/tweet/tweet.component';

@Component({
  selector: 'app-saved',
  imports: [
    RouteBackHeaderComponent,
    TweetComponent
  ],
  templateUrl: './saved.component.html',
  styleUrl: './saved.component.scss'
})
export class SavedComponent implements OnInit{

  tweetService = inject(TweetService);

  savedPosts: TweetModel[] = [];
  loading: boolean = false;

  ngOnInit(): void {
    this.loadSaved();
  }

  loadSaved() {
    this.loading = true;
    this.tweetService.saved().subscribe({
      next: saved => {
        this.savedPosts = [...this.savedPosts, ...saved]
        this.loading = false;
      }
    })
  }

}
