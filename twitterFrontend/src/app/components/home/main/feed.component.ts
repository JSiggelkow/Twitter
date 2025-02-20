import { Component } from '@angular/core';
import {Tab, TabList, TabPanel, TabPanels, Tabs} from 'primeng/tabs';
import {PostComponent} from '../../shared/post/post.component';
import {Message} from 'primeng/message';

@Component({
  selector: 'app-feed',
  imports: [
    Tabs,
    TabList,
    Tab,
    TabPanels,
    TabPanel,
    PostComponent,
    Message
  ],
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.scss',
  standalone: true,
})
export class FeedComponent {
}
