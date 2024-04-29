import {
  AfterViewInit,
  Component,
  EventEmitter,
  Injector,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { combineLatest, Observable, of } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { ETipoRiesgo } from 'src/app/core/common/enums/tipo-riesgo-enum';
import { CatalogoService } from 'src/app/core/common/services/catalogo-service';
import { Catalogo } from 'src/app/core/models/catalogo';
import { conditionalValidator } from 'src/app/core/utils/function-utils';
import { updateStateRiesgo } from '../../common/update-state-function';
import { EstatusInfoRiesgo } from '../../models/estatus-info-riesgo';
import { Riesgo } from '../../models/riesgo';
import { RiesgoDTO } from '../../models/riesgo-dto';
import { PaginaRegistroRiesgosComponent } from '../../pages/pagina-registro-riesgos/pagina-registro-riesgos.component';
import { NavegacionRiesgoService } from '../../services/navegacion-riesgo.service';
import { RiesgoServiceService } from '../../services/riesgo-service.service';
import { TipoRiesgoServiceService } from '../../services/tipo-riesgo-service.service';

@Component({
  selector: 'app-informacion-basica',
  templateUrl: './informacion-basica.component.html',
  styleUrls: ['./informacion-basica.component.css'],
})
export class InformacionBasicaComponent implements OnInit, AfterViewInit {
  loading = true;
  @Input('riesgo') riesgo: Riesgo;
  @Input('lock_info') lock_info: boolean;
  tiposRiesgo: Catalogo[];
  formulario: FormGroup;
  idRiesgo: number;
  submitted = false;
  esRiesgoDeCorrupcion = false;
  private _principal: PaginaRegistroRiesgosComponent;
  @Output('riesgoCreated')
  riesgoCreated = new EventEmitter<Riesgo>(null);
  @Output('onLoadConsecuenciaRiesgo')
  loadConsecuenciaRiesgo = new EventEmitter<string>();
  // Procesos
  procesos: Catalogo[] = [];

  constructor(
    private tipoRiesgoService: TipoRiesgoServiceService,
    private riesgoService: RiesgoServiceService,
    private navegacionRiesgoService: NavegacionRiesgoService,
    private toaStr: ToastrService,
    private fb: FormBuilder,
    private authService: AuthService,
    private catalogoService: CatalogoService,
    private _injector: Injector
  ) {}

  ngOnInit(): void {
    this._principal = this._injector.get(PaginaRegistroRiesgosComponent);
    this.crearFormulario();
    this.cargarInformacionInicial();
    this.loadCatalogos();
    this.validaRiesgo();
    if (this.lock_info) {
      //disable all inputs in form
      this.formulario.disable();
    }
  }

  private loadCatalogos() {
    combineLatest([
      this.catalogoService.fetchCatalogo('tipoProceso'),
      this.catalogoService.fetchCatalogo('tipoRiesgo'),
    ]).subscribe(([procesos, tiposRiesgo]) => {
      this.procesos = procesos;
      this.tiposRiesgo = tiposRiesgo;
    });
  }

  cargarInformacionInicial() {
    if (this._principal.idRiesgo) {
      combineLatest([
        this.tipoRiesgoService.fetchTipoRiesgo(),
        this.riesgoService.fetchRiesgo(this._principal.idRiesgo),
      ]).subscribe(([tiposRiesgo, riesgo]) => {
        this.tiposRiesgo = tiposRiesgo;
        this.setRiesgo(riesgo);
        this.loading = false;
        this.loadConsecuenciaRiesgo.emit(riesgo.riesgo.mayorConsecuencia);
      });
    } else {
      this.tipoRiesgoService.fetchTipoRiesgo().subscribe({
        next: (tipos) => (this.tiposRiesgo = tipos),
        complete: () => (this.loading = false),
      });
    }
  }

  ngAfterViewInit(): void {}

  crearFormulario() {
    this.formulario = this.fb.group({
      idTipoRiesgo: ['', Validators.required],
      idProceso: ['', Validators.required],
      nombre: ['', Validators.required],
      objetivo: ['', Validators.required],
      relacionConObjetivo: [false],
      motivacion: [
        '',
        conditionalValidator(
          () => this.esRiesgoDeCorrupcion,
          Validators.required
        ),
      ],
      responsabilidad: [
        '',
        conditionalValidator(
          () => this.esRiesgoDeCorrupcion,
          Validators.required
        ),
      ],
      oportunidad: [
        '',
        conditionalValidator(
          () => this.esRiesgoDeCorrupcion,
          Validators.required
        ),
      ],
    });

    if (this.riesgo) {
      this.formulario.patchValue(this.riesgo);
    }
  }

  cargarCatalogos() {
    this.tipoRiesgoService.fetchTipoRiesgo().subscribe({
      next: (tiposRiesgo) => {
        this.tiposRiesgo = tiposRiesgo;
      },
    });
  }

  validaRiesgo() {
    this.formulario.get('idTipoRiesgo').valueChanges.subscribe({
      next: (id) => {
        if (this.tiposRiesgo) {
          const tipoRiesgo = this.tiposRiesgo.find((t) => t.id === id);
          this.esRiesgoDeCorrupcion =
            tipoRiesgo.codigo === ETipoRiesgo.R_CORRUPCION;
          for (const key of ['motivacion', 'responsabilidad', 'oportunidad']) {
            this.formulario.get(key).updateValueAndValidity();
          }
        }
      },
    });
  }

  setRiesgo(riesgo: RiesgoDTO) {
    if (riesgo) {
      this.idRiesgo = riesgo.riesgo.id;
      const riesgoSelecionado = riesgo.riesgo;
      this.formulario.patchValue({
        idTipoRiesgo: riesgoSelecionado.tipoRiesgo.id,
        idProceso: riesgoSelecionado.tipoProceso?.id,
        ...riesgoSelecionado,
      });
      this.esRiesgoDeCorrupcion =
        riesgoSelecionado.tipoRiesgo.codigo === ETipoRiesgo.R_CORRUPCION;
    }
  }

  loadRiesgo() {
    if (this._principal.idRiesgo) {
      return this.riesgoService.riesgoFetchedSubject;
    } else {
      return of<RiesgoDTO>(null);
    }
  }

  cargarRiesgoSeleccionado() {
    this.riesgoService.riesgoFetchedSubject.subscribe({
      next: (riesgo) => {},
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.formulario.invalid) {
      return;
    }

    if (!this._principal.editMode) {
      this._create();
    } else {
      this._update();
    }
  }

  private _create() {
    this._handleResponse(
      this.riesgoService.saveRiesgo({
        ...this.formulario.value,
        idUsuario: this.authService.authStatus.userId,
      })
    );
  }

  private _update() {
    this._handleResponse(
      this.riesgoService.updateRiesgo({
        ...this.formulario.value,
        id: this.idRiesgo,
      })
    );
  }

  private _handleResponse(observable: Observable<Riesgo>) {
    observable.subscribe({
      next: (riesgo) => {
        if (!this._principal.editMode) {
          this.riesgoCreated.emit(riesgo);
        }
        this.idRiesgo = riesgo.id;
        this.navegacionRiesgoService.idRiesgoSubject.next(riesgo.id);
        this._updateStatus(riesgo.id);
      },
      error: (err) => {
        this.toaStr.error(
          'No ha sido posible guardar la información básica del riesgo'
        );
      },
      complete: () => {
        this.toaStr.success('Información básica del riesgo guardada con éxito');
        this.navegacionRiesgoService.changeTabSubject.next(2);
      },
    });
  }

  private _updateStatus(idRiesgo: number) {
    updateStateRiesgo({
      id: idRiesgo,
      status: EstatusInfoRiesgo.Causas,
    });
  }

  onCancel() {
    this.submitted = false;
    this.formulario.reset();
  }

  get f() {
    return this.formulario.controls;
  }
}
