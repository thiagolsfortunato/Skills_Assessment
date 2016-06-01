FatecControllers.controller('ProfileController',
    ['$scope', '$routeParams', 'ProfileService', 'InstitutionService', 'CourseService', '$log', 'localStorageService',
        function ($scope, $routeParams, profileService, institutionService, courseService, $log, localStorageService) {

            $scope.aluno;
            $scope.fatec;
            $scope.curso;

            $scope.getFatec = _getFatec;
            $scope.getCurso = _getCurso;

            init();

            function init() {
                $scope.aluno = localStorageService.get('user');
                $scope.getFatec($scope.aluno.instCode);
                $scope.getCurso(/*CODIGO*/);
                console.log($scope.aluno);
            }

            function _getFatec(idFatec) {
                institutionService.institutionFindCode(idFatec).then(function (data) {
                    $scope.fatec = data;

                });
            }

            function _getCurso(idCurso) {
                courseService.courseFindId(idCurso).then(function (data) {
                    $scope.curso = data;

                });
            }
            //EDITAR CONTEUDO


        }]);