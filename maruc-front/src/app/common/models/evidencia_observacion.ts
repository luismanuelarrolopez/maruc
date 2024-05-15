import { Evidencia } from "./evidencia";
import { Observacion } from "./Observacion";

export interface Evidencia_Observacion {
    id?: number;
    evidencia?: Evidencia;
    observacion?: Observacion;
}
