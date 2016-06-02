FatecControllers.controller('StudentHomeController',
    ['$scope', '$routeParams', '$log', 'StudentService', 'localStorageService',
        function ($scope, $routeParams, $log, studentService, localStorageService) {

            var usuario;

            $scope.aluno = { nome: 'Cicrano' };

            var loadAluno = _loadAluno;

            $scope.logout = _logout;

            init();

            function init() {
                var identify = localStorageService.get('user'); //identifica o usuario logado

                if (identify == null) {
                    document.location.href = '/Login.html'; //se não houver identificação redireciona para login
                }
                else if (identify.type.toLowerCase() != 'student') { //se não for aluno redireciona para login
                    document.location.href = '/Login.html';
                } else {
                    //loadAluno(identify.userCode); //se der tudo certo irá carregar o aluno logado chamando a função especifica
                }
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