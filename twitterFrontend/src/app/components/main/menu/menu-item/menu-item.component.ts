import {Component, inject, Input} from '@angular/core';
import {NgClass} from '@angular/common';
import {Router} from '@angular/router';
import {Toast} from 'primeng/toast';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-menu-item',
  imports: [
    NgClass,
    Toast
  ],
  templateUrl: './menu-item.component.html',
  styleUrl: './menu-item.component.scss',
  standalone: true,
  providers: [MessageService]
})
export class MenuItemComponent {

  router = inject(Router);
  messageService = inject(MessageService);

  @Input() title: string = '';
  @Input() iconClass: string = '';
  @Input() routeToPath: string = '';

  routeTo() {
    if (this.routeToPath === '') {
      this.notYetImplementedToast();
      return;
    }
    this.router.navigate([this.routeToPath]).then();
  }

  notYetImplementedToast() {
    this.messageService.add({
      severity: 'info',
      summary: 'Info',
      detail: 'Diese Funktion ist noch nicht implementiert!',
      life: 1500,
      closable: false
    })
  }

  isActiveRoute(): boolean {
    return this.router.url === this.routeToPath;
  }
}
