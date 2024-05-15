import {
  Directive,
  ElementRef,
  HostListener,
  OnInit,
  Renderer2,
} from '@angular/core';

@Directive({
  selector: '[appCharacterCount]',
})
export class CharacterCountDirective implements OnInit {
  private formGroup: any;
  private readonly maxLength: number;
  constructor(private _el: ElementRef, private _renderer: Renderer2) {
    this.maxLength = this._el.nativeElement.maxLength;
  }

  ngOnInit(): void {
    this.formGroup = this._el.nativeElement.parentNode;
    let counterElement = this._renderer.createElement('div');

    let text = this._renderer.createText(
      `${this._el.nativeElement.value.length} / ${this.maxLength}`
    );
    this._renderer.addClass(counterElement, 'character-counter');
    this._renderer.addClass(counterElement, 'float-end');
    this._renderer.appendChild(counterElement, text);
    this._renderer.appendChild(this.formGroup, counterElement);
  }

  @HostListener('ngModelChange', ['$event']) onKeyUp(value) {
    const textElements = this.formGroup.querySelectorAll('.character-counter');
    console.log(textElements);
    textElements[0].innerHTML = `${this._el.nativeElement.value.length} / ${this.maxLength}`;
  }
}
