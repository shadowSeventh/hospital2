import conf from "../conf";
import sMain from "./main";
import sMainOtherMain from "./main/otherMain";
import sMainHospital from "./main/hospital";
import sMainLoginTime from "./main/loginTime";

import sMainHospitalHome from "./main/hospital/home";
import sMainHospitalLogin from "./main/hospital/login";
import sMainHospitalRegister from "./main/hospital/register";


export default angular.module(`${conf.app}.states`, [
    sMain.name,
    sMainOtherMain.name,
    sMainHospital.name,
    sMainLoginTime.name,
    sMainHospitalHome.name,
    sMainHospitalLogin.name,
    sMainHospitalRegister.name,


])