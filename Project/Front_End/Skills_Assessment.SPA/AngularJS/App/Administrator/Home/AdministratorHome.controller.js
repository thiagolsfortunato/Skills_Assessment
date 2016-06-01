FatecControllers.controller('AdministratorHomeController',
    ['$scope', '$routeParams', '$log', 'localStorageService',
        function ($scope, $routeParams, $log, localStorageService) {


            $scope.admistrador;

            $scope.logout = _logout;

            init();

            function init() {
                var identify = localStorageService.get('user');

                if (identify == null) {
                    document.location.href = '/Login.html';
                }
                else if (identify.type.toLowerCase() != 'administrador') {
                    document.location.href = '/Login.html';
                } else {
                    $scope.admistrador = identify;
                }
            }

            function _logout() {
                localStorageService.clearAll();
            }



        }]);