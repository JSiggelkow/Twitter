import { Component } from '@angular/core';
import {Skeleton} from 'primeng/skeleton';

@Component({
  selector: 'app-post-skeleton',
  imports: [
    Skeleton
  ],
  templateUrl: './post-skeleton.component.html',
  styleUrl: './post-skeleton.component.scss'
})
export class PostSkeletonComponent {

}
