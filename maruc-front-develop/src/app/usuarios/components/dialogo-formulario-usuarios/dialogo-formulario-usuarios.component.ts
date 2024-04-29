import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { MarucErrorCode } from 'src/app/core/common/enums/error';
import { Dependencia } from '../../models/dependencia';
import { GuardarUsuarioDto } from '../../models/guardar-usuario-dto';
import { UsuarioRequest } from '../../models/requests/usuario-request';
import { Rol } from '../../models/rol';
import { DependenciaService } from '../../services/dependencia.service';
import { RolesService } from '../../services/roles.service';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'app-dialogo-formulario-usuarios',
  templateUrl: './dialogo-formulario-usuarios.component.html',
  styleUrls: ['./dialogo-formulario-usuarios.component.css'],
})
export class DialogoFormularioUsuariosComponent implements OnInit {
  @Input('idUsuarioSeleccionado')
  idUsuario: number;
  editMode = false;
  loading = false;
  formularioUsuario: FormGroup;
  submitted = false;
  roles$: Observable<Rol[]>;
  dependencias$: Observable<Dependencia[]>;
  // Events
  @Output('onClose')
  private onCerrarDialogoEvent = new EventEmitter();

  constructor(
    private fb: FormBuilder,
    private rolesService: RolesService,
    private usuarioService: UsuariosService,
    private dependenciaService: DependenciaService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.roles$ = this.rolesService.fetchRoles();
    this.dependencias$ = this.dependenciaService.fetchDependencias();
    if (this.idUsuario) {
      this.editMode = true;
      this.loadUsuario();
    }
    this.crearFormulario();
  }

  private loadUsuario() {
    this.loading = true;
    this.usuarioService.fetchUsuario(this.idUsuario).subscribe({
      next: (value) => this.formularioUsuario.patchValue(value),
      complete: () => (this.loading = false),
    });
  }

  crearFormulario() {
    this.formularioUsuario = this.fb.group({
      nombres: ['', Validators.required],
      apellidos: ['', Validators.required],
      email: [
        { value: '', disabled: this.editMode },
        [Validators.required, Validators.email],
      ],
      idRol: ['', Validators.required],
      idDependencia: ['', Validators.required],
    });
  }

  onCerrarDialogo() {
    this.formularioUsuario.reset();
    this.idUsuario = NaN;
    this.onCerrarDialogoEvent.emit();
  }

  onGuardar() {
    this.submitted = true;
    if (this.formularioUsuario.invalid) {
      return;
    }

    if (this.editMode) {
      this.doUpdate();
    } else {
      this.doSave();
    }
  }
  doSave() {
    this.usuarioService
      .guardarUsuario(this.formularioUsuario.value as GuardarUsuarioDto)
      .subscribe({
        error: (e) => {
          this.toastr.error('Error al guardar el usuario', 'Error');
        },
        complete: () => {
          this.onCerrarDialogo();
          this.usuarioService.dataChangeSubject.next(true);
          this.toastr.success('Usuario guardado correctamente', 'Guardado');
        },
      });
  }

  doUpdate() {
    this.usuarioService
      .doActualizarUsuario({
        id: this.idUsuario,
        ...this.formularioUsuario.value,
      } as UsuarioRequest)
      .subscribe({
        error: (e) => {
          if (e.error.codigoError === MarucErrorCode.EntidadYaExiste) {
            this.toastr.error(
              'Ya existe un usuario registrado con el correo electrónico',
              'Error'
            );
          } else {
            this.toastr.error('Error al guardar el usuario', 'Error');
          }
        },
        complete: () => {
          this.onCerrarDialogo();
          this.usuarioService.dataChangeSubject.next(true);
          this.toastr.success('Usuario actualizado correctamente', 'Guardado');
        },
      });
  }

  get f() {
    return this.formularioUsuario.controls;
  }
}
