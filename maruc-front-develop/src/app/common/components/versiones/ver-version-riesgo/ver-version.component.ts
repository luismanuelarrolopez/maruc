import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { EvidenciaService } from 'src/app/common/services/evidencia.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { SoporteEvidencia } from 'src/app/common/models/soporte-evidencia';
import { faDownload } from '@fortawesome/free-solid-svg-icons';
import { VersionRiesgo } from 'src/app/common/models/version_riesgo';
import { VersionesService } from 'src/app/common/services/versiones.service';
@Component({
  selector: 'ver-version-component',
  templateUrl: './ver-version.component.html',
  styleUrls: ['./ver-version.component.css']
})
export class VerVersionComponent implements OnInit {
  @Input() version: VersionRiesgo
  @Output() downloaded = new EventEmitter();
  urlSop: SafeResourceUrl
  file: Blob
  downloadIcon = faDownload
  loading = false
  filename = ""
  constructor(private sanitizer: DomSanitizer, private toast: ToastrService, private versionService: VersionesService) { }

  ngOnInit(): void {
    //descarga el version de evidencia
    this.loading = true
    this.filename = this.version.ruta_version.slice(37)
    this.versionService.descargarVersionRiesgo(this.version.id).subscribe(
      (data) => {
        this.file = data.body
        try {
          this.urlSop = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(data.body));
          this.loading = false
        } catch (e) {
          console.log(e)
        }
      })
  }

  descargarVersion() {
    let url = window.URL.createObjectURL(this.file);
    let a = document.createElement('a');
    document.body.appendChild(a);
    a.setAttribute('style', 'display: none');
    a.href = url;
    a.download = this.filename;
    a.click();
    window.URL.revokeObjectURL(url);
    a.remove();
    this.downloaded.emit();
  }

}
