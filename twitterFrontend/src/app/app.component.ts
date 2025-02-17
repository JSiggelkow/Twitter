import { Component } from '@angular/core';
import {Button} from 'primeng/button';
import {PrimeNG} from 'primeng/config';
import {MyPreset} from './mytheme';

@Component({
  selector: 'app-root',
  imports: [Button],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.scss'
})
export class AppComponent {

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

