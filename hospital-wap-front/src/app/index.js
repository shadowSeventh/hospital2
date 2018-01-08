// import $ from "jquery";
import conf from "./conf";

import "./global"  // global 全局的优先执行
import 'babel-polyfill';

import "animate.css/animate.css";
import "./css.scss"
// angular
import angular from "angular";
// angular-material
import ngMaterial from "angular-material";
import "angular-swiper";
import "angular-material/angular-material.css";
import services from "./services";

import filters from "./filters";
import directives from "./directives";
import config from "./config";

import "ui-router-extras";
import runState from "./runState";
import uiRouter from "angular-ui-router";

import "./libs";
import  states from "./states";
import 'weui';



console.log('config', config)
export default angular.module(`${conf.app}`, [
    uiRouter,
    "ksSwiper",
    ngMaterial,
    services.name,
    filters.name,
    // http.name,
    states.name,
    directives.name,
    config.name,
    // "ct.ui.router.extras",
])
    .run(runState)
