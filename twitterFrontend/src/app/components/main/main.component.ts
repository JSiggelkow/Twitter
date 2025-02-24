import {Component, inject, OnInit} from '@angular/core';
import {MenuComponent} from './menu/menu.component';
import {UserService} from '../../service/user.service';
import {RouterOutlet} from '@angular/router';
import {InfoComponent} from './info/info.component';

@Component({
  selector: 'app-home',
  imports: [
    MenuComponent,
    RouterOutlet,
    InfoComponent
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
