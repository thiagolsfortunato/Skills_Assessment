FatecControllers.controller('StudentHomeController',
    ['$scope', '$routeParams', '$log', 'StudentService', 'localStorageService', 'AuthenticationService',
        function ($scope, $routeParams, $log, studentService, localStorageService, authenticationService) {

            var usuario;

            $scope.aluno;

            var loadAluno = _loadAluno;

            $scope.logout = _logout;

            init();

            function init() {

                $scope.aluno = authenticationService.Validation('student');

            }

            function _loadAluno(userId) {

                studentService.studentFindCode(userId).then(function (data) { // devolve o aluno inteiro
                    $scope.aluno = data;

                });
            }

            function _logout() {
                localStorageService.clearAll();
            }


        }]);