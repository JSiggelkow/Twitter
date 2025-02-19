import { Component } from '@angular/core';
import {MenuComponent} from './menu/menu.component';
import {MainComponent} from './main/main.component';

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
export class HomeComponent {

}
