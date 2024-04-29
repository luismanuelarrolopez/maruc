import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-usuarios-principal',
  templateUrl: './usuarios-principal.component.html',
  styleUrls: ['./usuarios-principal.component.css'],
})
export class UsuariosPrincipalComponent implements OnInit {
  usuarioSeleccionadoId: number;
  constructor(private modalService: NgbModal) {}

  ngOnInit() {}

  onAbrirDialogo(content: any) {
    this.usuarioSeleccionadoId = NaN;
    this.modalService.open(content);
  }

  onSelect(value: number, content) {
    this.usuarioSeleccionadoId = value;
    this.modalService.open(content);
  }
}
