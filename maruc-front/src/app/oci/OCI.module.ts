import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { OCIRoutingModule } from "./OCI-routing.module";
import { CoreModule } from "../core/core.module";
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { LiderProcesoComponent } from './pages/oci.component';
import { CommonComponentsModule } from "../common/components/common-components.module";
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { RiesgosModule } from "../riesgos/riesgos.module";
@NgModule({
    imports: [
        CommonModule, 
        NgbPaginationModule,
        OCIRoutingModule, 
        CoreModule, 
        FormsModule, 
        FontAwesomeModule, 
        CommonComponentsModule,
        RiesgosModule
        ],
        
    declarations: [
        LiderProcesoComponent
    ],
})
export class OCIModule { }