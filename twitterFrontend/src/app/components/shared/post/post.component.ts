import {Component, EventEmitter, inject, Output} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {Textarea} from 'primeng/textarea';
import {Button} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgClass} from '@angular/common';
import {Toast} from 'primeng/toast';
import {MessageService} from 'primeng/api';

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

  text!: string;
  maxLength = 200;
  @Output() post: EventEmitter<string> = new EventEmitter<string>();
  messageService = inject(MessageService);

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
    this.post.emit(this.text)
    this.text = '';
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
