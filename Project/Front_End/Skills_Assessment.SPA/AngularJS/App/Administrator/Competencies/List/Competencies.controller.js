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

                    if (String(data) != "\"FAIL\"") {
                        $scope.competencies = data;
                    } else {
                       
                        $scope.competencies = null;
                    }                    

                });
            }

            function _competencieUpdate(competence) {
                // competencieupdate é o obj que chama a função da service
                
                competenciesService.competencieUpdate(competence).then(function (status) {

                    if (status == 200) {
                        $scope.competenciesList();
                        alert("Alterado com sucesso");
                    } else {
                        alert(':/ Ops! Problema ao salvar.');
                    }                  
                    
                });
            }

            function _competencieAdd(competence) {
                // competencieAdd é o obj que chama a função da service
                competenciesService.competencieAdd(competence).then(function (status) {
                    
                    if (status == 200) {
                        $scope.competenciesList();
                        alert("Salvo com sucesso.");
                    } else {
                        alert(':/ Ops! Problema ao salvar.');
                    }

                    
                                      

                });
            }

            function _competencieDelete(id) {
                // competencieAdd é o obj que chama a função da service
                console.log(id);
                competenciesService.competencieDelete(id).then(function (status) {

                    if (status != 200) {
                        alert(':/ Ops! Deve haver questões ligadas a esta competência');
                    } else {
                        $scope.competenciesList();
                        alert("Deletado com sucesso.");
                    }
                    
                });
            }



        }]);