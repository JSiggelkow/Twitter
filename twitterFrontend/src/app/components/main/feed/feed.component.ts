import {Component, inject, OnInit} from '@angular/core';
import {Tab, TabList, TabPanel, TabPanels, Tabs} from 'primeng/tabs';
import {PostComponent} from '../../shared/post/post.component';
import {Message} from 'primeng/message';
import {TweetComponent} from '../../shared/tweet/tweet.component';
import {TweetService} from '../../../service/tweet.service';
import {TweetModel} from '../../../model/tweet-model';

@Component({
  selector: 'app-feed',
  imports: [
    Tabs,
    TabList,
    Tab,
    TabPanels,
    TabPanel,
    PostComponent,
    Message,
    TweetComponent,
  ],
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.scss',
  standalone: true,
})
export class FeedComponent implements OnInit {

  tweetService = inject(TweetService);

  tweets: TweetModel[] = [];

  ngOnInit(): void {
    this.tweetService.getAll().subscribe({
      next: tweets => {
        this.tweets = tweets;
      },
      error: err => console.log(err)
    })
  }

}
