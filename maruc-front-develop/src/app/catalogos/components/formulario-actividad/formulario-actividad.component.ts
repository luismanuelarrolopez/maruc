import {
  Component,
  OnInit,
  Output,
  EventEmitter,
  Input,
  OnDestroy,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ActividadDTO } from '../../models/ActividadDTO';
import { CatalogoActividadService } from '../../services/catalogo-actividad.service';

@Component({
  selector: 'app-formulario-actividad',
  templateUrl: './formulario-actividad.component.html',
  styleUrls: ['./formulario-actividad.component.css'],
})
export class FormularioActividadComponent implements OnInit {
  @Input()
  id: number;
  isAddMode: boolean;
  submitted = false;
  loading = false;
  actividadForm: FormGroup;

  @Output() saved: EventEmitter<any> = new EventEmitter();

  constructor(
    private fb: FormBuilder,
    private catalogoActividadService: CatalogoActividadService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.catalogoActividadService.dataSavedSubject.subscribe({
      next: (value) => {
        if (value) {
          this.onSubmit();
        }
      },
    });

    this.isAddMode = !this.id;
    this.actividadForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(10)]],
      frecuenciaInvertida: [false],
    });

    if (!this.isAddMode) {
      this.catalogoActividadService.buscarPorId(this.id).subscribe({
        next: (v) => this.actividadForm.patchValue(v),
        error: (e) =>
          this.toastr.error('Error al seleccionar la actividad', 'Error'),
      });
    }
  }

  get f() {
    return this.actividadForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.actividadForm.invalid) {
      return;
    }
    this.guardarOEditar();
  }

  guardarOEditar() {
    if (this.isAddMode) {
      this.guardar();
    } else {
      this.editar();
    }
  }

  editar() {
    this.catalogoActividadService
      .editarActividad(this.id, this.actividadForm.value as ActividadDTO)
      .subscribe({
        error: (e) => this.showError('edit'),
        complete: () => {
          this.saved.emit();
          this.onReset();
          this.showSucces('edit');
        },
      });
  }

  guardar() {
    this.catalogoActividadService
      .guardarActividad(this.actividadForm.value as ActividadDTO)
      .subscribe({
        error: (e) => this.showError(),
        complete: () => {
          this.saved.emit();
          this.onReset();
          this.showSucces();
        },
      });
  }
  showError(action: 'save' | 'edit' = 'save') {
    if (action === 'save') {
      this.toastr.error('Error al guardar la actividad');
    } else {
      this.toastr.error('Error al editar la actividad');
    }
  }

  showSucces(action: 'save' | 'edit' = 'save') {
    if (action === 'save') {
      this.toastr.success('La actividad se ha guardado correctamente');
    } else {
      this.toastr.success('La actividad se ha editado correctamente');
    }
  }

  onReset() {
    this.submitted = false;
    this.isAddMode = true;
    this.id = NaN;
    this.actividadForm.reset();
  }
}
