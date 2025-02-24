import {Component, inject} from '@angular/core';
import {Button} from 'primeng/button';
import {MenuItemComponent} from './menu-item/menu-item.component';
import {MenuUserCardComponent} from './menu-user-card/menu-user-card.component';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-menu',
  imports: [
    Button,
    MenuItemComponent,
    MenuUserCardComponent
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss',
  standalone: true,
  providers: [MessageService]
})
export class MenuComponent {

  messageService = inject(MessageService);

  menuItems: { title: string, iconClass: string, routeToPath: string }[] = [
    {title: 'Startseite', iconClass: 'pi pi-home', routeToPath: '/home'},
    {title: 'Entdecken', iconClass: 'pi pi-search', routeToPath: ''},
    {title: 'Mitteilungen', iconClass: 'pi pi-bell', routeToPath: ''},
    {title: 'Nachrichten', iconClass: 'pi pi-envelope', routeToPath: ''},
    {title: 'Lesezeichen', iconClass: 'pi pi-bookmark', routeToPath: '/saved'},
    {title: 'Communities', iconClass: 'pi pi-users', routeToPath: ''},
    {title: 'Premium', iconClass: 'pi pi-star', routeToPath: ''},
    {title: 'Profil', iconClass: 'pi pi-user', routeToPath: ''},
    {title: 'Mehr', iconClass: 'pi pi-ellipsis-h', routeToPath: ''}
  ];


  notYetImplementedToast() {
    this.messageService.add({
      severity: 'info',
      summary: 'Info',
      detail: 'Diese Funktion ist noch nicht implementiert!',
      life: 1500,
      closable: false
    })
  }

}
