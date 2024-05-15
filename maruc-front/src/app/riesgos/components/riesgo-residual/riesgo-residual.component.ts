import { Component, Injector, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { concatMap, of } from 'rxjs';
import { updateStateRiesgo } from '../../common/update-state-function';
import { ControlResidual } from '../../models/control-residual';
import { EstatusInfoRiesgo } from '../../models/estatus-info-riesgo';
import { RiesgoResidualDTO } from '../../models/riesgo-residual-dto';
import { PaginaRegistroRiesgosComponent } from '../../pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { RiesgoServiceService } from '../../services/riesgo-service.service';

@Component({
  selector: 'app-riesgo-residual',
  templateUrl: './riesgo-residual.component.html',
  styleUrls: ['./riesgo-residual.component.css'],
})
export class RiesgoResidualComponent implements OnInit {
  @Input('lock_info') lock_info: boolean;
  loading = true;
  idRiesgoResidual: number;
  riesgoResidual: RiesgoResidualDTO;
  riesgoSaved = false;
  controles: ControlResidual[];

  // principal
  private principal: PaginaRegistroRiesgosComponent;

  constructor(
    private rs: RiesgoServiceService,
    private injector: Injector,
    private router: Router
  ) {
    console.log(this.principal);
  }

  ngOnInit(): void {
    this.principal = this.injector.get(PaginaRegistroRiesgosComponent);
    this.fetchRiesgoResidual();
  }

  fetchRiesgoResidual() {
    this.loading = true;
    this.rs
      .fetchRiesgoResidual(this.principal.idRiesgo)
      .pipe(
        concatMap((rr) => {
          this.riesgoResidual = rr;
          if (this.riesgoResidual.id) {
            return this.rs.fetchControlesResiduales(this.riesgoResidual.id);
          } else {
            return of([]);
          }
        })
      )
      .subscribe({
        next: (value) => (this.controles = value),
        complete: () => (this.loading = false),
      });
  }

  onRiesgoResidualSaved($event) {
    this.riesgoSaved = true;
  }

  showControles(): boolean {
    return this.riesgoResidual.id !== null || this.riesgoSaved;
  }

  finalizar() {
    this.updateEstatus();
    this.router.navigate(['/lider_proceso/riesgos']);
  }

  updateEstatus() {
    updateStateRiesgo({
      id: this.principal.idRiesgo,
      status: EstatusInfoRiesgo.Finalizado,
    });
  }
}
