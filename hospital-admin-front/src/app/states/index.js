import conf                                                     from "../conf";
import sMain                                                    from "./main";
import sMainOtherMain                                           from "./main/otherMain";
import sMainBindPhone                                           from "./main/bindPhone";
import sMainWxLogin                                             from "./main/wxLogin";
import sMainBargainApp                                            from "./main/bargainApp";
import sMainLoginTime                                           from "./main/loginTime";
// import   sMainJoinUs                                         from "./main/joinUS";
import sMainBargainAppHome                                        from "./main/bargainApp/home";




//砍价活动后台
import sMainBargainAppBargain                                     from "./main/bargainApp/bargain";

import sMainBargainAppBargainHome                                 from "./main/bargainApp/bargain/bargainHome";
import sMainBargainAppBargainAdd                                  from "./main/bargainApp/bargain/bargainAdd";
import sMainBargainAppBargainEdit                                  from "./main/bargainApp/bargain/bargainEdit";
import sMainBargainAppBargainView                                 from "./main/bargainApp/bargain/bargainView";
import sMainBargainAppBargainJoinNum                              from "./main/bargainApp/bargain/joinNum";
import sMainBargainAppBargainTextImg                              from "./main/bargainApp/bargain/textImg";
import sMainBargainAppBargainSuccecssPeo                          from "./main/bargainApp/bargain/succecssPeo";

import sMainBargainAppBargainPriceList                            from "./main/bargainApp/bargain/priceList";






export default angular.module(`${conf.app}.states`, [
    sMain.name,
    sMainOtherMain.name,
    sMainBindPhone.name,
    sMainWxLogin.name,
    sMainBargainApp.name,
    sMainLoginTime.name,
    sMainBargainAppHome.name,


    sMainBargainAppBargain.name,
    sMainBargainAppBargainHome.name,
    sMainBargainAppBargainAdd.name,
    sMainBargainAppBargainEdit.name,
    sMainBargainAppBargainView.name,
    sMainBargainAppBargainJoinNum.name,
    sMainBargainAppBargainTextImg.name,
    sMainBargainAppBargainSuccecssPeo.name,
    sMainBargainAppBargainPriceList.name,
])