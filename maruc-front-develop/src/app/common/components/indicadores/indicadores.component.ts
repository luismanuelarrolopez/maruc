import { Component, OnInit } from '@angular/core';
import { RiesgosService } from '../../services/riesgos.service';
import EIndicador from '../../models/enums/EIndicador';
import html2canvas from "html2canvas"; 
import { faDownload } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-indicadores',
  templateUrl: './indicadores.component.html',
  styleUrls: ['./indicadores.component.css']
})
export class IndicadoresComponent implements OnInit {
  downloadIcon = faDownload
  generales: any
  procesos: any
  dependencias: any
  loading = true
 
  imagenCreada;

  constructor(private objRiesgos: RiesgosService) { }

  ngOnInit(): void {
    this.fetchIndicadores();
  }

  fetchIndicadores() {
    this.loading = true;
    this.objRiesgos.fetchIndicadores().subscribe(
      (data: any[]) => {
        console.log(data);
        this.loading = false;
        this.generales = data.filter(x => x.tipo_indicador === EIndicador.GENERAL)
        this.procesos = data.filter(x => x.tipo_indicador === EIndicador.PROCESO)
        this.dependencias = data.filter(x => x.tipo_indicador === EIndicador.DEPENDENCIA)
      }
    );
  }

  crearImagen() {
    html2canvas(document.querySelector("#contenido")).then(canvas => {
 
      this.imagenCreada = canvas.toDataURL();      
      //create a <a> tag to download the canvas as an image
      var link = document.createElement('a');
      link.download = 'indicadores.png';
      link.href = this.imagenCreada;
      link.click();
    });
  }

}
