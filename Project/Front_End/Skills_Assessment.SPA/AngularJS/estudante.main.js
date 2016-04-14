window.publication = angular.module('estudante', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        
      
        .when('/question', { templateUrl: 'AngularJS/App/Student/Question/Question.view.html', controller: 'QuestionController' })


    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
