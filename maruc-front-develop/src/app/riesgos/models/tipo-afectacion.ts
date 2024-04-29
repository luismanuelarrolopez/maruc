import { Consecuencia } from "./consecuencia";

export interface TipoAfectacion {
    id?: number;
    nombre?: string;
    codigo?: string;
    consecuencias?: Consecuencia[];
}
