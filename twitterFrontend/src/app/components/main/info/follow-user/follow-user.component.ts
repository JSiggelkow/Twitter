import {Component, Input} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-follow-user',
  imports: [
    Avatar,
    Button
  ],
  templateUrl: './follow-user.component.html',
  styleUrl: './follow-user.component.scss'
})
export class FollowUserComponent {
  @Input() username: string = '';

}
