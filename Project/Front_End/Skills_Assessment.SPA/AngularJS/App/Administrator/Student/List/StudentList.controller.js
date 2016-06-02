FatecControllers.controller('StudentListController',
    ['$scope', '$routeParams', 'StudentService', 'CourseService', '$log', 'localStorageService',
        function ($scope, $routeParams, studentService, courseService, $log, localStorageService) {

            //Declaração de funções
            $scope.goToEditPage = _goToEdit;
            $scope.studentsLoad = _studentsLoad;
            $scope.admin;
            $scope.alunos = [{ ra: 123456, nome: "Fulano de tal", curso: "Aeronautica" },
                             { ra: 789123, nome: "Cicrano Soares", curso: "Gestão da Produção" }];

            init();

            function init() {
                $scope.admin = localStorageService.get('user');
                //$scope.studentsLoad();
            }

            function _goToEdit (obj) {

                studentService.setStudentCurrent(obj);
                document.location.href = "#/student/edit";

            }

            function _studentsLoad() {

                var code = $scope.admin.instCode;

                studentService.studentsList(code).then(function (data) {

                    if (data == 600) {
                        alert('ops algum erro aqui..');
                    } else {
                        $scope.alunos = data;
                    }

                });

            }

            

        }]);