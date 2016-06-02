FatecControllers.controller('StudentEditController',
    ['$scope', '$routeParams', 'StudentService', 'CourseService', '$log', 'localStorageService',
        function ($scope, $routeParams, studentService, courseService, $log, localStorageService) {

            //Declaração de funções
            $scope.loadCourses = _loadCourses;
            $scope.studentUpdate = _studentUpdate;
            $scope.cancel = _cancel;
            $scope.admin;
            $scope.aluno;
            $scope.cursos;


            init();

            function init() {
                $scope.loadCourses();
                $scope.admin = localStorageService.get('user');
                $scope.aluno = studentService.getStudentCurrent();
            }

            function _cancel() {
                document.location.href = "#/student/list";
            }

            function _studentUpdate(Student) {
                console.log($scope.cursos);
            /*    studentService.studentsUpdate(Student).then(function (data) {

                    if (data == 600) {
                        alert('ops algum erro aqui..');
                    } else {
                        alert('sucesso..');
                    }
                    
                });
                */
            }

            function _loadCourses() {

                courseService.courseList().then(function (data) {

                    $scope.cursos = data;

                });

            }



        }]);