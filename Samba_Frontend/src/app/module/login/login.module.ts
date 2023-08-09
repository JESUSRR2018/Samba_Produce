import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginPageComponent } from './login-page/login-page.component';
import { SectionOneComponent } from './login-page/components/section-one/section-one.component';
import { SectionTwoComponent } from './login-page/components/section-two/section-two.component';


@NgModule({
  declarations: [
    LoginPageComponent,
    SectionOneComponent,
    SectionTwoComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule
  ]
})
export class LoginModule { }
