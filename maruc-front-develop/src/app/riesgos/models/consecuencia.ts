import { Opcion } from "./opcion";

export interface Consecuencia {
    id?: number;
    descripcion?: string;
    tipoCampo?: string;
    opciones?: Opcion[];
}
