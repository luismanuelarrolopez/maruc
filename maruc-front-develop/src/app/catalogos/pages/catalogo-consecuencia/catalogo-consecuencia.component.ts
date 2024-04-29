import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CatalogoConsecuenciaService } from '../../services/catalogo-consecuencia.service';

@Component({
  selector: 'app-catalogo-consecuencia',
  templateUrl: './catalogo-consecuencia.component.html',
  styleUrls: ['./catalogo-consecuencia.component.css'],
})
export class CatalogoConsecuenciaComponent implements OnInit {
  idConsecuencia: number;

  constructor(
    private catalogoConsecuenciaService: CatalogoConsecuenciaService,
    private modalService: NgbModal
  ) {}

  ngOnInit() {
  }

  onCrearConsecuencia(content) {
    this.idConsecuencia = NaN;
    this.modalService.open(content);
  }

  onSelect(value: number, content) {
    this.idConsecuencia = value;
    this.modalService.open(content);
  }

  onSaved(event: any) {
    this.idConsecuencia = NaN;
    console.log('dismiss modal window')
    this.modalService.dismissAll();
  }

  onSaveEvent() {
    this.catalogoConsecuenciaService.dataSavedSubject.next(true);
  }
}
