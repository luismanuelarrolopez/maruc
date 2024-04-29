import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorVerificacionComponent } from '../core/common/components/error-verificacion/error-verificacion.component';
import { NoFooterLayoutComponent } from '../core/layout/no-footer-layout/no-footer-layout.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {
    path: '',
    component: NoFooterLayoutComponent,
    children: [
      {
        path: '',
        component: LoginComponent,
      },
      {
        path: 'error-verificacion',
        component: ErrorVerificacionComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
