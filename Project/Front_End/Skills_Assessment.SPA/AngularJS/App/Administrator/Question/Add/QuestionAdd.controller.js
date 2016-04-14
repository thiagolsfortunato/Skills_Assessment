FatecControllers.controller('QuestionAddController',
    ['$scope', '$routeParams', '$interval',
            function ($scope, $routeParams, $interval) {


                $scope.question = {};
                $scope.question.answers = [];


                $scope.currentAnswer = {};


                init();

                function init() {

                }

                $scope.setCurrentAnswer = function (obj, label) {

                    $scope.currentAnswer = obj;
                    $scope.alternativa = label;
                }

            }]);