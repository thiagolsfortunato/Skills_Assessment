FatecControllers.filter('cnpj', function () {
    return function (cnpj) {
        //return cpf.substr(0, 3) + '.' + cpf.substr(3, 3) + '.' + cpf.substr(6, 3) + '-' + cpf.substr(9, 2);
        if(cnpj != null){
            var novoCNPJ = cnpj.toString().replace(/[/\.-]/g, "");
            //alert(novoCNPJ);
            return novoCNPJ;
        } else {
            return;
        }
    };
});

FatecControllers.controller('RegisterInstitutionController',
    ['$scope', '$routeParams', 'localStorageService', 'RegisterService', 'CourseService',
        function ($scope, $routeParams, localStorageService, registerService, courseService) {

            //Declaração de funções
            $scope.fatecAdd = _fatecAdd;

            $scope.registerState = false;
            $scope.institution = {};
            $scope.user = {
                type: "Administrador",
            };

            $scope.errorClass = function () {

                if ($scope.user.password == $scope.user.password2) {
                    $scope.registerState = false;
                    
                   $scope.fatecAdd($scope.user, $scope.institution);
                  
                }

                else
                    $scope.registerState = true;
            };

            init();

            function init() {
                //validação se já está cadastrada.. implementar
            }

            function _fatecAdd(user, fatec) {
                // fatecAdd é o obj que chama a função da service
                delete user['password2'];
                console.log(user);
                console.log(fatec);
                
                registerService.fatecAdd(user, fatec).then(function (data) {

                    alert("Salvouuu");

                }); 
            }


        }]);