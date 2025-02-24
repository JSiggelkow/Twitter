import { Component } from '@angular/core';
import {PrimeNG} from 'primeng/config';
import {MyPreset} from './mytheme';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.scss'
})
export class AppComponent {


  //sets the custom theme and the specific css order to enable tailwind

  constructor(private readonly primeng: PrimeNG) {
        this.primeng.theme.set({
            preset: MyPreset,
                options: {
                    cssLayer: {
                        name: 'primeng',
                        order: 'tailwind-base, primeng, tailwind-utilities'
                    }
                }
            })
        }
    }

