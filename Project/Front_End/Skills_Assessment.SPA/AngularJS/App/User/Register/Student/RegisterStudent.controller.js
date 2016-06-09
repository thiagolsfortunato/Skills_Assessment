FatecControllers.controller('RegisterStudentController',
    ['$scope', '$routeParams', 'localStorageService', 'RegisterService',
        function ($scope, $routeParams, localStorageService, registerService) {

            //Declaração de funções
            var userAdd = _userAdd;
            $scope.fatecList = _fatecsList;

            $scope.registerState = false;
            $scope.user = {};
            $scope.fatecs; 
            $scope.cursos; 

            $scope.fatecSelecionada;
			$scope.cursoSelecionado;

            $scope.errorClass = function () {
                
                if ($scope.user.password == $scope.user.password2){
                    $scope.registerState = false;
                    userAdd($scope.user);
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
                //preparo o JSON para o back-end
                user.type = "Student";
                user.codeCourse = $scope.cursoSelecionado.codeCourse;
                user.instCode = $scope.fatecSelecionada.code;
                delete user["password2"];
                console.log(user);
                
                /*** aqui ocorre uma validação, verificando se o aluno ja existe!	**/
                registerService.validate(user).then(function (data) {

                    if(data == 'true'){
                    	alert('RA ou EMAIL já cadastrado \n -se perdeu a senha entre em contato com administrador de sua FATEC!');
                    }else{
                    	
                    	registerService.userAdd(user).then(function (data) {

                            alert("Cadastrado com sucesso, clique em ENTRAR");

                        });
                    }

                });
               
            }

            function _fatecsList() {
                
                registerService.fatecList().then(function (data) {

                    console.log(data);
                    
                    $scope.fatecs = data;

                }); 

            }


        }]);