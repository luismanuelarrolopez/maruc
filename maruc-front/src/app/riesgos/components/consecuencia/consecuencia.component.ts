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
import { Subject } from 'rxjs';
import { groupBy } from 'src/app/core/utils/function-utils';
import { indicate } from 'src/app/core/utils/indicator';
import { updateStateRiesgo } from '../../common/update-state-function';
import { Consecuencia } from '../../models/consecuencia';
import { ConsecuenciaListItem } from '../../models/consecuencia-list-item';
import { EstatusInfoRiesgo } from '../../models/estatus-info-riesgo';
import { Opcion } from '../../models/opcion';
import { OpcionConsecuencia } from '../../models/opcion-consecuencia';
import { TipoAfectacion } from '../../models/tipo-afectacion';
import { PaginaRegistroRiesgosComponent } from '../../pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { NavegacionRiesgoService } from '../../services/navegacion-riesgo.service';
import { RiesgoServiceService } from '../../services/riesgo-service.service';

@Component({
  selector: 'app-consecuencia',
  templateUrl: './consecuencia.component.html',
  styleUrls: ['./consecuencia.component.css'],
})
export class ConsecuenciaComponent implements OnInit {
  @Input('lock_info') lock_info: boolean;
  @Input('mayorConsecuencia') mayorConsecuencia: string;
  formulario: FormGroup;
  submitted = false;
  idRiesgo: number;
  afectaciones: TipoAfectacion[];
  afectacionSeleccionado: TipoAfectacion = {};
  opcionSeleccionada: Opcion = {};
  consecuenciaSeleccionada: Consecuencia = { opciones: [] };
  consecuencias: Map<string, any> = new Map();
  listaConsecuencias: ConsecuenciaListItem[] = [];
  mapaConsecuencias: Map<string, OpcionConsecuencia[]> = new Map();
  @Output('consecuenciaChange')
  onConsecuenciaChange: EventEmitter<string> = new EventEmitter<string>();
  @Output('onAddConsecuencia')
  onAddConsecuencia = new EventEmitter<number>();
  puntajeTotal = 0;

  /*  Nuevos componentes */
  private principal: PaginaRegistroRiesgosComponent;
  opcionesConsecuencias: OpcionConsecuencia[];

  /* Loading indicator */
  loading = true;
  loadingData$ = new Subject<boolean>();

  constructor(
    private _injector: Injector,
    public fb: FormBuilder,
    public riesgoService: RiesgoServiceService,
    public toastr: ToastrService,
    public navegadorRiesgoService: NavegacionRiesgoService
  ) {}

  get f() {
    return this.formulario.controls;
  }

  ngOnInit(): void {
    this.principal = this._injector.get(PaginaRegistroRiesgosComponent);
    this.cargarRiesgo();
    this.initVariableSeleccionables();
    this.cargarCatalogos();
    this.crearFormulario();
  }

  cargarRiesgo() {
    this.idRiesgo = this.principal.idRiesgo;
    if (this.principal.editMode) {
      this.riesgoService.fetchConsecuencias(this.idRiesgo).subscribe({
        next: (value) => {
          this.opcionesConsecuencias = value;
          this.mapaConsecuencias = groupBy(
            this.opcionesConsecuencias,
            (oc) => oc.tipoAfectacion
          );
        },
        complete: () => (this.loading = false),
      });
    }
  }

  private _fetchConsecuencias() {
    this.riesgoService
      .fetchConsecuencias(this.idRiesgo)
      .pipe(indicate(this.loadingData$))
      .subscribe({
        next: (value) => {
          this.opcionesConsecuencias = value;
          this.mapaConsecuencias = groupBy(
            this.opcionesConsecuencias,
            (oc) => oc.tipoAfectacion
          );
        },
      });
  }

  initVariableSeleccionables() {
    this.afectacionSeleccionado = {};
    this.opcionSeleccionada = {};
    this.consecuenciaSeleccionada = { opciones: [] };
  }

  cargarCatalogos() {
    this.riesgoService.fetchTiposAfectacion().subscribe({
      next: (data) => {
        console.log(data);
        this.afectaciones = data;
      },
    });
  }

  crearFormulario() {
    this.formulario = this.fb.group({
      afectacion: ['', Validators.required],
      consecuencia: ['', Validators.required],
      puntaje: [{ value: '', disabled: true }],
      opcion: [''],
    });
  }

  onAfectacionSelected(afectacion) {
    if (!afectacion.value) {
      return;
    }
    const value = afectacion.value.split(':')[1].trim();
    if (value) {
      this.afectacionSeleccionado = this.afectaciones.find(
        (item) => item.id === parseInt(value)
      );
      console.log(this.afectacionSeleccionado);
    }
  }

