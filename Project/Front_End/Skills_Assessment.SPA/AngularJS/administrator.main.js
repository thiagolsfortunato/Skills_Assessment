window.publication = angular.module('administrator', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {

    /*
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    $httpProvider.defaults.headers = { 'Content-Type': 'application/json;charset=utf-8' };
    //$httpProvider.defaults.withCredentials = true;
    //$httpProvider.defaults.headers.common = {};
    $httpProvider.defaults.headers.post = {};
    $httpProvider.defaults.headers.put = {};
    $httpProvider.defaults.headers.delete = {};
    //$httpProvider.defaults.headers.patch = {}; */
    

    $routeProvider
        
        .when('/user/list', { templateUrl: 'AngularJS/App/User/List/UserList.view.html', controller: 'UserListController' })
        .when('/user/add', { templateUrl: 'AngularJS/App/User/Add/UserAdd.view.html', controller: 'UserAddController' })
        .when('/user/edit', { templateUrl: 'AngularJS/App/User/Edit/UserEdit.view.html', controller: 'UserEditController' })
        .when('/user/edit/:Id', { templateUrl: 'AngularJS/App/User/Edit/UserEdit.view.html', controller: 'UserEditController' })
        .when('/user/changepassword', { templateUrl: 'AngularJS/App/User/ChangePassword/ChangePassword.view.html', controller: 'ChangePasswordController' })
        .when('/user/logout', { templateUrl: 'AngularJS/App/User/Logout/Logout.view.html', controller: 'LogoutController' })

        .when('/question', { templateUrl: 'AngularJS/App/Administrator/Question/Menu/QuestionMenu.view.html' })
        .when('/question/add', { templateUrl: 'AngularJS/App/Administrator/Question/Add/QuestionAdd.view.html', controller: 'QuestionAddController' })

        .when('/employee/add', { templateUrl: 'AngularJS/App/Administrator/Employee/Add/EmployeeAdd.view.html', controller: 'EmployeeAddController' })

        .when('/competencies', { templateUrl: 'AngularJS/App/Administrator/Competencies/List/Competencies.view.html', controller: 'CompetenciesController' })
        .when('/course', { templateUrl: 'AngularJS/App/Administrator/Course/List/Course.view.html', controller: 'CourseController' })



    $httpProvider.interceptors.push('authorizationInterceptor');
    
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
