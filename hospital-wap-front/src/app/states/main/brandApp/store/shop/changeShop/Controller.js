import conf from "../../../../../../conf";
var $scope,
    loginService,
    $state,
    $stateParams,
    $location,
    $http;
class Controller {
    constructor(_$scope,
                _loginService,
                _$state,
                _$stateParams,
                _$location,
                _$http) {
        $scope = _$scope;
        loginService = _loginService;
        $state = _$state;
        $stateParams = _$stateParams;
        $location =_$location;
        $http = _$http;
        loginService.loginCtl(true,$location.absUrl());
        $scope.go = function (state) {
            $state.go(state);
        };

        $http({
            method: "GET",
            // url: conf.apiPath + "/brandApp/" + $stateParams.brandAppId + '/home',
            url: conf.apiPath + "/brandAppId/"+$stateParams.brandAppId+"/home",
            params: {},
            headers: {
                'Authorization': 'Bearer ' + loginService.getAccessToken(),
                 "brandApp-Id": $scope.brandAppId
            }
        }).then(function (resp) {
            $scope.shopList = resp.data.data;
            // console.log('resp-----------------------', resp);
        }, function (error) {

        });

        /*返回上级*/
        $scope.fallbackPage = function () {
            if (history.length === 1) {
                $state.go("main.brandApp.store.shop.shopManage",{storeId:$stateParams.storeId}, {reload: true});
            } else {
                history.back();
            }
        };






    }
}

Controller.$inject = [
    '$scope',
    'loginService',
    '$state',
    '$stateParams',
    '$location',
    '$http',
];

export default Controller ;
