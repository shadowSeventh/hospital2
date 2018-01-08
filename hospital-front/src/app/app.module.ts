import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {HashLocationStrategy , LocationStrategy} from '@angular/common';
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";

import { Login } from './states/main/login/login.component';
import {Sidenav} from "./config/sidenav/sidenav.component";
import {MatSidenavModule} from "@angular/material";

const appRoutes: Routes = [
  { path: 'login', component: Login },
  { path: 'sidenav', component: Sidenav },
  {
    path: 'login',
    component: Login,
    // data: { title: 'Heroes List' }
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  { path: '**', component: Login }
];


@NgModule({
  declarations: [
    Login,
    Sidenav,
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    HttpModule,
    MatSidenavModule,
    FormsModule,
  ],
  providers: [
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    }
  ],
  bootstrap: [Login]
})
export class AppModule { }
