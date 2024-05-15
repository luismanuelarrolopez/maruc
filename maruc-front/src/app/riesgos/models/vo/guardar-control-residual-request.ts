import { ControlResidual } from '../control-residual';

export interface GuardarControlResidualRequest {
  idRiesgoResidual: number;
  controlResidual: ControlResidual;
  listaIdCausas: Array<number>;
  evidencia: string;
}
