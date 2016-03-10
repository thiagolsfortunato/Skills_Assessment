window.publication = angular.module('login', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'PortalLexosControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', { templateUrl: '../AngularJS/App/User/Login/Login.view.html', controller: 'LoginController' });

    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.PortalLexosControllers = angular.module('PortalLexosControllers', []);
