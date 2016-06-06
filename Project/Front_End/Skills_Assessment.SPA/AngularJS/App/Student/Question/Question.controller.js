FatecControllers.controller('QuestionController',
    ['$scope', '$routeParams', '$sce', 'QuestionService', '$log', 'localStorageService',
        function ($scope, $routeParams, $sce, questionService, $log, localStorageService) {

            $scope.question;

            var authData = localStorageService.get('authorizationData');
            var aluno = localStorageService.get('user');
            var percent;

            $scope.questionAmount = $routeParams.param1;
            $scope.currentAnwswerCode = -1;

            $scope.porcentagem;

            
            var midia = [/http/.test($scope.media)];
            
            $scope.isStreaming = midia;

            init();

            function init() {
                //passa o codigo do aluno como parametro, para buscar as questoes
                questionList(aluno.userCode);
                
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
                
                questionService.saveAnswer(quiz, aluno.userCode).then(function (data) {


                    questionList(aluno.userCode);


                    //toastr.error(data.Message, "Error");

                });

            }




            function questionList(alunoCode) {

                questionService.questionList(alunoCode).then(function (data) {

                    //console.log(data);

                    $scope.question = data;

                    var passo1 = $scope.question.unansweredQuestions / $scope.question.questionAmount;
                    var passo2 = passo1 * 100;
                    var passo3 = passo2 - 100;
                    var passo4 = passo3 * -1;
                    
                    $scope.porcentagem = Math.round(passo4 * 100) / 100;

                    percent = $scope.porcentagem;

                    if (percent == 100) {
                        questionService.generateResult().then(function (status) {
                            if (status == 200) {
                                console.log('calculo gerado com sucesso!');
                            }
                        });
                        alert('Obrigado vc concluiu com sucesso!');
                        document.location.href = '#/completed';
                    } else {
                        $scope.media = $scope.question.introduction.replace('watch?v=', 'embed/');

                        console.log($scope.media);
                    }


                    //toastr.error(data.Message, "Error");

                });
            }


        }]);