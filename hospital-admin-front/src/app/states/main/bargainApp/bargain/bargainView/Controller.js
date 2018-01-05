import conf from "../../../../../conf";
// import "jquery";
var $scope,
    $http,
    $state,
    $log,
    loginService,
    $mdDialog,
    $stateParams,
    $location,
    $q,
    Upload,
    wxService,
    $interval,
    alertService,
    $templateCache;
class Controller {
    constructor(_$scope,
                _$http,
                _$state,
                _$log,
                _loginService,
                _$mdDialog,
                _$stateParams,
                _$location,
                _$q,
                _Upload,
                _wxService,
                _$interval,
                _alertService,
                _$templateCache) {
        $scope = _$scope;
        $http = _$http;
        $mdDialog = _$mdDialog;
        $state = _$state;
        loginService = _loginService;
        $log = _$log;
        $q = _$q;
        alertService = _alertService;
        $interval = _$interval;
        $templateCache = _$templateCache;
        Upload = _Upload;
        $location = _$location;
        wxService = _wxService;
        $stateParams = _$stateParams;
        $scope.bargainAppId = $stateParams.bargainAppId;
        /////////////////////////////////
        loginService.loginCtl(true, $location.absUrl());
        if (wxService.isInWx()) {
            wxService.init().then(function (data) {
                if (data) {
                    $scope.wxInit = true;
                }
            });
        }


        $scope.goEdit = function (type) {
            $state.go("main.bargainApp.bargain.textImg", {
                from: 'view',
                type: type,
                id:$stateParams.id,
            })
        }


        $scope.getData = function () {
            $http({
                method: "GET",
                url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/bargain/admin/' + $stateParams.id ,
                params: {},
                headers: {
                    // 'Authorization': 'Bearer ' + loginService.getAccessToken()
                }
            }).then(function (resp) {
                console.log('resprespresprespresp', resp);
                $scope.bargainDataview = resp.data.data;
                    // $rootScope.rootBargainDataView = resp.data.data;

                //分享
                if (wxService.isInWx()) {
                    var confWx = {
                        title: $scope.bargainDataview.shareTitle,
                        desc: $scope.bargainDataview.shareDesp,
                        link: conf.bargainWapRootUrl + 'bargainApp/' + $stateParams.bargainAppId + '/bargain/' +  $scope.bargainDataview.id +'/home',
                        imgUrl: $scope.bargainDataview.awardImg,
                        success: function () {
                            // 用户确认分享后执行的回调函数
                            // alert('success');
                            $scope.maskHide();
                            $scope.$digest()
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                            // alert('cancel');
                            $scope.maskHide();
                            $scope.$digest()
                        }
                    };
                    if ($scope.wxInit) {
                        wxService.shareRing(confWx);
                        wxService.shareFriend(confWx);
                    } else {
                        wxService.init().then(function (data) {
                            if (data) {
                                $scope.wxInit = true;
                                wxService.shareRing(confWx);
                                wxService.shareFriend(confWx);
                            }
                        });
                    }
                }
            }, function () {
                //error
                $scope.cliclSave = false;
            });
        };
        $scope.getData();




        $scope.changeStatus = function (bargain) {
            console.log('bargain==============',bargain);
            if(bargain.status == 'ENABLE'){
                alertService.confirm(null,'确认禁用?','温馨提示').then(function (data) {
                    if(data){
                        $http({
                            method: "PUT",
                            url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/bargain/admin/' + bargain.id +'/changeStatus',
                            params: {
                                enable: false,
                            },
                            headers: {
                                // 'Authorization': 'Bearer ' + loginService.getAccessToken()
                            }
                        }).then(function (resp) {
                            $scope.getData();
                        }, function () {
                        });
                    }


                })
            }else if(bargain.status == 'CLOSED'){
                alertService.confirm(null,'确认启用?','温馨提示').then(function (data) {
                    if(data){
                        $http({
                            method: "PUT",
                            url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/bargain/admin/' + bargain.id +'/changeStatus',
                            params: {
                                enable: true,
                            },
                            headers: {
                                // 'Authorization': 'Bearer ' + loginService.getAccessToken()
                            }
                        }).then(function (resp) {
                            $scope.getData();
                        }, function () {
                        });
                    }
                })
            }
        };





        $scope.goView = function (id) {
            location.href = conf.bargainWapRootUrl + 'bargainApp/' + $stateParams.bargainAppId + '/bargain/' + id +'/home';
        }









        $scope.share = function (id) {
            $scope.maskShow();
        };

        //遮罩打开与关闭
        $scope.mask = false;
        $scope.maskShow = function () {
            $scope.mask = true;
        };
        $scope.maskHide = function () {
            $scope.mask = false;
        };






        $scope.fallbackPage = function () {
            $state.go("main.bargainApp.bargain.bargainHome", {}, {reload: true});
        };
    }
}

Controller.$inject = [
    '$scope',
    '$http',
    '$state',
    '$log',
    'loginService',
    '$mdDialog',
    '$stateParams',
    '$location',
    '$q',
    'Upload',
    'wxService',
    '$interval',
    'alertService',
    '$templateCache'
];

export default Controller ;
