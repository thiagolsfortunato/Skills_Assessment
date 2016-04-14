FatecControllers.controller('QuestionController',
    ['$scope', '$routeParams', 'QuestionService', '$log', 'localStorageService',
        function ($scope, $routeParams, questionService, $log, localStorageService) {

            $scope.question;

            var authData = localStorageService.get('authorizationData');

            $scope.questionAmount = $routeParams.param1;


            init();

            function init() {

                questionList();
            }

            $scope.range = function () {
                return new Array(3);
            };

            function questionList() {

                questionService.questionList(authData.token).then(function (data) {

                    console.log(data);
                    $scope.question = data;

                    alert("Question controller result");

                    //toastr.error(data.Message, "Error");

                });
            }


        }]);