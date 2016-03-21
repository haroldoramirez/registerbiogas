angular
    .module
        ('registercibiogas',
            ['ngRoute',
             'ngResource',
             'ngAnimate',
             'ui.bootstrap',
             'toastr',
             'ngMask',
             'mgcrea.ngStrap.datepicker',
             'mgcrea.ngStrap.navbar',
             'ui.bootstrap.showErrors'
            ]
        )
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/assets/app/views/home.html',
                controller: 'home.controller'
            })
            .when('/inscricao/novo', {
                templateUrl: '/assets/app/views/inscricao.html',
                controller: 'inscricao.controller'
            });
    })
    .config(function($datepickerProvider) {
            angular.extend($datepickerProvider.defaults, {
               iconLeft: 'fa fa-chevron-left',
               iconRight: 'fa fa-chevron-right',
               placement: 'bottom'
            });
    });