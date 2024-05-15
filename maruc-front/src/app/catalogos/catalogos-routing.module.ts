import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminGuard } from '../core/guards/admin.guard';
import { AuthGuard } from '../core/guards/auth.guard';
import { MainLayoutComponent } from '../core/layout/main-layout/main-layout.component';
import { PrincipalCatalogoComponent } from './pages/principal-catalogo/principal-catalogo.component';

const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [AuthGuard, AdminGuard],
    children: [
      {
        path: '',
        component: PrincipalCatalogoComponent
      }, {
        path: 'edit/actividad',
        component: PrincipalCatalogoComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [AuthGuard,AdminGuard]
})
export class CatalogosRoutingModule { }
