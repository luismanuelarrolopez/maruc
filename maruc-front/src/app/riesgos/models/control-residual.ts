import { Evidencia } from 'src/app/common/models/evidencia';
import { Periodicidad } from 'src/app/core/models/periodicidad';
import { TipoControl } from './tipo-control';

export class ControlResidual {
  id?: number;
  idTipoControl?: number;
  nombre?: string;
  responsable?: string;
  idPeriodicidad?: number;
  evidencia?: Evidencia;
  indicador?: string;
  tipoControl?: TipoControl;
  periodicidad?: Periodicidad;
}
