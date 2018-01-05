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

        $scope.getData = function () {
            $http({
                method: "GET",
                url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/bargain/admin/' + $stateParams.id + '/user/' + $stateParams.userId + '/order/info',
                params: {},
                headers: {
                    // 'Authorization': 'Bearer ' + loginService.getAccessToken()
                }
            }).then(function (resp) {
                console.log('resprespresprespresp', resp);
                $scope.orderDetail = resp.data.data
            }, function () {
                //error
            });
        };

        $scope.getData();


        $scope.cliclSave = false;

        $scope.deliver = function () {
            if ($scope.cliclSave) {
                return
            } else {
                $scope.cliclSave = true;
            }
            if (!$scope.orderDetail.company) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写快递公司')
            }
            if (!$scope.orderDetail.expressNo) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写快递单号')
            }

            $http({
                method: "PUT",
                url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/bargain/admin/' + $stateParams.id + '/user/' + $stateParams.userId + '/order/ship',
                data: {
                    company:$scope.orderDetail.company,
                    expressNo:$scope.orderDetail.expressNo,
                    memo:$scope.orderDetail.memo
                },
                headers: {
                    'Authorization': 'Bearer ' + loginService.getAccessToken()
                }
            }).then(function (resp) {
                console.log('resprespresprespresp', resp);
                alertService.msgAlert('exclamation-circle', '发货成功');
                $scope.fallbackPage();

            }, function () {
                //error
                $scope.cliclSave = false;
            });
        }








        $scope.wxSys = function () {
            if (wxService.isInWx()) {
                if ($scope.initWX) {
                    wx.scanQRCode({
                        desc: 'scanQRCode desc',
                        needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                        scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                        success: function (res) {
                            var result = res.resultStr.split(','); // 当needResult 为 1 时，扫码返回的结果
                            $scope.sysQrcode = result[result.length - 1];

                            $scope.orderDetail.expressNo = $scope.sysQrcode;
                            // $scope.showScanCodeAddDialog($scope.sysQrcode);
                            $scope.$digest();
                        },
                        error: function (res) {
                            if (res.errMsg.indexOf('function_not_exist') > 0) {
                                alertService.msgAlert("exclamation-circle", "版本过低请升级");
                            }
                        }
                    });
                } else {
                    wxService.init().then(function (data) {
                        if (data) {
                            wx.scanQRCode({
                                desc: 'scanQRCode desc',
                                needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                                scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                                success: function (res) {
                                    var result = res.resultStr.split(','); // 当needResult 为 1 时，扫码返回的结果
                                    $scope.sysQrcode = result[result.length - 1];
                                    $scope.orderDetail.expressNo = $scope.sysQrcode;
                                    // $scope.showScanCodeAddDialog($scope.sysQrcode);
                                    $scope.$digest();
                                },
                                error: function (res) {
                                    if (res.errMsg.indexOf('function_not_exist') > 0) {
                                        alertService.msgAlert("exclamation-circle", "版本过低请升级");
                                    }
                                }
                            });
                        }
                    })
                }
            }
        };









        $scope.fallbackPage = function () {
            $state.go("main.bargainApp.bargain.successPeo", {id:$stateParams.id}, {reload: true});
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
