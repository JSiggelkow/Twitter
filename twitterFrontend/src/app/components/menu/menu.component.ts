import { Component } from '@angular/core';
import {Button} from 'primeng/button';
import {MenuItemComponent} from './menu-item/menu-item.component';
import {MenuUserCardComponent} from './menu-user-card/menu-user-card.component';
import {Toast} from 'primeng/toast';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-menu',
  imports: [
    Button,
    MenuItemComponent,
    MenuUserCardComponent,
    Toast,
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss',
  standalone: true,
  providers: [MessageService]
})
export class MenuComponent {

  constructor(private readonly messageService: MessageService) {
  }

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

  notYetImplementedToast() {
   this.messageService.add({ severity: 'secondary', summary: 'Error', detail: 'Diese Funktion ist noch nicht implementiert!', life: 3000, closable: false})
  }

}
