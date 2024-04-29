import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiesgosTableComponent } from './riesgos-table/riesgos-table.component';
import { ControlesTableComponent } from './controles-table/controles-table.component';
import { CoreModule } from 'src/app/core/core.module';
import { RouterModule } from '@angular/router';
import { SeguimientoTableComponent } from './seguimiento-table/seguimiento-table.component';
import { IndicadoresComponent } from './indicadores/indicadores.component';
import { ListarObservacionesComponent } from './listar-observaciones/listar-observaciones.component';
import { VerSoporteEvidenciaComponent } from './ver-soporte-evidencia/ver-soporte-evidencia.component';
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { ObservacionPreviaComponent } from './observacion-previa/observacion-previa.component';
import { AgregarObservacionModalComponent } from './agregar-observacion-modal/agregar-observacion-modal.component';
import { ObservacionSoporteComponent } from './observacion-soporte/observacion_soporte.component';
import { EvidenciasComponent } from './evidencias/evidencias.component';
import { EditarSoporteComponent } from './evidencias/editar_soporte/editar_soporte.component';
import { AgregarSoporteComponent } from './evidencias/agregar_soporte/agregar_soporte.component';
import { RiesgosComponent } from './riesgos/riesgos.component';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { ControlesComponent } from './riesgos/controles/controles.component';
import { AgregarCalificacionComponent } from './evidencias/agregar-calificacion/agregar-calificacion.component';
import { SeguimientoComponent } from './seguimiento/seguimiento.component';
import { ControlesSeguimientoComponent } from './seguimiento/controles/controles.component';
import { VersionesComponent } from './versiones/versiones.component';
import { VersionRiesgosComponent } from './versiones/agregar-version/version_riesgos.component';
import { EditarVersionRiesgosComponent } from './versiones/editar-version/editar_version.component';
import { VerVersionComponent } from './versiones/ver-version-riesgo/ver-version.component';
@NgModule({
  imports: [
    CommonModule, 
    CoreModule,
    RouterModule,
    FontAwesomeModule,
    NgbPaginationModule
  ],
  declarations: [
    RiesgosTableComponent,
    ControlesTableComponent,
    SeguimientoTableComponent,
    IndicadoresComponent,
    ListarObservacionesComponent,
    VerSoporteEvidenciaComponent,
    ObservacionPreviaComponent,
    AgregarObservacionModalComponent,
    ObservacionSoporteComponent,
    EvidenciasComponent,
    EditarSoporteComponent,
    AgregarSoporteComponent,
    RiesgosComponent,
    ControlesComponent,
    AgregarCalificacionComponent,
    SeguimientoComponent,
    ControlesSeguimientoComponent,
    VersionesComponent,
    VersionRiesgosComponent,
    EditarVersionRiesgosComponent,
    VerVersionComponent
  ],
  exports: [
    RiesgosTableComponent,
    ControlesTableComponent,
    SeguimientoTableComponent,
    IndicadoresComponent,
    ListarObservacionesComponent,
    VerSoporteEvidenciaComponent,
    ObservacionPreviaComponent,
    AgregarObservacionModalComponent,
    ObservacionSoporteComponent,
    EvidenciasComponent,
    RiesgosComponent,
    ControlesComponent,
    SeguimientoComponent,
    ControlesSeguimientoComponent,
    VersionesComponent,
    VersionRiesgosComponent,
    EditarVersionRiesgosComponent,
    VerVersionComponent
  ]
})

export class CommonComponentsModule { }