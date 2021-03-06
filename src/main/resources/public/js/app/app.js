var app = angular

    .module('myApp', ['ui.router', 'ui.bootstrap', 'ngAnimate','ui.grid',
        'ui.grid.pagination', 'ui.grid.resizeColumns', 'ui.grid.autoResize'])

    .controller('myCtrl', function ($scope, $http) {
    });


//统一的处理函数
app.factory('responseObserver', function responseObserver($q, $window) {
    return {
        'responseError': function (errorResponse) {
            switch (errorResponse.code) {
                case 403:
                    break;
                case 404:
                    break;
                case 500:
                    break;
            }
            return (errorResponse);
        }
    };
})

// AngularJS 解析HTML安全策略
    .filter('trustHtml', function ($sce) {
        return function (input) {
            return $sce.trustAsHtml(input);
        }
    });

// 页面间传值
app.factory('Data', function () {
    var savedData = {};

    function set(data) {
        savedData = data;
    }

    function get() {
        return savedData;
    }

    return {
        set: set,
        get: get
    }
});

app.factory('HttpInterceptor', function ($q, $injector) {
    return {
        request: function (config) {
            return config;
        },
        requestError: function (err) {
            return (err);
        },
        response: function (res) {
            return res;
        },
        responseError: function (err) {
            var stateService = $injector.get('$state');
            if (-1 === err.status) {
                // 远程服务器无响应
            } else if (500 === err.status) {
            } else if (404 === err.status) {
            }
            return err;
        }
    };
});
// 路由转换
app.config(function ($stateProvider, $urlRouterProvider, $locationProvider,
                     $httpProvider) {
    $httpProvider.interceptors.push('HttpInterceptor');
    $urlRouterProvider.otherwise('/check');
    $stateProvider.state('index', {
        url: '/index',
        views: {
            '': {
                templateUrl: 'home.html'
            }
        }
    }).state('cards', {
        url: '/cards',
        views: {
            '': {
                templateUrl: 'cards.html'
            }

        }
    }).state('check', {
        url: '/check',
        views: {
            '': {
                templateUrl: 'pages/check.html',
                controller: CheckCtrl
            }

        }
    }).state('checkDay', {
        url: '/checkDay',
        views: {
            '': {
                templateUrl: 'pages/checkDay.html',
                controller: CheckDayInfoCtrl
            }

        }
    }).state('blank', {
        url: '/blank',
        views: {
            '': {
                templateUrl: 'blank.html'
            }
        }
    }).state('404', {
        url: '/404',
        views: {
            '': {
                templateUrl: '404.html'
            }
        }
    }).state('charts', {
        url: '/charts',
        views: {
            '': {
                templateUrl: 'charts.html'
            }

        }
    }).state('submit', {
        url: '/submit',
        views: {
            '': {
                templateUrl: 'pages/submit.html',
                controller: SubmitCtrl
            }

        }
    }).state('updateRecord', {
        url: '/updateRecord',
        views: {
            '': {
                templateUrl: 'pages/updateRecord.html',
                controller: UpdateRecordCtrl
            }

        }
    }).state('wechatApp', {
        url: '/wechatApp',
        views: {
            '': {
                templateUrl: 'pages/wechatApp.html',
                controller: WechatAppCtrl
            }

        }
    }).state('home', {
            url: '/home',
            views: {
                '': {
                    templateUrl: 'pages/home.html',
                    controller: HomeCtrl
                }

            }
        }).state('checkRatio', {
        url: '/checkRatio',
        views: {
            '': {
                templateUrl: 'pages/checkRatio.html',
                controller: CheckRatioCtrl
            }

        }
    })

});
app.filter('Enabled', function () {
    return function (enabled) {
        if (enabled) {
            return '可用';
        } else {
            return '禁用';
        }
    };
});
