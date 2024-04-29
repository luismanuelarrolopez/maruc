import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PaginableResult } from 'src/app/core/common/models/paginable-result';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { buildPageHttpParams } from 'src/app/core/utils/function-utils';
import { Asesoria } from '../models/asesoria';
import { GuardarAsesoriaDto } from '../models/guardar-asesoria-dto';

@Injectable({
  providedIn: 'root',
})
export class AsesoriasService {
  private readonly API_ENDPOINT = loadApiUrl();
  private readonly ASESORIAS_ENDPOINT = this.API_ENDPOINT + 'asesorias/';
  dataChangeSubject = new BehaviorSubject(false);
  dataSelectedSubject = new BehaviorSubject(0);
  constructor(private http: HttpClient) {}

  fetchAsesorias(
    page: number,
    size: number,
    oficinaAsesora
  ): Observable<PaginableResult<Asesoria>> {
    return this.http.get<PaginableResult<Asesoria>>(
      `${this.ASESORIAS_ENDPOINT}${oficinaAsesora}`,
      {
        params: buildPageHttpParams(page, size),
      }
    );
  }

  saveAsesoria(asesoria: GuardarAsesoriaDto): Observable<Asesoria> {
    return this.http.post<Asesoria>(this.ASESORIAS_ENDPOINT, asesoria);
  }

  updateAsesoria({ fechaAgenda, idAsesoria }): Observable<Asesoria> {
    return this.http.put<Asesoria>(`${this.ASESORIAS_ENDPOINT}${idAsesoria}`, {
      fechaAgenda,
    });
  }
}
