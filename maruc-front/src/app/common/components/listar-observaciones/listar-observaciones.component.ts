import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ObservacionService } from 'src/app/common/services/observacion.service';
import { Evidencia_Observacion } from '../../models/evidencia_observacion';
import { convertTimeToString } from 'src/app/core/utils/CommonServices'
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import { ETipoObservacion } from '../../models/enums/ETipoObservacion';

@Component({
  selector: 'listar-observaciones-previas',
  templateUrl: './listar-observaciones.component.html',
  styleUrls: ['./listar-observaciones.component.css']
})
export class ListarObservacionesComponent implements OnInit {
  @Input() id_entity: number
  @Input() codigo_actor : string
  @Input() corregir : boolean
  @Input() tipo_observacion : string
  @Output() MarcarCorregida : EventEmitter<any> = new EventEmitter()
  convertTimeToString : any = convertTimeToString
  observaciones : Evidencia_Observacion[] = []
  loading = true;
  checkicon = faCheck;

  constructor(private ObservacionService: ObservacionService) { }

  ngOnInit(): void {
    this.fetchObservaciones();
    console.log(this.corregir)
  }
  fetchObservaciones() {
    this.loading = true;
    this.ObservacionService.ListByIdEntityTipoActor(this.id_entity, this.codigo_actor, this.tipo_observacion).subscribe(
      (data: Evidencia_Observacion[]) => {
        this.observaciones = data;
        this.loading = false;
      }
    );
  }

  _MarcarCorregida(observacion, option) : void {
    if(option != observacion.corregida) {
        let values = {
          observacion,
          option
        }
        //find the observacion in the array and update the value
        this.observaciones.find(x => x.id == observacion.id).observacion.corregida = option;
        this.MarcarCorregida.emit(values);
    }
  }

}
