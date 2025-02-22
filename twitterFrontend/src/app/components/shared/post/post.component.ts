import {Component, EventEmitter, inject, Output} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {Textarea} from 'primeng/textarea';
import {Button} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgClass} from '@angular/common';
import {Toast} from 'primeng/toast';
import {MessageService} from 'primeng/api';
import {TweetService} from '../../../service/tweet.service';
import {TweetModel} from '../../../model/tweet-model';

@Component({
  selector: 'app-post',
  imports: [
    Avatar,
    Textarea,
    Button,
    ReactiveFormsModule,
    FormsModule,
    NgClass,
    Toast
  ],
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss',
  standalone: true,
  providers: [MessageService]
})
export class PostComponent {

  messageService = inject(MessageService);
  tweetService = inject(TweetService);

  text!: string;
  maxLength = 200;
  loading: boolean = false;

  @Output() posted: EventEmitter<TweetModel | null> = new EventEmitter<TweetModel | null>();


  icons = [
    'image',
    'video',
    'list',
    'face-smile',
    'calendar-clock',
    'map-marker'
  ]

  get remainingChars() {
    return this.maxLength - (this.text?.length || 0);
  }

  onPost() {
    this.loading = true;
    this.post();
  }

  post() {
    this.tweetService.post(this.text).subscribe({
      next: (tweet) => {
        this.loading = false;
        this.text = '';
        this.posted.emit(tweet);
      },
      error: () => {
        this.showTweetError();
        this.loading = false;
        this.posted.emit(null);
      }
    })
  }

  showTweetError() {
    this.messageService.add({
      severity: 'error',
      summary: 'Fehler',
      detail: 'Tweet konnte nicht gesendet werden!',
      life: 3000,
      closable: false
    })
  }

  notYetImplementedToast() {
    this.messageService.add({
      severity: 'info',
      summary: 'Info',
      detail: 'Diese Funktion ist noch nicht implementiert!',
      life: 1500,
      closable: false
    })
  }
}
