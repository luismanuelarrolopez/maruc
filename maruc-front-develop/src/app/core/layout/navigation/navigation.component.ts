import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { NotificacionService } from 'src/app/common/services/notificacion.service';
import { Notificacion } from 'src/app/common/models/notificacion';
import { convertTimeToStringWithTime } from 'src/app/core/utils/CommonServices';
import { Router } from '@angular/router';
import { Paginable } from '../../common/models/paginable';
@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent extends Paginable implements OnInit {

  convertTimeToStringWithTime : any = convertTimeToStringWithTime
  nombre_usuario = this.authService.authStatus.nombre;
  notificaciones : Notificacion[] = [];
  notificacionesSinLeer : number = 0;
  numero_paginas : number = 0
  openNotificaciones : boolean = false;

  constructor(private authService: AuthService, private notificacionService: NotificacionService, private router: Router) {
    super();
   }

  ngOnInit() {
    this.page = 0
    this.size = 3
    this.fetchNotificaciones()
    this.fetchCountNotificacionesSinLeer()
  }
  
  handleClickNofiticacion(notificacion: Notificacion) {
    
    if(notificacion.leida == false) {
      this.notificacionService.marcarLeida(notificacion).subscribe(
        (data) => {
          this.fetchNotificaciones()
        })
      }
      this.router.navigateByUrl(notificacion.enlace)
  }

  fetchNotificaciones() {
    if(this.authService.authStatus.userId != null) {
      this.notificacionService.fetch(this.authService.authStatus.userId, this.page, this.size).subscribe({
        next: (value) => {
          this.notificaciones = value.content;
          this.numero_paginas = value.totalPages - 1;
        },
        complete: () => {
          this.loading = false;
        }
      });
    }
  }

  handleClickVerMas() {
    this.openNotificaciones = true;
    this.page = this.page + 1
    this.notificacionService.fetch(this.authService.authStatus.userId, this.page, this.size).subscribe({
      next: (value) => {
        value.content.forEach(element => {
          this.notificaciones.push(element)
        });
        console.log(value)
        console.log(this.page)
      },
      complete: () => {
        this.loading = false;
      }
    });
  }


  fetchCountNotificacionesSinLeer() {
    if(this.authService.authStatus.userId != null) {
      this.notificacionService.fetchCountNotificacionesSinLeer(this.authService.authStatus.userId).subscribe(
        (data) => {
          this.notificacionesSinLeer =data;
        }
      );
    }
    
  }

  open(){
    this.openNotificaciones = !this.openNotificaciones;
  }

  logout() {
    this.authService.logout();
  }
}
