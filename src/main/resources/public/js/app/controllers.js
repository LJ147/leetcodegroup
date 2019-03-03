function LoginCtrl($scope, $http, $state) {
    $scope.user = null;
    $scope.login = function () {

        $http({
            url: 'login',
            method: 'POST',
            params: $scope.user
        }).success(function (response) {
            alert(response)
        })
    }

}

function CheckDayInfoCtrl($scope, $http, $state) {
    $scope.users = null;
    $scope.summaryInfo = null;

    var getCheckDayInfo = function () {
        $http({
            url: 'checkDayInfo/day',
            method: 'get',
            // params: {date:"2019-03-01"}
        }).success(function (response) {
            $scope.users = response
            $scope.order = "solvedQuestion"

        })
    };

    var getSummaryInfo = function () {
        $http({
            url: 'checkDayInfo/summary',
            method: 'get',
            // params: {date:"2019-03-01"}
        }).success(function (response) {
            $scope.summaryInfo = response
        })
    };


    getCheckDayInfo();
    getSummaryInfo();
}


function IndexCtrl($scope, $http, $state) {

}