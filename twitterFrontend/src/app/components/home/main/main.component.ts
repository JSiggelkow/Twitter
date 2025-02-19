import { Component } from '@angular/core';
import {Tab, TabList, TabPanel, TabPanels, Tabs} from 'primeng/tabs';
import {PostComponent} from '../../shared/post/post.component';

@Component({
  selector: 'app-main',
  imports: [
    Tabs,
    TabList,
    Tab,
    TabPanels,
    TabPanel,
    PostComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {
}
