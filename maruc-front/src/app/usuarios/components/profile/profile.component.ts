import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/auth/services/auth.service';
import { PerfilUsuarioResponse } from '../../models/response/perfil-usuario-response';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  usuario: PerfilUsuarioResponse;
  submitted = false;
  loading = true;
  loadingPassword = true;
  form: FormGroup;
  constructor(
    private al: ToastrService,
    private us: UsuariosService,
    private fb: FormBuilder,
    private as: AuthService
  ) {}

  ngOnInit() {
    this.initForm();
    this.loadData();
  }

  private loadData() {
    this.us.recuperarPerfil(this.as.authStatus.userEmail).subscribe({
      next: (value) => {
        this.usuario = value;
        this.form.patchValue(value);
      },
      error: (err) => console.log(`Error cargando el usuario: ${err}`),
      complete: () => (this.loading = false),
    });
  }

  private initForm() {
    this.form = this.fb.group({
      email: [{ value: '', disabled: true }],
      primerNombre: ['', Validators.required],
      segundoNombre: [''],
      primerApellido: ['', Validators.required],
      segundoApellido: [''],
    });
  }

  onCancel() {
    this.submitted = false;
    this.form.reset();
    this.form.patchValue(this.usuario);
  }

  onSave() {
    this.submitted = true;
    if (this.form.invalid) return;

    this.us
      .doActualizarPerfil(this.form.value as PerfilUsuarioResponse)
      .subscribe({
        complete: () =>
          this.al.success('Se ha actualizado la información correctamente'),
      });
  }

  get f() {
    return this.form.controls;
  }

  /*
  password: [
          '',
          [
            Validators.required,
            // 2. check whether the entered password has a number
            CustomValidators.patternValidator(/\d/, { hasNumber: true }),
            // 3. check whether the entered password has upper case letter
            CustomValidators.patternValidator(/[A-Z]/, {
              hasCapitalCase: true,
            }),
            // 4. check whether the entered password has a lower-case letter
            CustomValidators.patternValidator(/[a-z]/, { hasSmallCase: true }),
            CustomValidators.patternValidator(/[!#$%&'*+-/=?^_`{|}~]/, {
              hasSpecialCharacters: true,
            }),

            // 6. Has a minimum length of 8 characters
            Validators.minLength(8),
          ],
        ],
        confirmPassword: ['', Validators.required],*/
}
