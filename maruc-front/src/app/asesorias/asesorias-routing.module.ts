import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FuncionarioGuard } from '../core/guards/funcionario.guard';
import { MainLayoutComponent } from '../core/layout/main-layout/main-layout.component';
import { PrincipalAsesoriasComponent } from './pages/principal-asesorias/principal-asesorias.component';

const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: '',
        canActivate: [FuncionarioGuard],
        component: PrincipalAsesoriasComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AsesoriasRoutingModule {}
