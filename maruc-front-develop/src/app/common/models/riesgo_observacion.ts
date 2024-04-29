import { Riesgo } from "src/app/riesgos/models/riesgo";
import { Observacion } from "./Observacion";

export interface Riesgo_Observacion {
    id?: number;
    riesgo?: Riesgo;
    observacion?: Observacion;
}
