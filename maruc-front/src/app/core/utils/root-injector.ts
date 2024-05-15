import { Injector } from '@angular/core';

export let rootInjector: Injector;
export function setRootInjector(injector: Injector) {
  rootInjector = injector;
}
