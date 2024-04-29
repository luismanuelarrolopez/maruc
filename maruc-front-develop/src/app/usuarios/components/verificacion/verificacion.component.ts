import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CustomValidators } from 'src/app/core/utils/custom-validators';
import { VerificarUsuarioDTO } from '../../models/verificar-usuario-dto';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'app-verificacion',
  templateUrl: './verificacion.component.html',
  styleUrls: ['./verificacion.component.css'],
})
export class VerificacionComponent implements OnInit {
  formularioLogin: FormGroup;
  submitted: boolean;
  uuid: string;
  constructor(
    private fb: FormBuilder,
    private us: UsuariosService,
    private al: ToastrService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.formularioLogin = this.fb.group(
      {
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(8),
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
          ],
        ],
        confirmPassword: ['', Validators.required],
      },
      {
        validators: [CustomValidators.passwordMatchValidator],
      }
    );
    this.setUuid();
  }

  private setUuid() {
    this.route.paramMap.subscribe({
      next: (paramMap) => (this.uuid = paramMap.get('uuid')),
    });
    console.log(this.uuid);
  }

  doVerificar() {
    this.submitted = true;
    if (this.formularioLogin.invalid) return;

    const verificacion = {
      password: this.f['password'].value,
      verificacionPassword: this.f['confirmPassword'].value,
      uuid: this.uuid,
    } as VerificarUsuarioDTO;

    this.us.verificarUsuario(verificacion).subscribe({
      complete: () => {
        this.al
          .success('Se ha realizado la verificación correctamente')
          .onHidden.subscribe({
            complete: () => this.router.navigate(['/login']),
          });
      },
    });
  }

  get f() {
    return this.formularioLogin.controls;
  }

  get passwordMatchError() {
    return (
      this.formularioLogin.getError('mismatch') &&
      this.formularioLogin.get('confirmPassword')?.touched
    );
  }
}
