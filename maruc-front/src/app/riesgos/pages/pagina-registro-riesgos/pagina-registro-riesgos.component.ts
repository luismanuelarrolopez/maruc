import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription, switchMap } from 'rxjs';
import { Role } from 'src/app/auth/model/role';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Causa } from '../../models/causa';
import { Riesgo } from '../../models/riesgo';
import { RiesgoCompletoDTO } from '../../models/riesgo-completo-dto';
import { RiesgoDTO } from '../../models/riesgo-dto';
import { NavegacionRiesgoService } from '../../services/navegacion-riesgo.service';
import { RiesgoServiceService } from '../../services/riesgo-service.service';

@Component({
  selector: 'app-pagina-registro-riesgos',
  templateUrl: './pagina-registro-riesgos.component.html',
  styleUrls: ['./pagina-registro-riesgos.component.css'],
})
export class PaginaRegistroRiesgosComponent implements OnInit, OnDestroy {
  private navegacionSubscription: Subscription;
  editMode = false;
  metodologia_mode = false;
  observacion_mode = false;
  riesgo: RiesgoDTO;
  //optional, preguntar a sebastian
  riesgo_name: string;
  role: Role;
  lock_info = false;

  descripcionRiesgo: string;

  mayorConsecuencia: string;
  active: number;
  maxTabEnabled: number = 1;
  sumatoriaConsecuencias: number;
  idRiesgo: number;
  nivelProbabilidad: number;
  riesgoCompleto: RiesgoCompletoDTO;
  causas: Causa[] = [];
  loading: boolean = true;
  riesgoInherente = 0;
  nivelValoracion = 0;

  constructor(
    private navegacionRiesgo: NavegacionRiesgoService,
    private riesgoService: RiesgoServiceService,
    private _activeRoute: ActivatedRoute,
    private _router: Router,
    private toastr: ToastrService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    console.log('');
    this.role = this.authService.authStatus.userRole;
    if (this.role !== Role.LiderProceso) this.lock_info = true;
    this.editMode = this._activeRoute.snapshot.params['id'];
    this.fetchRiesgoSelecionado();
    this.navegacionSubscription =
      this.navegacionRiesgo.changeTabSubject.subscribe({
        next: (valor) => {
          this.active = valor;
          this.maxTabEnabled = Math.max(valor, this.maxTabEnabled);
        },
      });
  }

  fetchRiesgoSelecionado() {
    this._activeRoute.params.subscribe((params) => {
      let id_riesgo = params['id'];
      console.log('Id riesgo seleccionado:', id_riesgo);
      if (id_riesgo == undefined) this.loading = false;
      else {
        this._activeRoute.params
          .pipe(
            switchMap((params) => this.riesgoService.fetchRiesgo(params['id']))
          )
          .subscribe({
            next: (ret) => {
              console.log(ret);
              this.riesgo_name = ret.riesgo.nombre;
              this.riesgoService.riesgoFetchedSubject.next(ret);
              this.idRiesgo = ret.riesgo.id;
              this.maxTabEnabled = Math.max(
                this.maxTabEnabled,
                ret.riesgo.estatusInformacionRiesgo.valor
              );
              this.editMode = true;
              if (this.maxTabEnabled >= 7) this.metodologia_mode = true;
              if (this.role !== Role.LiderProceso) this.observacion_mode = true;
              this.loading = false;
            },
            error: (e) => {
              if (this.editMode) {
                this.toastr.error(
                  'No se ha encontrado información asociada al riesgo solicitado'
                );
                this._router.navigate(['/home']);
              }
            },
          });
      }
    });
  }

  onRiesgoCreated(riesgo: Riesgo) {
    this.riesgo = riesgo;
    this.idRiesgo = riesgo.id;
    this.editMode = true;
  }

  changeMayorConsecuencia(consecuencia: string) {
    this.mayorConsecuencia = consecuencia;
  }

  onAddConsecuencia(puntaje: number) {
    this.sumatoriaConsecuencias = puntaje;
  }

  onNivelProbabilidadChange(nivelProbabilidad: number) {
    console.log(nivelProbabilidad);
    this.nivelProbabilidad = nivelProbabilidad;
  }

  ngOnDestroy(): void {
    this.navegacionSubscription.unsubscribe();
  }

  onDescripcionRiesgoChange(descripcion: string) {
    this.descripcionRiesgo = descripcion;
  }

  onMayorConsecuenciaChange(mayorConsecuencia: string) {
    this.mayorConsecuencia = mayorConsecuencia;
  }
}
