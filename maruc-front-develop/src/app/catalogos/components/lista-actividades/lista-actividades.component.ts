import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Paginable } from 'src/app/core/common/models/paginable';
import { ActividadDTO } from '../../models/ActividadDTO';
import { CatalogoActividadService } from '../../services/catalogo-actividad.service';

@Component({
  selector: 'app-lista-actividades',
  templateUrl: './lista-actividades.component.html',
  styleUrls: ['./lista-actividades.component.css'],
})
export class ListaActividadesComponent extends Paginable implements OnInit {
  listaActividades: ActividadDTO[] = [];
  @Output()
  onSelect: EventEmitter<number> = new EventEmitter();

  constructor(
    private catalogoService: CatalogoActividadService,
    private toastr: ToastrService
  ) {
    super();
  }

  ngOnInit() {
    this.fetchActividades();
  }

  fetchActividades() {
    this.loading = true;
    this.catalogoService
      .obtenerListaActividades(this.page - 1, this.size)
      .subscribe({
        next: (v) => {
          this.listaActividades = v.content;
          this.totalElements = v.totalElements;
        },
        complete: () => (this.loading = false),
      });
  }

  onSeleccionarActividad(idActividad: number) {
    this.catalogoService.dataSavedSubject.next(false);
    this.onSelect.emit(idActividad);
  }

  onDelete(idActividad: number) {
    this.catalogoService.eliminarActividad(idActividad).subscribe({
      error: (e) => this.toastr.error('Error al eliminar la actividad'),
      complete: () => {
        this.toastr.success('Se ha eliminado la actividad correctamente');
        this.fetchActividades();
      },
    });
  }
}
