import {Component, inject, Input, OnChanges, SimpleChanges} from '@angular/core';
import {TweetComponent} from '../../shared/tweet/tweet.component';
import {TweetService} from '../../../service/tweet.service';
import {TweetModel} from '../../../model/tweet-model';
import {PostComponent} from '../../shared/post/post.component';
import {ProgressSpinner} from 'primeng/progressspinner';
import {PostSkeletonComponent} from '../../shared/post-skeleton/post-skeleton.component';
import {RouteBackHeaderComponent} from '../../shared/route-back-header/route-back-header.component';

@Component({
  selector: 'app-status',
  imports: [
    TweetComponent,
    PostComponent,
    ProgressSpinner,
    PostSkeletonComponent,
    RouteBackHeaderComponent
  ],
  templateUrl: './status.component.html',
  styleUrl: './status.component.scss'
})
export class StatusComponent implements OnChanges {

  tweetService = inject(TweetService);

  @Input()
  set id(id: string) {
    this.parentId = id;
  }

  parentId: string = '';
  parent?: TweetModel;
  comments?: TweetModel[];
  loading: boolean = false;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['id'] && changes['id'].currentValue !== changes['id'].previousValue) {
      this.loadStatus(); // Neue Daten bei ID-Änderung laden
    }
  }


  loadStatus() {
    this.loading = true;
    this.tweetService.status(this.parentId).subscribe({
      next: status => {
        this.parent = status.parent;
        this.comments = status.comments
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      }
    })
  }

  onCommented() {
    this.loadStatus()
  }

  loadMore() {
    if (!this.parent || !this.comments) return;
    this.tweetService.statusBefore(this.parentId, this.comments[this.comments.length - 1].createdAt).subscribe({
      next: status => {
        this.comments = [...this.comments!, ...status.comments]
        this.parent = status.parent;
      },
      error: () => {
      }
    })
  }
}
