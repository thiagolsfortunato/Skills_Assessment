FatecControllers.controller('ProfileController',
    ['$scope', '$routeParams', 'StudentService', '$log', 'localStorageService',
        function ($scope, $routeParams, studentService, $log, localStorageService) {

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
                    console.log($scope.aluno);
                });
            }

            function _cancel() {
                document.location.href = '#/home';
            }

            function _update() { //chamara serviço de alterar aluno
                console.log($scope.aluno);
            }

        }]);