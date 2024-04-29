import {
  Component,
  EventEmitter,
  Injector,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { combineLatest, map, Subject } from 'rxjs';
import { indicate } from 'src/app/core/utils/indicator';
import { average, numberInRange } from '../../../core/utils/function-utils';
import { updateStateRiesgo } from '../../common/update-state-function';
import { Actividad } from '../../models/actividad';
import { EstatusInfoRiesgo } from '../../models/estatus-info-riesgo';
import { RiesgoActividadDTO } from '../../models/riesgo-actividad-dto';
import { PaginaRegistroRiesgosComponent } from '../../pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { ActividadService } from '../../services/actividad-service';
import { NavegacionRiesgoService } from '../../services/navegacion-riesgo.service';

@Component({
  selector: 'app-probabilidad',
  templateUrl: './probabilidad.component.html',
  styleUrls: ['./probabilidad.component.css'],
})
export class ProbabilidadComponent implements OnInit {
  @Input('lock_info') lock_info: boolean;
  submitted = false;
  actividades: RiesgoActividadDTO[] = [];
  catalogoActividades: Actividad[] = [];
  @Output('onNivelProbabilidadChange')
  onNivelProbabilidadChange = new EventEmitter<number>();
  frecuencias = [
    { nombre: 'Muy baja', value: 20 },
    { nombre: 'Baja', value: 40 },
    { nombre: 'Media', value: 60 },
    { nombre: 'Alta', value: 80 },
    { nombre: 'Muy alta', value: 100 },
  ];
  form: FormGroup;
  porOcurrencia = 0;
  proOcurrencia = '';
  principal: PaginaRegistroRiesgosComponent;
  nivProbabilidad = 0;
  loading = true;

  // data loading subject
  loadingData$ = new Subject<boolean>();

  constructor(
    private fb: FormBuilder,
    private navegacionService: NavegacionRiesgoService,
    private toastr: ToastrService,
    private _injector: Injector,
    private actividadService: ActividadService
  ) {}

  get f() {
    return this.form.controls;
  }

  ngOnInit(): void {
    this.principal = this._injector.get(PaginaRegistroRiesgosComponent);
    this.form = this.fb.group({
      actividad: ['', Validators.required],
      frecuencia: ['', Validators.required],
    });
    this._fetchRiesgoActividades();
  }

  onAddActivity() {
    this.submitted = true;
    if (this.form.invalid) {
      return;
    }
    const actividadSeleccionada = this.catalogoActividades.find(
      (a) => a.id === parseInt(this.f['actividad'].value)
    );
    const frecValue = parseInt(this.f['frecuencia'].value);
    const frecuencia = actividadSeleccionada.frecuenciaInvertida
      ? 100 - frecValue
      : frecValue;

    this._saveActividad({
      idActividad: actividadSeleccionada.id,
      frecuencia: frecuencia,
      idRiesgo: this.principal.idRiesgo,
    });
  }

  private _saveActividad(riesgoActividad: RiesgoActividadDTO) {
    this.actividadService.saveActividad(riesgoActividad).subscribe({
      complete: () => this._onSaveComplete(),
    });
  }

  private _onSaveComplete() {
    this.toastr.success('La actividad se ha registrado correctamnte');
    this._limpiarFormulario();
    this._fetchRiesgoActividades();
  }

  private _limpiarFormulario() {
    this.form.reset();
    this.form.updateValueAndValidity();
    this.submitted = false;
  }

  private _fetchRiesgoActividades() {
    const actividades$ = this.actividadService.fetchActividadesNotInRiesgo(
      this.principal.idRiesgo
    );
    const riesgoActividades$ = this.actividadService.fetchRiesgoActividades(
      this.principal.idRiesgo
    );

    combineLatest([actividades$, riesgoActividades$])
      .pipe(
        indicate(this.loadingData$),
        map(([actividades, riesgoActividades]) => {
          this.catalogoActividades = actividades || [];
          this.actividades = riesgoActividades || [];
        })
      )
      .subscribe({
        complete: () => {
          this.calcularProbabilidad();
          this.loading = false;
        },
      });
  }

  calcularProbabilidad() {
    if (this.actividades.length > 0) {
      this.porOcurrencia = average(this.actividades.map((a) => a.frecuencia));
      if (numberInRange(this.porOcurrencia, 0, 20)) {
        this.proOcurrencia = 'Rara vez';
        this.nivProbabilidad = 1;
      } else if (numberInRange(this.porOcurrencia, 21, 40)) {
        this.proOcurrencia = 'Improbable';
        this.nivProbabilidad = 2;
      } else if (numberInRange(this.porOcurrencia, 41, 60)) {
        this.proOcurrencia = 'Posible';
        this.nivProbabilidad = 3;
      } else if (numberInRange(this.porOcurrencia, 61, 80)) {
        this.proOcurrencia = 'Probable';
        this.nivProbabilidad = 4;
      } else if (numberInRange(this.porOcurrencia, 80, 100)) {
        this.proOcurrencia = 'Casi Seguro';
        this.nivProbabilidad = 5;
      }
      this.onNivelProbabilidadChange.emit(this.nivProbabilidad);
    }
  }

  onCancel() {
    this.form.reset();
    this.form.updateValueAndValidity();
    this.submitted = false;
  }

  onNext() {
    updateStateRiesgo({
      id: this.principal.idRiesgo,
      status: EstatusInfoRiesgo.RiesgoInherente,
    });
    this.navegacionService.changeTabSubject.next(5);
  }

  onDelete(id: number) {
    this.actividadService.deleteActividad(id).subscribe({
      complete: () => {
        this.toastr.success('La actividad se ha eliminado correctamente');
        this._fetchRiesgoActividades();
      },
    });
  }
}
