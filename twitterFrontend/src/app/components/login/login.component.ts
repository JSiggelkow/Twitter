import {Component} from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {FloatLabel} from 'primeng/floatlabel';
import {InputText} from 'primeng/inputtext';
import {Password} from 'primeng/password';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-login',
  imports: [
    FloatLabel,
    ReactiveFormsModule,
    InputText,
    Password,
    Button,
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


  onSubmit() {
    this.authService.login(this.loginForm.value.username!, this.loginForm.value.password!)
      .subscribe({
        next: () => {
          this.router.navigate(['/home']).then();
        },
        error: () => {}
        }
      )
  }
}
