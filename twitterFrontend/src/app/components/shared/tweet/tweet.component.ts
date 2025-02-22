import {Component, inject, Input} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {Textarea} from 'primeng/textarea';
import {FormsModule} from '@angular/forms';
import {TweetModel} from '../../../model/tweet-model';
import {TimeAgoComponent} from '../time-ago/time-ago.component';
import {TweetService} from '../../../service/tweet.service';
import {NgClass} from '@angular/common';


@Component({
  selector: 'app-tweet',
  imports: [
    Avatar,
    Textarea,
    FormsModule,
    TimeAgoComponent,
    NgClass
  ],
  templateUrl: './tweet.component.html',
  styleUrl: './tweet.component.scss',
  standalone: true,
})
export class TweetComponent {

  viewsCount = 12345;

  @Input() tweet!: TweetModel;

  tweetService = inject(TweetService);

  onLike() {
    if (this.tweet.isLiked) {
      this.unlike();
    } else {
      this.like();
    }
  }

  like() {
    this.tweetService.toggleLike(this.tweet.id).subscribe({
      next: () => {
        this.tweet.isLiked = true;
        this.tweet.countLikes = 1 + this.tweet.countLikes;
      },
      error: () =>{

      }
    })
  }

  unlike() {
    this.tweetService.toggleLike(this.tweet.id).subscribe({
      next: () => {
        this.tweet.isLiked = false;
        this.tweet.countLikes = -1 + this.tweet.countLikes;
      },
      error: () =>{}
    })
  }

}
