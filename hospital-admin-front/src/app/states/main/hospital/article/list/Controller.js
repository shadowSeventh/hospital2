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
    alertService,
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
                _alertService,
                _$stateParams) {
        $scope = _$scope;
        $http = _$http;
        $mdDialog = _$mdDialog;
        $state = _$state;
        authService = _authService;
        loginService = _loginService;
        $log = _$log;
        Upload = _Upload;
        alertService = _alertService,
            $stateParams = _$stateParams;
        /////////////////////////////////
        // loginService.loginCtl(true);

        $scope.data = [];


        $scope.getPageInfo = function (page) {
            $http({
                method: 'GET',
                url: conf.apiPath + '/admin/article',
                params: {
                    size: conf.pageSize,
                    page: page,
                    // sort:'',
                    title: $scope.keyWords == '' ? null : $scope.keyWords,
                },
            }).then(function (resp) {
                console.log(resp.data.data.content);
                $scope.data = resp.data.data.content;
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


        $scope.saleDialog = function (id, status) {
            let showStatus;
            if (status == "NORMAL") {
                showStatus = "发布"
            } else {
                showStatus = "停用"
            }
            alertService.confirm(null, "确定" + showStatus + "该图文？", "温馨提示", "取消", "确认")
                .then(function (data) {
                    if (data) {
                        $http({
                            method: 'PUT',
                            url: conf.apiPath + "/admin/article/" + id,
                            params: {
                                status: status
                            },
                        }).success(function (data) {
                            if (data.status == 200) {
                                $scope.getPageInfo(0);
                            }
                        });
                    }
                });
        };

        $scope.delete = function (id) {
            $http({
                method: 'DELETE',
                url: conf.apiPath + "/admin/article/" + id,
            }).then(function (resp) {
                if (resp.status == 200) {
                    $scope.getPageInfo(0);
                }
            }, function (resp) {
            });
        };
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
    'alertService',
    '$stateParams'
];

export default Controller;
