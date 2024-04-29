import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Form } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from 'src/app/core/common/modal/modal.component';
import { FormularioActividadComponent } from '../../components/formulario-actividad/formulario-actividad.component';
import { ListaActividadesComponent } from '../../components/lista-actividades/lista-actividades.component';
import { CatalogoActividadService } from '../../services/catalogo-actividad.service';

@Component({
  selector: 'app-catalogo-actividad',
  templateUrl: './catalogo-actividad.component.html',
  styleUrls: ['./catalogo-actividad.component.css'],
})
export class CatalogoActividadComponent implements OnInit, AfterViewInit {
  @ViewChild('tblActividades')
  private tblActividades: ListaActividadesComponent;
  @ViewChild('frmActividades')
  private frmActividades: FormularioActividadComponent;

  idActividadSeleccionada: number;

  constructor(
    private catalogoActividadService: CatalogoActividadService,
    private modalService: NgbModal
  ) {}

  ngAfterViewInit(): void {}

  ngOnInit() {}

  onSaved(event: Event) {
    this.tblActividades.fetchActividades();
    this.modalService.dismissAll();
  }

  onSelect(value: number, content) {
    this.idActividadSeleccionada = value;
    this.modalService.open(content);
  }

  onOpenModal(content) {
    this.idActividadSeleccionada = NaN;
    this.modalService.open(content);
  }

  onFormActividadClose() {
    this.frmActividades.onReset();
  }

  onGuardar() {
    this.catalogoActividadService.dataSavedSubject.next(true);
  }
}
