import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoFooterLayoutComponent } from './no-footer-layout.component';

describe('NoFooterLayoutComponent', () => {
  let component: NoFooterLayoutComponent;
  let fixture: ComponentFixture<NoFooterLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoFooterLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoFooterLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
