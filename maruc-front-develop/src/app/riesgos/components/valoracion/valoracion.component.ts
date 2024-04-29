import { Component, Injector, Input, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable, Subject } from 'rxjs';
import { CatalogoService } from 'src/app/core/common/services/catalogo-service';
import { Catalogo } from 'src/app/core/models/catalogo';
import { numberInRange } from 'src/app/core/utils/function-utils';
import { indicate } from 'src/app/core/utils/indicator';
import { updateStateRiesgo } from '../../common/update-state-function';
import { Control } from '../../models/control';
import { EstatusInfoRiesgo } from '../../models/estatus-info-riesgo';
import { PaginaRegistroRiesgosComponent } from '../../pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { NavegacionRiesgoService } from '../../services/navegacion-riesgo.service';
import { RiesgoServiceService } from '../../services/riesgo-service.service';

export interface ControlListItem {
  control?: Control;
}

@Component({
  selector: 'app-valoracion',
  templateUrl: './valoracion.component.html',
  styleUrls: ['./valoracion.component.css'],
})
export class ValoracionComponent implements OnInit {
  @Input('lock_info') lock_info: boolean;
  controlSeleccionado: Control;

  // loading indicators
  loading = true;
  loadingData$ = new Subject<boolean>();

  private principal: PaginaRegistroRiesgosComponent;
  controles: Control[] = [];
  nivelValoracion = 0;
  sumatoria = 0;
  difusiones$: Observable<Catalogo[]>;
  periodicidad$: Observable<Catalogo[]>;

  constructor(
    private riesgoService: RiesgoServiceService,
    private navegacionService: NavegacionRiesgoService,
    private catalogoService: CatalogoService,
    private _toastr: ToastrService,
    private _injector: Injector
  ) {}

  ngOnInit(): void {
    this.principal = this._injector.get(PaginaRegistroRiesgosComponent);
    this._fetchControles();
    this.cargarCatalogos();
  }

  private _fetchControles() {
    this.riesgoService
      .fetchControles(this.principal.idRiesgo)
      .pipe(indicate(this.loadingData$))
      .subscribe({
        next: (value) => (this.controles = value),
        complete: () => {
          this.calcularSumatoria();
          this.loading = false;
        },
      });
  }

  cargarCatalogos() {
    this.difusiones$ = this.catalogoService.fetchCatalogo('difusiones');
  }

  seleccionar(control: Control) {
    this.controlSeleccionado = control;
  }

  calcularValoracion(control: Control) {
    const puntaje = control.puntajeControl;
    console.log(puntaje)
    return (
      puntaje.puntajeTipoControl -
      (puntaje.puntajeDifisuionControl +
        puntaje.puntajeEjecionSistemasDigitales +
        puntaje.puntajePeriodicidadEjecucion +
        puntaje.puntajePeriodicidadSeguimiento +
        puntaje.puntajeSeCumpleEjecuta)
    );
  }

  onFormCancel() {
    this.controlSeleccionado = undefined;
  }

  calcularSumatoria() {
    console.log(this.controles)
    this.sumatoria = this.controles
      .map((c) => this.calcularValoracion(c))
      .reduce((a, b) => a + b, 0);
    this.nivelValoracion = this._calcularNivelValoracion(this.sumatoria);
  }

  private _calcularNivelValoracion(sumatoria: number): number {
    if (sumatoria <= 0) return 0;
    if (numberInRange(sumatoria, 1, 60)) return 1;
    if (numberInRange(sumatoria, 61, 80)) return 2;
    return 4;
  }

  onFormSave() {
    console.log('Form saved');
    this._fetchControles();
  }

  onNext() {
    updateStateRiesgo({
      id: this.principal.idRiesgo,
      status: EstatusInfoRiesgo.RiesgoResidual,
    });
    this.navegacionService.changeTabSubject.next(7);
  }
}
