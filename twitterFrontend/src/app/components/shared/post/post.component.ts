import {Component} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {Textarea} from 'primeng/textarea';
import {Button} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-post',
  imports: [
    Avatar,
    Textarea,
    Button,
    ReactiveFormsModule,
    FormsModule,
    NgClass
  ],
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss',
  standalone: true,
})
export class PostComponent {

  text!: string;
  maxLength = 200;

  get remainingChars() {
    return this.maxLength - (this.text?.length || 0);
  }

}
