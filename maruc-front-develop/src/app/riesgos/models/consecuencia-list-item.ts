import { Consecuencia } from './consecuencia';
import { Opcion } from './opcion';
import { TipoAfectacion } from './tipo-afectacion';

export class ConsecuenciaListItem {
  constructor(
    public tipoAfectacion: TipoAfectacion,
    public consecuencia: Consecuencia,
    public opcion: Opcion
  ) {}
}
