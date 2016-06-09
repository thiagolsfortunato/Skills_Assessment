FatecControllers.controller('EmployeeAddController',
    ['$scope', '$routeParams', 'EmployeeService','RegisterService', 'InstitutionService', 'localStorageService',
     function ($scope, $routeParams, employeeService, registerService, institutionService, localStorageService) {

         var getFatec = _loadFatec;

         $scope.employeeAdd = _employeeAdd;

         $scope.cancel = _cancel;

         // scope.user é a nova psicologa a ser inserida no banco
         $scope.user = {
            //instCode: $scope.adm.instCode,
            type: 'psicologa'
         };

         $scope.fatec;

         $scope.adm;        

         init();

         function init () {
            $scope.adm = localStorageService.get('user'); //pega administrador logado
            delete $scope.adm['token']; //remove atributo token do adm
            $scope.user.instCode = $scope.adm.instCode; //seta codigo da fatec na nova psicologa
            getFatec($scope.adm.instCode);
         }


         function _loadFatec (idFatec) {
            institutionService.institutionFindCode(idFatec).then(function (data) {
                $scope.fatec = data;
                console.log($scope.fatec);

            });
         }

         function _employeeAdd(user) {
            user.instCode = $scope.fatec.codeInstitution
         
            // courseAdd é o obj que chama a função da service
            if (user.password != user.passwordConfirm) {
                delete user["passwordConfirm"];
                alert("Senhas não conferem");
                return;
            } else {
                console.log(user);
                delete user["passwordConfirm"];
                employeeService.employeeAdd(user).then(function (data) {

                    alert("Cadastrado com sucesso!");
                    document.location.href = "#/psicologa/list";
                });
            }
         }

            
         function _cancel() {
            document.location.href = "#/psicologa/list";
         }

         

     }]);