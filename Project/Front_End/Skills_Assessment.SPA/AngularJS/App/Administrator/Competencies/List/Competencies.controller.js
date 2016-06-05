FatecControllers.controller('CompetenciesController',
    ['$scope', '$routeParams', 'CompetenciesService', '$log', 'localStorageService',
        function ($scope, $routeParams, competenciesService, $log, localStorageService) {

            //Declaração de funções
            $scope.competenciesList = _competenciesList;
            $scope.competencieAdd = _competencieAdd;
            $scope.competencieDelete = _competencieDelete;
            $scope.competencieLoad = _competencieLoad;
            $scope.competencieCancel = _competencieCancel;
            $scope.competencieUpdate = _competencieUpdate;

            $scope.competence;
            $scope.btnLabel = "Adicionar";

            $scope.competencies;


            init();

            function init() {

                $scope.competenciesList();
            }

            function _competencieCancel() {

                $scope.btnLabel = "Adicionar";
                $scope.competence = null;
            }

            function _competencieLoad(obj) {

                $scope.competence = angular.copy(obj);;
                $scope.btnLabel = "Alterar";

            }

            function _competenciesList() {

                competenciesService.competenciesList().then(function (data) {

                    if (data == null) {
                        alert('ops!, parece que não há nenhuma competência cadastrada. \n'
                            + '- Tente veificar a conexão com servidor. ');
                    } else {
                        $scope.competencies = data;
                        console.log(data);
                    }

                });
            }

            function _competencieUpdate(competence) {
                // competencieupdate é o obj que chama a função da service
                console.log(competence);
                competenciesService.competencieUpdate(competence).then(function (data) {

                    if (status != 200) {
                        alert('ops!, contate seu prestador e informe que há algum erro no update. \n'
                            + '- Tente veificar a conexão com servidor. ');
                    } else {
                        $scope.competenciesList();
                        alert("Alterou");
                    }
                    
                });
            }

            function _competencieAdd(competence) {
                // competencieAdd é o obj que chama a função da service
                console.log(competence);
                competenciesService.competencieAdd(competence).then(function (data) {
                    
                    if (status != 200) {
                        alert('ops!, contate seu prestador e informe que há algum erro na inserção. \n'
                            + '- Tente veificar a conexão com servidor. ');
                    } else {
                        $scope.competenciesList();
                        alert("Salvouuu");
                    }                    

                });
            }

            function _competencieDelete(id) {
                // competencieAdd é o obj que chama a função da service
                console.log(id);
                competenciesService.competencieDelete(id).then(function (status) {

                    if (status != 200) {
                        alert('ops!, verifique se não há questões ligadas a esta competencia. \n'
                            + '- Tente veificar a conexão com servidor. ');
                    } else {
                        $scope.competenciesList();
                        alert("Deletouuu");
                    }

                });
            }



        }]);