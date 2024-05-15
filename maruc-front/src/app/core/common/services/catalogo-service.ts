import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Catalogo } from '../../models/catalogo';
import { loadApiUrl } from '../../utils/enviroment-utils';

@Injectable({ providedIn: 'root' })
export class CatalogoService {
  private baseUrl = `${loadApiUrl()}catalogo`;
  constructor(private http: HttpClient) {}

  fetchCatalogo(catalogo: string): Observable<Catalogo[]> {
    return this.http.get<Catalogo[]>(`${this.baseUrl}/${catalogo}`);
  }
}
