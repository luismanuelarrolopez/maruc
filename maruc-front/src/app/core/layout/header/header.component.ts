import { Component, OnInit } from '@angular/core';
import { faTriangleExclamation } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  materializacionIcon = faTriangleExclamation;
  toggle = true;
  constructor() {}
  ngOnInit(): void {}

  toggleNav() {
    this.toggle = !this.toggle;
  }
}
