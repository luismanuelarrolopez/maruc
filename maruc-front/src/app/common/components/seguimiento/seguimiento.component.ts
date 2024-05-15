import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Subscription } from 'rxjs';
import { TipoProceso } from 'src/app/common/models/TipoProceso';
import { TipoRiesgo } from 'src/app/common/models/TipoRiesgo';
import { RiesgosService } from 'src/app/common/services/riesgos.service';
import { TipoProcesoService } from 'src/app/common/services/tipoProceso.service';
import { Paginable } from 'src/app/core/common/models/paginable';
import { faDownload } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { Riesgo } from '../../models/riesgo';

@Component({
  selector: 'app-seguimiento',
  templateUrl: './seguimiento.component.html',
  styleUrls: ['./seguimiento.component.css']
})
export class SeguimientoComponent extends Paginable implements OnInit {
  
  urlSop : SafeResourceUrl
  file: Blob
  downloadIcon = faDownload
  riesgos_table: Riesgo[] = [];
  riesgos: Riesgo[] = [];
  list_procesos: TipoProceso[] = [];
  loading_report: boolean = false
  
  //search params
  key_search: string = '';
  select_proceso: number = 0;
  
  private dataChangeSubscription: Subscription;
  constructor(private sanitizer: DomSanitizer, private objRiesgos: RiesgosService, private objTipoProceso: TipoProcesoService, private toast: ToastrService,) { 
    super()
  }

  ngOnInit(): void {
    this.dataChangeSubscription = this.objRiesgos.dataChangeSubject.subscribe({
      next: (data) => {
        this.loading = true;
        this.fetchRiesgos()
        this.fetchTipoProceso()
      }
    }
    )    
  }

  pagSearch(){
    if (
      this.key_search.length > 2 ||
      this.select_proceso !== 0 
    ) {
      this.search()
    }
    else this.fetchRiesgos()
  }


  search() {
    if (
      this.key_search.length > 2 ||
      this.select_proceso !== 0 
    ) {
      this.loading = true;
      let values = {
        key: this.key_search,
        proceso: this.select_proceso,
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

  handleDownloadReport(){
    this.loading_report = true
    this.objRiesgos.Descargar_Reporte_Observaciones().subscribe(
      (data) => {
        this.file = data.body
        try{
          this.urlSop = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(data.body));
          this.descargarSoporte()
        }catch(e){
          console.log(e)
        }        
      })
  }

  descargarSoporte(){
    let url = window.URL.createObjectURL(this.file);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = "reporte_observaciones.pdf";
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
        this.loading_report = false
        this.toast.success("Reporte de observaciones descargado")
  }

  ngOnDestroy(): void {
    this.dataChangeSubscription.unsubscribe();
}

}
