import conf from "../../conf"
var $rootScope,
    $scope,
    ssSideNav,
    loginService;
class Controller {

    constructor(_$rootScope,
                _$scope,
                _ssSideNav,
                _loginService) {
        loginService = _loginService;
        $rootScope = _$rootScope;
        $scope = _$scope;
        ssSideNav = _ssSideNav;


        $scope.menu = ssSideNav;

        //$rootScope.ssSideNav 在config.js中有用到，勿删
        $rootScope.ssSideNav = ssSideNav;
        ////登出
        $scope.logout = () => {
            jso.wipeTokens();
            loginService.setAccessToken();
            loginService.setbrandAppId();
            location.href = location.protocol + conf.oauthPath + "/logout";
        };

    }

    close(tab, $event) {
        //console.log("------000 close", arguments);
        if ($event) {
            $event.stopPropagation();
        }
    }
}

Controller.$inject = [
    '$rootScope',
    '$scope',
    'ssSideNav',
    'loginService',
];

export default Controller ;
