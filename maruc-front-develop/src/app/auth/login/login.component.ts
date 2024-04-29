import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MarucErrorResponse } from 'src/app/core/common/common';
import { MarucErrorCode } from 'src/app/core/common/enums/error';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  formularioLogin: FormGroup;
  submitted: boolean;
  loading: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toaStr: ToastrService
  ) {}

  ngOnInit(): void {
    this.initFormulario();
  }

  initFormulario() {
    this.formularioLogin = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  async doLogin() {
    this.submitted = true;
    // validated form
    if (this.formularioLogin.invalid) {
      return;
    }
    this.loading = true;
    // ask for token
    this.authService.login(this.formularioLogin.value).subscribe({
      next: (authStatus) => {
        if (authStatus.isAuthenticated) {
          setTimeout(() => {
            this.router.navigate(['/dashboard']);
          }, 1000);
        }
      },
      error: (error: MarucErrorResponse) => {
        if (error.codigoError === MarucErrorCode.UsuarioDeshabilitado) {
          this.toaStr.error(
            'El usuario no ha sido verificado, por favor revisar el correo institucional para verificar la cuenta'
          );
          this.loading = false;
          setTimeout(() => {
            this.router.navigate(['/error-verificacion']);
          }, 3000);
        } else {
          this.loading = false;
          this.toaStr.error(
            'No se pudo iniciar sesión, por favor verifique su usuario y/o contraseña'
          );
        }
      },
    });
  }

  get f() {
    return this.formularioLogin.controls;
  }
}
