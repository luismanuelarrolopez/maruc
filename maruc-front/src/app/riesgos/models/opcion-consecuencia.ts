export interface OpcionConsecuencia {
  id?: number;
  idRiesgo: number;
  idOpcionConsecuencia: number;
  tipoAfectacion?: string;
  consecuencia?: string;
  puntaje?: number;
}
