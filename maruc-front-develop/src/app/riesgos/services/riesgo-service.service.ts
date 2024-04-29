import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Control } from 'src/app/common/models/control';
import { PaginableResult } from 'src/app/core/common/models/paginable-result';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { buildPageHttpParams } from 'src/app/core/utils/function-utils';
import { Actividad } from '../models/actividad';
import { Causa } from '../models/causa';
import { CausaCritica } from '../models/causa-critica';
import { ControlResidual } from '../models/control-residual';
import { OpcionConsecuencia } from '../models/opcion-consecuencia';
import { Riesgo } from '../models/riesgo';
import { RiesgoDTO } from '../models/riesgo-dto';
import { RiesgoInherenteDTO } from '../models/riesgo-inherente-dto';
import { RiesgoResidualDTO } from '../models/riesgo-residual-dto';
import { TipoControl } from '../models/tipo-control';

@Injectable({
  providedIn: 'root',
})
export class RiesgoServiceService {
  readonly BASE_API_URL: string = loadApiUrl();
  readonly ENDPOINT_URL: string = `${this.BASE_API_URL}riesgos`;

  riesgoSelectedSubject = new BehaviorSubject<number>(undefined);
  riesgoFetchedSubject = new BehaviorSubject<RiesgoDTO>(undefined);

  constructor(private http: HttpClient) {}

  saveRiesgo(riesgo: any): Observable<any> {
    return this.http.post(`${this.ENDPOINT_URL}/info-inicial`, riesgo);
  }

  updateRiesgo(riesgo: any): Observable<any> {
    return this.http.put(`${this.ENDPOINT_URL}/info-inicial`, riesgo);
  }

  fetchRiesgo(idRiesgo: number): Observable<any> {
    console.log(idRiesgo);
    return this.http.get<any>(`${this.ENDPOINT_URL}/` + idRiesgo);
  }

  fetchRiesgos(
    page: number,
    size: number
  ): Observable<PaginableResult<Riesgo>> {
    return this.http.get<PaginableResult<Riesgo>>(`${this.ENDPOINT_URL}?`, {
      params: buildPageHttpParams(page, size),
    });
  }

  fetchTiposAfectacion(): Observable<any> {
    return this.http.get(`${this.ENDPOINT_URL}/tipos-afectacion`);
  }

  updateCausa(causa: Causa): Observable<Causa> {
    return this.http.put<Causa>(`${this.ENDPOINT_URL}/causa`, causa);
  }

  saveCausa(causa: Causa): Observable<Causa> {
    return this.http.post<Causa>(`${this.ENDPOINT_URL}/causa`, causa);
  }

  fetchCausas(idRiesgo: number): Observable<Causa[]> {
    return this.http.get<Causa[]>(`${this.ENDPOINT_URL}/causas/${idRiesgo}`);
  }

  fetchCausasCriticas(idRiesgo: number): Observable<CausaCritica[]> {
    return this.http.get<CausaCritica[]>(
      `${this.ENDPOINT_URL}/causas/${idRiesgo}/criticas`
    );
  }

  deleteCausa(idCausa: number): Observable<any> {
    return this.http.delete(`${this.ENDPOINT_URL}/causa/${idCausa}`);
  }

  /* Consecuencias */

  fetchConsecuencias(idRiesgo: number): Observable<OpcionConsecuencia[]> {
    return this.http.get<OpcionConsecuencia[]>(
      `${this.ENDPOINT_URL}/consecuencias/${idRiesgo}`
    );
  }

  saveConsecuencia(
    consecuencia: OpcionConsecuencia
  ): Observable<OpcionConsecuencia> {
    return this.http.post<OpcionConsecuencia>(
      `${this.ENDPOINT_URL}/consecuencia`,
      consecuencia
    );
  }

  deleteConsecuencia(idConseucencia: number): Observable<any> {
    return this.http.delete(
      `${this.ENDPOINT_URL}/consecuencia/${idConseucencia}`
    );
  }

  fetchActividades(): Observable<Actividad[]> {
    return this.http.get<Actividad[]>(`${this.ENDPOINT_URL}/actividades`);
  }

  fetchTiposControl(): Observable<TipoControl[]> {
    return this.http.get<TipoControl[]>(`${this.ENDPOINT_URL}/tipos-control`);
  }

  fetchRiesgoResidual(id: number): Observable<any> {
    return this.http.get<any>(`${this.ENDPOINT_URL}/residual/${id}`);
  }

  saveActividades(
    actividadesToSaved: {
      idRiesgo: number;
      idActividad: number;
      frecuencia: number;
    }[]
  ) {
    return this.http.post<any>(
      `${this.ENDPOINT_URL}/actividades`,
      actividadesToSaved
    );
  }

  updateStateRiesgo({ id, status }): Observable<boolean> {
    return this.http.put<boolean>(
      `${this.ENDPOINT_URL}/estado/${id}/${status}`,
      {}
    );
  }

  saveControles(controles: any[]) {
    return this.http.post<any>(`${this.ENDPOINT_URL}/controles`, controles);
  }

  fetchRiesgoInherente(idRiesgo: number): Observable<RiesgoInherenteDTO> {
    return this.http.get<RiesgoInherenteDTO>(
      `${this.ENDPOINT_URL}/riesgo-inherente/${idRiesgo}`
    );
  }

  /*
  Controles
  */
  fetchControles(idRiesgo: number): Observable<Control[]> {
    return this.http.get<Control[]>(
      `${this.ENDPOINT_URL}/controles/${idRiesgo}`
    );
  }

  updateControl(idControl: number, control: Control): Observable<boolean> {
    return this.http.put<boolean>(
      `${this.ENDPOINT_URL}/control/${idControl}`,
      control
    );
  }

  /*
    Riesgo residual
   */
  fetchInfoRiesgoResidual(idRiesgo: number): Observable<RiesgoResidualDTO> {
    return this.http.get<RiesgoResidualDTO>(
      `${this.ENDPOINT_URL}/residual/${idRiesgo}`
    );
  }

  saveRiesgoResidual(riesgoResidual: RiesgoResidualDTO): Observable<boolean> {
    return this.http.post<boolean>(
      `${this.ENDPOINT_URL}/residual`,
      riesgoResidual
    );
  }

  fetchControlesResiduales(
    idRiesgoResidual: number
  ): Observable<ControlResidual[]> {
    return this.http.get<ControlResidual[]>(
      `${this.ENDPOINT_URL}/residual/controles/${idRiesgoResidual}`
    );
  }

  saveControlResidual(
    idRiesgoResidual: number,
    control: ControlResidual
  ): Observable<boolean> {
    return this.http.post<boolean>(
      `${this.ENDPOINT_URL}/residual/controles/${idRiesgoResidual}`,
      control
    );
  }

  editControlResidual(
    idControlResidual: number,
    control: ControlResidual
  ): Observable<boolean> {
    return this.http.put<boolean>(
      `${this.ENDPOINT_URL}/residual/controles/${idControlResidual}`,
      control
    );
  }

  deleteControlResidual(idControlResidual: number): Observable<boolean> {
    return this.http.delete<boolean>(
      `${this.ENDPOINT_URL}/residual/controles/${idControlResidual}`
    );
  }

  updateMayorConsecuencia(
    idRiesgo: number,
    mayorConsecuencia: string
  ): Observable<void> {
    return this.http.put<void>(
      `${this.ENDPOINT_URL}/consecuencias/${idRiesgo}`,
      mayorConsecuencia
    );
  }
}
