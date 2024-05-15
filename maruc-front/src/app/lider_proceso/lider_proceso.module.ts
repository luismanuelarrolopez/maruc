import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { LiderProcesoRoutingModule } from "./lider_proceso-routing.module";
import { CoreModule } from "../core/core.module";
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { RiesgosComponent } from "./pages/riesgos/riesgos.component";
import { ControlesComponent } from "./pages/riesgos/controles/controles.component";
import { CommonComponentsModule } from "../common/components/common-components.module";
import { LiderProcesoComponent } from './pages/lider-proceso.component';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { MaterializacionComponent } from "./pages/materializacion/materializacion.component";

@NgModule({
    imports: [
        CommonModule, 
        NgbPaginationModule, 
        LiderProcesoRoutingModule, 
        CoreModule, 
        FormsModule, 
        FontAwesomeModule, 
        CommonComponentsModule],

    declarations: [
        RiesgosComponent,
        ControlesComponent,
        LiderProcesoComponent,
        MaterializacionComponent
    ]    
})
export class LiderProcesoModule { }