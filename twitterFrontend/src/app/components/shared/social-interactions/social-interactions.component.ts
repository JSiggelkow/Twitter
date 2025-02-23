import {Component, EventEmitter, inject, Input, Output, ViewChild} from '@angular/core';
import {SocialInteractionsModel} from '../../../model/social-interactions-model';
import {NgClass} from '@angular/common';
import {TweetService} from '../../../service/tweet.service';
import {Popover} from 'primeng/popover';
import {Dialog} from 'primeng/dialog';
import {PostComponent} from '../post/post.component';
import {FeedService} from '../../../service/feed.service';

@Component({
  selector: 'app-social-interactions',
  imports: [
    NgClass,
    Popover,
    Dialog,
    PostComponent
  ],
  templateUrl: './social-interactions.component.html',
  styleUrl: './social-interactions.component.scss',
  standalone: true
})
export class SocialInteractionsComponent {

  tweetService = inject(TweetService);
  feedService = inject(FeedService);

  @Input() socialInteractions!: SocialInteractionsModel;
  @Output() socialInteractionsChange: EventEmitter<SocialInteractionsModel> = new EventEmitter<SocialInteractionsModel>();

  @ViewChild('retweetPop') retweetPop!: Popover;

  quoteDialogVisible: boolean = false;

  onLike(event: Event) {
    if (this.socialInteractions.isLiked) {
      this.unlike();
    } else {
      this.like();
    }
    event.stopPropagation();
  }

  like() {
    this.tweetService.toggleLike(this.socialInteractions.tweetId).subscribe({
      next: () => {
        const updated = {
          ...this.socialInteractions,
          isLiked : true,
          countLikes : this.socialInteractions.countLikes + 1,
        };
        this.socialInteractionsChange.emit(updated);
      },
      error: () => {

      }
    })
  }

  unlike() {
    this.tweetService.toggleLike(this.socialInteractions.tweetId).subscribe({
      next: () => {
        const updated = {
          ...this.socialInteractions,
          isLiked : false,
          countLikes : this.socialInteractions.countLikes - 1,
        };
        this.socialInteractionsChange.emit(updated);
      },
      error: () => {
      }
    })
  }

  onRetweet() {
    if (this.socialInteractions.isRetweeted) {
      this.unRetweet();
    } else {
      this.retweet()
    }
  }

  retweet() {
    this.tweetService.toggleRetweet(this.socialInteractions.tweetId).subscribe({
        next: () => {
          const updated = {
            ...this.socialInteractions,
            isRetweeted : true,
            countRetweets : this.socialInteractions.countRetweets + 1,
          };
          this.socialInteractionsChange.emit(updated);
        },
        error: () => {
        }
      }
    )
  }

  unRetweet() {
    this.tweetService.toggleRetweet(this.socialInteractions.tweetId).subscribe({
      next: () => {
        const updated = {
          ...this.socialInteractions,
          isRetweeted: false,
          countRetweets: this.socialInteractions.countRetweets - 1,
        };
        this.socialInteractionsChange.emit(updated);
      },
      error: () => {
      }
    })

  }

  openQuoteDialog() {
    this.quoteDialogVisible = true;
  }

  closeQuoteDialog() {
    this.quoteDialogVisible = false;
  }

  toggleRetweetPopover(event: Event) {
    this.retweetPop.toggle(event);
    event.stopPropagation();
  }

  handlePost() {
    this.quoteDialogVisible = false;
    this.feedService.refreshFeed();
  }
}
