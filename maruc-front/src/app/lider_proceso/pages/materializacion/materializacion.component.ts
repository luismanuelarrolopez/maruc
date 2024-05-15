import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Riesgo } from 'src/app/common/models/riesgo';
import { RiesgosService } from 'src/app/common/services/riesgos.service';

@Component({
  selector: 'materializacion-lider-proceso',
  templateUrl: './materializacion.component.html',
  styleUrls: ['./materializacion.component.css'],
})
export class MaterializacionComponent {
  riesgos: Riesgo[] = []
  loading: boolean = true
  riesgo_selected: Riesgo = null
  descripcion_materializacion: string = ''
  private dataChangeSubscription: Subscription;

  constructor(private modalService: NgbModal, private objRiesgos: RiesgosService, private authService: AuthService, private toast: ToastrService) { 
  }

  open(content) {
    this.riesgo_selected = null
    this.descripcion_materializacion = ''
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true})
  }

  ngOnInit(): void {
    this.dataChangeSubscription = this.objRiesgos.dataChangeSubject.subscribe({
      next: (data) => {
        this.fetchRiesgos()
      }
    }
    )    
  }

  fetchRiesgos() {
    this.objRiesgos.fetchRiesgosByLider(this.authService.authStatus.userId).subscribe({
      next: (value) => {
        this.riesgos = value;
        this.loading = false;
      }
    });
  }

  handleSelectRiesgo(e) {
    this.riesgo_selected = this.riesgos.find(x => x.id == e.target.value)
    console.log(this.riesgo_selected)
  }

  NotificarMaterializacion() {
    if(this.riesgo_selected == null) this.riesgo_selected = { id : 0}
    this.objRiesgos.notificarMaterializacion(this.riesgo_selected.id, this.descripcion_materializacion).subscribe()
    this.toast.success('Se ha notificado la materialización del riesgo')
    this.modalService.dismissAll()
  }
}
