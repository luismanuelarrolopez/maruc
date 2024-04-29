import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DifusionControl} from "../models/difusion-control";
import {loadApiUrl} from "../utils/enviroment-utils";

@Injectable({
  providedIn: 'root'
})
export class DifusionControlService {

  readonly BASE_API_URL: string = loadApiUrl();
  readonly ENDPOINT_URL: string = `${this.BASE_API_URL}difusion-control`;

  constructor(private http: HttpClient) {
  }

  fetchAll(): Observable<DifusionControl[]> {
    return this.http.get<DifusionControl[]>(this.ENDPOINT_URL);
  }
}
