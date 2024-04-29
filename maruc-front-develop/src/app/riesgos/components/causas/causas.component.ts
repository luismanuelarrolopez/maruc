import {
  AfterViewInit,
  Component,
  Injector,
  Input,
  OnInit,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Observable, Subject } from 'rxjs';
import { percentage2color } from 'src/app/core/utils/function-utils';
import { indicate } from 'src/app/core/utils/indicator';
import { updateStateRiesgo } from '../../common/update-state-function';
import { Causa } from '../../models/causa';
import { EstatusInfoRiesgo } from '../../models/estatus-info-riesgo';
import { PaginaRegistroRiesgosComponent } from '../../pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { NavegacionRiesgoService } from '../../services/navegacion-riesgo.service';
import { RiesgoServiceService } from '../../services/riesgo-service.service';

@Component({
  selector: 'app-causas',
  templateUrl: './causas.component.html',
  styleUrls: ['./causas.component.css'],
})
export class CausasComponent implements OnInit, AfterViewInit {
  @Input('lock_info') lock_info: boolean;
  private idRiesgo: number;
  formulario: FormGroup;
  submitted = false;
  causas: Causa[] = [];
  private causaSeleccionada: Causa;
  private principal: PaginaRegistroRiesgosComponent;
  loadingCausas$ = new Subject<boolean>();
  loadingTab$ = new Subject<boolean>();

  constructor(
    private _injector: Injector,
    private navegacionRiesgoService: NavegacionRiesgoService,
    private riesgoService: RiesgoServiceService,
    private fb: FormBuilder,
    private toaStr: ToastrService
  ) {}

  ngOnInit(): void {
    this.principal = this._injector.get<PaginaRegistroRiesgosComponent>(
      PaginaRegistroRiesgosComponent
    );
    this.construirFormulario();
    this.idRiesgo = this.principal.idRiesgo;
    console.log(this.idRiesgo);
  }

  ngAfterViewInit(): void {
    this.cargarCausasRiesgoSelecionado();
  }

  cargarCausasRiesgoSelecionado() {
    if (this.principal.editMode) {
      this.riesgoService
        .fetchCausas(this.principal.idRiesgo)
        .pipe(indicate(this.loadingTab$))
        .subscribe({
          next: (value) => (this.causas = value),
        });
    }
  }

  fetchCausas() {
    this.riesgoService
      .fetchCausas(this.principal.idRiesgo)
      .pipe(indicate(this.loadingCausas$))
      .subscribe({
        next: (value) => (this.causas = value),
      });
  }

  construirFormulario() {
    this.formulario = this.fb.group({
      id: [null],
      causa: ['', Validators.required],
      porque1: [''],
      porque2: [''],
      porque3: [''],
      puntaje1: [
        0,
        [Validators.min(0), Validators.max(10), Validators.pattern('[0-9]*')],
      ],
      puntaje2: [
        0,
        [Validators.min(0), Validators.max(10), Validators.pattern('[0-9]*')],
      ],
      puntaje3: [
        0,
        [Validators.min(0), Validators.max(10), Validators.pattern('[0-9]*')],
      ],
      sumatoria: [{ value: 0, disabled: true }],
      causaInicial: [''],
    });
  }

  onAgregarCausa() {
    this.submitted = true;
    if (this.formulario.invalid) {
      return;
    }
    this.causas.push({
      ...this.formulario.value,
      sumatoria: this.f['sumatoria'].value,
      idRiesgo: this.idRiesgo,
    });
    this.causas.sort((a, b) => b.sumatoria - a.sumatoria);
  }

  getBackColor(sumatoria: number): string {
    return percentage2color(sumatoria / 30);
  }

  onCancel() {
    this.formulario.reset();
    this.causaSeleccionada = null;
  }

  onSave() {
    this.submitted = true;
    if (this.formulario.invalid) {
      return;
    }

    const causa = this.formulario.value as Causa;

    if (this.causaSeleccionada) {
      this.handleSaveResponse(
        this.riesgoService.updateCausa({
          ...causa,
          sumatoria: this.f['sumatoria'].value,
          idRiesgo: this.principal.idRiesgo,
          id: this.causaSeleccionada.id,
        })
      );
    } else {
      this.handleSaveResponse(
        this.riesgoService.saveCausa({
          ...causa,
          sumatoria: this.f['sumatoria'].value,
          idRiesgo: this.principal.idRiesgo,
        })
      );
    }
  }

  private handleSaveResponse(observable: Observable<Causa>) {
    observable.subscribe({
      error: (err) => {
        this.toaStr.error('Error al guardar la causa');
      },
      complete: () => {
        this.fetchCausas();
        this.toaStr.success('Causa guardada correctamente');
        this.formulario.reset();
        this.formulario.updateValueAndValidity();
        this.submitted = false;
        this.principal.causas = this.causas;
        this.causaSeleccionada = null;
      },
    });
  }

  onSubmit() {
    if (this.causas.length === 0) {
      this.toaStr.warning('Debe ingresar al menos una causa');
      return;
    }
    /*this.riesgoService.saveCausas(this.causas).subscribe({
      next: (value) => {},

    });*/
  }

  onDelete(idCausa: number) {
    this.riesgoService.deleteCausa(idCausa).subscribe({
      complete: () => {
        this.fetchCausas();
        this.toaStr.success('Se ha eliminado la causa correctamente');
      },
    });
  }

  calcularSumatoria(causa: Causa) {
    return causa.puntaje1 + causa.puntaje2 + causa.puntaje3;
  }

  onPuntajeChange() {
    const puntaje1 = this.f['puntaje1'].value;
    const puntaje2 = this.f['puntaje2'].value;
    const puntaje3 = this.f['puntaje3'].value;
    const sumatoria = puntaje1 + puntaje2 + puntaje3;
    this.f['sumatoria'].setValue(sumatoria);
  }

  onNext() {
    updateStateRiesgo({
      id: this.idRiesgo,
      status: EstatusInfoRiesgo.Consecuencias,
    });
    this.navegacionRiesgoService.changeTabSubject.next(3);
  }

  onCausaSelected(causa: Causa) {
    this.causaSeleccionada = causa;
    this.formulario.patchValue(causa);
  }

  get f() {
    return this.formulario.controls;
  }
}
