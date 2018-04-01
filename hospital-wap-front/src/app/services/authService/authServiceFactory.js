import conf from "../../conf";
import store from "store";

authServiceFactory.$inject = ['$http', 'loginService', '$injector', '$state', '$q'];

function authServiceFactory($http, loginService, $injector, $state, $q) {

    function update(a) {
        var ssSideNavSections = $injector.get('ssSideNavSections');
        var sections = ssSideNavSections.sections;
        a = ['SA'];         //暂时不加权限
        if (a.indexOf("SA") > -1) {
            for (var i = 0; i < sections.length; i++) {
                $injector.get('ssSideNavSections').sections[i].show = true;
                var pages = sections[i].pages;
                // 二级菜单
                for (var j = 0; j < pages.length; j++) {
                    $injector.get('ssSideNavSections').sections[i].pages[j].show = true;
                }
            }
            return;
        }
        // 一级菜单          ---------------------------------测试新方法
        for (var i = 0; i < sections.length; i++) {
            var pages = sections[i].pages;

            // 二级菜单
            for (var j = 0; j < pages.length; j++) {
                // var flag = false;

                // 二级菜单所需的权限列表检查每一项的权限

                for (var auth = 0; auth < pages[j].authorities.length; auth++) {
                    if (a.indexOf(pages[j].authorities[auth]) > -1) {
                        // flag = true;
                        $injector.get('ssSideNavSections').sections[i].show = true;
                        $injector.get('ssSideNavSections').sections[i].pages[j].show = true;
                        break;
                    }
                }
            }
        }


        var endTime = new Date();
        console.log('------------------------3333333', endTime, endTime.getMilliseconds());

    }


    function setAuthorities(brandAppId, storeId) {
        var deferred = $q.defer();
        $http({
            method: "GET",
            url: conf.apiPath + "authorities",
            data: {},
            headers: {}
        }).then(function (data) {
            if (data.data.data) {
                store.set(conf.authorSet, data.data.data)
                deferred.resolve(true);//这就是等待的结果
                console.log('212121212', deferred);
            } else {
                store.set(conf.authorSet, null);
            }
        }, function (response) {
            store.set(conf.authorSet, null);
            if (response.status == '401') {
                // jso.wipeTokens();
                // loginService.setAccessToken(null);
                // loginService.setbrandAppId(null);
                // $http({
                //     method: "POST",
                //     url: conf.apiPath + "/login",
                //     headers: {},
                //     params: {},
                //     withCredentials: true,
                // }).success(function (resp) {
                //     // console.log('data', resp);
                //     location.reload();
                // }, function (resp) {
                //     console.log('ERR', resp);
                // });
                $state.go('main.hospital.login');
            } else if (response.data.status == '10029') {
                $state.go('main.brandApp.authorities');      //-------跳转到无权限页面
            }
        });
        return deferred.promise;
    }

    let getAuthorities = () => {
        store.get(conf.authorSet);
        return store.get(conf.authorSet);
    };


    function hasAuthor(author) {
        var auth = store.get(conf.authorSet);
        if (!auth) {
            auth = []
        }
        if (auth.indexOf(author) > -1 || auth.indexOf('SA') > -1) {
            return true
        } else {
            return false
        }


        // for (var i = 0; i < auth.length; i++) {
        //     if (auth[i] == author ) {
        //         return true
        //     }
        // }
        // return false
    }

    let loginCtl = (required, backUrl) => {
        console.log(`${TAG} => loginCtl`);

        if (!required) {
            return;
        }
        if (!getAuthorities()) {
            // alert('未登录，跳转到登录页');
            console.log("未登录，跳转到登录页");
            // TODO 登录跳转前访问的最后一个URl(backUrl) 保存到 localStorage 中
            // store.set('login_backUrl', backUrl);
            $state.go('main.hospital.login');
        } else {
            // getAuthorities();
            // alert('loginCtl');
            // return getUserCtl(true,backUrl);
        }

    };

    return {
        loginCtl: loginCtl,
        hasAuthor: hasAuthor,
        setAuthorities: setAuthorities,
        getAuthorities: getAuthorities,
        isUpdateApp: true,
        pages: {item: 0}
    };

}

export default authServiceFactory;


