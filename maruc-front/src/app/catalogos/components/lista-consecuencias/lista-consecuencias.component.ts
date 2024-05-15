import {
  Component,
  EventEmitter,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { Paginable } from 'src/app/core/common/models/paginable';
import { ConsecuenciaDTO } from '../../models/ConsecuenciaDTO';
import { CatalogoConsecuenciaService } from '../../services/catalogo-consecuencia.service';

@Component({
  selector: 'app-lista-consecuencias',
  templateUrl: './lista-consecuencias.component.html',
  styleUrls: ['./lista-consecuencias.component.css'],
})
export class ListaConsecuenciasComponent
  extends Paginable
  implements OnInit, OnDestroy
{
  private dataChangeSubscription: Subscription;
  listaConsecuencias: ConsecuenciaDTO[];
  @Output('onSelect')
  private onSelectConsecuencia: EventEmitter<any> = new EventEmitter();

  constructor(
    private consecuenciaService: CatalogoConsecuenciaService,
    private toastr: ToastrService
  ) {
    super();
  }

  ngOnInit() {
    this.dataChangeSubscription =
      this.consecuenciaService.dataChangeSubject.subscribe({
        next: (v) => this.fetchConsecuencias(),
      });
  }

  fetchConsecuencias() {
    this.loading = true;
    this.consecuenciaService
      .fetchConsecuencias(this.page - 1, this.size)
      .subscribe({
        next: (value) => {
          console.log(value);
          this.listaConsecuencias = value.content;
          this.totalElements = value.totalElements;
        },
        complete: () => {
          this.loading = false;
        },
      });
  }

  onEditar(idConsecuencia: number) {
    // this.consecuenciaService.dataSelectedSubject.next({
    //   disabled: false,
    //   isEdit: true,
    //   data: idConsecuencia,
    // });
    this.onSelectConsecuencia.emit(idConsecuencia);
  }

  onEliminar(idConsecuencia: number) {
    this.consecuenciaService.deleteById(idConsecuencia).subscribe({
      error: (e) =>
        this.toastr.error('Ocurrío un error al eliminar la consecuencia'),
      complete: () => {
        this.toastr.success('Se ha eliminado correctamente la consecuencia.');
        this.fetchConsecuencias();
      },
    });
  }

  ngOnDestroy() {
    this.dataChangeSubscription.unsubscribe();
  }
}
