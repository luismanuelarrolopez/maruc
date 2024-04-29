import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Riesgo } from 'src/app/common/models/riesgo';
import { RiesgosService } from 'src/app/common/services/riesgos.service';

@Component({
  selector: 'app-controles',
  templateUrl: './controles.component.html',
  styleUrls: ['./controles.component.css']
})
export class ControlesSeguimientoComponent implements OnInit {

  riesgo: any;
  loading = true;

  constructor(private route: ActivatedRoute, private objRiesgos: RiesgosService, private toast: ToastrService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      let id_riesgo= params['id'];
      if (id_riesgo == undefined) {
        this.toast.error("No se ha seleccionado un riesgo")
      }else{
        this.objRiesgos.fetchById(id_riesgo).subscribe(
          (data) => {
            this.riesgo = data
            this.riesgo.riesgo?.controles?.forEach(control => {
              control.observaciones_corregidas = true;
              control.evidencia?.evidenciaObservacion?.forEach(evidenciaObs => {
                if(!evidenciaObs.observacion.corregida) control.observaciones_corregidas = false;
              })
            })
            this.loading = false
          }
        )
      }
    });
  }

}
