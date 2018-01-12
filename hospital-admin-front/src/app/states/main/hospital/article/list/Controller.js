import conf from "../../../../../conf"
import E from "wangeditor";

var $scope,
    $http,
    authService,
    $state,
    $log,
    loginService,
    $mdDialog,
    Upload,
    $stateParams;

class Controller {
    constructor(_$scope,
                _$http,
                _$state,
                _$log,
                _authService,
                _loginService,
                _$mdDialog,
                _Upload,
                _$stateParams) {
        $scope = _$scope;
        $http = _$http;
        $mdDialog = _$mdDialog;
        $state = _$state;
        authService = _authService;
        loginService = _loginService;
        $log = _$log;
        Upload = _Upload;
        $stateParams = _$stateParams;
        /////////////////////////////////
        // loginService.loginCtl(true);

        $scope.data = [];


        $scope.getPageInfo=function (page) {
            $http({
                method: 'GET',
                url: conf.apiPath + '/admin/article',
                params: {
                    size: conf.pageSize,
                    page: page,
                    // sort:'',
                    keyword: $scope.title,
                },
            }).then(function (resp) {
                console.log(resp.data.data.content);
                $scope.data=resp.data.data.content;
                // console.log('Success ' + resp.data.data.cdnUrls[0].url);
            }, function (resp) {
                // console.log('Error status: ' + resp.status);
            });
        };

        $scope.focus = function (status) {
            if (status) {
                $scope.searchShow = true;
            } else {
                $scope.searchShow = false;
            }
        };

        $scope.clearKeyWord = function () {
            $scope.keyWord = '';
        };
        $scope.getPageInfo(0);
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
    'Upload',
    '$stateParams'
];

export default Controller;
