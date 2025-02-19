import { Component } from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {Textarea} from 'primeng/textarea';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-post',
  imports: [
    Avatar,
    Textarea,
    Button
  ],
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss'
})
export class PostComponent {

}
