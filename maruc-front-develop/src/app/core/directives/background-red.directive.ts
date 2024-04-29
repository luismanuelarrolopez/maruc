import {
  Directive,
  ElementRef,
  Input,
  OnChanges,
  Renderer2,
  SimpleChanges,
} from '@angular/core';

@Directive({
  selector: '[backgroundRed]',
})
export class BackgroundRedDirective implements OnChanges {
  @Input('backgroundRed')
  backgroundRed: boolean;

  constructor(private el: ElementRef, private renderer: Renderer2) {}

  ngOnChanges(changes: SimpleChanges) {
    if (this.backgroundRed) {
      this.renderer.addClass(this.el.nativeElement, 'background-red');
    } else {
      this.renderer.removeClass(this.el.nativeElement, 'background-red');
    }
  }
}
