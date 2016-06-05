FatecControllers.controller('QuestionMenuController',
    ['$scope', '$routeParams', 'QuestionService', '$interval',
            function ($scope, $routeParams, questionMenuService, $interval) {

                $scope.questions = [];

                init();

                function init() {

                    getAllQuestions();

                }

                $scope.goToEditPage = function (obj) {

                    questionMenuService.currentQuestion = obj;
                    document.location.href = "#/question/edit";

                }

                $scope.questionDelete = function (code) {

                    questionMenuService.questionDelete(code).then(function (data) {

                        getAllQuestions();
                        alert("Deletouu, [QuestionMenu.controller]");

                    });

                

                }

                function getAllQuestions() {

                    questionMenuService.questionGetAll().then(function (data) {

                        $scope.questions = data;

                        console.log(data);

                    });

                }

            }]);