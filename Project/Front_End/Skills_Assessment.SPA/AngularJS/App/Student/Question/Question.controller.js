FatecControllers.controller('QuestionController',
    ['$scope', '$routeParams', '$sce', 'QuestionService', '$log', 'localStorageService',
        function ($scope, $routeParams, $sce, questionService, $log, localStorageService) {

            $scope.question;

            var authData = localStorageService.get('authorizationData');

            $scope.questionAmount = $routeParams.param1;
            $scope.currentAnwswerCode = -1;


            init();

            function init() {

                questionList();
            }


            $scope.trustSrc = function (src) {
                return $sce.trustAsResourceUrl(src);
            }

            $scope.changeAnwser = function (code) {

                $scope.currentAnwswerCode = code;
            }

            $scope.saveAnswer = function () {
                
                if($scope.currentAnwswerCode <= 0)
                {
                    alert("se liga brow, responde essa po#%&!!!");
                }


                var quiz = {

                    answer: $scope.currentAnwswerCode,
                    question: $scope.question.code

                };

                questionService.saveAnswer(quiz).then(function (data) {


                    questionList();


                    //toastr.error(data.Message, "Error");

                });

            }




            function questionList() {

                questionService.questionList().then(function (data) {

                    console.log(data);

                    $scope.question = data;

                    $scope.media = $scope.question.introduction;


                    var passo1 = $scope.question.unansweredQuestions / $scope.question.questionAmount;
                    var passo2 = passo1 * 100;
                    var passo3 = passo2 - 100;
                    var passo4 = passo3 * -1;
                    
                    $scope.porcentagem = Math.round(passo4 * 100) / 100;


                    //toastr.error(data.Message, "Error");

                });
            }


        }]);