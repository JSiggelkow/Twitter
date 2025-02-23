import {Component, inject, Input, OnChanges, SimpleChanges} from '@angular/core';
import {TweetComponent} from '../../shared/tweet/tweet.component';
import {TweetService} from '../../../service/tweet.service';
import {TweetModel} from '../../../model/tweet-model';
import {Location} from '@angular/common';
import {PostComponent} from '../../shared/post/post.component';

@Component({
  selector: 'app-status',
  imports: [
    TweetComponent,
    PostComponent
  ],
  templateUrl: './status.component.html',
  styleUrl: './status.component.scss'
})
export class StatusComponent implements OnChanges {

  tweetService = inject(TweetService);
  _location = inject(Location)

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

  routeBack() {
    this._location.back();
  }

  onCommented() {
    this.loadStatus()
  }
}
