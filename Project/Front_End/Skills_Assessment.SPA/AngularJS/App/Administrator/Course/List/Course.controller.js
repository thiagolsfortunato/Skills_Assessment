FatecControllers.controller('CourseController',
    ['$scope', '$routeParams', 'CourseService', 'InstitutionService', '$log', 'localStorageService',
        function ($scope, $routeParams, courseService, institutionService, $log, localStorageService) {

            //Declaração de funções
            $scope.courseList = _courseList;
            $scope.courseAdd = _courseAdd;
            $scope.courseDelete = _courseDelete;
            $scope.courseLoad = _courseLoad;
            $scope.courseCancel = _courseCancel;
            $scope.courseUpdate = _courseUpdate;

            $scope.loadFatec = _loadFatec; //pega fatec cadastrada.

            $scope.btnLabel = "Adicionar";

            $scope.courses;
            $scope.course;

            $scope.fatec;

            var admin = localStorageService.get('user');
            
            init();

            function init() {

                $scope.courseList(admin.instCode);
                $scope.loadFatec(); //carrega fatec cadastrada
            }

            function _loadFatec() {
                institutionService.institutionList().then(function (data) {
                    $scope.fatec = data[0];
                });
            }

            function _courseCancel() {

                $scope.btnLabel = "Adicionar";
                $scope.course = null;
            }

            function _courseLoad(obj) {

                $scope.course = angular.copy(obj);;
                $scope.btnLabel = "Alterar";

            }

            function _courseList(fatecCode) {

                courseService.courseList(fatecCode).then(function (data) {

                    //console.log(data);

                    $scope.courses = data;

                });

            }

            function _courseUpdate(course) {
                // courseupdate é o obj que chama a função da service
                console.log(course);
                courseService.courseUpdate(course).then(function (data) {

                    $scope.courseList(admin.instCode);
                    alert("Alterou");
                });

            }

            //ARRUMAR - problema, quero saber id da fatec cadastrada no banco
            function _courseAdd(course) {
                // courseAdd é o obj que chama a função da service
                if (!$scope.fatec) {
                    alert('não há fatec cadastrada!');
                    return 0;
                };
                //course.codeInstitution = admin.instCode;     //pega o codigo da fatec cadastrada e seta no curso
                course.situation = 1;           //define situação ativa
                console.log(course);

                courseService.courseAdd(course, admin.instCode).then(function (data) {
                    if (data == 600) {
                        alert('verifique se há FATEC cadastrada!');
                    } else {
                        $scope.courseList(admin.instCode);
                        alert("Salvouuu");
                    }

                });

               
                
            }

            function _courseDelete(idCourse, idFatec) {
                
                console.log(idCourse, idFatec);
                courseService.courseDelete(idCourse, idFatec).then(function (data) {

                    $scope.courseList(admin.instCode);
                    alert("Deletouuu");

                });
            }

        }]);