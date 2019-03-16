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

function CheckDayInfoCtrl($scope, $http, $state, $filter) {
    $scope.summaryInfo = null;
    $scope.today = $filter('date')(new Date(), 'yyyy-MM-dd');



    $http({
        url: 'checkDayInfo/day',
        method: 'get',
        params: {date: $scope.today}
    }).success(function (response) {
            if (response.length > 0) {
                $scope.users = response;
                $scope.order = "solvedQuestion";

            } else {
                alert("所选日期暂无数据！")
            }
        }
    );


    var getSummaryInfo = function () {
        $http({
            url: 'checkDayInfo/summary',
            method: 'get',
            params: {date: $scope.today}
        }).success(function (response) {

            if (response != null) {
                $scope.summaryInfo = response

            }
        })
    };

    getSummaryInfo();


}


function IndexCtrl($scope, $http, $state) {

}