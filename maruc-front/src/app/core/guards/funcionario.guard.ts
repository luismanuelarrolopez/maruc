import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { Role } from 'src/app/auth/model/role';
import { AuthService } from 'src/app/auth/services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class FuncionarioGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    const authenticated = this.authService.authStatus.isAuthenticated;
    const isFuncionario =
      this.authService.userHasRole(Role.Oci) ||
      this.authService.userHasRole(Role.Opdi);
    if (!authenticated) {
      this.router.navigate(['/login']);
      return false;
    } else if (!isFuncionario) {
      this.router.navigate(['/dashboard']);
      return false;
    }
    return true;
  }
}
