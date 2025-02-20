import { Routes } from '@angular/router';
import {MainComponent} from './components/home/main.component';
import {LoginComponent} from './components/login/login.component';
import {authGuard} from './guards/auth.guard';
import {SignupComponent} from './components/signup/signup.component';
import {FeedComponent} from './components/home/main/feed.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: '',
    component: MainComponent,
    canActivate: [authGuard],
    children : [
      { path: 'home',
        component: FeedComponent}
    ]},
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent},
  { path: '**', redirectTo: '', pathMatch: 'full' }
];
