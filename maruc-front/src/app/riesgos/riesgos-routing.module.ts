import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainLayoutComponent } from '../core/layout/main-layout/main-layout.component';
import { PaginaRegistroRiesgosComponent } from './pages/pagina-registro-riesgos/pagina-registro-riesgos.component';

const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: 'registrar',
        component: PaginaRegistroRiesgosComponent,
      },
      {
        path: 'editar/:id',
        component: PaginaRegistroRiesgosComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RiesgosRoutingModule {}
