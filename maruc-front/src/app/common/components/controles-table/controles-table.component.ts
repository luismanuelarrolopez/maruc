import { Component, Input, OnInit } from '@angular/core';
import { faCircleExclamation } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-controles-table',
  templateUrl: './controles-table.component.html',
  styleUrls: ['./controles-table.component.css']
})
export class ControlesTableComponent implements OnInit {
  exclamationIcon = faCircleExclamation
  @Input() riesgo: any;
  @Input() options: boolean;
  constructor() { }

  ngOnInit(): void {
    console.log(this.riesgo)
  }

}
