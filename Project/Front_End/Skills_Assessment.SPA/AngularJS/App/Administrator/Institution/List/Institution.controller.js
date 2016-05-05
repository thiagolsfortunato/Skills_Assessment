FatecControllers.controller('InstitutionController',
    ['$scope', '$routeParams', 'InstitutionService', '$log', 'localStorageService',
        function ($scope, $routeParams, institutionService, $log, localStorageService) {

            //Declaração de funções
            $scope.institutionList = _institutionList;
            $scope.institutionAdd = _institutionAdd;
            $scope.institutionDelete = _institutionDelete;
            $scope.institutionLoad = _institutionLoad;
            $scope.institutionCancel = _institutionCancel;
            $scope.institutionUpdate = _institutionUpdate;

            $scope.btnLabel = "Adicionar";

            $scope.institutions;
            $scope.institution;


            init();

            function init() {

                $scope.institutionList();
            }

            function _institutionCancel() {

                $scope.btnLabel = "Adicionar";
                $scope.institution = null;
            }

            function _institutionLoad(obj) {

                $scope.institution = angular.copy(obj);;
                $scope.btnLabel = "Alterar";

            }

            function _institutionList() {

                institutionService.institutionList().then(function (data) {

                    console.log(data);

                    $scope.institutions = data;

                });

            }

            function _institutionUpdate(institution) {
                // courseupdate é o obj que chama a função da service
                console.log(institution);
                institutionService.institutionUpdate(institution).then(function (data) {

                    $scope.institutionList();
                    alert("Alterou");
                });

            }

            function _institutionAdd(institution) {
                // courseAdd é o obj que chama a função da service
                console.log(institution);
                institutionService.institutionSave(institution).then(function (data) {

                    $scope.institutionList();
                    alert("Salvouuu");

                });
            }

            function _institutionDelete(id) {

                console.log(id);
                institutionService.institutionDelete(id).then(function (data) {

                    $scope.institutionList();
                    alert("Deletouuu");

                });
            }

        }]);