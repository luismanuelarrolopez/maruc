import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { BtnAgendarAsesoriasComponent } from './common/components/btn-agendar-asesorias/btn-agendar-asesorias.component';
import { DialogoAsesoriasComponent } from './common/components/dialogo-asesorias/dialogo-asesorias.component';
import { ErrorVerificacionComponent } from './common/components/error-verificacion/error-verificacion.component';
import { PageNotFoundComponent } from './common/components/page-not-found/page-not-found.component';
import { SinDatosComponent } from './common/components/sin-datos/sin-datos.component';
import { SpinnerComponent } from './common/components/spinner/spinner.component';
import { ModalComponent } from './common/modal/modal.component';
import { BackgroundRedDirective } from './directives/background-red.directive';
import { CharacterCountDirective } from './directives/character-count.directive';
import { HasRoleDirective } from './directives/has-role.directive';
import { IsCausaCriticaDirective } from './directives/is-causa-critica-directive';
import { PasswordMatchingDirective } from './directives/password-matching.directive';
import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { NavigationComponent } from './layout/navigation/navigation.component';
import { NoFooterLayoutComponent } from './layout/no-footer-layout/no-footer-layout.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { SortPipe } from './pipes/sort.pipe';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([]),
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
  ],
  declarations: [
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    NoFooterLayoutComponent,
    NavigationComponent,
    MainLayoutComponent,
    ModalComponent,
    PageNotFoundComponent,
    SpinnerComponent,
    SinDatosComponent,
    BtnAgendarAsesoriasComponent,
    HasRoleDirective,
    IsCausaCriticaDirective,
    DialogoAsesoriasComponent,
    SortPipe,
    BackgroundRedDirective,
    PasswordMatchingDirective,
    ErrorVerificacionComponent,
    CharacterCountDirective,
  ],
  exports: [
    ModalComponent,
    SinDatosComponent,
    SpinnerComponent,
    FormsModule,
    ReactiveFormsModule,
    HasRoleDirective,
    IsCausaCriticaDirective,
    SortPipe,
    BackgroundRedDirective,
    CharacterCountDirective,
  ],
})
export class CoreModule {}
