import {Component, inject, Input} from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-route-back-header',
  imports: [],
  templateUrl: './route-back-header.component.html',
  styleUrl: './route-back-header.component.scss'
})
export class RouteBackHeaderComponent {

  @Input() title: string = '';

  _location = inject(Location)

 routeBack() {
    this._location.back();
  }
}
