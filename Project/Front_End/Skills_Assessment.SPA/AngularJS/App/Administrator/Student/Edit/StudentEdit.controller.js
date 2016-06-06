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
                
                $scope.admin = localStorageService.get('user');
                $scope.loadCourses($scope.admin.instCode);
                $scope.aluno = studentService.getStudentCurrent();
            }

            function _cancel() {
                document.location.href = "#/student/list";
            }

            function _studentUpdate(Student) {
                var codeCourse = Student.curso.codeCourse;
                
                var user = Student.user;
                delete Student['curso'];
                delete Student['user'];
                angular.forEach(user, function (value, key) {
                    Student[key] = value;

                });
                Student['codeCourse'] = codeCourse;
                console.log(Student);

                studentService.studentUpdate(Student).then(function (status) {
                    
                    if (status == 600) {
                        alert('ops algum erro aqui..');
                    } else {
                        alert('sucesso..');
                    }
                    
                });
                
            }

            function _loadCourses(fatecCode) {

                courseService.courseList(fatecCode).then(function (data) {

                    $scope.cursos = data;

                });

            }



        }]);