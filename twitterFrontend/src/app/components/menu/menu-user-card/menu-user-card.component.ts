import { Component } from '@angular/core';
import {Card} from 'primeng/card';
import {Avatar} from 'primeng/avatar';

@Component({
  selector: 'app-menu-user-card',
  imports: [
    Card,
    Avatar
  ],
  templateUrl: './menu-user-card.component.html',
  styleUrl: './menu-user-card.component.scss'
})
export class MenuUserCardComponent {

  username: string = 'Gregory Peck' //Todo: Muss später durch Anmeldung geladen werden
}
