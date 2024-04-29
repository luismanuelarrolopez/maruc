import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CatalogosRoutingModule } from './catalogos-routing.module';
import { PrincipalCatalogoComponent } from './pages/principal-catalogo/principal-catalogo.component';
import { CatalogoConsecuenciaComponent } from './pages/catalogo-consecuencia/catalogo-consecuencia.component';
import { CatalogoActividadComponent } from './pages/catalogo-actividad/catalogo-actividad.component';
import { FormularioActividadComponent } from './components/formulario-actividad/formulario-actividad.component';
import { ListaActividadesComponent } from './components/lista-actividades/lista-actividades.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CoreModule } from '../core/core.module';
import { FormularioConsecuenciaComponent } from './components/formulario-consecuencia/formulario-consecuencia.component';
import { ListaConsecuenciasComponent } from './components/lista-consecuencias/lista-consecuencias.component';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CatalogosRoutingModule,
    FormsModule,
    NgbPaginationModule,
    CoreModule
  ],
  declarations: [
    PrincipalCatalogoComponent,
    CatalogoConsecuenciaComponent,
    CatalogoActividadComponent,
    FormularioActividadComponent,
    ListaActividadesComponent,
    FormularioConsecuenciaComponent,
    ListaConsecuenciasComponent,
  ],
})
export class CatalogosModule {}
