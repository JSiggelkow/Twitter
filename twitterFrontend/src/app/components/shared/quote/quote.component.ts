import {Component, Input} from '@angular/core';
import {QuoteModel} from '../../../model/quote-model';
import {Avatar} from 'primeng/avatar';
import {Textarea} from 'primeng/textarea';
import {TimeAgoComponent} from '../time-ago/time-ago.component';

@Component({
  selector: 'app-quote',
  imports: [
    Avatar,
    Textarea,
    TimeAgoComponent
  ],
  templateUrl: './quote.component.html',
  styleUrl: './quote.component.scss',
  standalone: true
})
export class QuoteComponent {

  @Input() quote: QuoteModel = {
    postId : "",
    text : "",
    username : "",
    createdAt : ""
  }
}
