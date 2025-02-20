import {Component, ElementRef, inject, Input, OnDestroy, OnInit} from '@angular/core';
import {differenceInHours, differenceInMinutes, differenceInSeconds, format} from 'date-fns';

@Component({
  selector: 'app-time-ago',
  imports: [],
  templateUrl: './time-ago.component.html',
  styleUrl: './time-ago.component.scss'
})
export class TimeAgoComponent implements OnInit, OnDestroy {

  @Input() createdAt!: string;

  elementRef = inject(ElementRef);

  private intersectionObserver?: IntersectionObserver;
  private interval?: any;

  timeAgo!: string;

  ngOnDestroy(): void {
    this.clearInterval();
    this.stopObserver();
  }

  ngOnInit(): void {
    this.startObserver();
  }

  /*Starts an observer which monitors if the element is at least 10% on the screen, if it is, time update gets started */
  private startObserver(): void {
    this.intersectionObserver = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            this.startUpdatingTime();
          } else {
            this.clearInterval();
          }
        });
      },
      {threshold: 0.1}
    );

    this.intersectionObserver.observe(this.elementRef.nativeElement);
  }

  /*Updates the timeAgo field every Second :note only if it is in view --> observer */
  private startUpdatingTime(): void {
    const createdAt = new Date(this.createdAt);
    this.timeAgo = this.getTimeAgo(createdAt);

    this.clearInterval();

    this.interval = setInterval(() => {
      this.timeAgo = this.getTimeAgo(createdAt);
    }, 1000);
  }


  private stopObserver(): void {
    if (this.intersectionObserver) {
      this.intersectionObserver.disconnect();
      this.intersectionObserver = undefined;
    }
  }

  /* calculates and formates the timeAgo field */
  private getTimeAgo(createdAt: Date): string {
    const now = new Date();
    const seconds = differenceInSeconds(now, createdAt);
    const minutes = differenceInMinutes(now, createdAt);
    const hours = differenceInHours(now, createdAt);

    if (seconds < 60) {
      return `${seconds} Sek.`;
    } else if (minutes < 60) {
      return `${minutes} Min.`;
    } else if (hours < 24) {
      return `${hours} Std.`;
    } else if (hours < 48) {
      return `Gestern`;
    } else {
      return format(createdAt, 'dd.MM.yyyy');
    }
  }

  private clearInterval(): void {
    if (this.interval) {
      clearInterval(this.interval);
      this.interval = undefined;
    }
  }
}
