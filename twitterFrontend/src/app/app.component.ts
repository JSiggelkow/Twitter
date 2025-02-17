import { Component } from '@angular/core';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-root',
  imports: [Button],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.scss'
})
export class AppComponent {
}
