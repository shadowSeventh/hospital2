import conf from "../../../../conf";
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

        $scope.firstArr = [];
        $scope.nextArr = [];

        $scope.getPageInfo=function (page,s) {
            $http({
                method: 'GET',
                url: conf.apiPath + '/wap/article/list',
                params: {
                    // size: conf.pageSize,
                    // page: page,
                },
            }).then(function (resp) {
                console.log(resp.data.data.content);
                $scope.articles = resp.data.data.content;

                for(let i in $scope.articles ){
                    if(i <3){
                        $scope.firstArr.push( $scope.articles[i])
                    }else{
                        $scope.nextArr.push( $scope.articles[i])
                    }
                }

                console.log(' $scope.firstArr', $scope.firstArr);
                console.log(' $scope.nextArr', $scope.nextArr);

                // console.log('Success ' + resp.data.data.cdnUrls[0].url);
                $timeout(function () {
                    s.update()
                },50)

            }, function (resp) {
                // console.log('Error status: ' + resp.status);
            });
        };

        // $scope.getPageInfo();
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

export default Controller ;
