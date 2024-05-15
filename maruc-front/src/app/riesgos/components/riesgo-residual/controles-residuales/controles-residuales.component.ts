import { Component, Injector, Input, OnInit } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { forkJoin, Observable } from 'rxjs';
import { CatalogoService } from 'src/app/core/common/services/catalogo-service';
import { Periodicidad } from 'src/app/core/models/periodicidad';
import { conditionalValidator } from 'src/app/core/utils/function-utils';
import { Causa } from 'src/app/riesgos/models/causa';
import { ControlResidual } from 'src/app/riesgos/models/control-residual';
import { TipoControl } from 'src/app/riesgos/models/tipo-control';
import { GuardarControlResidualRequest } from 'src/app/riesgos/models/vo/guardar-control-residual-request';
import { PaginaRegistroRiesgosComponent } from 'src/app/riesgos/pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { CausaService } from 'src/app/riesgos/services/causa.service';
import { RiesgoServiceService } from 'src/app/riesgos/services/riesgo-service.service';

@Component({
  selector: 'app-controles-residuales',
  templateUrl: './controles-residuales.component.html',
  styleUrls: ['./controles-residuales.component.css'],
})
export class ControlesResidualesComponent implements OnInit {
  @Input('controles')
  controles: ControlResidual[];
  @Input('lock_info')
  lock_info: boolean;
  @Input('idRiesgoResidual')
  idRiesgoResidual: number;
  tiposControl$: Observable<TipoControl[]>;
  periodicidades$: Observable<Periodicidad[]>;
  loading = false;

  // Form
  submitted = false;
  form: FormGroup;

  // Seleccion
  controlSeleccionado: ControlResidual;
  // Causas
  causas: Causa[];
  private readonly principal: PaginaRegistroRiesgosComponent;
  causasControl: Causa[] = [];

  constructor(
    private injector: Injector,
    private rs: RiesgoServiceService,
    private cs: CatalogoService,
    private fb: FormBuilder,
    private al: ToastrService,
    private causasService: CausaService
  ) {
    this.principal = injector.get(PaginaRegistroRiesgosComponent);
  }

  ngOnInit() {
    this.loadCatalogos();
    this.loadCausas();
    this.createForm();
  }

  private loadCausas() {
    this.causasService
      .fetchCausasSinControlResidual(this.principal.idRiesgo)
      .subscribe({
        next: (value) =>
          (this.causas = value.sort(
            (a, b) => this.sumarPuntajes(b) - this.sumarPuntajes(a)
          )),
      });
  }

  private createForm() {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      responsable: ['', Validators.required],
      idTipoControl: ['', Validators.required],
      idPeriodicidad: ['', Validators.required],
      evidencia: [''],
      indicador: [''],
      idCausas: this.fb.array(
        [],
        [
          conditionalValidator(
            () => this.controlSeleccionado === null,
            Validators.required
          ),
        ]
      ),
    });
  }

  /* Manejo de la selección de las causas */
  onCausaSelected(e: any) {
    const checkArray: FormArray = this.form.get('idCausas') as FormArray;
    if (e.target.checked) {
      checkArray.push(new FormControl(e.target.value));
    } else {
      let i: number = 0;
      checkArray.controls.forEach((item: any) => {
        if (item.value == e.target.value) {
          checkArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  private loadCatalogos() {
    this.tiposControl$ = this.cs.fetchCatalogo('tipo-control');
    this.periodicidades$ = this.cs.fetchCatalogo('periodicidad');
  }

  private fetchControles() {
    this.loading = true;
    this.rs.fetchControlesResiduales(this.idRiesgoResidual).subscribe({
      next: (value) => (this.controles = value),
      complete: () => (this.loading = false),
    });
  }

  get f() {
    return this.form.controls;
  }

  onCancel() {
    this.controlSeleccionado = null;
    this.loadCausas();
    this.form.reset();
    this.form.patchValue({
      ...this.form.value,
      idTipoControl: '',
      idPeriodicidad: '',
      idCausas: [],
    });
  }

  onSave() {
    this.submitted = true;
    if (this.form.invalid) return;

    if (this.controlSeleccionado) {
      this.actualizar();
    } else {
      this.guardar();
    }
  }

  private actualizar() {
    const control: ControlResidual = {
      ...(this.form.value as ControlResidual),
      evidencia: {
        evidencia: this.form.controls['evidencia'].value,
      },
    };
    const listaIdCausas = this.form.controls['idCausas'].value as Array<number>;

    const request: GuardarControlResidualRequest = {
      idRiesgoResidual: this.idRiesgoResidual,
      listaIdCausas: listaIdCausas.filter((c) => c !== null),
      controlResidual: control,
      evidencia: this.f['evidencia'].value,
    };
    this.causasService
      .updateControlResidual(this.controlSeleccionado.id, request)
      .subscribe({
        error: (e) => {
          this.al.error('Error al guardar el control');
        },
        complete: () => {
          this.al.success('El control se ha actualizado exitosamente');
          this.onCancel();
          this.submitted = false;
          this.fetchControles();
        },
      });
  }

  private guardar() {
    const control: ControlResidual = {
      ...(this.form.value as ControlResidual),
      evidencia: {
        evidencia: this.form.controls['evidencia'].value,
      },
    };
    const listaIdCausas = this.form.controls['idCausas'].value as Array<number>;
    const request: GuardarControlResidualRequest = {
      idRiesgoResidual: this.idRiesgoResidual,
      listaIdCausas: listaIdCausas,
      controlResidual: control,
      evidencia: this.f['evidencia'].value,
    };
    this.causasService.saveControlResidual(request).subscribe({
      error: (e) => {
        this.al.error('Error al guardar el control');
      },
      complete: () => {
        this.loadCausas();
        this.al.success('El control se ha guardado exitosamente');
        this.onCancel();
        this.submitted = false;
        this.fetchControles();
      },
    });
  }

  onSeleccionar(control: ControlResidual) {
    // Cargar todas las causas, las del control y seleccionarlas y las que no pertenecen al control.
    this.submitted = false;
    this.controlSeleccionado = control;
    this.loadCausasToUpdate();
    this.form.patchValue({
      ...control,
      idTipoControl: control.tipoControl.id,
      idPeriodicidad: control.periodicidad.id,
      evidencia: this.controlSeleccionado.evidencia?.evidencia,
      idCausas: this.causas.map((c) => c.id),
    });
  }

  private loadCausasToUpdate() {
    console.log('Id control seleccionado:', this.controlSeleccionado.id);
    forkJoin({
      sinControl: this.causasService.fetchCausasSinControlResidual(
        this.principal.idRiesgo
      ),
      conControl: this.causasService.fetchCausasDeControlResidual(
        this.principal.idRiesgo,
        this.controlSeleccionado.id
      ),
    }).subscribe({
      next: ({ sinControl, conControl }) => {
        this.causasControl = conControl;
        this.causas = sinControl.concat(conControl);
        this.causas = this.causas.sort(
          (a, b) => this.sumarPuntajes(b) - this.sumarPuntajes(a)
        );
      },
      complete: () => console.log(this.causas),
    });
  }

  private sumarPuntajes(c: Causa): number {
    return c.puntaje1 + c.puntaje2 + c.puntaje3;
  }

  onDelete(id: number) {
    this.rs.deleteControlResidual(id).subscribe({
      complete: () => {
        this.al.success('El control se ha eliminado correctamente');
        this.fetchControles();
      },
    });
  }
}
