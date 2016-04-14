window.publication = angular.module('psicologo', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        

        //exemplo
        //.when('/employee/add', { templateUrl: 'AngularJS/App/Employee/Add/EmployeeAdd.view.html', controller: 'EmployeeAddController' })


    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
