import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from '../core/guards/admin.guard';
import { AuthGuard } from '../core/guards/auth.guard';
import { MainLayoutComponent } from '../core/layout/main-layout/main-layout.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { PasswordRecoveryComponent } from './components/password-recovery/password-recovery.component';
import { ProfileComponent } from './components/profile/profile.component';
import { VerificacionComponent } from './components/verificacion/verificacion.component';
import { UsuariosPrincipalComponent } from './pages/usuarios-principal/usuarios-principal.component';

const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: '',
        canActivate: [AuthGuard, AdminGuard],
        component: UsuariosPrincipalComponent,
      },
      {
        path: 'perfil',
        canActivate: [AuthGuard],
        component: ProfileComponent,
      },
    ],
  },
  {
    path: 'verificacion/:uuid',
    component: VerificacionComponent,
  },
  {
    path: 'forgot-password',
    component: ForgotPasswordComponent,
  },
  {
    path: 'forgot-password/:uuid',
    component: PasswordRecoveryComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [AuthGuard, AdminGuard],
})
export class UsuariosRoutingModule {}
