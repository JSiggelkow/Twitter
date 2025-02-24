import { Component } from '@angular/core';
import {InputText} from 'primeng/inputtext';
import {Button} from 'primeng/button';
import {Avatar} from 'primeng/avatar';

@Component({
  selector: 'app-info',
  imports: [
    InputText,
    Button,
    Avatar
  ],
  templateUrl: './info.component.html',
  styleUrl: './info.component.scss'
})
export class InfoComponent {

}
