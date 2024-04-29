import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { faSave } from '@fortawesome/free-solid-svg-icons';

import { ToastrService } from 'ngx-toastr';
import { convertTimeToString } from 'src/app/core/utils/CommonServices';
import { VersionRiesgo } from 'src/app/common/models/version_riesgo';
import { VersionesService } from 'src/app/common/services/versiones.service';

@Component({
  selector: 'editar-version-riesgos',
  templateUrl: './editar_version.component.html',
  styleUrls: ['./editar_version.component.css']
})
export class EditarVersionRiesgosComponent implements OnInit {

  @Input() version: VersionRiesgo;
  @Output() update = new EventEmitter<VersionRiesgo>();
  convertTimeToString : any = convertTimeToString
  version_riesgo : VersionRiesgo;
  saveicon = faSave;
  loading : boolean = false;
  constructor(private versionService: VersionesService, private toast: ToastrService ) { }

  ngOnInit(): void {
    this.version_riesgo = {...this.version};
  }

  public editarVersionRiesgo() : void {
    this.loading = true;
    this.versionService.actualizarVersionRiesgo(this.version_riesgo).subscribe(
      res => {
        console.log(res)
        this.update.emit(res)
        this.toast.success('Se actualizo correctamente la version de riesgo', 'Exito')
        this.loading = false;
        },
      err => {
        console.log(err)
        this.toast.error('Error al actualizar la version de riesgo', 'Error')
        this.loading = false;
      }
    );
  }

}
