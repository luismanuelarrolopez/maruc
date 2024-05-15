import { Injectable } from '@angular/core';
import { Evidencia } from '../models/evidencia';
import { BehaviorSubject, catchError, Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { FormConfig } from 'src/app/core/utils/FormConfig';
import { evidenciaDTO } from '../models/dto/evidenciaDto';
import ETipoActor from '../models/enums/ETipoActor';

@Injectable({
  providedIn: 'root'
})
export class EvidenciaService {

  private readonly API_ENDPOINT = loadApiUrl();
  private readonly EVIDENCIAS_ENDPOINT = this.API_ENDPOINT + 'evidencias/';
  private readonly SOPORTE_EVIDENCIAS_ENDPOINT = this.API_ENDPOINT + 'soporte_evidencia/';
  dataChangeSubject = new BehaviorSubject({} as Evidencia);
  dataSavedSubject = new BehaviorSubject(false);
  dataSelectedSubject = new BehaviorSubject({
    disabled: false,
    isEdit: false,
  } as FormConfig);
  constructor(private http: HttpClient, private toast: ToastrService) { }

  getEvidencia(id_evidencia: number): Observable<Evidencia> {
    return this.http.get<Evidencia>(this.EVIDENCIAS_ENDPOINT+"seleccionar_evidencia/"+id_evidencia)
  }

  fetchById(id: number): Observable<evidenciaDTO> {
    return this.http.get<evidenciaDTO>(`${this.EVIDENCIAS_ENDPOINT}` + id);
  }

  agregarSoporteEvidencia(soporte_evidencia: any): Observable<HttpEvent<{}>> {
    const req = new HttpRequest('POST', this.SOPORTE_EVIDENCIAS_ENDPOINT+"agregar_soporte_evidencia", soporte_evidencia, { reportProgress: true });
    return this.http.request(req);
  }

  actualizarSoporteEvidencia(soporte_evidencia: any): Observable<any> {
    let jsonValues = {...soporte_evidencia, evidencia: {id: soporte_evidencia.evidencia_id}}
    return this.http.put(this.SOPORTE_EVIDENCIAS_ENDPOINT+"actualizar_soporte_evidencia", jsonValues).pipe(
      catchError(e => {
        this.toast.error("Error al actualizar el soporte de evidencia")
        return throwError(e);
      })
    )
  }

  MergeObservacionSoporteEvidencia(soporte_evidencia: any): Observable<any> {
    let jsonValues = {...soporte_evidencia, evidencia: {id: soporte_evidencia.evidencia_id}}
    return this.http.put(this.SOPORTE_EVIDENCIAS_ENDPOINT+"actualizar_soporte_evidencia", jsonValues).pipe(
      catchError(e => {
        this.toast.error("Error al actualizar el soporte de evidencia")
        return throwError(e);
      })
    )
  }

  eliminarSoporteEvidencia(id: number): Observable<any> {
    return this.http.delete(this.SOPORTE_EVIDENCIAS_ENDPOINT+"eliminar_soporte_evidencia/"+id).pipe(
      catchError(e => {
        this.toast.error("Error eliminando el soporte de evidencia");
        console.log(e.error)
        return throwError(e);
      })
    );
  }

  descargarSoporteEvidencia(id: number): Observable<any> {
    return this.http.get(this.SOPORTE_EVIDENCIAS_ENDPOINT+"descargar_soporte_evidencia/"+id, {responseType: 'blob', observe: 'response'}).pipe(
      catchError(e => {
        this.toast.error("Error descargando el soporte de evidencia");
        return throwError(e);
      }))
  }

  getFileSoporteEvidencia(id: number): Observable<any> {
    return this.http.get(this.SOPORTE_EVIDENCIAS_ENDPOINT+"descargar_soporte_evidencia/"+id);
  }

  actualizarEvidencia(evidencia : Evidencia): Observable<any>{
    return this.http.put(this.EVIDENCIAS_ENDPOINT,evidencia).pipe(
      catchError(e => {
        this.toast.error("Error actualizando la evidencia")
        return throwError(e);
      }))
  }

  actualizarEvaluacion(evidencia : Evidencia, tipo_actor : string): Observable<any>{
    let jsonValues = {evidencia : evidencia, codigo_actor: tipo_actor};
    console.log(jsonValues);
    return this.http.put(this.EVIDENCIAS_ENDPOINT+"actualizarEvaluacion",jsonValues).pipe(
      catchError(e => {
        this.toast.error("Error actualizando la evidencia")
        return throwError(e);
      }))
  }
}
