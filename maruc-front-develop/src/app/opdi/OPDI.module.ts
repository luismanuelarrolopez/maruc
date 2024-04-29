import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { OPDIRoutingModule } from "./OPDI-routing.module";
import { CoreModule } from "../core/core.module";
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { LiderProcesoComponent } from './pages/opdi.component';
import { CommonComponentsModule } from "../common/components/common-components.module";
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { RiesgosModule } from "../riesgos/riesgos.module";
@NgModule({
    imports: [
        CommonModule, 
        OPDIRoutingModule, 
        CoreModule, 
        FormsModule, 
        FontAwesomeModule, 
        CommonComponentsModule, 
        NgbPaginationModule,
        RiesgosModule],
    declarations: [
        LiderProcesoComponent
    ]
})
export class OPDIModule { }