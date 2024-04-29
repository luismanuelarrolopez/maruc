import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpParams} from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { FormConfig } from 'src/app/core/utils/FormConfig';
import { Notificacion } from '../models/notificacion';
import { PaginableResult } from 'src/app/core/common/models/paginable-result';

@Injectable({
  providedIn: 'root'
})
export class NotificacionService {

  private readonly API_ENDPOINT = loadApiUrl();
  private readonly NOTIFICACION_ENDPOINT = this.API_ENDPOINT + 'notificacion/';
  dataChangeSubject = new BehaviorSubject({} as Notificacion);
  dataSavedSubject = new BehaviorSubject(false);
  dataSelectedSubject = new BehaviorSubject({
    disabled: false,
    isEdit: false,
  } as FormConfig);
  constructor(private http: HttpClient, private toast: ToastrService) { }

  fetch(user_id: number, page: number, size: number) {
    return this.http.get<PaginableResult<Notificacion>>(`${this.NOTIFICACION_ENDPOINT+user_id}`, {
      params: new HttpParams().set('page', page.toString()).set('size', size.toString())
    });
  }

  fetchCountNotificacionesSinLeer(user_id: number) {
    return this.http.get<number>(`${this.NOTIFICACION_ENDPOINT+"notificaciones_sin_leer/"+user_id}`);
  }

  marcarLeida(notificacion: Notificacion): Observable<Notificacion> {
    return this.http.put<Notificacion>(this.NOTIFICACION_ENDPOINT+"marcar_leida/"+notificacion.id, null);
  }
}
