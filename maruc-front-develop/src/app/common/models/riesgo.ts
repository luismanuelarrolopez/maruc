import { EstatusInfoRiesgo } from "src/app/riesgos/models/estatus-info-riesgo";
import { Control } from "./control";
import { TipoProceso } from "./TipoProceso";
import { TipoRiesgo } from "./TipoRiesgo";

export interface Riesgo {
    id?: number;
    nombre?: string;
    tipoRiesgo?: TipoRiesgo;
    tipoProceso?: TipoProceso;
    proceso?: string;
    riesgoInherente?: number;
    riesgoResidual?: number;
    controles?: Control[];
    relacionConObjetivo?: boolean;
    objetivo?: string;
    estatusInformacionRiesgo?: EstatusInfoRiesgo;
}
