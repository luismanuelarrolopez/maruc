import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-riesgos-table',
  templateUrl: './riesgos-table.component.html',
  styleUrls: ['./riesgos-table.component.css']
})
export class RiesgosTableComponent implements OnInit {

  @Input() riesgos: any[] = []
  constructor() { }

  ngOnInit(): void {
    console.log(this.riesgos)
  }

}
