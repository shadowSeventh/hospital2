import conf from "../../../conf";

var $rootScope,
    $scope,
    ssSideNav,
    loginService,
    sidenavTab,
    $state,
    $http,
    authService,
    $mdMedia,
    $mdSidenav,
    $stateParams;
class Controller {

    constructor(_$rootScope,
                _$scope,
                _ssSideNav,
                _loginService,
                _sidenavTab,
                _$state,
                _$http,
                _authService,
                _$mdMedia,
                _$mdSidenav,
                _$stateParams) {
        loginService = _loginService;
        $rootScope = _$rootScope;
        $scope = _$scope;
        $state = _$state;
        $http = _$http;
        $stateParams = _$stateParams;
        ssSideNav = _ssSideNav;
        authService = _authService;
        $mdMedia = _$mdMedia;
        $mdSidenav = _$mdSidenav;
        this.sidenavTab = sidenavTab = _sidenavTab;

        //console.log(sidenavTab);
        $scope.menu = ssSideNav;

        console.log('    $scope.menu',    $scope.menu);
        // loginService.loginCtl(true);
        $scope.hospitalId = $stateParams.hospitalId;
        //获取权限，勿删
        authService.setAuthorities($scope.hospitalId);
        //$rootScope.ssSideNav 在config.js中有用到，勿删
        $rootScope.ssSideNav = ssSideNav;




        $scope.openLeft = function () {
            if($scope.leftShow){
                $scope.ts = '打开侧边栏';
                $scope.leftShow = false;
            }else {
                $scope.ts = '关闭侧边栏';
                $scope.leftShow = true;
            }
        };
        $scope.leftShow = true;
        $scope.$watch(function() { return $mdMedia('gt-sm'); }, function(data) {
            $scope.leftShow = data;
            if(data){
                $scope.ts = '关闭侧边栏';
            }else {
                $scope.ts = '打开侧边栏';
            }

        });







        $scope.viewTab = function (tab) {
            $state.go(tab.curState, tab.tParams, {reload: true})
        };


    }

    close(tab, $event) {
        console.log("------000 close", arguments);
        if ($event) {
            $event.stopPropagation();
        }
        sidenavTab.closeTab(tab);
    }
}

Controller.$inject = [
    '$rootScope',
    '$scope',
    'ssSideNav',
    'loginService',
    'sidenavTab',
    '$state',
    '$http',
    'authService',
    '$mdMedia',
    '$mdSidenav',
    '$stateParams'
];

export default Controller ;
