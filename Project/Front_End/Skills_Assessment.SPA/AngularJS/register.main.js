window.publication = angular.module('register', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider

        .when('/Student', { templateUrl: '/fatec/AngularJS/App/User/Register/Student/RegisterStudent.view.html', controller: 'RegisterStudentController' })
        .when('/Institution', { templateUrl: '/fatec/AngularJS/App/User/Register/Institution/RegisterInstitution.view.html', controller: 'RegisterInstitutionController' })

    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.FatecControllers = angular.module('FatecControllers', []);
