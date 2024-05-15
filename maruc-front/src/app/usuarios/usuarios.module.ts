import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';
import { CoreModule } from '../core/core.module';
import { DialogoFormularioUsuariosComponent } from './components/dialogo-formulario-usuarios/dialogo-formulario-usuarios.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ListaUsuariosComponent } from './components/lista-usuarios/lista-usuarios.component';
import { PasswordRecoveryComponent } from './components/password-recovery/password-recovery.component';
import { ProfileComponent } from './components/profile/profile.component';
import { VerificacionComponent } from './components/verificacion/verificacion.component';
import { UsuariosPrincipalComponent } from './pages/usuarios-principal/usuarios-principal.component';
import { UsuariosRoutingModule } from './usuarios-routing.module';
import { ChangePasswordComponent } from './components/change-password/change-password.component';

@NgModule({
  imports: [
    CommonModule,
    UsuariosRoutingModule,
    CoreModule,
    ReactiveFormsModule,
    NgbPaginationModule,
    FormsModule,
    ToastrModule,
  ],
  declarations: [
    UsuariosPrincipalComponent,
    ForgotPasswordComponent,
    ListaUsuariosComponent,
    VerificacionComponent,
    DialogoFormularioUsuariosComponent,
    PasswordRecoveryComponent,
    ProfileComponent,
    ChangePasswordComponent,
  ],
})
export class UsuariosModule {}
