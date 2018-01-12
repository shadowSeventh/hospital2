import conf from "../conf";
import sMain from "./main";


import sMainContentMain from "./main/contentMain";
import sMainHospital from "./main/hospital";
import sMainLoginTime from "./main/loginTime";
import sMainHospitalHome from "./main/hospital/home";
import sMainHospitalArticle from "./main/hospital/article";
import sMainHospitalArticleList from "./main/hospital/article/list";
import sMainHospitalArticleAdd from "./main/hospital/article/add";


export default angular.module(`${conf.app}.states`, [
    sMain.name,
    sMainContentMain.name,


    sMainHospital.name,
    sMainLoginTime.name,
    sMainHospitalHome.name,
    sMainHospitalArticle.name,
    sMainHospitalArticleList.name,
    sMainHospitalArticleAdd.name,

])

