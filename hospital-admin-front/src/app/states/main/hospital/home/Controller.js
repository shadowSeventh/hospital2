var $scope,
    $http,
    authService,
    $state,
    $log,
    loginService,
    $mdDialog,
    $stateParams;
class Controller {
    constructor(_$scope,
                _$http,
                _$state,
                _$log,
                _authService,
                _loginService,
                _$mdDialog,
                _$stateParams) {
        $scope = _$scope;
        $http = _$http;
        $mdDialog = _$mdDialog;
        $state = _$state;
        authService = _authService;
        loginService = _loginService;
        $log = _$log;
        $stateParams = _$stateParams;
        /////////////////////////////////
        // loginService.loginCtl(true);
        $scope.hospitalId = $stateParams.hospitalId;




    }
}

Controller.$inject = [
    '$scope',
    '$http',
    '$state',
    '$log',
    'authService',
    'loginService',
    '$mdDialog',
    '$stateParams'
];

export default Controller ;
