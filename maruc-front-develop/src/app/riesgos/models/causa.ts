export interface Causa {
  id: number;
  causa: string;
  porque1: string;
  porque2: string;
  porque3: string;
  puntaje1: number;
  puntaje2: number;
  puntaje3: number;
  sumatoria: number;
  idRiesgo?: number;
  causaInicial?: string;
}
