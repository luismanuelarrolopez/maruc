import { Routes } from '@angular/router';
import { ErrorVerificacionComponent } from '../common/components/error-verificacion/error-verificacion.component';
import { PageNotFoundComponent } from '../common/components/page-not-found/page-not-found.component';

export const ALL_ROUTES: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    loadChildren: () =>
      import('../../auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('../../dashboard/dashboard.module').then((m) => m.DashboardModule),
  },
  {
    path: 'asesorias',
    loadChildren: () =>
      import('../../asesorias/asesorias.module').then((m) => m.AsesoriasModule),
  },
  {
    path: 'catalogos',
    loadChildren: () =>
      import('../../catalogos/catalogos.module').then((m) => m.CatalogosModule),
  },
  {
    path: 'usuarios',
    loadChildren: () =>
      import('../../usuarios/usuarios.module').then((m) => m.UsuariosModule),
  },
  {
    path: 'lider_proceso',
    loadChildren: () =>
      import('../../lider_proceso/lider_proceso.module').then(
        (m) => m.LiderProcesoModule
      ),
  },
  {
    path: 'oci',
    loadChildren: () => import('../../oci/OCI.module').then((m) => m.OCIModule),
  },
  {
    path: 'opdi',
    loadChildren: () =>
      import('../../opdi/OPDI.module').then((m) => m.OPDIModule),
  },
  {
    path: 'riesgos',
    loadChildren: () =>
      import('../../riesgos/riesgos.module').then((m) => m.RiesgosModule),
  },
  {
    path: 'error-verificacion',
    component: ErrorVerificacionComponent,
  },
  {
    path: '**',
    component: PageNotFoundComponent,
  },
];
