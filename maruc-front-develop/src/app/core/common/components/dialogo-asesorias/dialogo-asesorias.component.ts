import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AsesoriasService } from 'src/app/asesorias/services/asesorias.service';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-dialogo-asesorias',
  templateUrl: './dialogo-asesorias.component.html',
  styleUrls: ['./dialogo-asesorias.component.css'],
})
export class DialogoAsesoriasComponent implements OnInit {
  @Output('onClose')
  private onCerrarDialogoEvent = new EventEmitter();
  formulario: FormGroup;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private asesoriasService: AsesoriasService,
    private authService: AuthService,
    private toaStr: ToastrService
  ) {}

  ngOnInit(): void {
    this.construirFormulario();
  }

  construirFormulario() {
    this.formulario = this.fb.group({
      oficinaAsesora: ['', [Validators.required]],
      tema: ['', [Validators.required]],
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.formulario.invalid) {
      return;
    }

    this.asesoriasService
      .saveAsesoria({
        ...this.formulario.value,
        userId: this.authService.authStatus.userId,
      })
      .subscribe({
        error: (e) =>
          this.toaStr.error('Error al guardar la asesoria', 'Error'),
        complete: () => {
          this.toaStr.success('Asesoria guardada correctamente', 'Éxito');
          this.asesoriasService.dataChangeSubject.next(true);
          this.cerrarDialogo();
        },
      });
    this.cerrarDialogo();
  }

  cerrarDialogo() {
    this.onCerrarDialogoEvent.emit();
  }

  get f() {
    return this.formulario.controls;
  }
}
