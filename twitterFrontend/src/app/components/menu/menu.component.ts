import { Component } from '@angular/core';
import {Button} from 'primeng/button';
import {MenuItemComponent} from './menu-item/menu-item.component';
import {MenuUserCardComponent} from './menu-user-card/menu-user-card.component';

@Component({
  selector: 'app-menu',
  imports: [
    Button,
    MenuItemComponent,
    MenuUserCardComponent
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss',
  standalone: true
})
export class MenuComponent {


  menuItems = [
    { title: 'Startseite', iconClass: 'pi pi-home' },
    { title: 'Entdecken', iconClass: 'pi pi-search' },
    { title: 'Mitteilungen', iconClass: 'pi pi-bell' },
    { title: 'Nachrichten', iconClass: 'pi pi-envelope' },
    { title: 'Lesezeichen', iconClass: 'pi pi-bookmark' },
    { title: 'Communities', iconClass: 'pi pi-users' },
    { title: 'Premium', iconClass: 'pi pi-star' },
    { title: 'Profil', iconClass: 'pi pi-user' },
    { title: 'Mehr', iconClass: 'pi pi-ellipsis-h' }
  ];

}
