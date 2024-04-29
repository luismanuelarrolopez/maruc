import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { FormConfig } from 'src/app/core/utils/FormConfig';
import { TipoProceso } from '../models/TipoProceso';

@Injectable({
  providedIn: 'root'
})
export class TipoProcesoService {

  private readonly API_ENDPOINT = loadApiUrl();
  private readonly TIPO_PROCESO_ENDPOINT = this.API_ENDPOINT + 'tipos-proceso/';
  dataChangeSubject = new BehaviorSubject({} as TipoProceso[]);
  dataSavedSubject = new BehaviorSubject(false);
  dataSelectedSubject = new BehaviorSubject({
    disabled: false,
    isEdit: false,
  } as FormConfig);
  constructor(private http: HttpClient) { }

  fetchTipoProceso(): Observable<TipoProceso[]> {
    return this.http.get<TipoProceso[]>(this.TIPO_PROCESO_ENDPOINT);
  }

}
