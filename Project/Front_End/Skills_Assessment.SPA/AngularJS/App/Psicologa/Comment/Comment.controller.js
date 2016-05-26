FatecControllers.controller('CommentController',
    ['$scope', '$routeParams', 'PsicologaService', '$log',
        function ($scope, $routeParams, psicologaService, $log) {

            $scope.psicologa;

            $scope.aluno = 'zé';
            $scope.competencias = ['linda', 'comuna', 'feia','inteligencia', 'feminista'];
            $scope.pesos = [5, 60, 77, 33, 9];
            $scope.txt;

            $scope.sendComment = function (txt) {
                console.log(txt);
            }

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
            // FAZER
 /*           $scope.user = [];
            $scope.userDelete = _userDelete;

            init();

            function init() {
                userList();
            }

            function userList() {
                userService.userList().then(function (data) {
                    if (data.State == 0) {
                        $scope.user = data.Result;
                    }
                    else {
                        toastr.error(data.Message, "Error");
                    }
                });
            }

            function _userDelete(Id) {
                userService.userDelete(Id).then(function (data) {
                    if (data.State == 0) {
                        toastr.success(data.Message);
                        userList();
                    }
                    else {
                        toastr.error(data.Message, "Error");
                    }
                });
            }
    */
        }]);