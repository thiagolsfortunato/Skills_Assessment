FatecControllers.controller('PsicologaEditController',
    ['$scope', '$routeParams', 'PsicologaService', '$log', 'localStorageService',
        function ($scope, $routeParams, psicologaService, $log, localStorageService) {

            //Declaração de funções
            
            $scope.psicologaUpdate = _psicologaUpdate;
            $scope.cancel = _cancel;
            $scope.admin;
            $scope.psicologa;

            init();

            function init() {
                $scope.admin = localStorageService.get('user');
                $scope.psicologa = psicologaService.getPsicologaCurrent();
            }

            function _cancel() {
                document.location.href = "#/psicologa/list";
            }

            function _psicologaUpdate(psicologa) {
                console.log(psicologa);
                /*    studentService.studentsUpdate(Student).then(function (data) {
    
                        if (data == 600) {
                            alert('ops algum erro aqui..');
                        } else {
                            alert('sucesso..');
                        }
                        
                    });
                    */
            }

            



        }]);