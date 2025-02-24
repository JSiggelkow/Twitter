import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../service/auth.service';
import {catchError, map} from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const authService = inject(AuthService);

  // guards a route -> checks if user is authenticated if so routing is enabled if not routes to /login

  return authService.check().pipe(
    map((isLoggedIn: boolean) => {
      if (isLoggedIn) return true;
      router.navigate(['/login']).then();
      return false;
    }),
    catchError((err) => {
      router.navigate(['/login']).then();
      return [false];
    })
  );
};
