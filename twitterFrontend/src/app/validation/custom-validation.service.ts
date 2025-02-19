import {inject, Injectable} from '@angular/core';
import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';
import {catchError, map, Observable, of} from 'rxjs';
import {UserService} from '../service/user.service';

@Injectable({
  providedIn: 'root'
})
export class CustomValidationService {

  userService = inject(UserService);

  customPatternValidator(pattern: RegExp, code: string): (control: AbstractControl) => { [key: string]: any; } | null {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const valid = pattern.test(control.value);
      return valid ? null : {[code]: true};
    }

  }

  PasswordMatchesRepeatPassword: ValidatorFn = (
    control: AbstractControl,
  ): ValidationErrors | null => {
    const password = control.get('password');
    const repeatPassword = control.get('repeatPassword');

    return password && repeatPassword && password.value != repeatPassword.value ? {passwordsNotMatch: true} : null;
  }

  UniqueEmailValidator(): (control: AbstractControl) => (Promise<ValidationErrors | null> | Observable<ValidationErrors | null>) {
    return (control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> => {
      if (!control.value) return of(null);

      return this.userService.checkEmailExists(control.value).pipe(
        map((exists: boolean) => (exists ? {emailTaken: true} : null)),
        catchError(async () => null)
      );
    };
  }

  UniqueUsernameValidator(): (control: AbstractControl) => (Promise<ValidationErrors | null> | Observable<ValidationErrors | null>) {
    return (control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> => {
      if (!control.value) return of(null);

      return this.userService.checkUsernameExists(control.value).pipe(
        map((exists: boolean) => (exists ? { usernameTaken: true } : null)),
        catchError(async () => null)
      );
    };
  }
}
