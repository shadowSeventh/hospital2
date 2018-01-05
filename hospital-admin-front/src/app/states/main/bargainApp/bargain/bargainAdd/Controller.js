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


        if($stateParams.form != 'text'){
            $rootScope.rootBragainData = {};
            $rootScope.rootBragainData.userTypeRandom = {
                newUser: {},
                oldUser: {},
            };
        }else {

            if (!$rootScope.rootBragainData) {
                $rootScope.rootBragainData = {};
                $rootScope.rootBragainData.userTypeRandom = {
                    newUser: {},
                    oldUser: {},
                };
                // $rootScope.rootBragainData.newUser = {};
                // $rootScope.rootBragainData.oldUser = {};
            } else {
                // if(!$rootScope.rootBragainData.newUser){
                //     $rootScope.rootBragainData.newUser = {};
                // }
                // if(!$rootScope.rootBragainData.oldUser){
                //     $rootScope.rootBragainData.oldUser = {};
                // }

                if (!$rootScope.rootBragainData.userTypeRandom) {
                    $rootScope.rootBragainData.userTypeRandom = {
                        newUser: {},
                        oldUser: {},
                    };
                };


                if ($rootScope.rootBragainData.receiveType) {
                    $scope.dialogPayType = $rootScope.rootBragainData.receiveType;
                }
            }

        }



        $scope.getShopName = function () {
            $http({
                method: "GET",
                url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/getShopName',
                params: {},
                headers: {
                    // 'Authorization': 'Bearer ' + loginService.getAccessToken()
                }
            }).then(function (resp) {
                $rootScope.rootBragainData.shopName = resp.data.data;
            }, function () {
                //error
            });
        };


        if(!$rootScope.rootBragainData.shopName){
            $scope.getShopName();
        }



















        $scope.AddEventInput = function (type) {
            console.log('type=====',type);
            console.log('type222222=====',$rootScope.rootBragainData.type);
            var reg=/(^[1-9]{1}[0-9]*$)|(^[0-9]+\.[0-9]{0,2}$)|(^[0]$)/;
            if(type == 'awardPrice'){
               var price = $rootScope.rootBragainData.awardPrice;
                console.log('price=====',typeof price,price);
                if(!reg.test($rootScope.rootBragainData.awardPrice)){
                    $rootScope.rootBragainData.awardPrice = Number(price.toString().substring(0,price.toString().length-1));
                    console.log("请输入大于0的整数或者保留")
                }
            }else if(type == 'minTargetPrice'){
                var price = $rootScope.rootBragainData.minTargetPrice;
                console.log('price=====',typeof price,price);
                if(!reg.test($rootScope.rootBragainData.minTargetPrice)){
                    $rootScope.rootBragainData.minTargetPrice = Number(price.toString().substring(0,price.toString().length-1));
                    console.log("请输入大于0的整数或者保留")
                }
            }else if(type == 'newLower'){
                var price = $rootScope.rootBragainData.userTypeRandom.newUser.lowerPrice;
                console.log('price=====',typeof price,price);
                if(!reg.test($rootScope.rootBragainData.userTypeRandom.newUser.lowerPrice)){
                    $rootScope.rootBragainData.userTypeRandom.newUser.lowerPrice = Number(price.toString().substring(0,price.toString().length-1));
                    console.log("请输入大于0的整数或者保留")
                }
            }else if(type == 'newHigher'){
                var price = $rootScope.rootBragainData.userTypeRandom.newUser.higherPrice;
                console.log('price=====',typeof price,price);
                if(!reg.test($rootScope.rootBragainData.userTypeRandom.newUser.higherPrice)){
                    $rootScope.rootBragainData.userTypeRandom.newUser.higherPrice = Number(price.toString().substring(0,price.toString().length-1));
                    console.log("请输入大于0的整数或者保留")
                }
            }else if(type == 'oldLower'){
                var price = $rootScope.rootBragainData.userTypeRandom.oldUser.lowerPrice;
                console.log('price=====',typeof price,price);
                if(!reg.test($rootScope.rootBragainData.userTypeRandom.oldUser.lowerPrice)){
                    $rootScope.rootBragainData.userTypeRandom.oldUser.lowerPrice = Number(price.toString().substring(0,price.toString().length-1));
                    console.log("请输入大于0的整数或者保留")
                }
            }else if(type == 'oldHigher'){
                var price = $rootScope.rootBragainData.userTypeRandom.oldUser.higherPrice;
                console.log('price=====',typeof price,price);
                if(!reg.test($rootScope.rootBragainData.userTypeRandom.oldUser.higherPrice)){
                    $rootScope.rootBragainData.userTypeRandom.oldUser.higherPrice = Number(price.toString().substring(0,price.toString().length-1));
                    console.log("请输入大于0的整数或者保留")
                }
            }
        };



















        $scope.dialogChoosePayType = function (type) {
            $scope.dialogPayType = type;
        };
        $scope.cancelChoosePayType = function (data) {
            $scope.choosePayType = false;
            if (data) {
                $rootScope.rootBragainData.receiveType = $scope.dialogPayType;
                $scope.payType = $scope.dialogPayType;
            } else {
                $scope.dialogPayType = $rootScope.rootBragainData.receiveType;
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

                    $rootScope.rootBragainData.beginTime = timestamp2;

                    // $rootScope.rootBragainData.beginTime = {
                    //     year: result[0].value,
                    //     month: result[1].value>9?result[1].value:'0'+result[1].value,
                    //     day: result[2].value>9?result[2].value:'0'+result[2].value,
                    // };

                    console.log('333333333333333333333',$rootScope.rootBragainData.beginTime);

                    $scope.$digest();
                },
                id: 'datePickerStart',
                className: 'datePickerStart'
            });
        };
        $scope.chooseEndTime = function () {
            if ($rootScope.rootBragainData.beginTime) {
                // $filter('date')($rootScope.rootBragainData.beginTime, 'yyyy-MM-dd');
                // var startTime = $scope.startTime[0].value + '-' + $scope.startTime[1].value + '-' + $scope.startTime[2].value;
                weui.datePicker({
                    start: $filter('date')($rootScope.rootBragainData.beginTime, 'yyyy-MM-dd'), // 从今天开始
                    end: Number($filter('date')($rootScope.rootBragainData.beginTime, 'yyyy')) + 1,
                    defaultValue: [$filter('date')($rootScope.rootBragainData.beginTime, 'yyyy'), $filter('date')($rootScope.rootBragainData.beginTime, 'MM'), $filter('date')($rootScope.rootBragainData.beginTime, 'dd')],
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
                        $rootScope.rootBragainData.endTime = timestamp2;
                        // $rootScope.rootBragainData.endTime = {
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
                    $rootScope.rootBragainData.awardImg = resp.data.data.cdnUrls[0].url;
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
                from: 'add'
            })
        }


        $scope.cliclSave = false;

        $scope.save = function () {
            if ($scope.cliclSave) {
                return
            } else {
                $scope.cliclSave = true;
            }
            console.log('result================', $rootScope.rootBragainData);

            if (!$rootScope.rootBragainData.shopName) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写店铺名称')
            }
            if (!$rootScope.rootBragainData.name) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写活动名称')
            }

            if (!$rootScope.rootBragainData.beginTime) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请选择生效时间')
            }
            if (!$rootScope.rootBragainData.endTime) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请选择过期时间')
            }

            if($rootScope.rootBragainData.beginTime > $rootScope.rootBragainData.endTime){
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '生效时间不能大于过期时间!')
            }




            if (!$rootScope.rootBragainData.rule) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写规则说明')
            }
            // if (!$rootScope.rootBragainData.desp) {
            //     $scope.cliclSave = false;
            //     return alertService.msgAlert('exclamation-circle', '请填写活动图文')
            // }
            if (!$rootScope.rootBragainData.awardName) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写商品名称')
            }
            if (!$rootScope.rootBragainData.awardNum) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写商品数量')
            }

            if (!(/^[1-9]\d*$/.test($rootScope.rootBragainData.awardNum))) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '商品数量必须为大于0的正整数')
            }


            if (!$rootScope.rootBragainData.awardPrice) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写起始价格')
            }

            if ($rootScope.rootBragainData.awardPrice<=0) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '起始价格必须为大于0的数')
            }
            if (!$rootScope.rootBragainData.minTargetPrice) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写最低价格')
            }

            if ($rootScope.rootBragainData.minTargetPrice<=0) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '最低价格必须为大于0的数')
            }
            if (!$rootScope.rootBragainData.awardImg) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请选择商品图片')
            }
            if (!$rootScope.rootBragainData.userTypeRandom.newUser.lowerPrice || !$rootScope.rootBragainData.userTypeRandom.newUser.higherPrice) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写新人砍价范围')
            }

            if ($rootScope.rootBragainData.userTypeRandom.newUser.lowerPrice<0 || $rootScope.rootBragainData.userTypeRandom.newUser.higherPrice<0) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '新人砍价范围不能小于0')
            }

            if (!$rootScope.rootBragainData.userTypeRandom.oldUser.lowerPrice || !$rootScope.rootBragainData.userTypeRandom.oldUser.higherPrice) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写老会员砍价范围')
            }
            if ($rootScope.rootBragainData.userTypeRandom.oldUser.lowerPrice<0 || $rootScope.rootBragainData.userTypeRandom.oldUser.higherPrice<0) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '老会员砍价范围不能小于0')
            }

            if (!$rootScope.rootBragainData.receiveType) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请选择支付方式')
            }
            if (!$rootScope.rootBragainData.shareTitle) {
                $scope.cliclSave = false;
                return alertService.msgAlert('exclamation-circle', '请填写微信分享标题')
            }
            // if (!$rootScope.rootBragainData.shareDesp) {
            //     $scope.cliclSave = false;
            //     return alertService.msgAlert('exclamation-circle', '请填写微信分享描述')
            // }


            $rootScope.rootBragainData.status = 'ENABLE';

            // $rootScope.rootBragainData
            $http({
                method: "POST",
                url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/bargain/admin',
                data: {
                    shopName:$rootScope.rootBragainData.shopName,
                    awardImg:$rootScope.rootBragainData.awardImg,
                    awardName:$rootScope.rootBragainData.awardName,
                    awardNum:$rootScope.rootBragainData.awardNum,
                    awardPrice:($rootScope.rootBragainData.awardPrice*100).toFixed(),
                    beginTime:$rootScope.rootBragainData.beginTime,
                    desp:$rootScope.rootBragainData.desp,
                    endTime:$rootScope.rootBragainData.endTime,
                    minTargetPrice:($rootScope.rootBragainData.minTargetPrice*100).toFixed(),
                    name:$rootScope.rootBragainData.name,
                    receiveType:$rootScope.rootBragainData.receiveType,
                    rule:$rootScope.rootBragainData.rule,
                    shareTitle:$rootScope.rootBragainData.shareTitle,
                    status:$rootScope.rootBragainData.status,
                    shareDesp:$rootScope.rootBragainData.shareDesp,
                    userTypeRandom:{
                        newUser:{
                            higherPrice:($rootScope.rootBragainData.userTypeRandom.newUser.higherPrice*100).toFixed(),
                            lowerPrice:($rootScope.rootBragainData.userTypeRandom.newUser.lowerPrice*100).toFixed()
                        },
                        oldUser:{
                            higherPrice:($rootScope.rootBragainData.userTypeRandom.oldUser.higherPrice*100).toFixed(),
                            lowerPrice:($rootScope.rootBragainData.userTypeRandom.oldUser.lowerPrice*100).toFixed()
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
                        $rootScope.rootBragainData = {};
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
    '$rootScope',
    '$filter',
    '$templateCache'
];

export default Controller ;
