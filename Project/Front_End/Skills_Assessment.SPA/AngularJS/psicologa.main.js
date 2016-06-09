window.publication = angular.module('psicologa', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        

        //exemplo
        .when('/list', { templateUrl: '/fatec/AngularJS/App/Psicologa/List/Psicologa.view.html', controller: 'PsicologaController' })
        .when('/comment', { templateUrl: '/fatec/AngularJS/App/Psicologa/Comment/Comment.view.html', controller: 'CommentController' })

        .otherwise({ redirectTo: '/list' });

    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
