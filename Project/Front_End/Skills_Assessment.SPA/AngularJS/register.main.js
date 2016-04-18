window.publication = angular.module('register', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider

        .when('/', { templateUrl: '../AngularJS/App/User/Register/Register.view.html', controller: 'RegisterController' });

    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.FatecControllers = angular.module('FatecControllers', []);
