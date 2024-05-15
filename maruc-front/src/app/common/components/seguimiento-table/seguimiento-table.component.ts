import { Component, Input, OnInit } from '@angular/core';
import { faCircleExclamation } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-seguimiento-table',
  templateUrl: './seguimiento-table.component.html',
  styleUrls: ['./seguimiento-table.component.css']
})
export class SeguimientoTableComponent implements OnInit {
  exclamationIcon = faCircleExclamation
  @Input() riesgos: any[] = []
  constructor() { }

  ngOnInit(): void {
    console.log(this.riesgos)
  }

}
