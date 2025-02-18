import { Component } from '@angular/core';
import {Avatar} from 'primeng/avatar';

@Component({
  selector: 'app-menu-user-card',
  imports: [
    Avatar
  ],
  templateUrl: './menu-user-card.component.html',
  styleUrl: './menu-user-card.component.scss',
  standalone: true
})
export class MenuUserCardComponent {

  username: string = 'Gregory Peck' //Todo: Muss später durch Anmeldung geladen werden
}
