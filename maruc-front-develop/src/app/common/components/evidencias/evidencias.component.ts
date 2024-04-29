import { Component, OnInit } from '@angular/core';
import { faPen } from '@fortawesome/free-solid-svg-icons';
import { faEye } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { convertTimeToString } from 'src/app/core/utils/CommonServices';
import { RiesgosService } from 'src/app/common/services/riesgos.service';
import { Riesgo } from 'src/app/common/models/riesgo';
import { evidenciaDTO } from 'src/app/common/models/dto/evidenciaDto';
import { EvidenciaService } from 'src/app/common/services/evidencia.service';
import { Role } from 'src/app/auth/model/role';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-evidencias',
  templateUrl: './evidencias.component.html',
  styleUrls: ['./evidencias.component.css']
})
export class EvidenciasComponent implements OnInit {
  convertTimeToString : any = convertTimeToString
  add: boolean = false
  evidencia: evidenciaDTO
  penIcon = faPen
  eyeIcon = faEye
  trashIcon = faTrash
  loading: boolean = true
  deleting: boolean = false
  role : Role
  
  constructor(
    private objEvidencia: EvidenciaService, 
    private modalService: NgbModal, 
    private toast: ToastrService, 
    private route: ActivatedRoute,
    private authService: AuthService) { }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true})
  }

  addEvidencia(soporteEvidencia): void {
    this.evidencia.evidencia.soporteEvidencia.push(soporteEvidencia)
  }

  updateEvidencia(soporteEvidencia): void {
    this.evidencia.evidencia.soporteEvidencia.forEach(
      (item, index) => {
        if (item.id == soporteEvidencia.id) {
          this.evidencia.evidencia.soporteEvidencia[index] = soporteEvidencia
          this.modalService.dismissAll()
          this.toast.success("Soporte de evidencia actualizado correctamente")
        }
      }
    )
  }

  deleteEvidencia(id_soporteEvidencia): void {
    this.deleting = true
    this.objEvidencia.eliminarSoporteEvidencia(id_soporteEvidencia).subscribe(
      response => {
        this.evidencia.evidencia.soporteEvidencia = this.evidencia.evidencia.soporteEvidencia.filter(soporte => soporte.id !== id_soporteEvidencia)
        this.modalService.dismissAll()
        this.toast.success("Soporte de evidencia eliminado correctamente")
        this.deleting = false
        }
      )
  }

  ngOnInit(): void {
    this.role = this.authService.authStatus.userRole;
    this.route.params.subscribe(params => {
      let evidencia= params['id_evidencia'];
      let riesgo = params['id_riesgo']
      if (evidencia == undefined) {
        this.toast.error("No se ha seleccionado una evidencia")
      }else{
        this.objEvidencia.fetchById(evidencia).subscribe(
          evidencia => {
            this.evidencia = evidencia
            console.log(this.evidencia)
            this.evidencia.evidencia.soporteEvidencia.forEach(
              (item, index) => {
                this.evidencia.evidencia.soporteEvidencia[index].evidencia_id = this.evidencia.evidencia.id
              })
              this.loading = false
          }
        );
      }
    });
  }
}
