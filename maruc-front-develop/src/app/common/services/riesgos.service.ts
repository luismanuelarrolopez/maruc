import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, throwError } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Riesgo } from '../models/riesgo';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { FormConfig } from 'src/app/core/utils/FormConfig';
import { buildPageHttpParams } from 'src/app/core/utils/function-utils';
import { PaginableResult } from 'src/app/core/common/models/paginable-result';
import { searchDto } from '../models/dto/searchDto';
import { reporteRiesgosDto } from '../models/dto/reporteRiesgosDto';

@Injectable({
  providedIn: 'root'
})
export class RiesgosService {

  private readonly API_ENDPOINT = loadApiUrl();
  private readonly RIESGOS_ENDPOINT = this.API_ENDPOINT + 'riesgos/';
  dataChangeSubject = new BehaviorSubject({} as Riesgo);
  dataSavedSubject = new BehaviorSubject(false);
  dataSelectedSubject = new BehaviorSubject({
    disabled: false,
    isEdit: false,
  } as FormConfig);
  constructor(private http: HttpClient, private toast: ToastrService) { }

  fetchRiesgos(
    page: number,
    size: number
  ): Observable<PaginableResult<Riesgo>> {
    return this.http.get<PaginableResult<Riesgo>>(this.RIESGOS_ENDPOINT, {
      params: buildPageHttpParams(page, size)
    });
  }

  fetchRiesgosDTO(
    page: number,
    size: number,
    all: boolean
  ): Observable<PaginableResult<any>> {
    return this.http.get<PaginableResult<any>>(this.RIESGOS_ENDPOINT+"listarRiesgoDTO", {
      params: new HttpParams().set('all', all).set('page', page.toString()).set('size', size.toString())
    });
  }

  fetchRiesgosLiderDTO(
    page: number,
    size: number,
    all: boolean,
    id_usuario: number
  ): Observable<PaginableResult<any>> {
    return this.http.get<PaginableResult<any>>(this.RIESGOS_ENDPOINT+"listarRiesgoLiderDTO", {
      params: new HttpParams().set('id_usuario', id_usuario).set('all', all).set('page', page.toString()).set('size', size.toString())
    });
  }

  fetchRiesgosByLider(id_lider_proceso){
    return this.http.get<Riesgo[]>(`${this.RIESGOS_ENDPOINT}ListarRiesgos/${id_lider_proceso}`);
  }

  fetchById(id: number): Observable<any> {
    return this.http.get<any>(`${this.RIESGOS_ENDPOINT}` + id);
  }

  filterByProceso(id_proceso: number, page: number, size: number){
    return this.http.get<PaginableResult<Riesgo>>(`${this.RIESGOS_ENDPOINT}`, {
      params: new HttpParams().set('id_proceso', id_proceso).set('page', page.toString()).set('size', size.toString())
    });
  }

  filterByTipoRiesgo(id_tipo_riesgo: number, page: number, size: number){
    return this.http.get<PaginableResult<Riesgo>>(`${this.RIESGOS_ENDPOINT}`, {
      params: new HttpParams().set('id_tipo_riesgo', id_tipo_riesgo).set('page', page.toString()).set('size', size.toString())
    });
  }

  filterByRiesgoResidual(riesgo_residual: number, page: number, size: number){
    return this.http.get<PaginableResult<Riesgo>>(`${this.RIESGOS_ENDPOINT}`, {
      params: new HttpParams().set('riesgo_residual', riesgo_residual.toString()).set('page', page.toString()).set('size', size.toString())
    });
  }

  filterByRiesgoInherente(riesgo_inherente: number, page: number, size: number){
    return this.http.get<PaginableResult<Riesgo>>(`${this.RIESGOS_ENDPOINT}`, {
      params: new HttpParams().set('riesgo_inherente', riesgo_inherente.toString()).set('page', page.toString()).set('size', size.toString())
    });
  }

  fetchSearch(values: searchDto, page: number, size: number) {
    let jsonValues = {
      search: values, page, size
    }
    console.log(jsonValues)
    return this.http.post<PaginableResult<Riesgo>>(`${this.RIESGOS_ENDPOINT}filter`, jsonValues);
  }

  notificarMaterializacion(id_riesgo, descripcion){
    return this.http.post(`${this.RIESGOS_ENDPOINT}notificar-materializacion`, {
      id_riesgo,
      descripcion
    });
  }

  fetchIndicadores(){
    return this.http.get<any>(`${this.RIESGOS_ENDPOINT}Indicadores`)
  }
  
  Descargar_Reporte_Observaciones(): Observable<any> {
    return this.http.get(`${this.RIESGOS_ENDPOINT}Descargar_Reporte_Observaciones`, {responseType: 'blob', observe: 'response'}).pipe(
      catchError(e => {
        this.toast.error("Error descargando el reporte de observaciones");
        return throwError(e);
      }))
  }

  Descargar_Reporte_Riesgos(datareport : reporteRiesgosDto): Observable<any> {
    return this.http.post(`${this.RIESGOS_ENDPOINT}Descargar_Reporte_Riesgos`, datareport,{responseType: 'blob', observe: 'response'}).pipe(
      catchError(e => {
        this.toast.error("Error descargando el mapa de riesgos");
        return throwError(e);
      }))
  }

}
