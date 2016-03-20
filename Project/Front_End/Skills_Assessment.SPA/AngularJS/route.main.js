window.publication = angular.module('fatec', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);   

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        
        .when('/user/list', { templateUrl: 'AngularJS/App/User/List/UserList.view.html', controller: 'UserListController' })
        .when('/user/add', { templateUrl: 'AngularJS/App/User/Add/UserAdd.view.html', controller: 'UserAddController' })
        .when('/user/edit', { templateUrl: 'AngularJS/App/User/Edit/UserEdit.view.html', controller: 'UserEditController' })
        .when('/user/edit/:Id', { templateUrl: 'AngularJS/App/User/Edit/UserEdit.view.html', controller: 'UserEditController' })
        .when('/user/changepassword', { templateUrl: 'AngularJS/App/User/ChangePassword/ChangePassword.view.html', controller: 'ChangePasswordController' })
        .when('/user/logout', { templateUrl: 'AngularJS/App/User/Logout/Logout.view.html', controller: 'LogoutController' })

        .when('/question', { templateUrl: 'AngularJS/App/Question/Question.view.html', controller: 'QuestionController' })

        .when('/employee/add', { templateUrl: 'AngularJS/App/Employee/Add/EmployeeAdd.view.html', controller: 'EmployeeAddController' })


    $httpProvider.interceptors.push('authorizationInterceptor');
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
