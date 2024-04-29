import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonComponentsModule } from '../common/components/common-components.module';
import { CoreModule } from '../core/core.module';
import { CausasComponent } from './components/causas/causas.component';
import { ConsecuenciaComponent } from './components/consecuencia/consecuencia.component';
import { InformacionBasicaComponent } from './components/informacion-basica/informacion-basica.component';
import { ListaRiesgosComponent } from './components/lista-riesgos/lista-riesgos.component';
import { ProbabilidadComponent } from './components/probabilidad/probabilidad.component';
import { RiesgoInherenteComponent } from './components/riesgo-inherente/riesgo-inherente.component';
import { ControlesResidualesComponent } from './components/riesgo-residual/controles-residuales/controles-residuales.component';
import { InfoRiesgoResidualComponent } from './components/riesgo-residual/info-riesgo-residual/info-riesgo-residual.component';
import { RiesgoResidualComponent } from './components/riesgo-residual/riesgo-residual.component';
import { ControlFormComponent } from './components/valoracion/control-form/control-form.component';
import { ValoracionComponent } from './components/valoracion/valoracion.component';
import { ObservacionRiesgoComponent } from './pages/pagina-registro-riesgos/observacion_riesgo/observacion_riesgo.component';
import { PaginaRegistroRiesgosComponent } from './pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { SiNoPipe } from './pipes/si-no.pipe';
import { RiesgosRoutingModule } from './riesgos-routing.module';

@NgModule({
  declarations: [
    PaginaRegistroRiesgosComponent,
    InformacionBasicaComponent,
    CausasComponent,
    ListaRiesgosComponent,
    ConsecuenciaComponent,
    ProbabilidadComponent,
    RiesgoInherenteComponent,
    ValoracionComponent,
    ControlFormComponent,
    SiNoPipe,
    RiesgoResidualComponent,
    ObservacionRiesgoComponent,
    InfoRiesgoResidualComponent,
    ControlesResidualesComponent,
  ],
  imports: [
    CommonModule,
    CoreModule,
    NgbNavModule,
    RiesgosRoutingModule,
    CommonComponentsModule,
  ],
})
export class RiesgosModule {}
