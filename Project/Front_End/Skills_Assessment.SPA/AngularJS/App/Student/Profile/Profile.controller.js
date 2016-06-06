FatecControllers.controller('ProfileController',
    ['$scope', '$routeParams', 'StudentService', 'UserService', '$log', 'localStorageService',
        function ($scope, $routeParams, studentService, userService, $log, localStorageService) {

            var identify;

            $scope.aluno;
            $scope.cancel = _cancel;
            $scope.update = _update;

            var getAluno = _getAluno;

            init();

            function init() {
                identify = localStorageService.get('user');
                getAluno(identify.userCode);
            }

            function _getAluno(idAluno) {
                studentService.studentFindCode(idAluno).then(function (data) {
                    $scope.aluno = data;
                });
            }

            function _cancel() {
                document.location.href = '#/home';
            }

            function _update() { //chamara serviço de alterar aluno
                var aluno = $scope.aluno;
                var user = aluno.user;
                delete aluno['user'];
                angular.forEach(user, function (value, key) {
                    aluno[key] = value;

                });
               
                studentService.studentUpdate(aluno).then(function (status) {
                    if (status) {
                        alert('alterado com sucesso');
                    }
                    getAluno(identify.userCode);
                });
                
            }

        }]);