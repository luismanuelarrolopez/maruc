import {
  Component,
  EventEmitter,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Subject, Subscription } from 'rxjs';
import { Paginable } from 'src/app/core/common/models/paginable';
import { Usuario } from '../../models/usuario';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.css'],
})
export class ListaUsuariosComponent
  extends Paginable
  implements OnInit, OnDestroy
{
  usuarios: Usuario[];
  private dataChangeSubscription: Subscription;
  loadingData$ = new Subject<boolean>();
  @Output('onUsuarioSelected')
  eventoSeleccion = new EventEmitter<number>();

  constructor(
    private usuariosService: UsuariosService,
    private toastr: ToastrService
  ) {
    super();
  }

  ngOnInit() {
    this.dataChangeSubscription =
      this.usuariosService.dataChangeSubject.subscribe({
        next: (value) => this.fetchUsuarios(),
      });
  }

  fetchUsuarios() {
    this.loading = true;
    this.usuariosService.fetchUsuarios(this.page - 1, this.size).subscribe({
      next: (value) => {
        this.usuarios = value.content;
        this.totalElements = value.totalElements;
      },
      error:(e ) =>{
        console.log(e)
      },
      complete: () => {
        this.loading = false;
      },
    });
  }

  onEditar(idUsuario: number) {
    this.eventoSeleccion.emit(idUsuario);
  }

  onEliminar(idUsuario: number) {
    this.usuariosService.doEliminar(idUsuario).subscribe({
      complete: () => {
        this.usuariosService.dataChangeSubject.next(true);
        this.toastr.success('Se ha eliminado el usuario correctamente');
      },
    });
  }

  ngOnDestroy() {
    this.dataChangeSubscription.unsubscribe();
  }
}
