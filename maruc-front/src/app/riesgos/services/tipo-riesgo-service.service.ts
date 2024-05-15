import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { TipoRiesgo } from '../models/tipo-riesgo';

@Injectable({
  providedIn: 'root'
})
export class TipoRiesgoServiceService {
  readonly BASE_API_URL: string = loadApiUrl();
  readonly ENDPOINT_URL: string = `${this.BASE_API_URL}tipo-riesgo`;

  constructor(private http: HttpClient) { }

  fetchTipoRiesgo(): Observable<TipoRiesgo[]> {
    return this.http.get<TipoRiesgo[]>(this.ENDPOINT_URL);
  }
}
