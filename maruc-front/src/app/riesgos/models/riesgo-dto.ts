
import { Causa } from "./causa";
import { Riesgo } from "./riesgo";

export interface RiesgoDTO {
    riesgo?: Riesgo;
    causas?: Causa[];
}
