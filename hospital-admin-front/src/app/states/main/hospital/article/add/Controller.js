import conf from "../../../../../conf"
import E from "wangeditor";

var $scope,
    $http,
    authService,
    $state,
    $log,
    $rootScope,
    $mdDialog,
    Upload,
    $stateParams;

class Controller {
    constructor(_$scope,
                _$http,
                _$state,
                _$log,
                _authService,
                _$rootScope,
                _$mdDialog,
                _Upload,
                _$stateParams) {
        $scope = _$scope;
        $http = _$http;
        $mdDialog = _$mdDialog;
        $state = _$state;
        authService = _authService;
        $rootScope = _$rootScope;
        $log = _$log;
        Upload = _Upload;
        $stateParams = _$stateParams;
        /////////////////////////////////
        // $rootScope.loginCtl(true);

        $rootScope.titleName = '活动图文编辑';
        $scope.id = $stateParams.id;

        console.log($scope.id);
        $scope.data = [];


        //yun图片上传
        $scope.uploading = function (file) {
            $scope.f = file;
            // $scope.errFile =errFiles && errFiles[0];
            if (file) {
                Upload.upload({
                    url: conf.yunApiPath + '/app/5988791a6b869f4e18d5c8d5/org/598878fc6b869f4e0f19fb47/yunFile/',
                    data: {
                        file: file,
                    }
                }).then(function (resp) {
                    // console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ', resp.data);
                    $http({
                        method: 'GET',
                        url: conf.yunApiPath + '/app/5988791a6b869f4e18d5c8d5/org/598878fc6b869f4e0f19fb47/yunFile/' + resp.data.data + "/"
                    }).then(function (resp) {
                        //console.log('Success ' + resp.data.data.cdnUrls[0].url);
                        // 上传代码返回结果之后，将图片插入到编辑器中
                        $scope.data.headImg = resp.data.data.cdnUrls[0].url;
                    }, function (resp) {
                        console.log('Error status: ' + resp.status);
                    });
                }, function (resp) {
                    //console.log('Error status: ' + resp.status);
                }, function (evt) {
                    // var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    // console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
                });
            }
        };

        $scope.cancelDelImg = function () {
            $scope.data.headImg = '';
        };

        $scope.editor = new E('#div4');
        $scope.editor.customConfig.customUploadImg = function (files, insert) {
            // files 是 input 中选中的文件列表
            // insert 是获取图片 url 后，插入到编辑器的方法
            files.forEach(function (file) {
                Upload.upload({
                    url: conf.yunApiPath + '/app/5988791a6b869f4e18d5c8d5/org/598878fc6b869f4e0f19fb47/yunFile',
                    data: {
                        file: file,
                    }
                }).then(function (resp) {
                    $http({
                        method: 'GET',
                        url: conf.yunApiPath + '/app/5988791a6b869f4e18d5c8d5/org/598878fc6b869f4e0f19fb47/yunFile/' + resp.data.data
                    }).then(function (resp) {
                        // console.log('Success ' + resp.data.data.cdnUrls[0].url);
                        insert(resp.data.data.cdnUrls[0].url);
                    }, function (resp) {
                        // console.log('Error status: ' + resp.status);
                    });
                }, function (resp) {
                    // console.log('Error status: ' + resp.status);
                }, function (evt) {
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    // console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
                });
            });
            // 上传代码返回结果之后，将图片插入到编辑器中
        };
        $scope.editor.create();

        $scope.pageSave = function () {
            console.log($scope.data.title)
            $http({
                method: 'POST',
                url: conf.apiPath + '/admin/article/',
                params: {
                    title: $scope.data.title,
                    headImg: $scope.data.headImg,
                    type: $scope.data.type,
                    content: $scope.editor.txt.html(),
                    status: 'EDITING',
                }
            }).then(function (resp) {
                // console.log('Success ' + resp.data.data.cdnUrls[0].url);
                // insert(resp.data.data.cdnUrls[0].url);
            }, function (resp) {
                // console.log('Error status: ' + resp.status);
            });
        };


        $scope.init = function () {
            $http({
                method: 'GET',
                url: conf.apiPath + '/wap/article/' + $scope.id,
            }).then(function (resp) {
                console.log(resp.data.data);
                $scope.data = resp.data.data;
                $scope.editor.txt.html(resp.data.data.content);
            }, function (resp) {
            });
        };

        if ($scope.id != null) {
            $scope.init();
        }

        $scope.pageCancle = function () {
            $scope.fallbackPage();
        };
        $scope.fallbackPage = function () {
            if (history.length === 1) {
                $state.go("main.hospital.articleList", null, {reload: true});
            } else {
                history.back();
            }
        };
    }


}

Controller.$inject = [
    '$scope',
    '$http',
    '$state',
    '$log',
    'authService',
    '$rootScope',
    '$mdDialog',
    'Upload',
    '$stateParams'
];

export default Controller;
