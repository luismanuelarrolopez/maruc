import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { Causa } from '../models/causa';
import { GuardarControlResidualRequest } from '../models/vo/guardar-control-residual-request';

@Injectable({
  providedIn: 'root',
})
export class CausaService {
  private readonly BASE_API_URL: string = loadApiUrl();
  private readonly ENDPOINT_URL: string = `${this.BASE_API_URL}riesgos`;

  constructor(private http: HttpClient) {}

  fetchCausasSinControlResidual(idRiesgo: number): Observable<Causa[]> {
    return this.http.get<Causa[]>(
      `${this.ENDPOINT_URL}/controles/residuales/${idRiesgo}`
    );
  }

  fetchCausasDeControlResidual(
    idRiesgo: number,
    idControlResidual: number
  ): Observable<Causa[]> {
    return this.http.get<Causa[]>(
      `${this.ENDPOINT_URL}/controles/residuales/${idRiesgo}/${idControlResidual}`
    );
  }

  saveControlResidual(
    request: GuardarControlResidualRequest
  ): Observable<boolean> {
    return this.http.post<boolean>(
      `${this.ENDPOINT_URL}/controles/residuales/`,
      request
    );
  }

  updateControlResidual(
    idControlResidual: number,
    request: GuardarControlResidualRequest
  ): Observable<boolean> {
    return this.http.put<boolean>(
      `${this.ENDPOINT_URL}/controles/residuales/${idControlResidual}`,
      request
    );
  }
}
