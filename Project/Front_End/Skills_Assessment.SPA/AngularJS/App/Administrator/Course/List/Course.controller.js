FatecControllers.controller('CourseController',
    ['$scope', '$routeParams', 'CourseService', '$log', 'localStorageService',
        function ($scope, $routeParams, courseService, $log, localStorageService) {

            //Declaração de funções
            $scope.courseList = _courseList;
            $scope.courseAdd = _courseAdd;
            $scope.courseDelete = _courseDelete;
            $scope.courseLoad = _courseLoad;
            $scope.courseCancel = _courseCancel;
            $scope.courseUpdate = _courseUpdate;

            $scope.btnLabel = "Adicionar";

            $scope.courses;
            $scope.course;


            init();

            function init() {

                $scope.courseList();
            }

            function _courseCancel() {

                $scope.btnLabel = "Adicionar";
                $scope.course = null;
            }

            function _courseLoad(obj) {

                $scope.course = angular.copy(obj);;
                $scope.btnLabel = "Alterar";

            }

            function _courseList() {

                courseService.courseList().then(function (data) {

                    console.log(data);

                    $scope.courses = data;

                });

            }

            function _courseUpdate(course) {
                // courseupdate é o obj que chama a função da service
                console.log(course);
                courseService.courseUpdate(course).then(function (data) {

                    $scope.courseList();
                    alert("Alterou");
                });

            }

            function _courseAdd(course) {
                // courseAdd é o obj que chama a função da service
                console.log(course);
                courseService.courseAdd(course).then(function (data) {
                    
                    $scope.courseList();
                    alert("Salvouuu");

                });
            }

            function _courseDelete(id) {
                
                console.log(id);
                courseService.courseDelete(id).then(function (data) {

                    $scope.courseList();
                    alert("Deletouuu");

                });
            }

        }]);