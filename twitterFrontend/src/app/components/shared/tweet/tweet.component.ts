import { Component } from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {Textarea} from 'primeng/textarea';
import {FormsModule} from '@angular/forms';

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


  name = 'Gregory Peck';
  username = 'gregorypeck';
  time = '34min'
  text = 'In einem Monat schreiben wir Datenbanken! Ich habe echt angst davor :( Kann mir jemand helfen?'
  commentCount = 26;
  retweetCount = 28;
  likeCount = 815;
  viewsCount = 12345;

}
