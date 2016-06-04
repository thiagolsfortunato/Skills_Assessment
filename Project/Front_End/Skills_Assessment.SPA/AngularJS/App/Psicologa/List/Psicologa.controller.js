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
    ['$scope', '$routeParams', 'PsicologaService', 'StudentService', '$log', 'localStorageService',
        function ($scope, $routeParams, psicologaService, studentService, $log, localStorageService) {

            var Login = _login;
            var LoadStudents = _loadStudents;

            $scope.psicologa = { "nome": "nome da psicoloca" };

            $scope.logout = _logout;

            $scope.alunos;

            $scope.cursos = [{ nome: "Aeronáutica" }, { nome: "Gestão Produção" }, { nome: "Logistica" }, { nome: "Banco de Dados" }, { nome: "ADS" }];

            $scope._ano = [{ ano: 2016 }, { ano: 2015 }, { ano: 2014 }];
            $scope.semestres = [{ id: '1', desc: '1º Semestre' }, { id: '2', desc: '2º Semestre' }];

            //usado em filtros
            $scope.cursoSelected;
            $scope.anoSelected;
            $scope.semestreSelected;

            $scope.func = function () {
                console.log($scope.cursoSelected.nome);
                console.log($scope.anoSelected.ano);
                console.log($scope.semestreSelected.id);
            }

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
                Login();
                LoadStudents();
            }

            function _loadStudents() {

                var fatecCode = $scope.psicologa.instCode;

                studentService.studentsLoadResults(fatecCode).then(function (data) {

                    $scope.alunos = data;

                    console.log($scope.alunos);

                });
            }

            function _login() {
                var identify = localStorageService.get('user');

                if (identify == null) {
                    document.location.href = '/Login.html';
                }
                else if (identify.type.toLowerCase() != 'psicologa') {
                    document.location.href = '/Login.html';
                } else {
                    $scope.psicologa = identify;
                }
            }

            function _logout() {
                localStorageService.clearAll();
            }



        }]);