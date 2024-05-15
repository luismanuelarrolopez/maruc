import {Injectable} from '@angular/core';
import {loadApiUrl} from "../utils/enviroment-utils";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Periodicidad} from "../models/periodicidad";

@Injectable({
  providedIn: 'root'
})
export class PeriodicidadService {
  readonly BASE_API_URL: string = loadApiUrl();
  readonly ENDPOINT_URL: string = `${this.BASE_API_URL}periodicidad`;

  constructor(private http: HttpClient) {
  }

  fetchAll(): Observable<Periodicidad[]> {
    return this.http.get<Periodicidad[]>(this.ENDPOINT_URL);
  }
}
