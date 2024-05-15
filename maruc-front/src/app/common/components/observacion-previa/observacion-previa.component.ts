import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { convertTimeToString } from 'src/app/core/utils/CommonServices'

@Component({
  selector: 'observacion-previa',
  templateUrl: './observacion-previa.component.html',
  styleUrls: ['./observacion-previa.component.css']
})
export class ObservacionPreviaComponent implements OnInit {
  @Input() observacion;
  @Input() corregir : boolean
  convertTimeToString : any = convertTimeToString
  @Output() MarcarCorregida : EventEmitter<any> = new EventEmitter()
  constructor() { }

  ngOnInit(): void {
  }

  _MarcarCorregida(observacion, option) : void {
    if(option != observacion.corregida) {
        let values = {
          observacion,
          option
        }
        //find the observacion in the array and update the value
        this.observacion.observacion.corregida = option;
        this.MarcarCorregida.emit(values);
    }
  }
}
