import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import { PaginableResult } from 'src/app/core/common/models/paginable-result';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { FormConfig } from 'src/app/core/utils/FormConfig';
import { CargarConsecuenciaVO } from '../models/CargarConsecuenciaVO';
import { ConsecuenciaDTO } from '../models/ConsecuenciaDTO';
import { TipoAfectacionDTO } from '../models/TipoAfectacionDTO';

@Injectable({
  providedIn: 'root',
})
export class CatalogoConsecuenciaService {
  private readonly API_ENDPOINT = loadApiUrl();
  private readonly TIPO_AFECTACION_ENDPOINT =
    this.API_ENDPOINT + 'tipos-afectacion/';
  private readonly CONSECUENCIA_ENDPOINT = this.API_ENDPOINT + 'consecuencias/';
  dataChangeSubject = new BehaviorSubject({} as ConsecuenciaDTO);
  dataSavedSubject = new Subject<boolean>();
  dataSelectedSubject = new BehaviorSubject({
    disabled: false,
    isEdit: false,
  } as FormConfig);

  constructor(private http: HttpClient) {}

  fetchTiposAfectacion(): Observable<TipoAfectacionDTO[]> {
    return this.http.get<TipoAfectacionDTO[]>(this.TIPO_AFECTACION_ENDPOINT);
  }

  fetchConsecuencias(
    page: number,
    size: number
  ): Observable<PaginableResult<ConsecuenciaDTO>> {
    return this.http.get<PaginableResult<ConsecuenciaDTO>>(
      this.CONSECUENCIA_ENDPOINT,
      {
        params: { page: page.toString(), size: size.toString() },
      }
    );
  }

  fetchById(id: number): Observable<ConsecuenciaDTO> {
    return this.http.get<ConsecuenciaDTO>(`${this.CONSECUENCIA_ENDPOINT}${id}`);
  }

  fetchCatalogoById(id: number): Observable<CargarConsecuenciaVO> {
    return this.http.get<CargarConsecuenciaVO>(
      `${this.CONSECUENCIA_ENDPOINT}${id}`
    );
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`${this.CONSECUENCIA_ENDPOINT}${id}`);
  }

  guardar(consecuencia: ConsecuenciaDTO): Observable<ConsecuenciaDTO> {
    return this.http.post<ConsecuenciaDTO>(
      this.CONSECUENCIA_ENDPOINT,
      consecuencia
    );
  }

  actualizar(consecuencia: CargarConsecuenciaVO): Observable<boolean> {
    return this.http.put<boolean>(this.CONSECUENCIA_ENDPOINT, consecuencia);
  }

  eliminarOpcion(id: number): Observable<any> {
    return this.http.delete(`${this.CONSECUENCIA_ENDPOINT}opcion/${id}`)
  }
}
