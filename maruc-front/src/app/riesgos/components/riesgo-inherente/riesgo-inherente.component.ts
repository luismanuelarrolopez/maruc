import { Component, Injector, Input, OnInit } from '@angular/core';
import { updateStateRiesgo } from '../../common/update-state-function';
import { EstatusInfoRiesgo } from '../../models/estatus-info-riesgo';
import { RiesgoInherenteDTO } from '../../models/riesgo-inherente-dto';
import { PaginaRegistroRiesgosComponent } from '../../pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { NavegacionRiesgoService } from '../../services/navegacion-riesgo.service';
import { RiesgoServiceService } from '../../services/riesgo-service.service';

@Component({
  selector: 'app-riesgo-inherente',
  templateUrl: './riesgo-inherente.component.html',
  styleUrls: ['./riesgo-inherente.component.css'],
})
export class RiesgoInherenteComponent implements OnInit {
  @Input('lock_info') lock_info: boolean;
  riesgoInherente: RiesgoInherenteDTO = {};
  private principal: PaginaRegistroRiesgosComponent;

  // load indicator
  loading = true;

  constructor(
    private riesgoService: RiesgoServiceService,
    private _injector: Injector,
    private navRiesgoService: NavegacionRiesgoService
  ) {}

  ngOnInit(): void {
    this.principal = this._injector.get(PaginaRegistroRiesgosComponent);
    this.riesgoService.fetchRiesgoInherente(this.principal.idRiesgo).subscribe({
      next: (value) => (this.riesgoInherente = value),
      complete: () => (this.loading = false),
    });
  }

  goNext() {
    updateStateRiesgo({
      id: this.principal.idRiesgo,
      status: EstatusInfoRiesgo.Valoracion,
    });
    this.navRiesgoService.changeTabSubject.next(6);
  }

  get colorToleranciaRiesgo() {
    return this.riesgoInherente.tolerancia?.toLowerCase();
  }
}
