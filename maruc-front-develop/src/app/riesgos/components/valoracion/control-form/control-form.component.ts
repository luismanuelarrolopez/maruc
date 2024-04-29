import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Control } from '../../../models/control';
import { DifusionControlService } from '../../../../core/services/difusion-control.service';
import { PeriodicidadService } from '../../../../core/services/periodicidad.service';
import { Observable } from 'rxjs';
import { Periodicidad } from '../../../../core/models/periodicidad';
import { DifusionControl } from '../../../../core/models/difusion-control';
import { Catalogo } from 'src/app/core/models/catalogo';
import { CatalogoService } from 'src/app/core/common/services/catalogo-service';
import { RiesgoServiceService } from 'src/app/riesgos/services/riesgo-service.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-control-form',
  templateUrl: './control-form.component.html',
  styleUrls: ['./control-form.component.css'],
})
export class ControlFormComponent implements OnInit, OnChanges {
  form: FormGroup;
  @Input('control')
  control: Control;
  @Output('onSave')
  saveEvent: EventEmitter<any> = new EventEmitter();
  @Output('onCancel')
  cancelEvent: EventEmitter<any> = new EventEmitter();
  peridicidades$: Observable<Catalogo[]>;
  difusiones$: Observable<Catalogo[]>;
  ejecucionSistemaDigitales$: Observable<Catalogo[]>;
  empleaOEjecuta$: Observable<Catalogo[]>;
  habilitar = false;

  constructor(
    private fb: FormBuilder,
    private catalogoService: CatalogoService,
    private riesgoService: RiesgoServiceService,
    private toStr: ToastrService
  ) {}

  ngOnInit(): void {
    this.construirFormulario();
    this.cargarCatalogos();
  }

  cargarCatalogos() {
    this.peridicidades$ = this.catalogoService.fetchCatalogo('periodicidad');
    this.difusiones$ = this.catalogoService.fetchCatalogo('difusion');
    this.ejecucionSistemaDigitales$ = this.catalogoService.fetchCatalogo(
      'ejecucion-sistemas-digitales'
    );
    this.empleaOEjecuta$ =
      this.catalogoService.fetchCatalogo('emplea-o-ejecuta');
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['control']['currentValue']) {
      this.habilitar = true;
      this.control = changes['control']['currentValue'] as Control;

      if (this.control) {
        this.form.patchValue({
          tipoControl: this.control.tipoControl.nombre,
          aplica: this.control.aplica,
          nombre: this.control.nombre,
          responsable: this.control.responsable,
          ejecucionSistemasDigitales:
            this.control.ejecucionSistemasDigitales?.id,
          difusionControl: this.control.difusion.id,
          empleaEjecuta: this.control.empleaOEjecuta.id,
          periodicidadEjecucion: this.control.periodicidadEjecucion.id,
          periodicidadSeguimiento: this.control.periodicidadSegumiento.id,
        });
      }
    }
  }

  cambioResponable() {
    if (this.control)
      this.control.responsable = this.form.controls['responsable'].value;
  }

  private construirFormulario() {
    this.form = this.fb.group({
      tipoControl: [{ value: '', disabled: true }],
      aplica: [false],
      nombre: [''],
      responsable: [''],
      ejecucionSistemasDigitales: [''],
      difusionControl: [''],
      empleaEjecuta: [false],
      periodicidadEjecucion: [''],
      periodicidadSeguimiento: [''],
    });
  }

  private get f() {
    return this.form.controls;
  }

  private _handleSave() {
    const control: Control = {
      ...this.control,
      aplica: this.f['aplica'].value,
      nombre: this.f['nombre'].value,
      responsable: this.f['responsable'].value,
      ejecucionSistemasDigitales: {
        id: this.f['ejecucionSistemasDigitales'].value,
      },
      difusion: { id: this.f['difusionControl'].value },
      empleaOEjecuta: {
        id: this.f['empleaEjecuta'].value,
      },
      periodicidadEjecucion: {
        id: this.f['periodicidadEjecucion'].value,
      },
      periodicidadSegumiento: { id: this.f['periodicidadSeguimiento'].value },
    };

    return this.riesgoService.updateControl(control.id, control);
  }

  onSave() {
    this._handleSave().subscribe({
      complete: () => {
        this.toStr.success('Control actualizad con éxito');
        this.saveEvent.emit();
        this.habilitar = false;
        this.form.reset();
      },
    });
  }

  onCancel() {
    this.habilitar = false;
    this.cancelEvent.emit();
    this.form.reset();
  }
}
