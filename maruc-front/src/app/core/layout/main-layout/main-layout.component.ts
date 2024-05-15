import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.css']
})
export class MainLayoutComponent implements OnInit {

  enableTopButton : boolean = false;
  constructor() { }

  ngOnInit(): void {
  }

  toTopPage() {
    window.scrollTo(0, 0);
  }

  handleScroll(event) {
    //if the scroll is less than 200px from the top, show the top button
    if (event.target.scrollTop > 200) {
      this.enableTopButton = true;
    }else{
      this.enableTopButton = false;
    }
  }

  topFunction() {
    let element = document.getElementById("container-principal");
    element.scrollTop = 0;
  }
}
