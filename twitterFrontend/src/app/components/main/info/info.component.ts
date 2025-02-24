import { Component } from '@angular/core';
import {InputText} from 'primeng/inputtext';
import {Button} from 'primeng/button';
import {FollowUserComponent} from './follow-user/follow-user.component';

@Component({
  selector: 'app-info',
  imports: [
    InputText,
    Button,
    FollowUserComponent
  ],
  templateUrl: './info.component.html',
  styleUrl: './info.component.scss'
})
export class InfoComponent {
  // this is only a class to fit the app to the "normal" twitter style
  // this component has no interactions or methods!
}
