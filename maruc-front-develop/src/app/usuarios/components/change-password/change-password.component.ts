import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/auth/services/auth.service';
import { CustomValidators } from 'src/app/core/utils/custom-validators';
import { ChangePasswordRequest } from '../../models/requests/change-password-request';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'maruc-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent implements OnInit {
  formulario: FormGroup;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private userService: UsuariosService,
    private toastr: ToastrService,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    this.formulario = this.initForm();
  }

  private initForm(): FormGroup {
    return this.fb.group(
      {
        actual: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
      },
      {
        validators: [CustomValidators.passwordMatchValidator],
      }
    );
  }

  /* Events */
  onSubmit() {
    this.submitted = true;
    if (!this.formulario.valid) {
      return;
    }

    this.sendRequest({
      idUsuario: this.auth.authStatus.userId,
      actualPassword: this.f['actual'].value,
      newPassword: this.f['password'].value,
      confirmPassword: this.f['confirmPassword'].value,
    } as ChangePasswordRequest);
  }

  onCancel() {
    this.submitted = false;
    this.formulario.reset();
  }
  /* Handlers */

  private sendRequest(request: ChangePasswordRequest) {
    this.userService.doCambiarPassword(request).subscribe({
      error: (errorResponse) => {
        this.toastr.error(errorResponse.error.message);
      },
      complete: () => {
        this.toastr.success('La contraseña se ha actualizado correctamente');
        this.auth.logout();
      },
    });
  }

  /* Getters */
  get f() {
    return this.formulario.controls;
  }
}
