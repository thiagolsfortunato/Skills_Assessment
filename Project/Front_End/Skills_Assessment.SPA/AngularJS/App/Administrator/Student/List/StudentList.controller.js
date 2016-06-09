FatecControllers.controller('StudentListController',
    ['$scope', '$routeParams', 'StudentService', 'CourseService', '$log', 'localStorageService',
        function ($scope, $routeParams, studentService, courseService, $log, localStorageService) {

            //Declaração de funções
            var studentsLoad = _studentsLoad;

            $scope.goToEditPage = _goToEdit;
            $scope.deleteStudent = _deleteStudent;
            $scope.admin;
            $scope.alunos;

            init();

            function init() {
                $scope.admin = localStorageService.get('user');
                studentsLoad();
            }

            function _goToEdit (obj) {

                studentService.setStudentCurrent(obj);
                document.location.href = "#/student/edit";

            }

            function _studentsLoad() {

                var code = $scope.admin.instCode;

                studentService.studentsList(code).then(function (data) {
                    
                        $scope.alunos = data;
                        
                });

            }

            function _deleteStudent(aluno) {

                var code = aluno.user.userCode

                studentService.studentDelete(code).then(function (status) {
                    
                        console.log('excluído com sucesso!');

                });
                
            }
            
        }]);