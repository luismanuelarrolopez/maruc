import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PaginableResult } from 'src/app/core/common/models/paginable-result';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { buildPageHttpParams } from 'src/app/core/utils/function-utils';
import { GuardarUsuarioDto } from '../models/guardar-usuario-dto';
import { ChangePasswordRequest } from '../models/requests/change-password-request';
import { PasswordRecoveryRequest } from '../models/requests/password-recovery-request';
import { UsuarioRequest } from '../models/requests/usuario-request';
import { PerfilUsuarioResponse } from '../models/response/perfil-usuario-response';
import { Usuario } from '../models/usuario';
import { VerificarUsuarioDTO } from '../models/verificar-usuario-dto';

@Injectable({
  providedIn: 'root',
})
export class UsuariosService {
  private readonly API_ENDPOINT = loadApiUrl();
  private readonly USUARIOS_ENDPOINT = this.API_ENDPOINT + 'usuarios/';
  dataChangeSubject = new BehaviorSubject(false);

  constructor(private http: HttpClient) {}

  fetchUsuarios(
    page: number,
    size: number
  ): Observable<PaginableResult<Usuario>> {
    return this.http.get<PaginableResult<Usuario>>(this.USUARIOS_ENDPOINT, {
      params: buildPageHttpParams(page, size),
    });
  }

  guardarUsuario(usuario: GuardarUsuarioDto): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.API_ENDPOINT}auth/signup`, usuario);
  }

  verificarUsuario(verificacion: VerificarUsuarioDTO): Observable<boolean> {
    return this.http.put<boolean>(
      `${this.USUARIOS_ENDPOINT}verificacion`,
      verificacion
    );
  }

  recuerarPassword(email: string): Observable<void> {
    return this.http.post<void>(`${this.USUARIOS_ENDPOINT}forgot`, email);
  }

  doRecovery(request: PasswordRecoveryRequest): Observable<boolean> {
    return this.http.put<boolean>(`${this.USUARIOS_ENDPOINT}recovery`, request);
  }

  recuperarPerfil(email: string): Observable<PerfilUsuarioResponse> {
    return this.http.get<PerfilUsuarioResponse>(
      `${this.USUARIOS_ENDPOINT}profile/${email}`
    );
  }

  doActualizarPerfil(usuario: PerfilUsuarioResponse): Observable<boolean> {
    return this.http.put<boolean>(`${this.USUARIOS_ENDPOINT}profile`, usuario);
  }

  fetchUsuario(idUsuario: number): Observable<UsuarioRequest> {
    return this.http.get<UsuarioRequest>(
      `${this.USUARIOS_ENDPOINT}${idUsuario}`
    );
  }

  doActualizarUsuario(usuario: UsuarioRequest): Observable<boolean> {
    return this.http.put<boolean>(`${this.USUARIOS_ENDPOINT}`, usuario);
  }

  doEliminar(id: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.USUARIOS_ENDPOINT}${id}`);
  }

  doCambiarPassword(
    changePasswordRequest: ChangePasswordRequest
  ): Observable<boolean> {
    return this.http.put<boolean>(
      `${this.API_ENDPOINT}profile`,
      changePasswordRequest
    );
  }
}
