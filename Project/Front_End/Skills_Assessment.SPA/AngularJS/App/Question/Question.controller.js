FatecControllers.controller('QuestionController',
    ['$scope', '$routeParams', 'QuestionService', '$log',
        function ($scope, $routeParams, questionService, $log) {

            $scope.question;

            init();

            function init() {

                questionList();

            }

            function questionList() {

                questionService.questionList().then(function (data) {

                    alert("Question controller result");

                    toastr.error(data.Message, "Error");

                });
            }


        }]);