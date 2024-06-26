import {
  ChangeDetectorRef,
  Component,
  ContentChild,
  ElementRef,
  EventEmitter,
  HostListener,
  Input,
  OnInit,
  Output,
  TemplateRef,
} from '@angular/core';

@Component({
  selector: 'app-custom-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
})
export class ModalComponent implements OnInit {
  @ContentChild('modalHeader')
  header: TemplateRef<any>;
  @ContentChild('modalBody')
  body: TemplateRef<any>;
  @ContentChild('modalFooter')
  footer: TemplateRef<any>;
  @Input() closeOnOutsideClick = true;

  @Output()
  private onClose: EventEmitter<any> = new EventEmitter();

  visible = false;
  visibleAnimate = false;

  constructor(
    private elementRef: ElementRef,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit() {}

  ngOnDestroy() {
    this.close();
  }

  open(): void {
    document.body.classList.add('modal-open');

    this.visible = true;
    setTimeout(() => {
      this.visibleAnimate = true;
    });
  }

  close(): void {
    document.body.classList.remove('modal-open');

    this.visibleAnimate = false;
    setTimeout(() => {
      this.visible = false;
      this.changeDetectorRef.markForCheck();
      this.onClose.emit();
    }, 200);
  }

  @HostListener('click', ['$event'])
  onContainerClicked(event: MouseEvent): void {
    if (
      (<HTMLElement>event.target).classList.contains('modal') &&
      this.isTopMost() &&
      this.closeOnOutsideClick
    ) {
      this.close();
    }
  }

  @HostListener('document:keydown', ['$event'])
  onKeyDownHandler(event: KeyboardEvent) {
    // If ESC key and TOP MOST modal, close it.
    if (event.key === 'Escape' && this.isTopMost()) {
      this.close();
    }
  }

  /**
   * Returns true if this modal is the top most modal.
   */
  isTopMost(): boolean {
    return !this.elementRef.nativeElement.querySelector(':scope .modal');
  }
}
