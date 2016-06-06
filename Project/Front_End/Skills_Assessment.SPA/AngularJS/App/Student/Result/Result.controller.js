FatecControllers.controller('ResultController',
    ['$scope', '$routeParams', '$log', 'StudentService', 'localStorageService',
        function ($scope, $routeParams, $log, studentService, localStorageService) {

            $scope.aluno;
            
            $scope.comentario = '';
            $scope.finalizado = true;

            var LoadResult = _resultado;

            function init() {

                $scope.aluno = localStorageService.get('user');

                LoadResult($scope.aluno.userCode);

            }

            init();
                        

            function _resultado(userCode) {
                
                studentService.studentResult(userCode).then(function (data) {

                    var resultado = data;
                    //verifica se o objeto veio vazio, igual a -> {}
                    var finished = Object.keys(resultado).length !== 0;
                    console.log(resultado);

                    if (finished) {
                        console.log('aqui');
                        $scope.finalizado = true;
                        $scope.comentario = resultado.comments;

                        var competencias = [];
                        var pesos = [];
                        //insere as competencias e os respectivos pesos no gráfico
                        angular.forEach(resultado.competencies, function (value, key) {
                            competencias.push(value.type);
                            pesos.push(value.weight);

                        });
                        
                        _radarChart(competencias, pesos);
                        
                    } else {
                        $scope.finalizado = false;
                    }

                });

            }

            function _radarChart(competencias, pesos) {

                var radarData =
                    {
                        labels: competencias,
                        datasets: [{
                            fillColor: "rgba(63,169,245,.1)",
                            strokeColor: "rgba(63,169,245,1)",
                            pointColor: "rgba(151,187,205,1)",
                            pointStrokeColor: "#fff",
                            data: pesos
                        }]
                    }

                var options = {
                    segmentShowStroke: false,
                    animateScale: true
                }

                var ctx2 = document.getElementById("radarChart").getContext("2d");
                var myNewChart = new Chart(ctx2).Radar(radarData);
                new Chart(ctx2).Radar(radarData, options);

            }


        }]);