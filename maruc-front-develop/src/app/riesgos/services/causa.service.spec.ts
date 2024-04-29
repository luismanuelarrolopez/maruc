/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { CausaService } from './causa.service';

describe('Service: Causa', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CausaService]
    });
  });

  it('should ...', inject([CausaService], (service: CausaService) => {
    expect(service).toBeTruthy();
  }));
});
