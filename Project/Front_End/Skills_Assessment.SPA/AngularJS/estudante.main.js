window.publication = angular.module('estudante', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        
      
        .when('/home', { templateUrl: '/fatec/AngularJS/App/Student/Home/StudentHome.view.html', controller: 'StudentHomeController' })

        .when('/question', { templateUrl: '/fatec/AngularJS/App/Student/Question/Question.view.html', controller: 'QuestionController' })

        .when('/perfil', { templateUrl: '/fatec/AngularJS/App/Student/Profile/Profile.view.html', controller: 'ProfileController' })

        .when('/completed', { templateUrl: '/fatec/AngularJS/App/Student/Completed/Completed.view.html', controller: 'CompletedController' })

        .when('/result', { templateUrl: '/fatec/AngularJS/App/Student/Result/Result.view.html', controller: 'ResultController' })

        .otherwise({ redirectTo: '/home' });

    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
