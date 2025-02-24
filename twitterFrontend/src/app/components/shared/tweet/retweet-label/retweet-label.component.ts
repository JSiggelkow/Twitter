import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-retweet-label',
  imports: [],
  templateUrl: './retweet-label.component.html',
  styleUrl: './retweet-label.component.scss',
  standalone: true,
})
export class RetweetLabelComponent {

  @Input() username: string = '';
}
