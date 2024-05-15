import { Component, Input, OnInit } from '@angular/core';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { ObservacionService } from 'src/app/common/services/observacion.service';
import { Evidencia_Observacion } from 'src/app/common/models/evidencia_observacion';
import ETipoActor from 'src/app/common/models/enums/ETipoActor';
import { convertTimeToString } from 'src/app/core/utils/CommonServices'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observacion } from 'src/app/common/models/Observacion';
import { ETipoObservacion } from 'src/app/common/models/enums/ETipoObservacion';
import { Role } from 'src/app/auth/model/role';

@Component({
  selector: 'app-observacion-soporte',
  templateUrl: './observacion_soporte.component.html',
  styleUrls: ['./observacion_soporte.component.css']
})
export class ObservacionSoporteComponent implements OnInit {

  @Input() id_evidencia: number;
  @Input() observacion_mode: boolean;
  @Input() tipo_actor: Role;
  convertTimeToString : any = convertTimeToString
  checkicon = faCheck;
  observacion_model: Observacion = {}
  evidencia_observacion_opdi: Evidencia_Observacion
  evidencia_observacion_oci : Evidencia_Observacion
  loading_observacion_opdi : boolean = true
  loading_observacion_oci: boolean = true
  codigo_actor_oci : string = ETipoActor.OCI
  codigo_actor_opdi : string = ETipoActor.OPDI
  tipo_observacion = ETipoObservacion.EVIDENCIA

  constructor(private ObservacionService: ObservacionService, private toast: ToastrService,private modalService: NgbModal ) { }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true, size: 'lg'})
  }
  
  ngOnInit(): void {
    this.fetchObservacionOPDI(this.id_evidencia)
    this.fetchObservacionOCI(this.id_evidencia)
    this.observacion_mode = this.tipo_actor === Role.LiderProceso ? true : false;
    this.observacion_model = {corregida: false, observacion: "", tipoActor: {codigo: this.tipo_actor === Role.Oci? ETipoActor.OCI : ETipoActor.OPDI}, tipoObservacion: {codigo: this.tipo_observacion}}
  }

  public AgregarObservacionSoporteEvidencia(model) : void {
    this.observacion_model = model;
    this.loading_observacion_oci = this.tipo_actor === Role.Oci? true : false
    this.loading_observacion_opdi = this.tipo_actor === Role.Opdi? true : false
    this.observacion_model.corregida = model.corregida
    this.observacion_model.id = null
    this.observacion_model.tipoActor = {};
    this.observacion_model.tipoActor.codigo = this.tipo_actor === Role.Oci? ETipoActor.OCI : ETipoActor.OPDI;
    this.observacion_model.tipoObservacion = {};
    this.observacion_model.tipoObservacion.codigo = ETipoObservacion.EVIDENCIA;
    this.ObservacionService.MergeObservacionSoporteEvidencia(this.id_evidencia,this.observacion_model).subscribe(
      (data: Evidencia_Observacion) => {
        if(this.tipo_actor === Role.Oci) this.evidencia_observacion_oci = data;
        if(this.tipo_actor === Role.Opdi) this.evidencia_observacion_opdi = data;
        this.toast.success("Observación agregada correctamente");
        this.loading_observacion_oci=false;
        this.loading_observacion_opdi=false;
        this.observacion_model = {corregida: false, observacion: "", tipoActor: {codigo: this.tipo_actor === Role.Oci? ETipoActor.OCI : ETipoActor.OPDI}, tipoObservacion: {codigo: ETipoObservacion.RIESGO}}
      }
    );
  }

  public MarcarCorregida(evidencia_observacion) : void {
    evidencia_observacion.observacion.corregida = evidencia_observacion.option
    this.ObservacionService.ActualizarObservacionSoporteEvidencia(this.id_evidencia,evidencia_observacion.observacion).subscribe(
      (data: Evidencia_Observacion) => {
        if(evidencia_observacion.option) this.toast.success("Observación marcada como corregida");
        else this.toast.warning("Observación marcada como no corregida");
        if(evidencia_observacion.observacion.id == this.evidencia_observacion_opdi.id){
          this.evidencia_observacion_opdi = data
        }
        if(evidencia_observacion.observacion.id == this.evidencia_observacion_oci.id){
          this.evidencia_observacion_oci = data
        }
      }
    );
  }

  public fetchObservacionOPDI(id_evidencia: number): void {
    this.ObservacionService.fetchByIdEvidenciaTipoActor(id_evidencia,  ETipoActor.OPDI).subscribe(
      (data: Evidencia_Observacion) => {
        this.evidencia_observacion_opdi = data;
        this.loading_observacion_opdi = false;
      }
    );
  }

  public fetchObservacionOCI(id_evidencia: number): void {
    this.ObservacionService.fetchByIdEvidenciaTipoActor(id_evidencia, ETipoActor.OCI).subscribe(
      (data: Evidencia_Observacion) => {
        this.evidencia_observacion_oci = data;
        this.loading_observacion_oci = false;
      }
    );
  }

}
