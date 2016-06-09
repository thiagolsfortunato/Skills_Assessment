FatecControllers.controller('CommentController',
    ['$scope', '$routeParams', 'PsicologaService', 'StudentService', '$log',
        function ($scope, $routeParams, psicologaService, studentService, $log) {

            

            $scope.psicologa;

            $scope.aluno;
            var competencias = [];
            var pesos = [];
            $scope.txt;

            $scope.sendComment = function (txt) {

                console.log(txt);
                var codigoAluno = $scope.aluno.userCode;
                psicologaService.sendComment(txt, codigoAluno).then(function (data) {

                    alert('comentado com sucesso!');
                    document.location.href = "#/list";

                });
                
            }

            var LoadComp = _loadCompetencies;

            function init() {

                $scope.aluno = studentService.getStudentCurrent();

                LoadComp($scope.aluno.competencies);
                
            }

            init();

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
            
            function _loadCompetencies(obj) {

                angular.forEach(obj, function (value, key) {
                    competencias.push(value.type);
                    pesos.push(value.weight);
                    
                });

            }

            
              

        }]);