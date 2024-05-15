import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { HttpEventType } from '@angular/common/http';
import { asapScheduler } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SoporteEvidencia } from 'src/app/common/models/soporte-evidencia';
import { EvidenciaService } from 'src/app/common/services/evidencia.service';

@Component({
  selector: 'app-forms-soporte-evidencia',
  templateUrl: './agregar_soporte.component.html',
  styleUrls: ['./agregar_soporte.component.css']
})
export class AgregarSoporteComponent implements OnInit {

  @Input() id_evidencia: number;
  @Output() add = new EventEmitter<SoporteEvidencia>();

  saveicon = faSave;
  soporte_evidencia : SoporteEvidencia = {};
  archivo : File;
  progreso : number = 0;
  constructor(private evidenciaService: EvidenciaService, private toast: ToastrService, private modalService: NgbModal ) { }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true})
  }

  ngOnInit(): void {

  }

  //function to asign file to archivo variable
  public onFileSelected(event) : void{
    //if the file is bigger than 50MB show error
    if(event.target.files[0].size > 52428800) 
    {
      this.toast.error('El archivo no puede ser mayor a 50MB', 'Error')
      this.archivo = null;
      this.soporte_evidencia.ruta_soporte  = null;
    }
    else this.archivo = (event.target).files[0];
  }

  public AgregarSoporteEvidencia() : void {
    let data = new FormData();
    data.append('id_evidencia', this.id_evidencia.toString());
    data.append('nombre', this.soporte_evidencia.nombre);
    data.append('file', this.archivo);
    this.evidenciaService.agregarSoporteEvidencia(data).subscribe(
      event => {
        switch (event.type) {
          case HttpEventType.UploadProgress:
            let total = event.total? event.total : 0;
            this.progreso = Math.round(event.loaded / total * 100);
            break;
          case HttpEventType.Response:
            this.toast.success("Soporte de evidencia agregado correctamente");
            this.modalService.dismissAll();
            this.progreso = 0;
            this.add.emit(event.body as SoporteEvidencia);
            this.soporte_evidencia = { nombre:""};
            break;
          default:
            break;
        }
      }
    );
  }

}
