FatecControllers.controller('MyFatecController',
    ['$scope', '$routeParams', 'InstitutionService', '$log', 'localStorageService',
        function ($scope, $routeParams, institutionService, $log, localStorageService) {

            //Declaração de funções
            $scope.institutionLoad = _institutionLoad;
            $scope.institutionUpdate = _institutionUpdate;

            $scope.btnLabel = "Alterar";

            $scope.institution;
            $scope.admin;

            init();

            function init() {
                $scope.admin = localStorageService.get('user');
                $scope.institutionLoad();
            }

            

            function _institutionLoad() {

                var code = $scope.admin.instCode;

                institutionService.institutionFindCode(code).then(function (data) {
                    
                    if (data == 'null') {
                        alert(':/ Ops! Algum problema ao procurar seus dados.');
                    } else {
                        $scope.institution = data;
                    }

                });

            }

            function _institutionUpdate(institution) {
                // courseupdate é o obj que chama a função da service
                console.log(institution);
                institutionService.institutionUpdate(institution).then(function (status) {

                    $scope.institutionLoad();
                    alert("Alterado com sucesso!");
                });

            }

            

        }]);