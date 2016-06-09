FatecControllers.controller('PsicologoListController',
    ['$scope', '$routeParams', 'PsicologaService', 'localStorageService', '$interval',
            function ($scope, $routeParams, psicologaService, localStorageService, $interval) {

                $scope.goToEditPage = _goToEdit;
                $scope.psicologaDelete = _delete;
                
                $scope.admin;
                $scope.psicologas;// = [{ id: 001, nome: 'Vanessa', email: 'vanessa@fatec.com', password: 'vanessa', fatec: 'Jessen Vidal' },
                                    // { id: 002, nome: 'Amanda', email: 'amanda@fatec.com', password: 'amanda', fatec: 'Jessen Vidal' }];

                var loadPsicologas = _loadPsicologas;

                init();

                function init() {
                    $scope.admin = localStorageService.get('user');
                    loadPsicologas($scope.admin.instCode);

                }

                function _goToEdit(obj) {

                    psicologaService.setPsicologaCurrent(obj);
                    document.location.href = "#/psicologa/edit";

                }

                function _delete (code) {

                    psicologaService.deletePsicologa(code).then(function (data) {

                        alert("Deletada...");

                    });



                }

                function _loadPsicologas(fatecCode) {

                    psicologaService.getAllPsicologas(fatecCode).then(function (data) {

                        $scope.psicologas = data;

                        console.log($scope.psicologas);

                    });

                }

            }]);