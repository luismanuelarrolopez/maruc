import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CustomValidators } from 'src/app/core/utils/custom-validators';
import { PasswordRecoveryRequest } from '../../models/requests/password-recovery-request';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'app-password-recovery',
  templateUrl: './password-recovery.component.html',
  styleUrls: ['./password-recovery.component.css'],
})
export class PasswordRecoveryComponent implements OnInit {
  form: FormGroup;
  submitted = false;
  private uuid: string;

  constructor(
    private fb: FormBuilder,
    private us: UsuariosService,
    private route: ActivatedRoute,
    private al: ToastrService,
    private router: Router
  ) {
    this.setUuid();
  }

  ngOnInit() {
    this.initForm();
  }

  private setUuid() {
    this.route.paramMap.subscribe({
      next: (map) => {
        this.uuid = map.get('uuid');
      },
    });
  }

  private initForm() {
    this.form = this.fb.group(
      {
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
        confirmPassword: ['', [Validators.required]],
      },
      {
        validators: [CustomValidators.passwordMatchValidator],
      }
    );
  }

  /*
    Realiza la petición al backend para recuperar la contraseña del usuario.
  */
  doRecovery() {
    console.log(this.form);
    this.submitted = true;
    if (this.form.invalid) return;

    const request = {
      uuid: this.uuid,
      password: this.f['password']?.value,
      verificacionPassword: this.f['confirmPassword'].value,
    } as PasswordRecoveryRequest;

    this.us.doRecovery(request).subscribe({
      complete: () => {
        this.al
          .success('Se ha actualizado tu contraseña existosamente')
          .onHidden.subscribe({
            complete: () => this.router.navigate(['login']),
          });
      },
    });
  }

  get passwordMismatchError() {
    return this.form.hasError('mismatch') && this.f['confirmPassword']?.touched;
  }

  get f() {
    return this.form.controls;
  }
}
