import {
  Component,
  OnInit,
  OnDestroy,
  AfterViewInit,
  Renderer2, inject,
} from '@angular/core';
import {Subscription, fromEvent} from 'rxjs';
import {throttleTime} from 'rxjs/operators';
import {TweetService} from '../../../service/tweet.service';
import {TweetModel} from '../../../model/tweet-model';
import {Tab, TabList, TabPanel, TabPanels, Tabs} from 'primeng/tabs';
import {PostComponent} from '../../shared/post/post.component';
import {TweetComponent} from '../../shared/tweet/tweet.component';
import {Message} from 'primeng/message';
import {ProgressSpinner} from 'primeng/progressspinner';
import {Toast} from 'primeng/toast';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss'],
  standalone: true,
  imports: [
    Tabs,
    TabList,
    Tab,
    TabPanels,
    TabPanel,
    PostComponent,
    TweetComponent,
    Message,
    ProgressSpinner,
    Toast
  ],
  providers: [MessageService]
})
export class FeedComponent implements OnInit, AfterViewInit, OnDestroy {

  tweets: TweetModel[] = [];
  loadNewestLimit = 25;
  loading: boolean = false;

  private scrollContainer?: HTMLElement;
  private scrollSubscription?: Subscription;

  renderer = inject(Renderer2);
  tweetService = inject(TweetService);
  messageService = inject(MessageService);

  ngOnInit(): void {
    this.loading = true;
    this.loadNewest();
  }

  ngAfterViewInit(): void {
    // gets the container in which the scroll active --> the main component --> because there is the overflow-y-scroll css attribute
    this.scrollContainer = this.renderer.selectRootElement('.flex.flex-row.h-screen.overflow-y-scroll', true);

    if (!this.scrollContainer) {
      return;
    }

    this.scrollSubscription = this.getScrollEvent();
  }

  /* load the newest tweets on the first load of the feed with a limit */
  private loadNewest(): void {
    this.tweetService.newest(this.loadNewestLimit).subscribe({
      next: (tweets) => {
        this.loading = false;
        this.tweets = tweets;
      },
      error: () => {
        this.loading = false;
        this.showErrorToast()
      }
    });
  }

  /* gets the scroll event for the feed */
  private getScrollEvent(): Subscription {
    return fromEvent(this.scrollContainer!, 'scroll')
      .pipe(
        throttleTime(200) // check scroll only every 200ms for better performance
      )
      .subscribe(() => {
        const scrollPosition = this.getScrollPercent();

        if (scrollPosition > 90) {
          this.loadBefore();
        }
      });
  }

  // calculates the percentage of how far the user has scrolled
  private getScrollPercent(): number {
    const scrollHeight = this.scrollContainer!.scrollHeight - this.scrollContainer!.clientHeight;
    const scrollTop = this.scrollContainer!.scrollTop;

    return (scrollTop / scrollHeight) * 100;
  }

  /* Loads the next tweets after the last tweet that is already loaded this method gets called if the user scrolled 90% of the feed */
  private loadBefore(): void {
    if (this.tweets.length === 0) {
      return;
    }
    // gets the created at time of the current last loaded tweet
    const lastTweetCreatedAt = this.tweets[this.tweets.length - 1].createdAt;

    this.tweetService.before(lastTweetCreatedAt).subscribe({
      next: (tweets) => this.tweets = [...this.tweets, ...tweets],
      error: () => this.showErrorToast()
    });
  }

  showErrorToast() {
    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: 'Fehler beim Laden der Tweets!',
      life: 3000,
      closable: false
    })
  }

  ngOnDestroy(): void {
    this.scrollSubscription?.unsubscribe();
  }

  // methods gets triggered if the user posts a new tweet --> adds the new tweet to the first entry in the tweet array
  handlePost(tweet: TweetModel | null) {
   if (tweet) this.tweets.unshift(tweet);
   else this.showErrorToast();
  }
}
