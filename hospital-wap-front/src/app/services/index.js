import conf from "../conf";
import angular from "angular";

import alertService from "./alertService";
import userService from "./userService";
import loginService from "./loginService";
import addressService from  './addressService';

import authService from  './authService';

export default angular.module(`${conf.app}.services`, [
    alertService.name,
    userService.name,
    loginService.name,
    addressService.name,
    authService.name,
]);
