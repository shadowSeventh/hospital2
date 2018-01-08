import   conf                               from "../conf";
import   sMain                              from "./main";


import   sMainContentMain                   from "./main/contentMain";
import   sMainHospital                     from "./main/hospital";
import   sMainLoginTime                     from "./main/loginTime";
import   sMainHospitalHome                 from "./main/hospital/home";



export default angular.module(`${conf.app}.states`, [
    sMain.name,
    sMainContentMain.name,


    sMainHospital.name,
    sMainLoginTime.name,
    sMainHospitalHome.name,

])

