FatecControllers.controller('RegisterStudentController',
    ['$scope', '$routeParams', 'localStorageService', 'RegisterService',
        function ($scope, $routeParams, localStorageService, registerService) {

            //Declaração de funções
            $scope.userAdd = _userAdd;
            $scope.fatecList = _fatecsList;

            $scope.registerState = false;
            $scope.user = {};
            $scope.fatecs; // = [{ nome: "Jessen" }, { nome: "Jacarei" }, { nome: "Pindamonhangaba" }];
            $scope.cursos; // = [{ nome: "Aeronáutica" }, { nome: "Gestão Produção" }, { nome: "Logistica" }];

            $scope.fatecSelecionada;

            $scope.errorClass = function () {
                
                if ($scope.user.password == $scope.user.password2){
                    $scope.registerState = false;
                    $scope.userAdd($scope.user);
                }
                    
                else
                    $scope.registerState = true;
            };

            init();

            function init() {

                $scope.fatecList();
            }

            function _userAdd(user) {
                // userAdd é o obj que chama a função da service
                
                user.type = "Student";
                user.codeCourse = user.curso.codeCourse;
                user.instCode = user.fatec.codeInstitution;
                delete user["password2"];
                delete user["fatec"];
                delete user["curso"];
                console.log(user);
                registerService.userAdd(user).then(function (data) {

                    alert("Salvouuu");

                });
            }

            function _fatecsList() {
                
                registerService.fatecList().then(function (data) {

                    console.log(data);
                    
                    $scope.fatecs = data;

                }); 

            }


        }]);