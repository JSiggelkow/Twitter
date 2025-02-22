import {Component, inject, OnInit} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {UserService} from '../../../../service/user.service';
import {Popover} from 'primeng/popover';
import {Button} from 'primeng/button';
import {AuthService} from '../../../../service/auth.service';
import {Router} from '@angular/router';
import {Toast} from 'primeng/toast';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-menu-user-card',
  imports: [
    Avatar,
    Popover,
    Button,
    Toast
  ],
  templateUrl: './menu-user-card.component.html',
  styleUrl: './menu-user-card.component.scss',
  standalone: true,
  providers: [MessageService]
})
export class MenuUserCardComponent implements OnInit {

  username: string | null = null;

  userService = inject(UserService);
  authService = inject(AuthService);
  router = inject(Router);
  messageService = inject(MessageService);
  loading: boolean = false;

  ngOnInit(): void {
    this.userService.user$.subscribe(user => {
      this.username = user?.username ?? '';
    });
  }

  onLogout() {
    this.loading = true;
    this.logout()
  }

  logout() {
    this.authService.logout().subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/login']).then();
      },
      error: () => {
        this.loading = false;
        this.showError();
      }
    })
  }

  private showError() {
    this.messageService.add({
      severity: 'error',
      summary: 'Fehler',
      detail: 'Logout ist fehlgeschlagen!',
      life: 3000,
      closable: false
    })
  }
}
