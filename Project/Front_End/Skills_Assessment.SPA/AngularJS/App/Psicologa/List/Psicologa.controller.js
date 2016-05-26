FatecControllers.controller('PsicologaController',
    ['$scope', '$routeParams', 'PsicologaService', '$log',
        function ($scope, $routeParams, psicologaService, $log) {

            $scope.psicologa = {
                "nome": "nome da psicoloca",
            };

            $scope.alunos = [
                { "id": 1, "nome": "pé de muleque", curso: "Aeronáutica", ano: "2014", "semestre": "1", "situacao": "andamento" },
                { "id": 2, "nome": "zé pequeno", curso: "Logistica", ano: "2016", "semestre": "1", "situacao": "concluido" },
                { "id": 3, "nome": "aluno 42", curso: "Gestão Produção", ano: "2016", "semestre": "2", "situacao": "parado" },
                { "id": 4, "nome": "Thiago F", curso: "Banco de Dados", ano: "2016", "semestre": "2", "situacao": "andamento" },
                { "id": 5, "nome": "Daniel W", curso: "ADS", ano: "2015", "semestre": "2", "situacao": "andamento" }
            ];

            $scope.cursos = [{ nome: "Aeronáutica" }, { nome: "Gestão Produção" }, { nome: "Logistica" }, { nome: "Banco de Dados" }, { nome: "ADS" }];

            $scope._ano = [{ ano: 2016 }, { ano: 2015 }, { ano: 2014 }];

            $scope.cursoSelected;
            $scope.anoSelected;
            $scope.semestreSelected;

            $scope.func = function () {
                console.log($scope.cursoSelected.nome);
                console.log($scope.anoSelected.ano);
                console.log($scope.semestreSelected);
            }

            function Ctrl($scope) {
                // Can replace this with: ng-init="checkboxSelection = '1'".
                $scope.semestreSelected = 1;

                // Can use parseInt(x, 10) on $scope.checkboxSelection or index.toString() if you want to remove the single quotes you see in isCheckboxSelected('1').
                $scope.isCheckboxSelected = function (index) {
                    return index == $scope.semestreSelected;
                };
            }

            // FAZER
 /*           $scope.user = [];
            $scope.userDelete = _userDelete;

            init();

            function init() {
                userList();
            }

            function userList() {
                userService.userList().then(function (data) {
                    if (data.State == 0) {
                        $scope.user = data.Result;
                    }
                    else {
                        toastr.error(data.Message, "Error");
                    }
                });
            }

            function _userDelete(Id) {
                userService.userDelete(Id).then(function (data) {
                    if (data.State == 0) {
                        toastr.success(data.Message);
                        userList();
                    }
                    else {
                        toastr.error(data.Message, "Error");
                    }
                });
            }
    */
        }]);