import { OpcionConsecuencia } from './OpcionConsecuencia';
import { TipoAfectacionDTO } from './TipoAfectacionDTO';

export interface ConsecuenciaDTO {
  id?: number;
  descripcion?: string;
  tipoCampo?: 'seleccion' | 'texto';
  tipoAfectacion?: TipoAfectacionDTO;
  opciones?: OpcionConsecuencia[];
}
