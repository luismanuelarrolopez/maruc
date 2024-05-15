import {
  Directive,
  Input,
  OnInit,
  TemplateRef,
  ViewContainerRef,
} from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';

@Directive({
  selector: '[hasRole]',
})
export class HasRoleDirective implements OnInit {
  @Input('hasRole')
  roleName: any;

  constructor(
    private viewContainerRef: ViewContainerRef,
    private templateRef: TemplateRef<any>,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const role = this.authService.getRoleByName(this.roleName);
    if (this.authService.userHasRole(role)) {
      this.viewContainerRef.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainerRef.clear();
    }
  }
}
