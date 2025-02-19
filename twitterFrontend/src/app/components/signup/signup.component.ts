import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {FloatLabel} from 'primeng/floatlabel';
import {Password} from 'primeng/password';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-signup',
  imports: [
    ReactiveFormsModule,
    InputText,
    FloatLabel,
    Password,
    Button
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss',
  standalone: true
})
export class SignupComponent {

  constructor(
    private readonly router: Router,
    private readonly authService: AuthService) {
  }

  usernameFormControl = new FormControl('', [
    Validators.required,
  ]);

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ])

  passwordFormControl = new FormControl('', [
    Validators.required,

  ])

  repeatPasswordFormControl = new FormControl('', [
    Validators.required,
  ])

  signupForm = new FormGroup({
    username: this.usernameFormControl,
    email: this.emailFormControl,
    password: this.passwordFormControl,
    repeatPassword: this.repeatPasswordFormControl,
  })

  onSubmit() {
    this.signUp();
  }

  signUp() {
    this.authService.signup(this.getSignUpModel())
      .subscribe({
        next: () => {
          this.router.navigate(['/login']).then();
        },
        error: () => {}
      })
  }

  getSignUpModel() {
    return {
      username: this.signupForm.value.username ?? '',
      email: this.signupForm.value.email ?? '',
      password: this.signupForm.value.password ?? '',
    }
  }

  toLogin() {
    this.router.navigate(['/login']).then();
  }
}
