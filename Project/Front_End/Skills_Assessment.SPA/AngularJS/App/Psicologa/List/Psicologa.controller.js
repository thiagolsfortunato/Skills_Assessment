FatecControllers.filter('zeros', function () {
    return function (id) {
        if (id == null) {
            return;
        } else {
            return id.toString().concat('000');
        }
        
    };
});

FatecControllers.controller('PsicologaController',
    ['$scope', '$routeParams', 'PsicologaService', 'StudentService', 'CourseService', '$log', '$location', 'localStorageService', 'AuthenticationService',
        function ($scope, $routeParams, psicologaService, studentService, courseService, $log, $location, localStorageService, authenticationService) {

            
            var LoadStudents = _loadStudents;
            var LoadCourses = _loadCourses;

            $scope.psicologa;

            $scope.avaliar = _avaliar;

            $scope.logout = _logout;

            $scope.situacao = [{ code: 0, nome: 'andamento' },
                               { code: 1, nome: 'avaliar' },
                               { code: 2, nome: 'avaliado' }];

            $scope.alunos;

            $scope.cursos;// = [{ nome: "Aeronautica" }, { nome: "Gestão Produção" }, { nome: "Logistica" }, { nome: "Banco de Dados" }, { nome: "ADS" }];

            $scope._ano = [{ ano: 2016 }, { ano: 2015 }, { ano: 2014 }];
            $scope.semestres = [{ id: '1', desc: '1º Semestre' }, { id: '2', desc: '2º Semestre' }];

            //usado em filtros
            $scope.cursoSelected;
            $scope.anoSelected;
            $scope.semestreSelected;



            init();

            function init() {

                $scope.psicologa = authenticationService.Validation('psicologa');
                LoadCourses();
                LoadStudents();
                
            }

            function _loadStudents() {

                var fatecCode = $scope.psicologa.instCode;

                studentService.studentsList(fatecCode).then(function (data) {

                    $scope.alunos = data;

                    //forçado fazer isto pois o filtro não funciona com menos de 4 casas este caso..
                    angular.forEach(data, function (value, key) {
                        $scope.alunos[key].period = value.period * 1000;
                    });

                });
            }

            function _loadCourses(fatecCode) {

                var fatecCode = $scope.psicologa.instCode;

                courseService.courseList(fatecCode).then(function (data) {

                    $scope.cursos = data;
                    
                });

            }

            function _avaliar(userCode) {
                //busca o aluno a ser avaliado e seta-o na service
                studentService.studentResult(userCode).then(function (data) {

                    studentService.setStudentCurrent(data);
                    $location.path("/comment");
                });
                
                //document.location.href = '#/comment';
            }

            function _logout() {
                localStorageService.clearAll();
            }



        }]);