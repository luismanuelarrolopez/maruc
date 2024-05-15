import { TipoControl } from './tipo-control';
import { Periodicidad } from '../../core/models/periodicidad';
import { DifusionControl } from '../../core/models/difusion-control';
import { PuntajeDTO } from './puntaje-dto';

export interface Control {
  id?: number;
  tipoControl?: TipoControl;
  aplica?: boolean;
  puntajeTipoControl?: number;
  nombre?: string;
  responsable?: string;
  ejecucionSistemasDigitales?: {
    id?: number;
    nombre?: string;
  };
  empleaOEjecuta?: {
    id?: number;
    nombre?: string;
  };
  puntajeControl?: PuntajeDTO;
  difusion?: DifusionControl;
  periodicidadEjecucion?: Periodicidad;
  periodicidadSegumiento?: Periodicidad;
  valoracion?: number;
}
