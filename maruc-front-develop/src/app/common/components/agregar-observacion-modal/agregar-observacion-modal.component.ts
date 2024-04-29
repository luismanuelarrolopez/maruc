import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { faSave, faPlus } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'agregar-observacion-modal',
  templateUrl: './agregar-observacion-modal.component.html',
  styleUrls: ['./agregar-observacion-modal.component.css']
})
export class AgregarObservacionModalComponent implements OnInit {
  @Input() observacion_model;  
  @Input() observacion;
  @Output() AgregarObservacion : EventEmitter<any> = new EventEmitter();
  baseModel : any
  saveicon = faSave;
  plusIcon = faPlus;

  constructor(private modalService: NgbModal) { }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true})
  }

  ngOnInit(): void {
    this.baseModel = {...this.observacion_model}
  }

  _AgregarObservacion() : void {
    this.modalService.dismissAll()
    this.AgregarObservacion.emit(this.observacion_model);
    this.observacion_model = this.baseModel;
  }

}
