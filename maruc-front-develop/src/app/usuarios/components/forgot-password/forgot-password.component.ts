import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent implements OnInit {
  form: FormGroup;
  submitted = false;
  constructor(
    private fb: FormBuilder,
    private us: UsuariosService,
    private al: ToastrService
  ) {}

  ngOnInit() {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  doRecuperar() {
    this.submitted = true;
    if (this.form.invalid) return;

    this.us.recuerarPassword(this.f['email'].value).subscribe({
      error: (err) => {
        this.al.error(err.error.mensaje);
      },
      complete: () =>
        this.al.success(
          'Se ha enviado un correo con las instrucciones para recuperar la contraseña.'
        ),
    });
  }

  get f() {
    return this.form.controls;
  }
}
