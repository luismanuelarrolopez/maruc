import { Injectable } from '@angular/core';
import { Evidencia } from '../models/evidencia';
import { BehaviorSubject, catchError, Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { FormConfig } from 'src/app/core/utils/FormConfig';
import { Evidencia_Observacion } from '../models/evidencia_observacion';
import { Observacion } from '../models/Observacion';
import { Riesgo_Observacion } from '../models/riesgo_observacion';

@Injectable({
  providedIn: 'root'
})
export class ObservacionService {
  
  private readonly API_ENDPOINT = loadApiUrl();
  private readonly OBSERVACION_ENDPOINT = this.API_ENDPOINT + 'observacion/';
  dataChangeSubject = new BehaviorSubject({} as Evidencia_Observacion);
  dataSavedSubject = new BehaviorSubject(false);
  dataSelectedSubject = new BehaviorSubject({
    disabled: false,
    isEdit: false,
  } as FormConfig);
  constructor(private http: HttpClient, private toast: ToastrService) { }

  //Observación evidencia

  fetchById(id: number): Observable<Evidencia> {
    return this.http.get<Evidencia>(this.OBSERVACION_ENDPOINT+"seleccionar_evidencia/"+id)
  }

  fetchByIdEvidencia(id: number): Observable<Evidencia_Observacion> {
    return this.http.get<Evidencia_Observacion>(this.OBSERVACION_ENDPOINT+"seleccionar_observacion_evidencia_by_evidencia/" + id);
  }

  fetchByIdEvidenciaTipoActor(id_evidencia: number, codigo_actor: string): Observable<Evidencia_Observacion> {
    return this.http.get<any>(this.OBSERVACION_ENDPOINT+"seleccionar_observacion_evidencia_by_evidencia_and_codigo_actor/" + id_evidencia+"/"+codigo_actor);
  }

  listarEvidenciaObservacion(id_evidencia: number, codigo_actor: string): Observable<Evidencia_Observacion[]> {
    return this.http.get<Evidencia_Observacion[]>(this.OBSERVACION_ENDPOINT+"listar_evidencia_observacion/"  + id_evidencia+"/"+codigo_actor);
  }
  
  MergeObservacionSoporteEvidencia(id_evidencia: any, observacion: Observacion): Observable<any> {
    let jsonValues = {  
      id_entity: id_evidencia,
      observacion: observacion
    }
    return this.http.post(this.OBSERVACION_ENDPOINT+"merge_observacion_evidencia", jsonValues).pipe(
      catchError(e => {
        this.toast.error("Error al agregar la observación de evidencia")
        return throwError(e);
      })
    )
  }

  ListByIdEvidenciaTipoActor(id_evidencia: number, codigo_actor: string): Observable<Evidencia_Observacion[]> {
    return this.http.get<Evidencia_Observacion[]>(this.OBSERVACION_ENDPOINT+"listar_observacion_evidencia_by_evidencia_and_codigo_actor/" + id_evidencia+"/"+codigo_actor);
  }

  ListByIdEntityTipoActor(id_entity: number, codigo_actor: string, tipo_observacion : string): Observable<any[]> {
    return this.http.get<any[]>(this.OBSERVACION_ENDPOINT+"listar_observacion_entity_by_entity_and_codigo_actor/" + id_entity+"/"+codigo_actor+"/"+parseInt(tipo_observacion));
  }

  ActualizarObservacionSoporteEvidencia(id_evidencia: any, observacion: Evidencia_Observacion): Observable<any> {
    let jsonValues = {  
      id_entity: id_evidencia,
      id_entity_observacion: observacion.id,
      observacion: observacion.observacion
    }
    return this.http.put(this.OBSERVACION_ENDPOINT+"actualizar_observacion_evidencia", jsonValues).pipe(
      catchError(e => {
        this.toast.error("Error al actualizar el soporte de evidencia")
        return throwError(e);
      })
    )
  }  

  //observacion riesgo

  fetchByIdRiesgoTipoActor(id_riesgo: number, codigo_actor: string) {
    return this.http.get<any>(this.OBSERVACION_ENDPOINT+"seleccionar_observacion_riesgo_by_riesgo_and_codigo_actor/" + id_riesgo+"/"+codigo_actor);
  } 

  listarRiesgoObservacion(id_riesgo: number, codigo_actor: string): Observable<Riesgo_Observacion[]> {
    return this.http.get<Riesgo_Observacion[]>(this.OBSERVACION_ENDPOINT+"listar_riesgo_observacion/"  + id_riesgo+"/"+codigo_actor);
  }
  
  MergeObservacionSoporteRiesgo(id_riesgo: any, observacion: Observacion): Observable<any> {
    let jsonValues = {  
      id_entity: id_riesgo,
      observacion: observacion
    }
    return this.http.post(this.OBSERVACION_ENDPOINT+"merge_observacion_riesgo", jsonValues).pipe(
      catchError(e => {
        this.toast.error("Error al agregar la observación de Riesgo")
        return throwError(e);
      })
    )
  }

  ListByIdRiesgoTipoActor(id_Riesgo: number, codigo_actor: string): Observable<Riesgo_Observacion[]> {
    return this.http.get<Riesgo_Observacion[]>(this.OBSERVACION_ENDPOINT+"listar_observacion_riesgo_by_riesgo_and_codigo_actor/" + id_Riesgo+"/"+codigo_actor);
  }

  ActualizarObservacionRiesgo(id_riesgo: any, observacion: Riesgo_Observacion): Observable<any> {
    let jsonValues = {  
      id_entity: id_riesgo,
      id_entity_observacion: observacion.id,
      observacion: observacion.observacion
    }
    return this.http.put(this.OBSERVACION_ENDPOINT+"actualizar_observacion_riesgo", jsonValues).pipe(
      catchError(e => {
        this.toast.error("Error al actualizar el soporte de Riesgo")
        return throwError(e);
      })
    )
  }  
}
