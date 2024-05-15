import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RiesgosService } from '../../../../common/services/riesgos.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-controles',
  templateUrl: './controles.component.html',
  styleUrls: ['./controles.component.css']
})
export class ControlesComponent implements OnInit {
  riesgo: any;
  loading: boolean = true;
  constructor(private route: ActivatedRoute, private objRiesgos: RiesgosService, private toast: ToastrService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      let id_riesgo= params['id'];
      if (id_riesgo == undefined) {
        this.toast.error("No se ha seleccionado un riesgo")
      }else{
        this.objRiesgos.fetchById(id_riesgo).subscribe(
          (riesgo) => {
            this.riesgo = riesgo;
            this.riesgo.riesgo?.controles?.forEach(control => {
              control.observaciones_corregidas = true;
              control.evidencia?.evidenciaObservacion?.forEach(evidenciaObs => {
                if(!evidenciaObs.observacion.corregida) control.observaciones_corregidas = false;
              })
            })
            this.loading = false;
          }
        )
      }
    });
  }

}
