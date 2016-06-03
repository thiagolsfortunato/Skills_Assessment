FatecControllers.controller('ResultController',
    ['$scope', '$routeParams', '$log', 'StudentService', 'localStorageService',
        function ($scope, $routeParams, $log, studentService, localStorageService) {

            $scope.aluno = { name: 'Tom' };

            $scope.terminou = true;

            $scope.competencias = ['linda', 'comuna', 'feia', 'inteligencia', 'feminista'];
            $scope.pesos = [5, 6, 7, 3, 9];

            var radarData =
            {
                labels: $scope.competencias,
                datasets: [{
                    fillColor: "rgba(63,169,245,.1)",
                    strokeColor: "rgba(63,169,245,1)",
                    pointColor: "rgba(151,187,205,1)",
                    pointStrokeColor: "#fff",
                    data: $scope.pesos
                }]
            }
            var options = {
                segmentShowStroke: false,
                animateScale: true
            }

            var ctx2 = document.getElementById("radarChart").getContext("2d");
            var myNewChart = new Chart(ctx2).Radar(radarData);
            new Chart(ctx2).Radar(radarData, options);




        }]);