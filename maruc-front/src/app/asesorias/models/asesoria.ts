export interface Asesoria {
  id: number;
  tema: string;
  fechaSolicitud: Date;
  fechaAgendada: Date;
  solicitante: {
    id: number;
    nombres: string;
    apellidos: string;
  };
}
