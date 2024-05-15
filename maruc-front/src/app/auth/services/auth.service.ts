import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { catchError, map, Observable, tap } from 'rxjs';
import { transformError } from 'src/app/core/common/common';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { AuthServerResponse } from '../model/auth-server-response';
import { AuthStatus } from '../model/auth-status';
import { LoginRequest } from '../model/login-request';
import { Role } from '../model/role';

const defaultUserStatus: AuthStatus = {
  isAuthenticated: false,
  userRole: Role.None,
  userEmail: null,
  token: null,
  userId: null,
  nombre: null,
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  readonly BASE_API_URL: string = loadApiUrl();
  readonly ENDPOINT_URL: string = `${this.BASE_API_URL}auth/signin`;
  readonly ROLES: { [key: string]: Role } = {
    ADMIN: Role.Admin,
    OPDI: Role.Opdi,
    OCI: Role.Oci,
    LIDER: Role.LiderProceso,
  };

  authStatus: AuthStatus = defaultUserStatus;

  constructor(private http: HttpClient, private router: Router) {
    const token = localStorage.getItem('access_token');
    if (token) {
      this.authStatus = this.decode(token);
    }
  }

  resetAuthObject() {
    this.authStatus = defaultUserStatus;
    localStorage.removeItem('access_token');
  }

  login(loginRequest: LoginRequest): Observable<AuthStatus> {
    this.logout();
    const loginResponse$ = this.http
      .post<AuthServerResponse>(this.ENDPOINT_URL, loginRequest)
      .pipe(
        tap((value) => localStorage.setItem('access_token', value.token)),
        map((value) => this.decode(value.token)),
        catchError(transformError)
      );

    loginResponse$.subscribe({
      next: (response) => Object.assign(this.authStatus, response),
      error: (error) => {
        this.logout();
        return error;
      },
    });

    return loginResponse$;
  }

  logout() {
    this.resetAuthObject();
  }

  decode(token: string): AuthStatus {
    const decodedToken = this.getDecodedAccessToken(token);
    return {
      isAuthenticated: true,
      userRole: this.ROLES[decodedToken.authorities[0].authority],
      userEmail: decodedToken.sub,
      token: token,
      userId: decodedToken.userId,
      nombre: decodedToken.nombre,
    };
  }

  userHasRole(role: Role): boolean {
    return this.authStatus.userRole === role;
  }

  getRoleByName(roleName: string): Role {
    return this.ROLES[roleName];
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwtDecode(token);
    } catch (Error) {
      return null;
    }
  }
}
