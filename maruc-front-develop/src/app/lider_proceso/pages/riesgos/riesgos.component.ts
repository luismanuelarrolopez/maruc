import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Riesgo } from 'src/app/common/models/riesgo';
import { Paginable } from 'src/app/core/common/models/paginable';
import { RiesgosService } from '../../../common/services/riesgos.service';
import { faCircleExclamation } from '@fortawesome/free-solid-svg-icons';
import { Toast, ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-riesgos-lider-proceso',
  templateUrl: './riesgos.component.html',
  styleUrls: ['./riesgos.component.css'],
})
export class RiesgosComponent extends Paginable implements OnInit, OnDestroy {
  
  exclamationIcon = faCircleExclamation
  riesgos_table: any[] = [];
  riesgos: any[] = [];
  key_search: string = ''
  private dataChangeSubscription: Subscription;
  constructor(private objRiesgos: RiesgosService, private toastr: ToastrService, private authService: AuthService) { 
    super()
  }

  ngOnInit(): void {
    this.dataChangeSubscription = this.objRiesgos.dataChangeSubject.subscribe({
      next: (data) => {
        this.fetchRiesgos()
      }
    }
    )    
  }

  search() {
    if (
      this.key_search.length > 2 
    ) {
      this.loading = true;
      let values = {
        key: this.key_search, 
        all: false
      };
      this.objRiesgos.fetchSearch(values, this.page - 1, this.size).subscribe({
        next: (value) => {
          this.riesgos_table = value.content;
          this.totalElements = value.totalElements;
        },
        complete: () => {
          this.loading = false;
        },
      });
    } else this.riesgos_table = this.riesgos;
  }

  fetchRiesgos() {
    this.loading = true;
    this.objRiesgos.fetchRiesgosLiderDTO(this.page - 1, this.size, true, this.authService.authStatus.userId).subscribe({
      next: (value) => {
        this.riesgos_table = value.content;
        this.riesgos = value.content;
        this.totalElements = value.totalElements;
        console.log(this.riesgos_table)
      },
      complete: () => {
        this.loading = false;
      },
      error: (error) => {
        console.log(error);
        this.toastr.error("Error al cargar los riesgos", "Error");
      },
    });
  }

  handleKey_Search(event) {
    if (event.keyCode === 13) {
      this.search();
    }
    if(event.target.value.length === 0){
      this.search();
    }
  }
  
  ngOnDestroy(): void {
      this.dataChangeSubscription.unsubscribe();
  }

}
