import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Riesgo } from 'src/app/common/models/riesgo';
import { TipoProceso } from 'src/app/common/models/TipoProceso';
import { TipoProcesoService } from 'src/app/common/services/tipoProceso.service';
import { Paginable } from 'src/app/core/common/models/paginable';
import { TipoRiesgo } from 'src/app/riesgos/models/tipo-riesgo';
import { TipoRiesgoServiceService } from 'src/app/riesgos/services/tipo-riesgo-service.service';
import { RiesgosService } from '../../../common/services/riesgos.service';
import { faDownload } from '@fortawesome/free-solid-svg-icons';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';
import { reporteRiesgosDto } from '../../models/dto/reporteRiesgosDto';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-riesgos-oci',
  templateUrl: './riesgos.component.html',
  styleUrls: ['./riesgos.component.css'],
})
export class RiesgosComponent extends Paginable implements OnInit, OnDestroy {
  urlSop : SafeResourceUrl
  file: Blob
  downloadIcon = faDownload

  riesgos_table: Riesgo[] = [];
  riesgos: Riesgo[] = [];
  list_procesos: TipoProceso[] = [];
  list_tipo_riesgo: TipoRiesgo[] = [];
  list_riesgo_residual: string[] = [];
  list_riesgo_inherente: string[] = [];
  loading_report: boolean = false;
  reporte_model: reporteRiesgosDto = {version: "", vigencia: ""};

  //search params
  key_search: string = '';
  select_proceso: number = 0;
  select_tipo_riesgo: number = 0;
  select_riesgo_residual: number = 0;
  select_riesgo_inherente: number = 0;

  private dataChangeSubscription: Subscription;
  constructor(
    private objRiesgos: RiesgosService,
    private objTipoProceso: TipoProcesoService,
    private objTipoRiesgo: TipoRiesgoServiceService,
    private sanitizer: DomSanitizer,
    private toast: ToastrService,
    private modalService: NgbModal
  ) {
    super();
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true})
  }

  ngOnInit(): void {
    this.dataChangeSubscription = this.objRiesgos.dataChangeSubject.subscribe({
      next: (data) => {
        this.fetchRiesgos();
        this.fetchTipoProceso();
        this.fetchTipoRiesgo();
      },
    });
  }

  search() {
    if (
      this.key_search.length > 2 ||
      this.select_proceso !== 0 ||
      this.select_tipo_riesgo !== 0 ||
      this.select_riesgo_residual !== 0 ||
      this.select_riesgo_inherente !== 0
    ) {
      this.loading = true;
      let values = {
        key: this.key_search,
        proceso: this.select_proceso,
        tipo_riesgo: this.select_tipo_riesgo,
        riesgo_residual: this.select_riesgo_residual,
        riesgo_inherente: this.select_riesgo_inherente,
        all: true
      };
      console.log(values);
      this.objRiesgos.fetchSearch(values, this.page - 1, this.size).subscribe({
        next: (value) => {
          this.riesgos_table = value.content;
          this.totalElements = value.totalElements;
        },
        complete: () => {
          this.loading = false;
        },
      });
    } else this.riesgos_table = this.riesgos;
  }

  fetchRiesgos() {
    this.loading = true;
    this.objRiesgos.fetchRiesgosDTO(this.page - 1, this.size, false).subscribe({
      next: (value) => {
        this.riesgos_table = value.content;
        this.riesgos = value.content;
        this.totalElements = value.totalElements;
      },
      complete: () => {
        this.loading = false;
      },
      error: (error) => {
        console.log(error);
        this.toast.error("Error al cargar los riesgos", "Error");
      },
    });
  }

  fetchTipoProceso() {
    this.loading = true;
    this.objTipoProceso.fetchTipoProceso().subscribe({
      next: (value) => {
        this.list_procesos.push(...value);
      },
    });
  }

  fetchTipoRiesgo() {
    this.loading = true;
    this.objTipoRiesgo.fetchTipoRiesgo().subscribe({
      next: (value) => {
        this.list_tipo_riesgo.push(...value);
      },
    });
  }

  handleKey_Search(event) {
    if (event.keyCode === 13) {
      this.search();
    }
    if(event.target.value.length === 0){
      this.search();
    }
  }

  handleSelectProceso(event) {
    let id_proceso = event.target.value;
    if (id_proceso !== 0) {
      this.select_proceso = parseInt(id_proceso);
      this.search();
    }
  }

  handleSelectTipoRiesgo(event) {
    let id_tipo_riesgo = event.target.value;
    if (id_tipo_riesgo !== 0) {
      this.select_tipo_riesgo = parseInt(id_tipo_riesgo);
      this.search();
    }
  }

  handleSelectRiesgoResidual(event) {
    let riesgo_residual = event.target.value;
    if (riesgo_residual !== 0) {
      this.select_riesgo_residual = parseInt(riesgo_residual);
      this.search();
    }
  }

  handleSelectRiesgoInherente(event) {
    let riesgo_inherente = event.target.value;
    if (riesgo_inherente !== 0) {
      this.select_riesgo_inherente = parseInt(riesgo_inherente);
      this.search();
    }
  }

  handleDownloadReport(){
    this.modalService.dismissAll()
    this.loading_report = true;
    this.objRiesgos.Descargar_Reporte_Riesgos(this.reporte_model).subscribe(
      (data) => {
        this.file = data.body
        try{
          this.urlSop = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(data.body));
          this.descargarSoporte()
        }catch(e){
          console.log(e)
          this.loading_report = false;
        }    
        this.reporte_model = {version: "", vigencia: ""};    
      },
      error => {
        this.loading_report = false;
      }
      )
  }

  descargarSoporte(){
    let url = window.URL.createObjectURL(this.file);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = "Mapa_Riesgos.pdf";
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
        this.loading_report = false;
        this.toast.success("Mapa de riesgos descargado")
  }

  ngOnDestroy(): void {
    this.dataChangeSubscription.unsubscribe();
  }
}
