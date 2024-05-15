import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NavegacionRiesgoService {
  changeTabSubject = new BehaviorSubject<number>(1);
  idRiesgoSubject = new BehaviorSubject<number>(0);

  constructor() {}
}
