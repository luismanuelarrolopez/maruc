import { Rol } from './rol';

export interface Usuario {
  id?: number;
  nombres?: string;
  apellidos?: string;
  email?: string;
  rol?: Rol;
}
