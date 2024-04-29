import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { loadApiUrl } from 'src/app/core/utils/enviroment-utils';
import { Rol } from '../models/rol';

@Injectable({
  providedIn: 'root',
})
export class RolesService {
  private readonly API_ENDPOINT = loadApiUrl();
  private readonly ROLES_ENDPOINT = this.API_ENDPOINT + 'roles/';
  constructor(private http: HttpClient) {}

  fetchRoles(): Observable<Rol[]> {
    return this.http.get<Rol[]>(this.ROLES_ENDPOINT);
  }
}
