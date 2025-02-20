import {Component, Input} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {Textarea} from 'primeng/textarea';
import {FormsModule} from '@angular/forms';
import {TweetModel} from '../../../model/tweet-model';
import {TimeAgoComponent} from '../time-ago/time-ago.component';


@Component({
  selector: 'app-tweet',
  imports: [
    Avatar,
    Textarea,
    FormsModule,
    TimeAgoComponent
  ],
  templateUrl: './tweet.component.html',
  styleUrl: './tweet.component.scss',
  standalone: true,
})
export class TweetComponent {

  viewsCount = 12345;

  @Input() tweet!: TweetModel;

}
