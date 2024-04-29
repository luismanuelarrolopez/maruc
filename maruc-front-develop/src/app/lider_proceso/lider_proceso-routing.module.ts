import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ControlesComponent } from "./pages/riesgos/controles/controles.component";
import { MainLayoutComponent } from "../core/layout/main-layout/main-layout.component";
import { RiesgosComponent } from "./pages/riesgos/riesgos.component";
import { LiderProcesoComponent } from "./pages/lider-proceso.component";
import { MaterializacionComponent } from "./pages/materializacion/materializacion.component";
import { EvidenciasComponent } from "../common/components/evidencias/evidencias.component";
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
                path: "riesgos/controles/:id_riesgo/evidencias/:id_evidencia",
                component: EvidenciasComponent
            },
            {
                path: "materializacion",
                component: MaterializacionComponent
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
export class LiderProcesoRoutingModule {}