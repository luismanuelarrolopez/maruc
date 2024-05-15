import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Paginable } from 'src/app/core/common/models/paginable';
import { PaginableResult } from 'src/app/core/common/models/paginable-result';
import { Asesoria } from '../../models/asesoria';
import { AsesoriasService } from '../../services/asesorias.service';

@Component({
  selector: 'app-lista-asesorias',
  templateUrl: './lista-asesorias.component.html',
  styleUrls: ['./lista-asesorias.component.css'],
})
export class ListaAsesoriasComponent
  extends Paginable
  implements OnInit, OnDestroy
{
  onDataChangeSubscription: Subscription;
  asesorias: Asesoria[];

  constructor(
    private asesoriasService: AsesoriasService,
    private modal: NgbModal,
    private authService: AuthService
  ) {
    super();
  }

  ngOnInit(): void {
    this.onDataChangeSubscription =
      this.asesoriasService.dataChangeSubject.subscribe({
        next: (dataChanged: boolean) => this.fetchAsesorias(),
      });
  }

  fetchAsesorias() {
    this.loading = true;
    this.asesoriasService
      .fetchAsesorias(
        this.page - 1,
        this.size,
        this.authService.authStatus.userRole
      )
      .subscribe({
        next: (paginableResult: PaginableResult<Asesoria>) => {
          this.asesorias = paginableResult.content;
          this.totalElements = paginableResult.totalElements;
        },
        complete: () => (this.loading = false),
      });
  }

  abrirModalAgendarAsesoria(content: any, idAsesoria: number) {
    this.modal.open(content, {
      animation: true,
    });
    this.asesoriasService.dataSelectedSubject.next(idAsesoria);
  }

  ngOnDestroy(): void {
    this.onDataChangeSubscription.unsubscribe();
  }
}
