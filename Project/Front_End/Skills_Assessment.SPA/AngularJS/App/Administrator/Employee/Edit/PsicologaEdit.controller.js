FatecControllers.controller('PsicologaEditController',
    ['$scope', '$routeParams', 'PsicologaService', 'InstitutionService','$log', 'localStorageService',
        function ($scope, $routeParams, psicologaService, institutionService, $log, localStorageService) {

            //Declaração de funções
            
    		var institutionLoad = _institutionLoad;
            $scope.psicologaUpdate = _psicologaUpdate;
            $scope.cancel = _cancel;
            $scope.admin;
            $scope.psicologa;
            $scope.institution;
            

            init();

            function init() {
                $scope.admin = localStorageService.get('user');
                $scope.psicologa = psicologaService.getPsicologaCurrent();
                institutionLoad();
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

            function _institutionLoad() {

                var code = $scope.admin.instCode;

                institutionService.institutionFindCode(code).then(function (data) {
                	
                    if (data == null) {
                        alert('ops algum erro aqui..');
                    } else {
                        $scope.institution = data;
                    }

                });

            }



        }]);