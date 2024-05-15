import { Injectable } from '@angular/core';
import { Evidencia } from '../models/evidencia';
import { BehaviorSubject, catchError, Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { FormConfig } from 'src/app/core/utils/FormConfig';
import { evidenciaDTO } from '../models/dto/evidenciaDto';
import { VersionRiesgo } from '../models/version_riesgo';

@Injectable({
  providedIn: 'root'
})
export class VersionesService {
  private readonly API_ENDPOINT = loadApiUrl();
  private readonly VERSIONES_ENDPOINT = this.API_ENDPOINT + 'version_riesgo/';
  dataChangeSubject = new BehaviorSubject({} as VersionRiesgo);
  dataSavedSubject = new BehaviorSubject(false);
  dataSelectedSubject = new BehaviorSubject({
    disabled: false,
    isEdit: false,
  } as FormConfig);
  constructor(private http: HttpClient, private toast: ToastrService) { }

  listarVersiones(): Observable<any> {
    return this.http.get(this.VERSIONES_ENDPOINT+"listar").pipe(
      catchError(e => {
        this.toast.error("Error listando versiones");
        return throwError(e);
      }))
  }

  agregarVersionRiesgo(version_riesgo: any): Observable<HttpEvent<{}>> {
    const req = new HttpRequest('POST', this.VERSIONES_ENDPOINT+"agregar_version_riesgo", version_riesgo, { reportProgress: true });
    return this.http.request(req);
  }

  actualizarVersionRiesgo(version_riesgo: any): Observable<any> {
    return this.http.put(this.VERSIONES_ENDPOINT+"actualizar_version_riesgo", version_riesgo).pipe(
      catchError(e => {
        this.toast.error("Error al actualizar la versión de riesgos")
        return throwError(e);
      })
    )
  }

  MergeObservacionVersionRiesgo(version_riesgo: any): Observable<any> {
    return this.http.put(this.VERSIONES_ENDPOINT+"actualizar_version_riesgo", version_riesgo).pipe(
      catchError(e => {
        this.toast.error("Error al actualizar la versión de riesgos")
        return throwError(e);
      })
    )
  }

  eliminarVersionRiesgo(id: number): Observable<any> {
    return this.http.delete(this.VERSIONES_ENDPOINT+"eliminar_version_riesgo/"+id).pipe(
      catchError(e => {
        this.toast.error("Error eliminando la versión de riesgos");
        console.log(e.error)
        return throwError(e);
      })
    );
  }

  descargarVersionRiesgo(id: number): Observable<any> {
    return this.http.get(this.VERSIONES_ENDPOINT+"descargar_version_riesgo/"+id, {responseType: 'blob', observe: 'response'}).pipe(
      catchError(e => {
        this.toast.error("Error descargando la versión de riesgos");
        return throwError(e);
      }))
  }

  getFileVersionRiesgo(id: number): Observable<any> {
    return this.http.get(this.VERSIONES_ENDPOINT+"descargar_version_riesgo/"+id);
  }

  
}
