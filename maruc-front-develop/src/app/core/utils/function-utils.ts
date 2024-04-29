import {HttpParams} from '@angular/common/http';
import {FormControl, ValidatorFn} from '@angular/forms';
import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

export function buildPageHttpParams(page: number, size: number) {
  let httpParam = new HttpParams();
  httpParam = httpParam.append('page', page.toString());
  httpParam = httpParam.append('size', size.toString());
  return httpParam;
}

export function buildDateFromNgbDate({year, month, day}: NgbDateStruct) {
  return new Date(year, month - 1, day);
}

export interface BooleanFn {
  (): boolean;
}

export function conditionalValidator(
  predicate: BooleanFn,
  validator: ValidatorFn
): ValidatorFn {
  return (formControl: FormControl) => {
    if (!formControl.parent) {
      return null;
    }

    let error = null;
    if (predicate()) {
      error = validator(formControl);
    }
    return error;
  };
}

export function percentage2color(percentage: number) {
  if (percentage < 0.25) {
    return 'rgb(92, 184, 92, .3)';
  } else if (percentage < 0.5) {
    return 'rgb(240, 173, 78, .3)';
  } else if (percentage < 0.75) {
    return 'rgb(217, 83, 79, .3)';
  } else {
    return '#5bc0de';
  }
}

export function groupBy(list: Array<any>, testFunction: Function) {
  return list.reduce((group, item) => {
    const key = testFunction(item);
    const collection = group.get(key);
    if (!collection) {
      group.set(key, [item]);
    } else {
      collection.push(item);
    }
    return group;
  }, new Map<any, Array<any>>());
}

export function average(list: Array<number>) {
  return list.reduce((a, b) => a + b) / list.length;
}

export function numberInRange(x: number, inf: number, sup: number) {
  return x >= inf && x <= sup;
}
