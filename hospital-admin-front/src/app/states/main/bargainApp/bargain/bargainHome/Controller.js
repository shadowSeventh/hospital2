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

        $scope.clearKeyWord = function () {
            $scope.keyWord = '';
        };

        //搜索
        $scope.search = function () {
            $scope.pageTitle = '全部';
            $scope.status = 'all';
            $scope.pageChange(0);
        };

        $scope.focus = function (status) {
            if(status){
                $scope.searchShow = true;
            }else {
                $scope.searchShow = false;
            }
        };


        $scope.status = $stateParams.status ? $stateParams.status : "all";
        $scope.isClick = $stateParams.isClick ? $stateParams.isClick : "1";
        $scope.tabs = function (status, tableIndex, search) {
            $scope.status = status;
            // $scope.tableIndex = tableIndex;
            $scope.isClick = tableIndex;
            $scope.pageChange(0);
        };


        $scope.listDialogShow = function (bargain) {
            for(var i = 0 ; i< $scope.bargainList.content.length;i++){
                if($scope.bargainList.content[i].id == bargain.id){
                    bargain.listDialog = !bargain.listDialog;
                }else {
                    $scope.bargainList.content[i].listDialog = false;
                }
            }
        };



        $scope.currpage = 0;
        $scope.pageChange = function (currpage) {

            $http({
                method: "GET",
                url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/bargain/admin',
                params: {
                    page:currpage,
                    size:conf.pageSize,
                    status:$scope.status,
                    keyWord:$scope.keyWord,
                },
                headers: {
                    'Authorization': 'Bearer ' + loginService.getAccessToken()
                }
            }).then(function (resp) {
                $scope.currpage = currpage;
                    console.log('resprespresprespresp', resp);
                    if(currpage){
                        $scope.bargainList.number = resp.data.data.number;
                        for (var i=0;i< resp.data.data.content.length;i++){
                            $scope.bargainList.content.push(resp.data.data.content[i])
                        }
                    }else {
                        $scope.bargainList = resp.data.data;
                    }
                }, function () {
                    //error
                    $scope.cliclSave = false;
                }
            );
        }


        $scope.pageChange(0);



        $scope.changeStatus = function (bargain) {
            console.log('bargain==============',bargain);
            if(bargain.bargainStatus == 'ENABLE'){
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
                            $scope.pageChange(0);
                        }, function () {
                        });
                    }
                })
            }else if(bargain.bargainStatus == 'CLOSED'){
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
                            $scope.pageChange(0);
                        }, function () {
                        });
                    }
                })
            }
        };






        $scope.getBackUrlId = function () {
            $http({
                method: "GET",
                url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/backToShop',
                params: {},
                headers: {
                    // 'Authorization': 'Bearer ' + loginService.getAccessToken()
                }
            }).then(function (resp) {
                $scope.brandAppId = resp.data.data.brandAppId;
                $scope.shopId = resp.data.data.shopId;
            }, function () {

            });
        };

        $scope.getBackUrlId();






        // https://kingsilk.net/bargain/local/?_ddnsPort=16600#/bargainApp/5a30f4e146e0fb00083bd03f/bargain/5a323e32a9233d2eaa273274/home

        $scope.goView = function (id) {
            location.href = conf.bargainWapRootUrl + 'bargainApp/' + $stateParams.bargainAppId + '/bargain/' + id +'/home';
        }








        $scope.fallbackPage = function () {
           if($scope.brandAppId && $scope.shopId){
                location.href = conf.backShopUrl + 'brandApp/' + $scope.brandAppId + '/store/'+ $scope.shopId +'/marketing';
           }else {
               $http({
                   method: "GET",
                   url: conf.apiPath + "/bargainApp/" + $stateParams.bargainAppId + '/backToShop',
                   params: {},
                   headers: {
                       // 'Authorization': 'Bearer ' + loginService.getAccessToken()
                   }
               }).then(function (resp) {
                   $scope.brandAppId = resp.data.data.brandAppId;
                   $scope.shopId = resp.data.data.shopId;
                   location.href = conf.backShopUrl + 'brandApp/' + $scope.brandAppId + '/store/'+ $scope.shopId +'/marketing';
               }, function () {

               });
           }
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
