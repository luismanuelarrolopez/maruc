import { Component, Input, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { EvidenciaService } from 'src/app/common/services/evidencia.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { SoporteEvidencia } from 'src/app/common/models/soporte-evidencia';
import { faDownload } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'ver-soporte-evidencia-component',
  templateUrl: './ver-soporte-evidencia.component.html',
  styleUrls: ['./ver-soporte-evidencia.component.css']
})
export class VerSoporteEvidenciaComponent implements OnInit {
  @Input() soporte: SoporteEvidencia
  urlSop : SafeResourceUrl
  file: Blob
  downloadIcon = faDownload
  loading = false
  filename = ""
  constructor(private sanitizer: DomSanitizer, private toast: ToastrService, private objEvidencia: EvidenciaService ) { }

  ngOnInit(): void {
    //descarga el soporte de evidencia
    this.loading = true
    console.log(this.soporte)
    this.filename = this.soporte.ruta_soporte.slice(37)
    this.objEvidencia.descargarSoporteEvidencia(this.soporte.id).subscribe(
      (data) => {
        this.file = data.body
        try{
          this.urlSop = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(data.body));
          this.loading = false
        }catch(e){
          console.log(e)
        }        
      })
  }

  descargarSoporte(){
    let url = window.URL.createObjectURL(this.file);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = this.filename;
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
  }

}
