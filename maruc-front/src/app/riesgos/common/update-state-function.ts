import { rootInjector } from 'src/app/core/utils/root-injector';
import { RiesgoServiceService } from '../services/riesgo-service.service';

export function updateStateRiesgo(config: {
  id: number;
  status: string;
}): void {
  const service = rootInjector.get(RiesgoServiceService);
  service.updateStateRiesgo(config).subscribe({
    complete: () => console.log('Riesgo actualizado correctamente'),
  });
}
