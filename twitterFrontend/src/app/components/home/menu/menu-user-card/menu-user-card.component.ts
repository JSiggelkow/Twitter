import {Component, inject, OnInit} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {UserService} from '../../../../service/user.service';

@Component({
  selector: 'app-menu-user-card',
  imports: [
    Avatar
  ],
  templateUrl: './menu-user-card.component.html',
  styleUrl: './menu-user-card.component.scss',
  standalone: true
})
export class MenuUserCardComponent implements OnInit {

  username: string | null = null;

  userService = inject(UserService);

  ngOnInit(): void {
    this.userService.user$.subscribe(user => {
      this.username = user?.username ?? '';
    });
  }
}
