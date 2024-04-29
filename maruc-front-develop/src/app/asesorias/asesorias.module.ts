import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AsesoriasRoutingModule } from './asesorias-routing.module';
import { CoreModule } from '../core/core.module';
import { PrincipalAsesoriasComponent } from './pages/principal-asesorias/principal-asesorias.component';
import { ListaAsesoriasComponent } from './components/lista-asesorias/lista-asesorias.component';
import {
  NgbDatepickerModule,
  NgbPaginationModule,
} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormularioAgendarAsesoriaComponent } from './components/formulario-agendar-asesoria/formulario-agendar-asesoria.component';

@NgModule({
  declarations: [
    PrincipalAsesoriasComponent,
    ListaAsesoriasComponent,
    FormularioAgendarAsesoriaComponent,
  ],
  imports: [
    CommonModule,
    CoreModule,
    AsesoriasRoutingModule,
    NgbPaginationModule,
    ReactiveFormsModule,
    FormsModule,
    NgbDatepickerModule,
  ],
})
export class AsesoriasModule {}
