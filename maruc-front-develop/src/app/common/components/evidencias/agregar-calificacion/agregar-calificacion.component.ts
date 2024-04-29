import { Component, Input, OnInit } from '@angular/core';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { Evidencia } from 'src/app/common/models/evidencia';
import { EvidenciaService } from 'src/app/common/services/evidencia.service';
import ETipoActor from 'src/app/common/models/enums/ETipoActor';
import { Role } from 'src/app/auth/model/role';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'agregar-calificacion',
  templateUrl: './agregar-calificacion.component.html',
  styleUrls: ['./agregar-calificacion.component.css']
})
export class AgregarCalificacionComponent implements OnInit {
  saveicon = faSave;
  @Input() evidencia: Evidencia;
  loading = false;
  role : Role;

  constructor(private objEvidencia : EvidenciaService, private toast: ToastrService, private authService: AuthService) { }

  ngOnInit(): void {
    console.log(this.evidencia)
    this.role = this.authService.authStatus.userRole;
  }

  agregarPorcentajeAvance(){
    this.loading = true
    this.objEvidencia.actualizarEvaluacion(this.evidencia, this.role == Role.Oci ? ETipoActor.OCI : ETipoActor.OPDI).subscribe(
      (data: Evidencia) => {
        this.toast.success("Porcentaje actualizado con exito")
        this.loading = false
      }
    );
  }
  
  _MarcarCumplimientoOCI(values){
    this.evidencia.cumplimiento_oci = values;
  }

  _MarcarCumplimientoOPDI(values){
    this.evidencia.cumplimiento_opdi = values;
  }
}
