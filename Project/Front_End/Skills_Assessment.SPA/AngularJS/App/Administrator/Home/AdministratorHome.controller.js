FatecControllers.controller('AdministratorHomeController',
    ['$scope', '$routeParams', '$log', 'localStorageService',
        function ($scope, $routeParams,  $log, localStorageService) {

            
            $scope.admistrador;
   

            init();

            function init() {
                if (localStorageService.get('user').type != 'Administrador') {
                    document.location.href = '/Login.html';
                } else {
                    $scope.admistrador = localStorageService.get('user');
                }
            }

            

        }]);