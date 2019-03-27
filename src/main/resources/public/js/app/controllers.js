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

function CheckCtrl($scope, $http, $state,$filter) {
    $scope.summaryInfo = null;
    $scope.users = null;

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

    $http({
        url: 'checkDayInfo/summary',
        method: 'get',
        params: {date: $scope.today}
    }).success(function (response) {

        if (response != null) {
            $scope.summaryInfo = response;

        }
    })


}


function CheckDayInfoCtrl($scope, $http, $state, $filter) {
    $scope.summaryInfo = null;
    $scope.today = $filter('date')(new Date(), 'yyyy-MM-dd');
    $scope.index = 0;

    var paginationOptions = {
        pageNumber: 1,
        pageSize: 10
    };

    $scope.gridOptions = {
        paginationPageSizes: [10, 50, 100],
        paginationPageSize: 10,
        enableHorizontalScrollbar: 0, // grid水平滚动条是否显示, 0-不显示 1-显示
        enableVerticalScrollbar: 0, // grid垂直滚动条是否显示, 0-不显示 1-显示
        useExternalPagination: true, // 是否使用分页按钮
        useExternalSorting: false, // 是否使用自定义排序规则
        rowHeight: 60,
        headerHeight: 60,
        headerClass: 'text-center',
        columnDefs: [{
            name: '序号',
            cellTemplate: '<div class="ui-grid-cell-contents" style="width: 100%; height: 500px; text-align: center;">{{grid.renderContainers.body.visibleRowCache.indexOf(row)+(grid.options.paginationPageSize*(grid.options.paginationCurrentPage-1))+1}}</div>\'',
            width: '10%',
            enableSorting: false,
            enableColumnMenu: false
        }, {
            name: '用户名',
            displayName: '用户名',
            cellTemplate: '<div style="width: 100%; height: 500px; text-align: center;"><a href={{row.entity.address}}> {{row.entity.username}}</a></div>',
            width: '20%',
            enableSorting: false,
            enableColumnMenu: false
        }, {
            name: '头像',
            displayName: '头像',
            cellTemplate: '<div style="width: 100%; height: 500px; text-align: center;"><a href={{row.entity.address}}><img width="50px" src= {{row.entity.avatar}} style="border-radius: 50%"\n' +
                '                                                        alt="user avatar"></a></div>',
            width: '20%',
            enableSorting: false,
            enableColumnMenu: false
        }, {
            name: '刷题数',
            displayName: '刷题数',
            field: 'solvedQuestion',
            type: 'number',
            cellClass: 'text-center',
            width: '10%'

        }, {
            name: '今日查卡',
            displayName: '今日查卡',
            cellTemplate:
                '<div style="width: 100%; height: 500px; text-align: center;" class="text-xs font-weight-bold text-success text-uppercase mb-1" ng-hide="row.entity.checked==1">已打卡</div>' +
                '<div style="width: 100%; height: 500px; text-align: center;" class="text-xs font-weight-bold text-warning text-uppercase mb-1" ng-hide="row.entity.checked==0">缺卡</div>',
            width: '20%',
            enableSorting: false,
            enableColumnMenu: false
        }, {
            name: '点赞',
            displayName: '点赞',
            width: '20%',
            enableSorting: false,
            type: 'number',
            enableColumnMenu: false,
            cellTemplate:
                '<div style="width: 100%; height: 500px; text-align: center;"><a  ng-click="row.entity.upvoteNumber = row.entity.upvoteNumber+ 1;grid.appScope.upvote(row.entity.username) "><i class="fas fa-thumbs-up"></i><span>{{row.entity.upvoteNumber}}</span></a></div>'
        }],
        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;
            /**
             * 排序
             */
            $scope.gridApi.core.on.sortChanged($scope, function (grid,
                                                                 sortColumns) {
                if (sortColumns.length == 0) {
                    paginationOptions.sort = null;
                } else {
                    $scope.sorts.field = sortColumns[0].field;// 获取想要排序的哪行的名字
                    $scope.sorts.direction = sortColumns[0].sort.direction; // 获取排序的方式
                }
                getPage();
            });
            /**
             * 进行分页
             */
            $scope.gridApi.pagination.on.paginationChanged($scope, function (
                newPage, pageSize) {
                paginationOptions.pageNumber = newPage;
                paginationOptions.pageSize = pageSize;
                getPage();
            });
        }
    };
    $scope.getTableHeight = function () {
        var rowHeight = 60; // your row height
        var headerHeight = 60; // your header height
        return {
            height: ($scope.gridOptions.data.length * rowHeight + headerHeight + 2) + "px"
        };
    };
    var getPage = function () {
        // 获取当前页数
        var page = paginationOptions.pageNumber - 1;
        $http.get("checkDayInfo/info?page=" + page + "&pageSize=" + paginationOptions.pageSize +
            "&date=" + $scope.today).success(function (response) {
            if (response.totalElements > 0) {
                $scope.gridOptions.totalItems = response.totalElements;
                $scope.gridOptions.data = response.content;
                $scope.users = response.content;

            } else {
                alert("所选日期暂无数据！")
            }
        })
    };


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
    getPage();

    // 点赞
    $scope.upvote = function (toMemberId) {

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