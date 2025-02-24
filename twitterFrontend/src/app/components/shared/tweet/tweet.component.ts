import {Component, inject, Input} from '@angular/core';
import {TweetModel} from '../../../model/tweet-model';
import {Avatar} from 'primeng/avatar';
import {TimeAgoComponent} from '../time-ago/time-ago.component';
import {Textarea} from 'primeng/textarea';
import {QuoteComponent} from '../quote/quote.component';
import {SocialInteractionsComponent} from "./social-interactions/social-interactions.component";
import {SocialInteractionsModel} from "../../../model/social-interactions-model";
import {RetweetLabelComponent} from "./retweet-label/retweet-label.component";
import {Router} from '@angular/router';

@Component({
  selector: 'app-tweet',
  imports: [
    Avatar,
    TimeAgoComponent,
    Textarea,
    QuoteComponent,
    SocialInteractionsComponent,
    RetweetLabelComponent
  ],
  templateUrl: './tweet.component.html',
  styleUrl: './tweet.component.scss'
})
export class TweetComponent {

  router = inject(Router);

  @Input() tweet: TweetModel = {
    id: "",
    text: "",
    video: "",
    image: "",
    username: "",
    retweetedByUsername: "",
    quoteDTO: null,
    createdAt: "",
    countLikes: "",
    countRetweets: "",
    countComments: "",
    isLiked: false,
    isRetweeted: false,
    isSaved: false,
  };

  // gets the required attributes for the socialInteractions component
  get socialInteractions(): SocialInteractionsModel {
    return {
      tweetId: this.tweet.id,
      quote: {
        postId: this.tweet.id,
        text: this.tweet.text,
        createdAt: this.tweet.createdAt,
        username: this.tweet.username,
      },
      viewsCount: 21, //hardcoded
      countComments: +this.tweet.countComments,
      countLikes: +this.tweet.countLikes,
      countRetweets: +this.tweet.countRetweets,
      isLiked: this.tweet.isLiked,
      isRetweeted: this.tweet.isRetweeted,
      isSaved: this.tweet.isSaved
    }
  }

  //updates the tweet on social interactions change so the input object gets updated --> update gets triggered if social interactions component emits a change
  onSocialInteractionsChange(updated: SocialInteractionsModel) {
    this.tweet = {
      ...this.tweet,
      isLiked: updated.isLiked,
      isRetweeted: updated.isRetweeted,
      isSaved: updated.isSaved,
      countLikes: updated.countLikes.toString(),
      countRetweets: updated.countRetweets.toString(),
      countComments: updated.countComments.toString(),
    };

  }

  navigateToTweet(id: string) {
    console.log(id);
    this.router.navigate(['/status', id]).then();
  }

  navigateToQuote(event: Event, id: string) {
    event.stopPropagation();
    this.navigateToTweet(id);
  }

}
