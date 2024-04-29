import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { FormConfig } from 'src/app/core/utils/FormConfig';
import { CargarConsecuenciaVO } from '../../models/CargarConsecuenciaVO';
import { ConsecuenciaDTO } from '../../models/ConsecuenciaDTO';
import { OpcionConsecuencia } from '../../models/OpcionConsecuencia';
import { TipoAfectacionDTO } from '../../models/TipoAfectacionDTO';
import { CatalogoConsecuenciaService } from '../../services/catalogo-consecuencia.service';

@Component({
  selector: 'app-formulario-consecuencia',
  templateUrl: './formulario-consecuencia.component.html',
  styleUrls: ['./formulario-consecuencia.component.css'],
})
export class FormularioConsecuenciaComponent implements OnInit, OnDestroy {
  tiposAfectacion: TipoAfectacionDTO[] = [];
  formularioConsecuencia: FormGroup;
  submitted = false;
  disabled: boolean;
  isEditMode: boolean;
  loading = true;
  consecuencia: CargarConsecuenciaVO;
  listaOpciones: Array<FormGroup> = [];

  @Output('onSaved')
  private eventoGuardado: EventEmitter<any> = new EventEmitter();
  @Input('config')
  private config: FormConfig;
  @Input('id')
  idConsecuencia: number;
  @Output('onClose')
  private eventoCerrarDialogo: EventEmitter<any> = new EventEmitter();

  constructor(
    private serviceConsecuencia: CatalogoConsecuenciaService,
    private fb: FormBuilder,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.createForm();
    this.serviceConsecuencia.dataSavedSubject.subscribe({
      next: (a) => {
        if (a === true) {
          this.onSubmit();
        }
      },
    });

    if (this.idConsecuencia) {
      this.isEditMode = true;
      this.loadConsecuencia();
    } else {
      this.isEditMode = false;
      this.loading = false;
    }
  }

  private loadConsecuencia() {
    this.serviceConsecuencia.fetchCatalogoById(this.idConsecuencia).subscribe({
      next: (value) => this.cargarDatosEnFormulario(value),
      complete: () => (this.loading = false),
    });
  }

  private cargarDatosEnFormulario(consecuencia: CargarConsecuenciaVO) {
    this.formularioConsecuencia.patchValue({
      ...consecuencia,
      tipoAfectacion: consecuencia.idTipoAfectacion,
      consecuencia: consecuencia.descripcion,
      puntaje:
        consecuencia.tipoCampo === 'texto'
          ? consecuencia.listaOpciones[0].puntaje
          : '',
      opciones:
        consecuencia.tipoCampo === 'texto'
          ? []
          : this.cargarOpciones(consecuencia.listaOpciones),
    });
  }

  private cargarOpciones(opciones: OpcionConsecuencia[]) {
    opciones.forEach((opcion: OpcionConsecuencia) => {
      const fg = this.fb.group({
        id: [opcion.id],
        descripcion: [opcion.descripcion, Validators.required],
        puntaje: [opcion.puntaje, Validators.required],
      });
      this.listaOpciones.push(fg);
    });
  }

  createForm() {
    this.serviceConsecuencia.fetchTiposAfectacion().subscribe({
      next: (value) => (this.tiposAfectacion = value),
    });
    this.formularioConsecuencia = this.fb.group({
      tipoCampo: [
        { value: '', disabled: this.disabled },
        [Validators.required],
      ],
      tipoAfectacion: [
        { value: '', disabled: this.disabled },
        [Validators.required],
      ],
      consecuencia: [
        { value: '', disabled: this.disabled },
        [Validators.required],
      ],
      puntaje: [
        { value: '0', disabled: this.disabled },
        [Validators.required, Validators.pattern('^[\\d]+$')],
      ],
      opciones: this.fb.array([]),
    });
  }

  private esFormularioValido(): boolean {
    const formularioPrincipalValido = this.formularioConsecuencia.valid;
    const esTexto = this.f['tipoCampo'].value === 'texto';
    const hayOpcionesSeleccionadas = this.listaOpciones.length !== 0;
    const listaOpcionesValida =
      this.listaOpciones.filter((fg) => !fg.valid).length === 0;

    if (esTexto && formularioPrincipalValido) {
      return true;
    }

    if (
      !esTexto &&
      formularioPrincipalValido &&
      hayOpcionesSeleccionadas &&
      listaOpcionesValida
    ) {
      return true;
    }

    return false;
  }

