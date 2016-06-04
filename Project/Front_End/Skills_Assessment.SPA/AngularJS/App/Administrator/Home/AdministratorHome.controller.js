FatecControllers.controller('AdministratorHomeController',
    ['$scope', '$routeParams', '$log', 'localStorageService', 'AuthenticationService',
        function ($scope, $routeParams, $log, localStorageService, authenticationService) {


            $scope.admistrador;

            $scope.logout = _logout;

            init();

            function init() {

                $scope.admistrador = authenticationService.Validation('administrador');

            }

            function _logout() {
                localStorageService.clearAll();
            }



        }]);