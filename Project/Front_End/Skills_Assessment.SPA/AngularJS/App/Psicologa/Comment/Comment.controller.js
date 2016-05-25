FatecControllers.controller('CommentController',
    ['$scope', '$routeParams', 'PsicologaService', '$log',
        function ($scope, $routeParams, psicologaService, $log) {

            $scope.psicologa;

            var radarData =
            {
                labels: ['Linda', 'Feia', 'Comuna'],
                datasets: [{
                    fillColor: "rgba(63,169,245,.1)",
                    strokeColor: "rgba(63,169,245,1)",
                    pointColor: "rgba(151,187,205,1)",
                    pointStrokeColor: "#fff",
                    data: [0, 6000, 7780]
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