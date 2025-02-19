import { Component } from '@angular/core';
import {Tab, TabList, TabPanel, TabPanels, Tabs} from 'primeng/tabs';
import {PostComponent} from '../../shared/post/post.component';
import {Message} from 'primeng/message';

@Component({
  selector: 'app-main',
  imports: [
    Tabs,
    TabList,
    Tab,
    TabPanels,
    TabPanel,
    PostComponent,
    Message
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {
}
