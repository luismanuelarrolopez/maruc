import {
  Component,
  EventEmitter,
  Injector,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { RiesgoResidualDTO } from 'src/app/riesgos/models/riesgo-residual-dto';
import { PaginaRegistroRiesgosComponent } from 'src/app/riesgos/pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { RiesgoServiceService } from 'src/app/riesgos/services/riesgo-service.service';

@Component({
  selector: 'app-info-riesgo-residual',
  templateUrl: './info-riesgo-residual.component.html',
  styleUrls: ['./info-riesgo-residual.component.css'],
})
export class InfoRiesgoResidualComponent implements OnInit {
  formGeneral: FormGroup;
  @Input('lock_info')
  lock_info: boolean;
  submitted = false;
  @Input('riesgoResidual')
  riesgoResidual: RiesgoResidualDTO;
  private readonly principal: PaginaRegistroRiesgosComponent;

  @Output('onSaved')
  private readonly onSaved: EventEmitter<void> = new EventEmitter();

  constructor(
    private injector: Injector,
    private fb: FormBuilder,
    private rs: RiesgoServiceService,
    private al: ToastrService
  ) {
    this.principal = this.injector.get(PaginaRegistroRiesgosComponent);
  }

  ngOnInit() {
    console.log(this.lock_info);
    this.formGeneral = this.fb.group({
      nivel: [{ value: '', disabled: true }],
      tratamiento: ['', Validators.required],
    });

    this.formGeneral.patchValue({
      ...this.riesgoResidual,
      tratamiento: this.riesgoResidual.tratamiento || '',
    });

    if (this.lock_info) {
      this.formGeneral.disable();
    }
  }

  onSave() {
    this.submitted = true;
    if (this.formGeneral.invalid) return;

    const riesgoResidual = {
      ...this.riesgoResidual,
      ...this.formGeneral.value,
    };
    this.rs.saveRiesgoResidual(riesgoResidual).subscribe({
      complete: () => {
        this.onSaved.emit();
        this.al.success(
          'Se ha guardado la información básica del riesgo residual correctamente'
        );
      },
    });
  }

  get f() {
    return this.formGeneral.controls;
  }
}
