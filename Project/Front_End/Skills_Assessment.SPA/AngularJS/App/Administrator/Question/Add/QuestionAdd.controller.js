FatecControllers.controller('QuestionAddController',
    ['$scope', '$routeParams', '$interval', 'CompetenciesService', 'QuestionService',
    function ($scope, $routeParams, $interval, competenciesService, questionService) {      


                $scope.question = {};
                $scope.competencies = [];

                $scope.question.answers = new Array(4);


                $scope.competenciesInitial = [];
                $scope.competenciesList = _competenciesList;



                $scope.currentAnswer = {};


                init();

                function init() {

                    $scope.competenciesList();

                }

                $scope.getNumber = function (num) {
                    return new Array(num);
                }

                $scope.saveQuestion = function (obj) {

                    questionService.questionAdd(obj).then(function (data) {

                        alert("We areeee the champiom my frieeeend");

                    });

                }

                function _competenciesList() {

                    competenciesService.competenciesList().then(function (data) {

                        //$scope.competencies = data;
                        //$scope.competenciesInitial = angular.copy($scope.competencies);

                        for (var i = 0; i < $scope.question.answers.length; i++) {

                            $scope.question.answers[i] = {};
                            $scope.question.answers[i].competencies = data;
                        }


                        console.log($scope.question);


                    });
                }

                $scope.saveCompetence = function () {


                    $scope.question.answers[$scope.index].competencies = angular.copy($scope.currentAnswer.competencies);
                    $scope.currentAnswer.competencies = [];
                }

                $scope.setCurrentAnswer = function (index) {

                    $scope.currentAnswer.competencies = angular.copy($scope.question.answers[index].competencies);
                    $scope.index = index;
                }

              

            }]);