import html from "!html-loader?minimize=true!./view.html";
import Controller from "./Controller";

confState.$inject = ['$stateProvider'];

function confState($stateProvider) {

    $stateProvider.state("main.hospital.doctor", {
        url: "/doctor",

        resolve: {},
        views: {
            "doctor@main.hospital": {
                template: html,
                controller: Controller,
                controllerAs: "vm"
            }
        }
    });

}


export default confState;




