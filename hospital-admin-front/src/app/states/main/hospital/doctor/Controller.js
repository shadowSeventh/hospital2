import conf from "../../../../conf";

var $scope,
    $http,
    loginService,
    Upload,
    $stateParams,
    authService,
    errorService,
    alertService,
    dateFilter,
    $state;


Controller.$inject = [
    '$scope',
    '$http',
    'loginService',
    'Upload',
    '$stateParams',
    'authService',
    'errorService',
    'alertService',
    'dateFilter',
    '$state'];

function Controller(_$scope,
                    _$http,
                    _loginService,
                    _Upload,
                    _$stateParams,
                    _authService,
                    _errorService,
                    _alertService,
                    _dateFilter,
                    _$state) {
    $scope = _$scope;
    $http = _$http;
    $stateParams = _$stateParams;
    loginService = _loginService;
    authService = _authService;
    errorService = _errorService;
    Upload = _Upload;
    $state = _$state;
    dateFilter = _dateFilter;
    alertService = _alertService;


    conf.title = "人员管理>会员管理";
    $scope.data = {};
    $scope.user = {};
    $scope.curPage = 1;
    $scope.doctorList = [];
    $scope.pageChanged = function (curPage) {
        if ($scope.data.number > $scope.data.totalPages) {
            return;
        }

        $http({
            method: "GET",
            url: conf.apiPath + "/admin/doctor",
            params: {
                page: curPage ? curPage : $scope.curPage - 1,
                size: conf.pageSize,
                keyWord: $scope.user.keyword,
                status: null,
                department: null
            },

        }).success(function (data) {
            $scope.doctorList = data.data.data.content
        });
    };

    $scope.pageChanged();


    $scope.alert = function (userId, enabled) {
        console.log(enabled);
        $scope.userId = userId;
        $scope.disabled = enabled;
        var tmp;
        if (enabled) {
            tmp = "禁用"
        } else {
            tmp = "启用"
        }
        alertService.confirm(null, "确定" + tmp + "该会员？", "温馨提示", "取消", "确认").then(function (data) {

            if (data == true) {
                $http({
                    method: "PUT",
                    url: conf.apiPath + "/brandApp/" + $scope.brandAppId + "/partnerStaff/" + $scope.userId + "/enable",
                    params: {
                        disabled: $scope.disabled
                    },
                    headers: {
                        'Authorization': 'Bearer ' + loginService.getAccessToken(),
                        "brandApp-Id": $scope.brandAppId
                    },
                }).success(function (data) {
                    if (data.code === "SUCCESS") {
                        $scope.pageChanged();
                    }
                });
            }
        });
    };


}

export default Controller;
