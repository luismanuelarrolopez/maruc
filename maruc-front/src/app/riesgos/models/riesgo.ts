import { Catalogo } from 'src/app/core/models/catalogo';
import { ActividadListItem } from './actividad-list-item';
import { Causa } from './causa';
import { GuardarConsecuenciaVO } from './vo/guardar-consecuencia-vo';

export interface Riesgo {
  id: number;
  nombre: string;
  objetivo: string;
  relacionConObjetivo: boolean;
  motivacion: string;
  reponsabilidad: string;
  oportunidad: string;
  tipoProceso?: Catalogo;
  tipoRiesgo: {
    id: number;
    codigo: string;
    descripcion: string;
  };
  causas?: Causa[];
  consecuencia?: GuardarConsecuenciaVO;
  actividad?: ActividadListItem[];
  estatusInformacionRiesgo?: {
    valor: number;
  };
  mayorConsecuencia: string;
}
