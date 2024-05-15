import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  setValue(key: string, value: any) {
    localStorage.setItem(key, JSON.stringify(value));
  }
  
  getValue(key: string) {
    return localStorage.getItem(key);
  }
}
