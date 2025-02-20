import {Component, inject, OnInit} from '@angular/core';
import {MenuComponent} from './menu/menu.component';
import {UserService} from '../../service/user.service';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [
    MenuComponent,
    RouterOutlet
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss',
  standalone: true
})
export class MainComponent implements OnInit {

  userService = inject(UserService);

  ngOnInit(): void {
    this.userService.fetchUserInfo();
  }

}
