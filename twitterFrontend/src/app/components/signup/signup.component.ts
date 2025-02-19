import {Component, inject} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {FloatLabel} from 'primeng/floatlabel';
import {Password} from 'primeng/password';
import {Button} from 'primeng/button';
import {CustomValidationService} from '../../validation/custom-validation.service';
import {Message} from 'primeng/message';
import {Divider} from 'primeng/divider';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-signup',
  imports: [
    ReactiveFormsModule,
    InputText,
    FloatLabel,
    Password,
    Button,
    Message,
    Divider,
    NgClass
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss',
  standalone: true
})
export class SignupComponent {

  customValidationService = inject(CustomValidationService);
  router = inject(Router);
  authService = inject(AuthService);

  usernameFormControl = new FormControl('', {
    validators: [
      Validators.required,
      this.customValidationService.customPatternValidator(/^\S+$/, "whitespace"),
      this.customValidationService.customPatternValidator(/^\w+$/, "onlyLettersNumbersUnderscore"),
      Validators.minLength(3),
      Validators.maxLength(20),
    ],
    asyncValidators: [this.customValidationService.UniqueUsernameValidator().bind(this.customValidationService.UniqueUsernameValidator)],
  });

  emailFormControl = new FormControl('', {
    validators: [
      Validators.required,
      Validators.email,
    ],
    asyncValidators: [this.customValidationService.UniqueEmailValidator().bind(this.customValidationService.UniqueEmailValidator)],
  })

  passwordFormControl = new FormControl('', {
    validators: [
      Validators.required,
      Validators.minLength(8),
      Validators.maxLength(255),
      this.customValidationService.customPatternValidator(/^\s*$|^\S+$/, "whitespace")
    ],
    updateOn: 'change',
  })

  repeatPasswordFormControl = new FormControl('', [
    Validators.required,

  ])

  signupForm = new FormGroup({
    username: this.usernameFormControl,
    email: this.emailFormControl,
    password: this.passwordFormControl,
    repeatPassword: this.repeatPasswordFormControl,
  }, {
    validators: [
      this.customValidationService.PasswordMatchesRepeatPassword
    ]
  })

  loading: boolean = false;
  signupError: boolean = false;

  onSubmit() {
    this.loading = true;
    if (this.signupForm.valid) {
      this.signUp();
    }
  }

  signUp() {
    this.authService.signup(this.getSignUpModel())
      .subscribe({
        next: () => {
          this.signupError = false;
          this.loading = false;
          this.router.navigate(['/login']).then();
        },
        error: () => {
          this.loading = false;
          this.signupError = true;
        }
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

  getUsernameErrorMessage(formControl: FormControl): string {
    const errorType = formControl.errors ? Object.keys(formControl.errors)[0] : null;
    switch (errorType) {
      case "usernameTaken":
        return "Benutzername bereits vergeben";
      case "required":
        return "Feld darf nicht leer sein";
      case "minlength":
        return "Mindestens 3 Zeichen erforderlich";
      case "whitespace":
        return "Leerzeichen nicht erlaubt";
      case "onlyLettersNumbersUnderscore":
        return "Nur Buchstaben, Zahlen und Unterstriche erlaubt"
      case "maxlength":
        return "Maximal 20 Zeichen erlaubt";
      default:
        return "Ein Fehler ist aufgetreten";
    }
  }

  getEmailErrorMessage(formControl: FormControl): string {
    const errorType = formControl.errors ? Object.keys(formControl.errors)[0] : null;
    switch (errorType) {
      case "email":
        return "Invalide E-Mail Adresse";
      case "emailTaken":
        return "E-Mail Adresse bereits vergeben";
      case "required":
        return "Feld darf nicht leer sein";
      default:
        return "Ein Fehler ist aufgetreten";
    }
  }

  getPasswordErrorMessage(formControl: FormControl): string {
    const errorType = formControl.errors ? Object.keys(formControl.errors)[0] : null;
    switch (errorType) {
      case "required":
        return "Feld darf nicht leer sein";
      case "minlength":
        return "Mindestens 8 Zeichen erforderlich";
      case "maxlength":
        return "Maximal 255 Zeichen erlaubt";
      default:
        return "Ein Fehler ist aufgetreten";
    }
  }

}
