import {
  Component,
  EventEmitter,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { buildDateFromNgbDate } from 'src/app/core/utils/function-utils';
import { AsesoriasService } from '../../services/asesorias.service';

@Component({
  selector: 'app-formulario-agendar-asesoria',
  templateUrl: './formulario-agendar-asesoria.component.html',
  styleUrls: ['./formulario-agendar-asesoria.component.css'],
})
export class FormularioAgendarAsesoriaComponent implements OnInit, OnDestroy {
  @Output('onClose')
  private onCloseEvent = new EventEmitter<any>();
  formulario: FormGroup;
  model: NgbDateStruct;
  private dataSelectSubscription: Subscription;
  submitted = false;
  idAsesoria: number;

  constructor(
    private fb: FormBuilder,
    private asesoriaServices: AsesoriasService,
    private toaStr: ToastrService
  ) {}

  ngOnInit(): void {
    this.construirFormulario();
    this.dataSelectSubscription =
      this.asesoriaServices.dataSelectedSubject.subscribe({
        next: (data) => (this.idAsesoria = data),
      });
  }

  ngOnDestroy(): void {
    this.dataSelectSubscription.unsubscribe();
  }

  construirFormulario() {
    this.formulario = this.fb.group({
      fechaAgendada: ['', Validators.required],
    });
  }

  cerrarDialogo() {
    this.onCloseEvent.emit();
  }

  onSubmit() {
    this.submitted = true;
    if (this.formulario.invalid) {
      return;
    }

    const payload = {
      fechaAgenda: buildDateFromNgbDate({ ...this.model }),
      idAsesoria: this.idAsesoria,
    };

    this.asesoriaServices.updateAsesoria(payload).subscribe({
      error: (e) => this.toaStr.error('Error al guardar la asesoria', 'Error'),
      complete: () => {
        this.toaStr.success('Asesoria agendada correctamente', 'Éxito');
        this.asesoriaServices.dataChangeSubject.next(true);
        this.cerrarDialogo();
      },
    });
  }

  now() {
    const now = new Date();
    return {
      year: now.getFullYear(),
      month: now.getMonth() + 1,
      day: now.getDate(),
    };
  }

  get f() {
    return this.formulario.controls;
  }
}
