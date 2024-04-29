import { Directive } from '@angular/core';
import {
  FormControl,
  NG_VALIDATORS,
  ValidationErrors,
  Validator,
} from '@angular/forms';

@Directive({
  selector: '[passwordMatching]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: PasswordMatchingDirective,
      multi: true,
    },
  ],
})
export class PasswordMatchingDirective implements Validator {
  constructor() {}

  validate(control: FormControl): ValidationErrors {
    console.log('Creating password matching directive validator');
    return null;
  }
}
