import {Component, Input} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {Textarea} from 'primeng/textarea';
import {FormsModule} from '@angular/forms';
import {TweetModel} from '../../../model/tweet-model';

@Component({
  selector: 'app-tweet',
  imports: [
    Avatar,
    Textarea,
    FormsModule
  ],
  templateUrl: './tweet.component.html',
  styleUrl: './tweet.component.scss'
})
export class TweetComponent {


  @Input() tweet!: TweetModel;


  time = '34min'
  viewsCount = 12345;

}
