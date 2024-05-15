import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-btn-agendar-asesorias',
  templateUrl: './btn-agendar-asesorias.component.html',
  styleUrls: ['./btn-agendar-asesorias.component.css'],
})
export class BtnAgendarAsesoriasComponent implements OnInit {
  constructor(private modal: NgbModal) {}

  ngOnInit(): void {}

  abrirDialogo(content: any) {
    this.modal.open(content);
  }
}
