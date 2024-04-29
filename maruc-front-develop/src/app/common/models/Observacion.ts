import { Evidencia_Observacion } from "./evidencia_observacion";
import { TipoActor } from "./TipoActor";
import { TipoObservacion } from "./TipoObservacion";

export interface Observacion{
    id?: number;
    observacion?: string;
    evidenciaObservacion?: Evidencia_Observacion;
    corregida?: boolean;
    tipoActor?: TipoActor;
    tipoObservacion?: TipoObservacion;
    fechaCreacion?: Date;
    fechaActualizacion?: Date;
}