  onSubmit() {
    this.submitted = true;
    console.log('Guardando formulario');
    // Todo validar actualizar y guardado para campos tipo texto.
    // también agregar las validaciones en el html.
    if (!this.esFormularioValido()) {
      console.log('Data not valid');
      return;
    }
    const consecuencia: ConsecuenciaDTO =
      this.obtenerConsecuenciaDeFormulario();
    if (this.isEditMode) {
      this.serviceConsecuencia
        .actualizar({
          id: this.idConsecuencia,
          idTipoAfectacion: consecuencia.tipoAfectacion.id,
          listaOpciones: consecuencia.opciones,
          descripcion: consecuencia.descripcion,
          tipoCampo: consecuencia.tipoCampo,
        })
        .subscribe({
          next: (value) =>
            this.serviceConsecuencia.dataChangeSubject.next(consecuencia),
          error: (err) =>
            this.toastr.error('Error al momento de actualizar la consecuencia'),
          complete: () => {
            this.toastr.success(
              `La consecuencia se ha actualizado correctamente`
            );
            this.onCerrarDialogo();
          },
        });
    } else {
      console.log('saving event');
      this.serviceConsecuencia.guardar(consecuencia).subscribe({
        next: (v) => this.serviceConsecuencia.dataChangeSubject.next(v),
        error: (e) => {
          this.toastr.error(`Error al momento de guardar la consecuencia`);
          console.log(JSON.stringify(e));
        },
        complete: () => {
          this.toastr.success(`La consecuencia se ha registrado correctamente`);
          this.onCerrarDialogo();
        },
      });
    }
  }

  mapToForm(consecuencia: ConsecuenciaDTO) {
    this.formularioConsecuencia.patchValue({
      tipoCampo: consecuencia.tipoCampo,
      tipoAfectacion: consecuencia.tipoAfectacion?.id,
      consecuencia: consecuencia.descripcion,
      puntaje: consecuencia.opciones?.[0]?.puntaje,
    });
  }

  obtenerConsecuenciaDeFormulario(): ConsecuenciaDTO {
    const consecuencia = {
      descripcion: this.f['consecuencia'].value,
      tipoCampo: this.f['tipoCampo'].value,
      tipoAfectacion: {
        id: this.f['tipoAfectacion'].value,
      },
    };
    console.log(consecuencia);
    if (consecuencia.tipoCampo === 'texto') {
      return {
        ...consecuencia,
        opciones: [
          {
            descripcion: '',
            puntaje: this.f['puntaje'].value,
          },
        ],
      };
    } else {
      return { ...consecuencia, opciones: this.obtenerOpciones() };
    }
  }

  obtenerOpciones(): OpcionConsecuencia[] {
    const opciones = this.listaOpciones.map(
      (fg) => fg.value as OpcionConsecuencia
    );
    console.log(opciones);
    return opciones;
  }

  showListaOpciones() {
    return this.f['tipoCampo']?.value === 'seleccion';
  }

  onAgregarOpcion() {
    const grupoOpcion = this.fb.group({
      id: '',
      descripcion: ['', Validators.required],
      puntaje: ['', [Validators.required, Validators.pattern('^[\\d]+$')]],
    });
    this.listaOpciones.push(grupoOpcion);
  }

  onBorrarOpcion(indexOpcion: number) {
    const fg = this.listaOpciones.splice(indexOpcion, 1)[0];
    const { id } = fg.value;
    if (id) {
      this.serviceConsecuencia.eliminarOpcion(id).subscribe();
    }
  }

  onCambioTipoCampo() {
    this.f['opciones'] = this.fb.array([]);
    this.f['puntaje'].setValue(0);
  }

  get f() {
    return this.formularioConsecuencia.controls;
  }

  ngOnDestroy(): void {
    //this.dataSelectedSubscription.unsubscribe();
  }

  onCerrarDialogo() {
    this.eventoCerrarDialogo.emit();
  }
}
