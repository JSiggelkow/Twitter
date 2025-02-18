import {Component, Input} from '@angular/core';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-menu-item',
  imports: [
    NgClass
  ],
  templateUrl: './menu-item.component.html',
  styleUrl: './menu-item.component.scss',
  standalone: true,
})
export class MenuItemComponent {

  @Input() title: string = '';
  @Input() iconClass: string = '';

}
