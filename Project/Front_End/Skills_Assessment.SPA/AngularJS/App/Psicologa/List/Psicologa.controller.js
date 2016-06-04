FatecControllers.filter('zeros', function () {
    return function (id) {
        if (id == null) {
            return;
        } else {
            return id.toString().concat('00');
        }
        
    };
});

FatecControllers.controller('PsicologaController',
    ['$scope', '$routeParams', 'PsicologaService', 'StudentService', '$log', 'localStorageService', 'AuthenticationService',
        function ($scope, $routeParams, psicologaService, studentService, $log, localStorageService, authenticationService) {

            
            var LoadStudents = _loadStudents;

            $scope.psicologa;

            $scope.avaliar = _avaliar;

            $scope.logout = _logout;

            $scope.situacao = [{ code: 0, nome: 'andamento' },
                               { code: 1, nome: 'avaliar' },
                               { code: 2, nome: 'avaliado' }];

            $scope.alunos = [{ ra: 1234120, name: 'pedro', situacao: 0 },
                             { ra: 1234320, name: 'mariana', situacao: 1 },
                             { ra: 1234420, name: 'alan', situacao: 2 }];

            $scope.cursos = [{ nome: "Aeronáutica" }, { nome: "Gestão Produção" }, { nome: "Logistica" }, { nome: "Banco de Dados" }, { nome: "ADS" }];

            $scope._ano = [{ ano: 2016 }, { ano: 2015 }, { ano: 2014 }];
            $scope.semestres = [{ id: '1', desc: '1º Semestre' }, { id: '2', desc: '2º Semestre' }];

            //usado em filtros
            $scope.cursoSelected;
            $scope.anoSelected;
            $scope.semestreSelected;


            function Ctrl($scope) {
                // Can replace this with: ng-init="checkboxSelection = '1'".
                $scope.semestreSelected = 1;

                // Can use parseInt(x, 10) on $scope.checkboxSelection or index.toString() if you want to remove the single quotes you see in isCheckboxSelected('1').
                $scope.isCheckboxSelected = function (index) {
                    return index == $scope.semestreSelected;
                };
            }

            init();

            function init() {

                $scope.psicologa = authenticationService.Validation('psicologa');
                //LoadStudents();
            }

            function _loadStudents() {

                var fatecCode = $scope.psicologa.instCode;

                studentService.studentsLoadResults(fatecCode).then(function (data) {

                    $scope.alunos = data;

                    console.log($scope.alunos);

                });
            }

            function _avaliar(userCode) {
                //busca o aluno a ser avaliado e seta-o na service
                studentService.studentResult(fatecCode).then(function (data) {

                    studentService.setStudentCurrent(data);

                }); 
                document.location.href = '#/comment';
            }

            function _logout() {
                localStorageService.clearAll();
            }



        }]);