import { Causa } from "./causa";
import { ControlResidual } from "./control-residual";

export interface CausaCritica {
    causa: Causa;
    valoracion: number;
    controlResidual: ControlResidual;
}
