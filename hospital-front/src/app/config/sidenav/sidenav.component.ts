// import {Component} from '@angular/core';
//
// /** @title Sidenav open & close behavior */
// @Component({
//   selector: 'sidenav.component',
//   templateUrl: 'sidenav.component.html',
//   // styleUrls: ['sidenav-open-close-example.css'],
// })
// export class Sidenav {
//   // events = [];
// }
//
//
// /**  Copyright 2017 Google Inc. All Rights Reserved.
//  Use of this source code is governed by an MIT-style license that
//  can be found in the LICENSE file at http://angular.io/license */


import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'sidenav.component',
  templateUrl: './sidenav.component.html',
  // styleUrls: ['./sidenav.component.scss']
})
export class Sidenav implements OnInit {

  // mode = side : 不会自动关闭
  // mode = over : 会自动关闭
  // mode = push : 会自动关闭

  constructor() {
  }

  ngOnInit(): void {

  }


}
