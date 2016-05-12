FatecControllers.controller('EmployeeAddController',
    ['$scope', '$routeParams', 'EmployeeService','RegisterService',
     function ($scope, $routeParams, employeeService, registerService) {

         
            $scope.user = {};
            $scope.fatecs;

            function init () {
                $scope.fatecLists();
            }


            $scope.fatecLists  = function () {
                registerService.fatecList().then(function (data) {
                    $scope.fatecs = data;
                    console.log($scope.fatecs);

                });
            }
            
            init();
        }]);