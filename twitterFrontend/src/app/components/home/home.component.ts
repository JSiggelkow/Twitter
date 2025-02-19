import {Component, inject, OnInit} from '@angular/core';
import {MenuComponent} from './menu/menu.component';
import {MainComponent} from './main/main.component';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-home',
  imports: [
    MenuComponent,
    MainComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
  standalone: true
})
export class HomeComponent implements OnInit {

  userService = inject(UserService);

  ngOnInit(): void {
    this.userService.fetchUserInfo();
  }

}
