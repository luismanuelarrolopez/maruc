import { Control } from "./control";
import { SoporteEvidencia } from "./soporte-evidencia";

export interface Evidencia {
    id?: number;
    porcentajeAvanceOci?: number;
    porcentajeAvanceOpdi?: number;
    cumplimiento_oci?: boolean
    cumplimiento_opdi?: boolean
    evidencia?: string;
    id_control?: number;
    soporteEvidencia?: SoporteEvidencia[];
    control?: Control;
}
