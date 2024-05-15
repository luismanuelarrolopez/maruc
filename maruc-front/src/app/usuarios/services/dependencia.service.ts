import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { Dependencia } from '../models/dependencia';

@Injectable({
    providedIn: 'root'
})
export class DependenciaService {
  private readonly API_ENDPOINT = loadApiUrl();
  private readonly DEPENDENCIAS_ENDPOINT = this.API_ENDPOINT + 'dependencias/';

  constructor(private http: HttpClient) {}

  fetchDependencias(): Observable<Dependencia[]> {
    return this.http.get<Dependencia[]>(
      this.DEPENDENCIAS_ENDPOINT
    );
  }
}
