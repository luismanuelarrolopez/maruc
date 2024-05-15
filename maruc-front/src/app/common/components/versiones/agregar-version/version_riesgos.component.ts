import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { HttpEventType } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { VersionRiesgo } from 'src/app/common/models/version_riesgo';
import { VersionesService } from 'src/app/common/services/versiones.service';

@Component({
  selector: 'app-forms-version-riesgos',
  templateUrl: './version_riesgos.component.html',
  styleUrls: ['./version_riesgos.component.css']
})
export class VersionRiesgosComponent implements OnInit {

  @Input() id_evidencia: number;
  @Output() add = new EventEmitter<VersionRiesgo>();

  saveicon = faSave;
  version_riesgo : VersionRiesgo = {};
  archivo : File;
  progreso : number = 0;
  loading : boolean = false;
  constructor(private versionService: VersionesService, private toast: ToastrService, private modalService: NgbModal ) { }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true})
  }

  ngOnInit(): void {

  }

  //function to asign file to archivo variable
  public onFileSelected(event) : void{
    this.archivo = (event.target).files[0];
  }

  public AgregarVersionRiesgo() : void {
    this.loading = true;
    let data = new FormData();
    data.append('nombre', this.version_riesgo.nombre);
    data.append('file', this.archivo);
    this.versionService.agregarVersionRiesgo(data).subscribe(
      event => {
        switch (event.type) {
          case HttpEventType.UploadProgress:
            let total = event.total? event.total : 0;
            this.progreso = Math.round(event.loaded / total * 100);
            break;
          case HttpEventType.Response:
            this.toast.success("Versión agregada correctamente");
            this.modalService.dismissAll();
            this.progreso = 0;
            this.add.emit(event.body as VersionRiesgo);
            this.version_riesgo = { nombre:""};
            this.loading = false;
            break;
          default:
            this.loading = false;
            break;
        }
      }
    );
  }

}
