import { Injector, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule, HashLocationStrategy, LocationStrategy } from '@angular/common';

import { AppComponent } from './app.component';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RouterModule, Routes } from '@angular/router';
import {
  HttpClient,
  HttpClientModule,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ALL_ROUTES } from './core/routes/all-routes';
import { MarucHttpInterceptor } from './core/interceptors/maruc-http.interceptor';
import { setRootInjector } from './core/utils/root-injector';
import { RiesgoServiceService } from './riesgos/services/riesgo-service.service';

const appRoutes: Routes = ALL_ROUTES;

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    CommonModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
    NgbModule,
  ],
  bootstrap: [AppComponent],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MarucHttpInterceptor,
      multi: true,
    },
    {
      provide: RiesgoServiceService,
      useClass: RiesgoServiceService,
      deps: [HttpClient],
    },
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
})
export class AppModule {
  constructor(private injector: Injector) {
    setRootInjector(injector);
  }
}