  onConsecuenciaSelected(consecuencia) {
    const value = consecuencia.value.split(':')[1].trim();
    if (value) {
      this.consecuenciaSeleccionada =
        this.afectacionSeleccionado.consecuencias.find(
          (item) => item.id === parseInt(value)
        );

      if (
        this.consecuenciaSeleccionada.tipoCampo === 'seleccion' &&
        this.consecuenciaSeleccionada.opciones
      ) {
        this.consecuenciaSeleccionada.opciones.sort(
          (a, b) => a.puntaje - b.puntaje
        );
      }
    }
  }

  onOpcionSelected(opcion) {
    const value = opcion.value.split(':')[1].trim();
    if (value) {
      this.opcionSeleccionada = this.consecuenciaSeleccionada.opciones.find(
        (item) => item.id === parseInt(value)
      );
      console.log(this.opcionSeleccionada);
    }
  }

  agregarConsecuencia() {
    if (this.formulario.invalid) {
      return;
    }
    this.puntajeTotal += this.consecuenciaSeleccionada.opciones[0]
      ? this.consecuenciaSeleccionada.opciones[0].puntaje
      : this.opcionSeleccionada.puntaje;
    this.listaConsecuencias.push(
      new ConsecuenciaListItem(
        this.afectacionSeleccionado,
        this.consecuenciaSeleccionada,
        this.consecuenciaSeleccionada.opciones[0] ?? this.opcionSeleccionada
      )
    );
    this.mapaConsecuencias = groupBy(
      this.listaConsecuencias,
      (consecuencia) => consecuencia.tipoAfectacion.nombre
    );
    this.afectacionSeleccionado.consecuencias =
      this.afectacionSeleccionado.consecuencias.filter(
        (item) => item.id !== this.consecuenciaSeleccionada.id
      );
    this.onAddConsecuencia.emit(this.puntajeTotal);
    this.resetForm();
  }

  resetForm() {
    this.mayorConsecuencia = '';
    this.formulario.reset();
    this.formulario.updateValueAndValidity();
    this.initVariableSeleccionables();
  }

  getPuntajeTotal(key: string) {
    return this.mapaConsecuencias.get(key).reduce((a, b) => {
      return a + b.puntaje;
    }, 0);
  }

  eliminarConsecuencia(key: string, value: any) {
    console.log(key);
    console.log(value);
    this.afectaciones
      .find((item) => item.nombre === key)
      .consecuencias.push(value.consecuencia);
    this.listaConsecuencias = this.listaConsecuencias.filter((item) => {
      return item.consecuencia.id !== value.consecuencia.id;
    });
    this.mapaConsecuencias = groupBy(
      this.listaConsecuencias,
      (consecuencia) => consecuencia.tipoAfectacion.nombre
    );
  }

  onMayorConsecuenciaChange() {
    this.riesgoService
      .updateMayorConsecuencia(this.principal.idRiesgo, this.mayorConsecuencia)
      .subscribe({
        complete: () => {
          this.onConsecuenciaChange.emit(this.mayorConsecuencia);
          console.log('ok');
        },
      });
  }

  onSave() {
    this.submitted = true;
    if (this.formulario.invalid) {
      return;
    }

    this.riesgoService
      .saveConsecuencia({
        idOpcionConsecuencia:
          this.consecuenciaSeleccionada.tipoCampo === 'seleccion'
            ? this.f['opcion'].value
            : this.consecuenciaSeleccionada.opciones[0]?.id,
        idRiesgo: this.idRiesgo,
      })
      .subscribe({
        complete: () => {
          this._fetchConsecuencias();
          this.toastr.success('Consecuencia guardada con éxito');
          this.formulario.reset();
        },
      });
  }

  onSubmit() {
    /*this.riesgoService
      .saveConsecuencias(
        this.listaConsecuencias.map((cli) => ({
          idOpcionConsecuencia: cli.opcion.id,
          idRiesgo: this.idRiesgo,
        })),
        this.mayorConsecuencia
      )
      .subscribe({
        next: (value) => {
          updateStateRiesgo({
            id: this.idRiesgo,
            status: EstatusInfoRiesgo.Probabilidad,
          });
        },
        complete: () => {
          this.toastr.success('Consecuencias guardadas correctamente');
          this.navegadorRiesgoService.changeTabSubject.next(4);
        },
      });*/
  }

  onDelete(idConsecuencia: number) {
    this.riesgoService.deleteConsecuencia(idConsecuencia).subscribe({
      complete: () => {
        this._fetchConsecuencias();
        this.toastr.success('Se eliminó la consecuencia correctamente');
      },
    });
  }

  onNext() {
    updateStateRiesgo({
      id: this.principal.idRiesgo,
      status: EstatusInfoRiesgo.Probabilidad,
    });
    this.navegadorRiesgoService.changeTabSubject.next(4);
  }

  onCancel() {
    this.mayorConsecuencia = '';
    this.resetForm();
    this.listaConsecuencias = [];
  }
}
