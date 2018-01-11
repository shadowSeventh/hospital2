import conf from "../conf";
import angular from "angular";
import alertService from "./alertService";
import commonService from "./commonService";
import enumService from "./enumService";
import errorService from "./errorService";
import authService from "./authService";
import imgService from "./imgService";
import orderNumService from "./orderNumService";
import loginService from "./loginService"

export default angular.module(`${conf.app}.services`, [
    alertService.name,
    commonService.name,
    enumService.name,
    errorService.name,
    authService.name,
    imgService.name,
    orderNumService.name,
    loginService.name

]);
