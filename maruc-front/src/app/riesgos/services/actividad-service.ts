import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { Actividad } from '../models/actividad';
import { RiesgoActividadDTO } from '../models/riesgo-actividad-dto';

@Injectable({ providedIn: 'root' })
export class ActividadService {
  readonly ENDPOINT_URL: string = `${loadApiUrl()}riesgos/actividades`;
  constructor(private _http: HttpClient) {}

  fetchActividadesNotInRiesgo(idRiesgo: number): Observable<Actividad[]> {
    return this._http.get<Actividad[]>(
      `${this.ENDPOINT_URL}/notIn/${idRiesgo}`
    );
  }

  fetchRiesgoActividades(idRiesgo: number): Observable<RiesgoActividadDTO[]> {
    return this._http.get<RiesgoActividadDTO[]>(
      `${this.ENDPOINT_URL}/${idRiesgo}`
    );
  }

  saveActividad(actividad: RiesgoActividadDTO): Observable<boolean> {
    return this._http.post<boolean>(`${this.ENDPOINT_URL}`, actividad);
  }

  deleteActividad(idActividad: number): Observable<boolean> {
    return this._http.delete<boolean>(`${this.ENDPOINT_URL}/${idActividad}`);
  }
}
