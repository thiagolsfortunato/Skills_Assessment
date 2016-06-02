window.publication = angular.module('administrator', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    

    $routeProvider
        
        .when('/home', { templateUrl: 'AngularJS/App/Administrator/Home/AdministratorHome.view.html', controller: 'AdministratorHomeController' })
        .when('/user/list', { templateUrl: 'AngularJS/App/User/List/UserList.view.html', controller: 'UserListController' })
        .when('/user/add', { templateUrl: 'AngularJS/App/User/Add/UserAdd.view.html', controller: 'UserAddController' })
        .when('/user/edit', { templateUrl: 'AngularJS/App/User/Edit/UserEdit.view.html', controller: 'UserEditController' })
        .when('/user/edit/:Id', { templateUrl: 'AngularJS/App/User/Edit/UserEdit.view.html', controller: 'UserEditController' })
        .when('/user/changepassword', { templateUrl: 'AngularJS/App/User/ChangePassword/ChangePassword.view.html', controller: 'ChangePasswordController' })
        .when('/user/logout', { templateUrl: 'AngularJS/App/User/Logout/Logout.view.html', controller: 'LogoutController' })

        .when('/question', { templateUrl: 'AngularJS/App/Administrator/Question/Menu/QuestionMenu.view.html', controller: 'QuestionMenuController' })
        .when('/question/add', { templateUrl: 'AngularJS/App/Administrator/Question/Add/QuestionAdd.view.html', controller: 'QuestionAddController' })
        .when('/question/edit', { templateUrl: 'AngularJS/App/Administrator/Question/Add/QuestionAdd.view.html', controller: 'QuestionAddController' })

        .when('/employee/add', { templateUrl: 'AngularJS/App/Administrator/Employee/Add/EmployeeAdd.view.html', controller: 'EmployeeAddController' })

        .when('/competencies', { templateUrl: 'AngularJS/App/Administrator/Competencies/List/Competencies.view.html', controller: 'CompetenciesController' })
        .when('/course', { templateUrl: 'AngularJS/App/Administrator/Course/List/Course.view.html', controller: 'CourseController' })

        .when('/institution', { templateUrl: 'AngularJS/App/Administrator/Institution/List/Institution.view.html', controller: 'InstitutionController' })

        .when('/myfatec', { templateUrl: 'AngularJS/App/Administrator/Institution/MyFatec/MyFatec.view.html', controller: 'MyFatecController' })
        .when('/student/list', { templateUrl: 'AngularJS/App/Administrator/Student/List/StudentList.view.html', controller: 'StudentListController' })
        .when('/student/edit', { templateUrl: 'AngularJS/App/Administrator/Student/Edit/StudentEdit.view.html', controller: 'StudentEditController' })

        .otherwise({  redirectTo: '/home'  });


    $httpProvider.interceptors.push('authorizationInterceptor');
    
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
