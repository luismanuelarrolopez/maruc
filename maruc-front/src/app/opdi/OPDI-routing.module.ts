import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { MainLayoutComponent } from "../core/layout/main-layout/main-layout.component";
import { LiderProcesoComponent } from "./pages/opdi.component";
import { SeguimientoComponent } from "../common/components/seguimiento/seguimiento.component";
import { ControlesSeguimientoComponent } from "../common/components/seguimiento/controles/controles.component";
import { PaginaRegistroRiesgosComponent } from "../riesgos/pages/pagina-registro-riesgos/pagina-registro-riesgos.component";
import { EvidenciasComponent } from "../common/components/evidencias/evidencias.component";
import { RiesgosComponent } from "../common/components/riesgos/riesgos.component";
import { ControlesComponent } from "../common/components/riesgos/controles/controles.component";
import { IndicadoresComponent } from "../common/components/indicadores/indicadores.component";
import { VersionesComponent } from "../common/components/versiones/versiones.component";

const routes: Routes = [
    {
        path: "",
        component: MainLayoutComponent,
        children: [
            {
                path: "",
                component: LiderProcesoComponent,
            },
            {
                path: "riesgos",
                component: RiesgosComponent
            },
            {
                path: "riesgos/controles/:id",
                component: ControlesComponent                
            },
            {
                path: "monitoreo",
                component: SeguimientoComponent
            },
            {
                path: "monitoreo/controles/:id",
                component: ControlesSeguimientoComponent
            },
            {
                path: "monitoreo/controles/:id_riesgo/evidencias/:id_evidencia",
                component: EvidenciasComponent
            },
            {
                path: "monitoreo/redaccion/:id",
                component: PaginaRegistroRiesgosComponent
            },
            {
                path: "indicadores",
                component: IndicadoresComponent
            },
            {
                path: "versiones",
                component: VersionesComponent
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class OPDIRoutingModule {}