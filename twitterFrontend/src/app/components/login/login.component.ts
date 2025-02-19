import {Component} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {FloatLabel} from 'primeng/floatlabel';
import {InputText} from 'primeng/inputtext';
import {Password} from 'primeng/password';
import {Button} from 'primeng/button';
import {Message} from 'primeng/message';
import {LoginModel} from '../../model/login-model';

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
  login: LoginModel = {username: '', password: ''};


  onSubmit() {

    this.setUpLoginModel();

    this.authService.login(this.login)
      .subscribe({
          next: () => {
            this.loginError = false;
            this.router.navigate(['/home']).then();
          },
          error: () => {
            this.loginError = true;
          }
        }
      )
  }

  toSignup() {
    this.router.navigate(['/signup']).then();
  }

  setUpLoginModel() {
    this.login.username = this.usernameFormControl.value!;
    this.login.password = this.passwordFormControl.value!;
  }
}
