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

function CheckCtrl($scope, $http, $state, $filter) {
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
    });
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

function HomeCtrl($scope, $http, $state, $filter) {


    $scope.summaryInfo = null;
    $scope.users = null;

    $scope.today = $filter('date')(new Date(), 'yyyy-MM-dd');


    $(document).ready(function () {
        $('#dataTable').DataTable({
            "ajax": {
                "url": "checkDayInfo/day",
                "data": {date: $scope.today},
                "type": "GET",
                "dataSrc": ""
            },
            "order": [[3, 'desc'], [4, 'desc']],
            "deferRender": true,
            "columns": [
                {
                    "data": "username",
                    "title": "用户名",
                    "width": "100px",
                    render: function (data, type, row, meta) {
                        return '<a href="' + row.address + '">' + data + '</a>';
                    }
                },
                {
                    "data": "avatar",
                    "title": "头像",
                    "width": "100px",
                    render: function (data, type, row, meta) {
                        return '<a href=' + row.address + '><img width="50px" src=' + data + ' style="border-radius:50% "' + 'alt="user avatar"></a>';
                    }
                }, {
                    "data": "checked",
                    "title": "打卡",
                    "width": "100px",
                    render: function (data, type, row, meta) {
                        var node = "";
                        if (data == "0") {
                            node += "<div class=\"text-xs font-weight-bold text-success text-uppercase mb-1\">已打卡</div>"
                        } else {
                            node += " <div class=\"text-xs font-weight-bold text-warning text-uppercase mb-1\">缺卡</div>"
                        }
                        return node;
                    }
                },
                {
                    "data": "solvedProblemNumberOfToday",
                    "title": "今日刷题",
                    "width": "100px",
                },
                {
                    "data": "solvedQuestion",
                    "title": "刷题数",
                    "width": "100px",
                },
                {
                    "data": "upvoteNumber",
                    "title": "点赞",
                    "width": "100px",

                }
            ]
        })
    });

    $http({
        url: 'checkDayInfo/summary',
        method: 'get',
        params: {date: $scope.today}
    }).success(function (response) {

        if (response != null) {
            $scope.summaryInfo = response;

        }
    });


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


function CheckRatioCtrl1($scope, $http, $state, $filter) {

    $scope.days = null;
    $scope.ratioList = null;

    $scope.today = $filter('date')(new Date(), 'yyyy-MM-dd');

    $http({
        url: 'checkDayInfo/checkRatioList',
        method: 'get',
        params: {date: $scope.today}
    }).success(function (response) {

        if (response != null) {
            $scope.days = response.days;
            $scope.ratioList = response.ratioList;


        }
    });

// Set new default font family and font color to mimic Bootstrap's default styling
    Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
    Chart.defaults.global.defaultFontColor = '#858796';

    function number_format(number, decimals, dec_point, thousands_sep) {
        // *     example: number_format(1234.56, 2, ',', ' ');
        // *     return: '1 234,56'
        number = (number + '').replace(',', '').replace(' ', '');
        var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
            s = '',
            toFixedFix = function (n, prec) {
                var k = Math.pow(10, prec);
                return '' + Math.round(n * k) / k;
            };
        // Fix for IE parseFloat(0.55).toFixed(0) = 0;
        s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
        if (s[0].length > 3) {
            s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
        }
        if ((s[1] || '').length < prec) {
            s[1] = s[1] || '';
            s[1] += new Array(prec - s[1].length + 1).join('0');
        }
        return s.join(dec);
    }

// Area Chart Example
    var ctx = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: $scope.days,
            datasets: [{
                label: "Earnings",
                lineTension: 0.3,
                backgroundColor: "rgba(78, 115, 223, 0.05)",
                borderColor: "rgba(78, 115, 223, 1)",
                pointRadius: 3,
                pointBackgroundColor: "rgba(78, 115, 223, 1)",
                pointBorderColor: "rgba(78, 115, 223, 1)",
                pointHoverRadius: 3,
                pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                pointHitRadius: 10,
                pointBorderWidth: 2,
                data: $scope.ratioList,
            }],
        },
        options: {
            maintainAspectRatio: false,
            layout: {
                padding: {
                    left: 10,
                    right: 25,
                    top: 25,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                    time: {
                        unit: 'date'
                    },
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    ticks: {
                        maxTicksLimit: 7
                    }
                }],
                yAxes: [{
                    ticks: {
                        maxTicksLimit: 5,
                        padding: 10,
                        // Include a dollar sign in the ticks
                        callback: function (value, index, values) {
                            return '$' + number_format(value);
                        }
                    },
                    gridLines: {
                        color: "rgb(234, 236, 244)",
                        zeroLineColor: "rgb(234, 236, 244)",
                        drawBorder: false,
                        borderDash: [2],
                        zeroLineBorderDash: [2]
                    }
                }],
            },
            legend: {
                display: false
            },
            tooltips: {
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                titleMarginBottom: 10,
                titleFontColor: '#6e707e',
                titleFontSize: 14,
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                intersect: false,
                mode: 'index',
                caretPadding: 10,
                callbacks: {
                    label: function (tooltipItem, chart) {
                        var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                        return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
                    }
                }
            }
        }
    });

}


function CheckRatioCtrl($scope, $http, $state, $filter) {

    $scope.days = null;
    $scope.ratioList = null;
    $scope.checkInfoCount = null;

    $scope.today = $filter('date')(new Date(), 'yyyy-MM-dd');

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);


    var domCount = document.getElementById("checkInfoCount");
    var checkInfoCount = echarts.init(domCount);

    $http({
        url: 'checkDayInfo/checkRatioList',
        method: 'get',
        params: {date: $scope.today}
    }).success(function (response) {

        if (response != null) {
            $scope.days = response.days;
            $scope.ratioList = response.ratioList;
            $scope.checkInfoCount = response.checkInfoCount;


            // 打卡率曲线
            var app = {};
            option = null;
            option = {
                tooltip: {
                    trigger: 'axis'
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    data: $scope.days
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: $scope.ratioList,
                    name: '打卡率',
                    type: 'line'
                }]
            };
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }


            // 小组人数曲线
            var checkInfoCountApp = {};
            optionOfCheckInfoCount = null;
            // checkInfoCountApp.title = '坐标轴刻度与标签对齐';

            optionOfCheckInfoCount = {
                color: ['#3398DB'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: $scope.days,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '小组人数',
                        type: 'bar',
                        barWidth: '60%',
                        data: $scope.checkInfoCount
                    }
                ]
            };
            if (optionOfCheckInfoCount && typeof optionOfCheckInfoCount === "object") {
                checkInfoCount.setOption(optionOfCheckInfoCount, true);
            }


        }
    });


}


