import {Directive, ElementRef, Input, OnChanges, Renderer2, SimpleChanges} from "@angular/core";

@Directive({
  selector: '[isCausaCritica]'
})
export class IsCausaCriticaDirective implements OnChanges {

  @Input('isCausaCritica')
  index: number;

  constructor(private el: ElementRef, private renderer: Renderer2) {
    console.log(el.nativeElement);
    console.log(this.el.nativeElement.getAttribute('data-index'));
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.index < 3) {
      this.renderer.addClass(this.el.nativeElement, 'background-red');
    } else {
      this.renderer.removeClass(this.el.nativeElement, 'background-red');
    }
  }
}
