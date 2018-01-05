import conf from "../../conf";
import store from "store";

loginServiceFactory.$inject = ['$q', '$http', '$state', '$stateParams','$location'];
function loginServiceFactory($q, $http, $state, $stateParams,$location) {

    const TAG = "loginService";

    let setbargainAppId = (bargainAppId) => {
        store.set(conf.bargainAppId, bargainAppId);
    };

    let setAccessToken = (token) => {
        store.set(conf.token, token);
    };

    let getbargainAppId = () => {
        store.get(conf.bargainAppId);
        return store.get(conf.bargainAppId);
    };

    let getAccessToken = () => {
        if (!store.get(conf.token)) {
            store.set(conf.token, null);
            return;
        }
        store.get(conf.token);
        return store.get(conf.token);
    };
    let getCheck = () => {
        console.log(111);
        $http({
            method: "GET",
            url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + "/partner/check",
            data: {},
            headers: {
                'Authorization': 'Bearer ' + store.get(conf.token),
                'bargainApp-Id': $stateParams.bargainAppId,
            }
        }).then(function (resp) {
                console.log('respTop,check', resp);
            }, function (error) {
                console.log(error);
            }
        );
    };
    // getCheck();
    /**
     * 判断是否登录，未登录跳转到登录页
     * @param required
     * @param backUrl
     */
    let loginCtl = (required, backUrl) => {
        console.log(`${TAG} => loginCtl`);

        if (!required) {
            return;
        }

        console.log('getAccessToken()==',getAccessToken());

        if (!getAccessToken()) {
            // alert('未登录，跳转到登录页');
            console.log("未登录，跳转到登录页",$stateParams.bargainAppId);
            // TODO 登录跳转前访问的最后一个URl(backUrl) 保存到 localStorage 中
            // store.set('login_backUrl', backUrl);
            $state.go("main.wxLogin",{backUrl:backUrl,bargainAppId:$stateParams.bargainAppId})
        } else {
            // alert('loginCtl');
            // return getUserCtl(true,backUrl);
        }

    };


    /**
     * 判断是否加入，未加入跳转到加入页
     * @param getUser
     * @param backUrl
     */
    let getUserCtl = (getUser, backUrl) => {
        console.log(`${TAG} => getUser`);
        // alert(`${TAG} => getUser`);
        if (getUser) {
            $http({
                method: "get",
                url: conf.apiPath + "/member/info",
                headers: {
                    'Authorization': 'Bearer ' + getAccessToken(),
                    'bargainApp-Id': $stateParams.bargainAppId,
                }
            }).then(function (data) {
                    // alert('/member/info');
                    // alert(data.data.status);
                    if (data.data.status == 301) {
                        $state.go("main.bargainApp.joinUs");
                    }
                }, function (error) {
                    console.log('error', error);
                    if (error.status == 401) {
                        // alert('token过期，跳转到登录页');
                        store.set(conf.token, null);
                        store.remove(conf.token);
                        jso.wipeTokens();
                        store.set('login_backUrl', backUrl);
                        $state.go("main.wxLogin", {backUrl: backUrl})
                    }
                }
            );

        }
    };


    /**
     * 获取localStorage内容
     * @param key
     */
    let getLocalStorage = (key) => {
        return store.get(key);
    };

    /**
     * 设置localStorage
     * @param key
     * @param value
     */
    let setLocalStorage = (key, value) => {
        store.set(key, value);
    };

    return {
        getUserCtl: getUserCtl,
        loginCtl: loginCtl,
        setbargainAppId: setbargainAppId,
        setAccessToken: setAccessToken,
        getbargainAppId: getbargainAppId,
        getAccessToken: getAccessToken,
        getLocalStorage: getLocalStorage,
        setLocalStorage: setLocalStorage,
        getCheck: getCheck,
        isUpdateApp: true,
        pages: {item: 0}
    };
}

export default  loginServiceFactory;