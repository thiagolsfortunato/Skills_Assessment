FatecControllers.controller('StudentHomeController',
    ['$scope', '$routeParams', '$log', 'localStorageService',
        function ($scope, $routeParams, $log, localStorageService) {


            $scope.aluno;

            $scope.logout = _logout;

            init();

            function init() {
                var identify = localStorageService.get('user');

                if (identify == null) {
                    document.location.href = '/Login.html';
                }
                else if (identify.type.toLowerCase() != 'student') {
                    document.location.href = '/Login.html';
                } else {
                    $scope.aluno = identify;
                }
            }

            function _logout() {
                localStorageService.clearAll();
            }


        }]);