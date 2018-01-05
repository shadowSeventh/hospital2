import conf from "../../../../../conf";
import weui from 'weui.js';

import PhotoClip from 'photoclip';

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
    $rootScope,
    $filter,
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
                _$rootScope,
                _$filter,
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
        $rootScope = _$rootScope;
        $filter = _$filter;
        $scope.bargainAppId = $stateParams.bargainAppId;
        /////////////////////////////////
        loginService.loginCtl(true, $location.absUrl());

        $scope.choosePayType = false;
        $scope.dialogPayType = 1;


        $scope.dialogChoosePayType = function (type) {
            $scope.dialogPayType = type;
        };
        $scope.cancelChoosePayType = function (data) {
            $scope.choosePayType = false;
            if (data) {
                $rootScope.rootBragainDataEdit.receiveType = $scope.dialogPayType;
                $scope.payType = $scope.dialogPayType;
            } else {
                $scope.dialogPayType = $rootScope.rootBragainDataEdit.receiveType;
            }
        };
        $scope.sureChoosePayType = function () {
            $scope.choosePayType = true;
        };
        $scope.chooseStartTime = function () {
            weui.datePicker({
                start: new Date(), // 从今天开始
                end: new Date().getFullYear() + 1,
                defaultValue: [new Date().getFullYear(), new Date().getMonth() + 1, new Date().getDate()],
                onChange: function (result) {
                    // console.log('1111',result);
                },
                onConfirm: function (result) {
                    console.log(result);
                    $scope.startTime = result;


                    var year = result[0].value.toString();
                    var month = result[1].value>9?result[1].value.toString():('0'+result[1].value.toString());
                    var day = result[2].value>9?result[2].value.toString():('0'+result[2].value.toString());
                    var stringTime = year + "-" + month + "-" + day;
                    console.log('11111111111111111',stringTime);
                    var timestamp2 = Date.parse(new Date(stringTime));
                    console.log('22222222222222222222222',timestamp2);

                    $rootScope.rootBragainDataEdit.beginTime = timestamp2;

                    // $rootScope.rootBragainDataEdit.beginTime = {
                    //     year: result[0].value,
                    //     month: result[1].value>9?result[1].value:'0'+result[1].value,
                    //     day: result[2].value>9?result[2].value:'0'+result[2].value,
                    // };

                    console.log('333333333333333333333',$rootScope.rootBragainDataEdit.beginTime);

                    $scope.$digest();
                },
                id: 'datePickerStart',
                className: 'datePickerStart'
            });
        };
        $scope.chooseEndTime = function () {
            if ($rootScope.rootBragainDataEdit.beginTime) {
                // $filter('date')($rootScope.rootBragainDataEdit.beginTime, 'yyyy-MM-dd');
                // var startTime = $scope.startTime[0].value + '-' + $scope.startTime[1].value + '-' + $scope.startTime[2].value;
                weui.datePicker({
                    start: $filter('date')($rootScope.rootBragainDataEdit.beginTime, 'yyyy-MM-dd'), // 从今天开始
                    end: Number($filter('date')($rootScope.rootBragainDataEdit.beginTime, 'yyyy')) + 1,
                    defaultValue: [$filter('date')($rootScope.rootBragainDataEdit.beginTime, 'yyyy'), $filter('date')($rootScope.rootBragainDataEdit.beginTime, 'MM'), $filter('date')($rootScope.rootBragainDataEdit.beginTime, 'dd')],
                    onChange: function (result) {
                        // console.log(result);
                    },
                    onConfirm: function (result) {
                        console.log(result);
                        $scope.endTime = result;

                        var year = result[0].value.toString();
                        var month = result[1].value>9?result[1].value.toString():('0'+result[1].value.toString());
                        var day = result[2].value>9?result[2].value.toString():('0'+result[2].value.toString());
                        var stringTime = year + "-" + month + "-" + day;
                        console.log('11111111111111111',stringTime);
                        var timestamp2 = Date.parse(new Date(stringTime));
                        console.log('22222222222222222222222',timestamp2);
                        $rootScope.rootBragainDataEdit.endTime = timestamp2;
                        // $rootScope.rootBragainDataEdit.endTime = {
                        //     year: result[0].value,
                        //     month: result[1].value>9?result[1].value:'0'+result[1].value,
                        //     day: result[2].value>9?result[2].value:'0'+result[2].value,
                        // };
                        $scope.$digest();
                    },
                    id: 'datePickerEnd',
                    className: 'datePickerEnd'
                });
            } else {
                return alertService.msgAlert('exclamation-circle', '请先选择生效时间')
            }
        };


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

                resp.data.data.userTypeRandom.newUser.higherPrice = resp.data.data.userTypeRandom.newUser.higherPrice/100;
                resp.data.data.userTypeRandom.newUser.lowerPrice  = resp.data.data.userTypeRandom.newUser.lowerPrice/100;
                resp.data.data.userTypeRandom.oldUser.higherPrice = resp.data.data.userTypeRandom.oldUser.higherPrice/100;
                resp.data.data.userTypeRandom.oldUser.lowerPrice  = resp.data.data.userTypeRandom.oldUser.lowerPrice/100;
                resp.data.data.awardPrice = resp.data.data.awardPrice/100;
                resp.data.data.minTargetPrice = resp.data.data.minTargetPrice/100;
                $rootScope.rootBragainDataEdit = resp.data.data;
                console.log( 'rootBragainDataEdit33333333=========',$rootScope.rootBragainDataEdit);
                $scope.dialogPayType = $rootScope.rootBragainDataEdit.receiveType;
                    // $rootScope.rootBargainDataView = resp.data.data;
                // $rootScope.rootBragainDataEdit.userTypeRandom = {
                //     newUser:{
                //         higherPrice:resp.data.data.userTypeRandom.newUser.higherPrice/100,
                //         lowerPrice:resp.data.data.userTypeRandom.newUser.lowerPrice/100
                //     },
                //     oldUser:{
                //         higherPrice:resp.data.data.userTypeRandom.oldUser.higherPrice/100,
                //         lowerPrice:resp.data.data.userTypeRandom.oldUser.lowerPrice/100
                //     }
                // };
            }, function () {
                    //error
                $scope.cliclSave = false;
            });
        };



        if($stateParams.form != 'text'){
            // $rootScope.rootBragainDataEdit = {};
            // $rootScope.rootBragainDataEdit.userTypeRandom = {
            //     newUser: {},
            //     oldUser: {},
            // };
            $scope.getData();
            console.log( 'rootBragainDataEdit222222222222=========',$rootScope.rootBragainDataEdit);
        }else {

            console.log( 'rootBragainDataEdit1111111111=========',$rootScope.rootBragainDataEdit);

            if (!$rootScope.rootBragainDataEdit) {
                $rootScope.rootBragainDataEdit = {};
                $rootScope.rootBragainDataEdit.userTypeRandom = {
                    newUser: {},
                    oldUser: {},
                };
            } else {
                if (!$rootScope.rootBragainDataEdit.userTypeRandom) {
                    $rootScope.rootBragainDataEdit.userTypeRandom = {
                        newUser: {},
                        oldUser: {},
                    };
                }
                if ($rootScope.rootBragainDataEdit.receiveType) {
                    $scope.dialogPayType = $rootScope.rootBragainDataEdit.receiveType;
                }
            }
        }

















        $scope.choosePhote = false;
        // 图片裁剪
        var pc = new PhotoClip('#clipArea', {
            size: [260, 260],
            outputSize: 640,
            // adaptive: ['70','40'],
            file: '#file,#file2',
            view: '#view',
            ok: '#clipBtn',
            style: {
                maskColor: 'rgba(0,0,0,0.7)',
                // jpgFillColor:''
            },
            loadStart: function () {
                console.log('开始读取照片');
            },
            loadComplete: function () {
                console.log('照片读取完成', $scope);
                $scope.choosePhote = true;
                $scope.$apply();
            },
            done: function (dataURL) {
                console.log('base64裁剪完成,正在上传');
                $scope.saveImg(dataURL);
            },
            fail: function (msg) {
                alert(msg);
            }
        });
        $scope.saveImg = function (dataUrl) {
            $http({
                method: "POST",
                url: conf.yunApiPath + "/app/5988791a6b869f4e18d5c8d5/org/598878fc6b869f4e0f19fb47/yunFile/b64",
                data: {
                    base64DataUrl: dataUrl
                },
                headers: {
                    'Authorization': 'Bearer ' + loginService.getAccessToken()
                }
            }).then(function (resp) {
                    $scope.dataUrl = resp.data.data;
                    $scope.getImg($scope.dataUrl);
                    console.log(resp.data.data)

                }, function () {
                    //error
                }
            );
        };
        $scope.getImg = function (id) {
            $http({
                method: "GET",
                url: conf.yunApiPath + "/app/5988791a6b869f4e18d5c8d5/org/598878fc6b869f4e0f19fb47/yunFile/" + id,
                headers: {
                    'Authorization': 'Bearer ' + loginService.getAccessToken()
                }
            }).then(function (resp) {
                    console.log(resp.data.data);
                    // $scope.imgs = resp.data.data.cdnUrls[0].url ;
                    $rootScope.rootBragainDataEdit.awardImg = resp.data.data.cdnUrls[0].url;
                    $scope.choosePhote = false;
                }, function () {
                    //error
                }
            );
        };
        $scope.cancelChoosePhote = function () {
            $scope.choosePhote = false;
        };


        $scope.goEdit = function (type) {
            // var json = angular.toJson($scope.skuData);
            $state.go("main.bargainApp.bargain.textImg", {
                type: type,
                from: 'edit',
                id:$stateParams.id,
            })
        }


        $scope.cliclSave = false;

        $scope.save = function () {
            if ($scope.cliclSave) {
                return
            } else {
                $scope.cliclSave = true;
            }
            console.log('result================', $rootScope.rootBragainDataEdit);

            if (!$rootScope.rootBragainDataEdit.shopName) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写店铺名称')
            }



            if (!$rootScope.rootBragainDataEdit.name) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写活动名称')
            }

            if (!$rootScope.rootBragainDataEdit.beginTime) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请选择生效时间')
            }
            if (!$rootScope.rootBragainDataEdit.endTime) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请选择过期时间')
            }



            if($rootScope.rootBragainDataEdit.beginTime > $rootScope.rootBragainDataEdit.endTime){
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '生效时间不能大于过期时间!')
            }





            if (!$rootScope.rootBragainDataEdit.rule) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写规则说明')
            }
            // if (!$rootScope.rootBragainDataEdit.desp) {
            //     $scope.cliclSave = false;
            //     return alertService.msgAlert('exclamation-circle', '请填写活动图文')
            // }
            if (!$rootScope.rootBragainDataEdit.awardName) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写商品名称')
            }
            if (!$rootScope.rootBragainDataEdit.awardNum) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写商品数量')
            }


            // if ($rootScope.rootBragainDataEdit.awardNum<0) {
            //     $scope.cliclSave = false;
            //     return alertService.msgAlert('exclamation-circle', '商品数量不能小于0')
            // }

            if (!(/^[1-9]\d*$/.test($rootScope.rootBragainDataEdit.awardNum))) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '商品数量必须为大于0的正整数')
            }


            if (!$rootScope.rootBragainDataEdit.awardPrice) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写起始价格')
            }

            if ($rootScope.rootBragainDataEdit.awardPrice<=0) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '起始价格必须为大于0的数')
            }

            if (!$rootScope.rootBragainDataEdit.minTargetPrice) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写最低价格')
            }

            if ($rootScope.rootBragainDataEdit.minTargetPrice<=0) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '最低价格必须为大于0的数')
            }
            if (!$rootScope.rootBragainDataEdit.awardImg) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请选择商品图片')
            }
            if (!$rootScope.rootBragainDataEdit.userTypeRandom.newUser.lowerPrice || !$rootScope.rootBragainDataEdit.userTypeRandom.newUser.higherPrice) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写新人砍价范围')
            }

            if ($rootScope.rootBragainDataEdit.userTypeRandom.newUser.lowerPrice<0 || $rootScope.rootBragainDataEdit.userTypeRandom.newUser.higherPrice<0) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '新人砍价范围不能小于0')
            }

            if (!$rootScope.rootBragainDataEdit.userTypeRandom.oldUser.lowerPrice || !$rootScope.rootBragainDataEdit.userTypeRandom.oldUser.higherPrice) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写老会员砍价范围')
            }
            if ($rootScope.rootBragainDataEdit.userTypeRandom.oldUser.lowerPrice<0 || $rootScope.rootBragainDataEdit.userTypeRandom.oldUser.higherPrice<0) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '老会员砍价范围不能小于0')
            }

            if (!$rootScope.rootBragainDataEdit.receiveType) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请选择支付方式')
            }
            if (!$rootScope.rootBragainDataEdit.shareTitle) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写微信分享标题')
            }
            // if (!$rootScope.rootBragainDataEdit.shareDesp) {
            //     $scope.cliclSave = false;
            //     return alertService.msgAlert('exclamation-circle', '请填写微信分享描述')
            // }


            $rootScope.rootBragainDataEdit.status = 'ENABLE';


            $http({
                method: "PUT",
                url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/bargain/admin/'+ $stateParams.id,
                // data: $rootScope.rootBragainDataEdit,
                data:{
                    shopName:$rootScope.rootBragainDataEdit.shopName,
                    awardImg:$rootScope.rootBragainDataEdit.awardImg,
                    awardName:$rootScope.rootBragainDataEdit.awardName,
                    awardNum:$rootScope.rootBragainDataEdit.awardNum,
                    awardPrice:($rootScope.rootBragainDataEdit.awardPrice*100).toFixed(),
                    beginTime:$rootScope.rootBragainDataEdit.beginTime,
                    desp:$rootScope.rootBragainDataEdit.desp,
                    endTime:$rootScope.rootBragainDataEdit.endTime,
                    minTargetPrice:($rootScope.rootBragainDataEdit.minTargetPrice*100).toFixed(),
                    name:$rootScope.rootBragainDataEdit.name,
                    receiveType:$rootScope.rootBragainDataEdit.receiveType,
                    rule:$rootScope.rootBragainDataEdit.rule,
                    shareTitle:$rootScope.rootBragainDataEdit.shareTitle,
                    status:$rootScope.rootBragainDataEdit.status,
                    shareDesp:$rootScope.rootBragainDataEdit.shareDesp,
                    userTypeRandom:{
                        newUser:{
                            higherPrice:($rootScope.rootBragainDataEdit.userTypeRandom.newUser.higherPrice*100).toFixed(),
                            lowerPrice:($rootScope.rootBragainDataEdit.userTypeRandom.newUser.lowerPrice*100).toFixed()
                        },
                        oldUser:{
                            higherPrice:($rootScope.rootBragainDataEdit.userTypeRandom.oldUser.higherPrice*100).toFixed(),
                            lowerPrice:($rootScope.rootBragainDataEdit.userTypeRandom.oldUser.lowerPrice*100).toFixed()
                        }
                    }

                },
                headers: {
                    'Authorization': 'Bearer ' + loginService.getAccessToken()
                }
            }).then(function (resp) {
                    console.log('resprespresprespresp', resp);
                    $scope.cliclSave = false;
                    if (resp.data.status == 200) {
                        $rootScope.rootBragainDataEdit = {};
                        alertService.msgAlert('exclamation-circle', '保存成功');
                        $scope.fallbackPage();
                    }
                    // $scope.dataUrl = resp.data.data;
                }, function () {
                    //error
                    $scope.cliclSave = false;
                }
            );


        };

        $scope.fallbackPage = function () {
            $state.go("main.bargainApp.bargain.bargainView", {id:$stateParams.id}, {reload: true});
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
    '$rootScope',
    '$filter',
    '$templateCache'
];

export default Controller ;
