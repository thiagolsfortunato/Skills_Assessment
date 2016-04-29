FatecControllers.controller('QuestionMenuController',
    ['$scope', '$routeParams', 'QuestionService', '$interval',
            function ($scope, $routeParams, questionMenuService, $interval) {

                $scope.questions = [];



                init();

                function init() {

                    getAllQuestions();

                }


                function getAllQuestions() {

                    questionMenuService.questionGetAll().then(function (data) {

                        $scope.questions = data;

                        console.log(data);

                    });

                }

            }]);