import { Evidencia } from "./evidencia";

export interface SoporteEvidencia {
    id?: number;
    nombre?: string;
    ruta_soporte?: string;
    fechaCreacion?: Date;
    evidencia_id?: number;
    evidencia?: Evidencia;
}
