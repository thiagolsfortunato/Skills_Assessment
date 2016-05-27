FatecControllers.controller('StudentHomeController',
    ['$scope', '$routeParams', '$log', 'localStorageService',
        function ($scope, $routeParams, $log, localStorageService) {


            $scope.aluno;


            init();

            function init() {
                if (localStorageService.get('user').type != 'Student') {
                    document.location.href = '/Login.html';
                } else {
                    $scope.aluno = localStorageService.get('user');
                    console.log($scope.aluno);
                }
            }



        }]);