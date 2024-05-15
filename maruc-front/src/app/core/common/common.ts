import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';

export function transforError(error: HttpErrorResponse | string) {
  let errorMessage = 'Un error inesperado ha ocurrido';
  if (typeof error === 'string') {
    errorMessage = error;
  } else if (error.error instanceof ErrorEvent) {
    errorMessage = `Error! ${error.error.message}`;
  } else if (error.status) {
    errorMessage = `El error falló con ${error.status} ${error.statusText}`;
  }
  return throwError(() => errorMessage);
}

export class MarucErrorResponse {
  codigoError: string;
  mensaje: string;
  codigoHttp: string;
  url: string;
  metodo: string;
}

export function transformError(error: HttpErrorResponse) {
  return throwError(() => error.error as MarucErrorResponse);
}
