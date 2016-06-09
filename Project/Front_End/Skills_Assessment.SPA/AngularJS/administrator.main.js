window.publication = angular.module('administrator', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar', 'FatecControllers']);

publication.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    

    $routeProvider
        
        .when('/home', { templateUrl: '/fatec/AngularJS/App/Administrator/Home/AdministratorHome.view.html', controller: 'AdministratorHomeController' })
        .when('/psicologa/list', { templateUrl: '/fatec/AngularJS/App/Administrator/Employee/List/PsicologaList.view.html', controller: 'PsicologoListController' })
        .when('/psicologa/add', { templateUrl: '/fatec/AngularJS/App/Administrator/Employee/Add/EmployeeAdd.view.html', controller: 'EmployeeAddController' })
        .when('/psicologa/edit', { templateUrl: '/fatec/AngularJS/App/Administrator/Employee/Edit/PsicologaEdit.view.html', controller: 'PsicologaEditController' })
        .when('/user/edit/:Id', { templateUrl: '/fatec/AngularJS/App/User/Edit/UserEdit.view.html', controller: 'UserEditController' })
        .when('/user/changepassword', { templateUrl: '/fatec/AngularJS/App/User/ChangePassword/ChangePassword.view.html', controller: 'ChangePasswordController' })
        .when('/user/logout', { templateUrl: '/fatec/AngularJS/App/User/Logout/Logout.view.html', controller: 'LogoutController' })

        .when('/question', { templateUrl: '/fatec/AngularJS/App/Administrator/Question/Menu/QuestionMenu.view.html', controller: 'QuestionMenuController' })
        .when('/question/add', { templateUrl: '/fatec/AngularJS/App/Administrator/Question/Add/QuestionAdd.view.html', controller: 'QuestionAddController' })
        .when('/question/edit', { templateUrl: '/fatec/AngularJS/App/Administrator/Question/Add/QuestionAdd.view.html', controller: 'QuestionAddController' })

        .when('/employee/add', { templateUrl: '/fatec/AngularJS/App/Administrator/Employee/Add/EmployeeAdd.view.html', controller: 'EmployeeAddController' })

        .when('/competencies', { templateUrl: '/fatec/AngularJS/App/Administrator/Competencies/List/Competencies.view.html', controller: 'CompetenciesController' })
        .when('/course', { templateUrl: '/fatec/AngularJS/App/Administrator/Course/List/Course.view.html', controller: 'CourseController' })

        .when('/institution', { templateUrl: '/fatec/AngularJS/App/Administrator/Institution/List/Institution.view.html', controller: 'InstitutionController' })

        .when('/myfatec', { templateUrl: '/fatec/AngularJS/App/Administrator/Institution/MyFatec/MyFatec.view.html', controller: 'MyFatecController' })
        .when('/student/list', { templateUrl: '/fatec/AngularJS/App/Administrator/Student/List/StudentList.view.html', controller: 'StudentListController' })
        .when('/student/edit', { templateUrl: '/fatec/AngularJS/App/Administrator/Student/Edit/StudentEdit.view.html', controller: 'StudentEditController' })

        .otherwise({  redirectTo: '/home'  });


    $httpProvider.interceptors.push('authorizationInterceptor');
    
}]);


window.FatecControllers = angular.module('FatecControllers', ['ui.bootstrap', 'ngReallyClickModule', 'ui.select', 'ngSanitize', 'checklist-model']);
