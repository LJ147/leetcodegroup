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
                $scope.summaryInfo = response;

            }
        })
    };

    getSummaryInfo();

    // 点赞
    $scope.upvote = function (toMemberId, upvoteNumber, index) {
        $http({
            url: '/api/upvote/webVote',
            method: 'post',
            params: {'fromMemberId': -1, 'toMemberId': toMemberId}
        }).success(function (response) {
            console.log("upvote: " + toMemberId);
        })
    };


}


function IndexCtrl($scope, $http, $state) {

}

function UpdateRecordCtrl($scope, $http, $state) {

}

function WechatAppCtrl($scope, $http, $state) {

}


function SubmitCtrl($scope, $http, $state) {
    $scope.message = null;
    $scope.submitUrl = function () {
        $http({
            url: '/api/member/create',
            method: 'post',
            params: {url: $scope.url}
        }).success(function (response) {
            alert(response)
        })
    };
}

function AddUpvoteCount(index) {
    $("#upvote_" + index).text(upvoteNumber + 1);
}