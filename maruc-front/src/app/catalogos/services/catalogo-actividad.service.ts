import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Paginable } from 'src/app/core/common/models/paginable';
import { PaginableResult } from 'src/app/core/common/models/paginable-result';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { ActividadDTO } from '../models/ActividadDTO';

@Injectable({
  providedIn: 'root',
})
export class CatalogoActividadService {
  readonly BASE_API_URL: string = loadApiUrl();
  readonly ENDPOINT_URL: string = `${this.BASE_API_URL}actividades/`;
  dataSavedSubject = new BehaviorSubject(false);


  constructor(private http: HttpClient) {}

  obtenerListaActividades(page: number, size: number): Observable<PaginableResult<ActividadDTO>> {
    return this.http.get<PaginableResult<ActividadDTO>>(this.ENDPOINT_URL, {
      params: { page: page.toString(), size: size.toString() },
    });
  }

  guardarActividad(actividad: ActividadDTO): Observable<ActividadDTO> {
    return this.http.post<ActividadDTO>(this.ENDPOINT_URL, actividad);
  }

  editarActividad(
    id: number,
    actividad: ActividadDTO
  ): Observable<ActividadDTO> {
    return this.http.put<ActividadDTO>(
      `${this.ENDPOINT_URL}${id}`,
      actividad
    );
  }

  buscarPorId(id: number): Observable<ActividadDTO> {
    return this.http.get<ActividadDTO>(`${this.ENDPOINT_URL}${id}`);
  }

  eliminarActividad(idActividad: number): Observable<any> {
    return this.http.delete(`${this.ENDPOINT_URL}${idActividad}`);
  }
}
