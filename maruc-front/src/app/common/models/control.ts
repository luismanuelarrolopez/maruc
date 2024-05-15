import { TipoControl } from "src/app/riesgos/models/tipo-control";
import { Evidencia } from "./evidencia";

export interface Control {
    id?: number;
    causa?: string;
    control?: string;
    responsable?: string;
    periodicidad?: string;
    tipoControl?: TipoControl;
    evidencia?: Evidencia;
}
