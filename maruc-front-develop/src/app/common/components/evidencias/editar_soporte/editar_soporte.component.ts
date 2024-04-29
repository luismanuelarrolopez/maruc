import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { faSave } from '@fortawesome/free-solid-svg-icons';

import { ToastrService } from 'ngx-toastr';
import { HttpEventType } from '@angular/common/http';
import { convertTimeToString } from 'src/app/core/utils/CommonServices';
import { SoporteEvidencia } from 'src/app/common/models/soporte-evidencia';
import { EvidenciaService } from 'src/app/common/services/evidencia.service';

@Component({
  selector: 'editar-soporte-evidencia',
  templateUrl: './editar_soporte.component.html',
  styleUrls: ['./editar_soporte.component.css']
})
export class EditarSoporteComponent implements OnInit {

  @Input() soporte: SoporteEvidencia;
  @Output() update = new EventEmitter<SoporteEvidencia>();
  convertTimeToString : any = convertTimeToString
  soporte_evidencia : SoporteEvidencia;
  saveicon = faSave;
  constructor(private evidenciaService: EvidenciaService, private toast: ToastrService ) { }

  ngOnInit(): void {
    this.soporte_evidencia = {...this.soporte};
    console.log(this.soporte_evidencia);
  }

  public editarSoporteEvidencia() : void {
    console.log(this.soporte_evidencia);
    this.evidenciaService.actualizarSoporteEvidencia(this.soporte_evidencia).subscribe(
      res => {
        console.log(res)
        this.update.emit(res)
        }
    );
  }

}
