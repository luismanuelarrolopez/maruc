import { OpcionConsecuencia } from './OpcionConsecuencia';

export interface CargarConsecuenciaVO {
  id?: number;
  descripcion: string;
  tipoCampo: string;
  idTipoAfectacion: number;
  listaOpciones: Array<OpcionConsecuencia>;
}
