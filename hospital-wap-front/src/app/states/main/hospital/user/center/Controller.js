import conf from "../../../../../conf";

var $scope,
    $http,
    $state,
    $log,
    $mdBottomSheet,
    loginService,
    $mdDialog,
    $timeout,
    $stateParams,
    $location,
    alertService,
    authService,
    $rootScope;

class Controller {
    constructor(_$scope,
                _$http,
                _$state,
                _$log,
                _$mdBottomSheet,
                _loginService,
                _$mdDialog,
                _$timeout,
                _$stateParams,
                _$location,
                _alertService,
                _authService,
                _$rootScope) {
        $scope = _$scope;
        $http = _$http;
        $mdDialog = _$mdDialog;
        $state = _$state;
        $rootScope = _$rootScope;
        $timeout = _$timeout;
        loginService = _loginService;
        $log = _$log;
        authService = _authService;
        $stateParams = _$stateParams;
        $mdBottomSheet = _$mdBottomSheet;
        $location = _$location;
        alertService = _alertService;
        /////////////////////////////////


        authService.loginCtl(true);
        console.log(123333333)
    //     $scope.user = [];
    //     $scope.register = function () {
    //         if ($scope.user.pwd != $scope.user.rePwd) {
    //             alertService.msgAlert("exclamation-circle", "密码不一致！");
    //         }
    //
    //         $http({
    //             method: 'POST',
    //             url: conf.apiPath + '/register',
    //             params: {
    //                 phone: $scope.user.phone,
    //                 passWord: $scope.user.pwd,
    //                 // size: conf.pageSize,
    //                 // page: page,
    //             },
    //         }).then(function (resp) {
    //
    //
    //         }, function (resp) {
    //         });
    //     };
    }
}

Controller.$inject = [
    '$scope',
    '$http',
    '$state',
    '$log',
    '$mdBottomSheet',
    'loginService',
    '$mdDialog',
    '$timeout',
    '$stateParams',
    '$location',
    'alertService',
    'authService',
    '$rootScope'
];

export default Controller;
