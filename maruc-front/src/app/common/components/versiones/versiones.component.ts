import { Component, OnInit } from '@angular/core';
import { faPen } from '@fortawesome/free-solid-svg-icons';
import { faEye } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { convertTimeToString } from 'src/app/core/utils/CommonServices';
import { Role } from 'src/app/auth/model/role';
import { AuthService } from 'src/app/auth/services/auth.service';
import { VersionesService } from '../../services/versiones.service';

@Component({
  selector: 'app-versiones',
  templateUrl: './versiones.component.html',
  styleUrls: ['./versiones.component.css']
})
export class VersionesComponent implements OnInit {
  convertTimeToString : any = convertTimeToString
  add: boolean = false
  versiones: any[] = []
  penIcon = faPen
  eyeIcon = faEye
  trashIcon = faTrash
  loading: boolean = true
  deleting: boolean = false
  role : Role
  
  constructor(
    private objVersion: VersionesService, 
    private modalService: NgbModal, 
    private toast: ToastrService, 
    private authService: AuthService) { }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true})
  }

  addVersion(versionRiesgo): void {
    this.versiones.push(versionRiesgo)
  }

  verVersion(): void {
    this.modalService.dismissAll();
  }

  updateVersion(versionRiesgo): void {
    this.versiones.forEach(
      (item, index) => {
        if (item.id == versionRiesgo.id) {
          this.versiones[index] = versionRiesgo
          this.modalService.dismissAll()
        }
      }
    )
  }

  deleteVersion(id_versionRiesgo): void {
    this.deleting = true
    this.objVersion.eliminarVersionRiesgo(id_versionRiesgo).subscribe(
      response => {
        this.versiones = this.versiones.filter(version => version.id !== id_versionRiesgo)
        this.modalService.dismissAll()
        this.toast.success("Version riesgo eliminado correctamente")
        this.deleting = false
        }
      )
  }

  ngOnInit(): void {
    this.role = this.authService.authStatus.userRole;
    
        this.objVersion.listarVersiones().subscribe(
          data => {
            this.versiones = data
            this.loading = false
          }
        );
  }
}
