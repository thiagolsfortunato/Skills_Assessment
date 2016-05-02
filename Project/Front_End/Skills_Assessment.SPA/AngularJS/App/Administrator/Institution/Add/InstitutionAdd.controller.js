FatecControllers.controller('InstitutionAddController',
    ['$scope', '$routeParams', '$interval', 'InstitutionService',
            function ($scope, $routeParams, $interval, registerService) {


                $scope.institution = {};
                $scope.institutionSave = _institutionSave;

                function _institutionSave(institution) {
                    console.log(institution);
                    registerService.institutionSave(institution);
                }


            }]);