import {Component} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {FloatLabel} from 'primeng/floatlabel';
import {InputText} from 'primeng/inputtext';
import {Password} from 'primeng/password';
import {Button} from 'primeng/button';
import {Message} from 'primeng/message';

@Component({
  selector: 'app-login',
  imports: [
    FloatLabel,
    ReactiveFormsModule,
    InputText,
    Password,
    Button,
    Message,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  standalone: true
})
export class LoginComponent {

  constructor(
    private readonly router: Router,
    private readonly authService: AuthService) {
  }

  usernameFormControl = new FormControl('', [
    Validators.required,
  ]);

  passwordFormControl = new FormControl('', [
    Validators.required,
  ])

  loginForm = new FormGroup({
    username: this.usernameFormControl,
    password: this.passwordFormControl
  })

  loginError: boolean = false;
  loading: boolean = false;

  onSubmit() {
    this.loading = true;
    this.login();
  }

  login() {
    this.authService.login(this.getLoginModel())
      .subscribe({
          next: () => {
            this.loginError = false;
            this.loading = false;
            this.router.navigate(['/home']).then();
          },
          error: () => {
            this.loginError = true;
            this.loading = false;
          }
        }
      )
  }

  toSignup() {
    this.router.navigate(['/signup']).then();
  }

  getLoginModel() {
    return {
      username: this.loginForm.value.username ?? '',
      password: this.loginForm.value.password ?? ''
    }
  }
}
