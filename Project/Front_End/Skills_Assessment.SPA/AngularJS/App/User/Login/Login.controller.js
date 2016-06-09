FatecControllers.controller('LoginController',
    ['$scope', '$routeParams', 'AuthenticationService', 'localStorageService', 
        function ($scope, $routeParams, authenticationService, localStorageService) {

            $scope.Login = _login;
            
            $scope.loginEmail;
            $scope.loginPassword;
            $scope.loginState = true;

            $scope.btn_enter = function (keyEvent) {
                if (keyEvent.which === 13)
                    $scope.Login();
            }

            function _login() {


                var loginUser = {
                    userName : $scope.loginEmail,
                    password: $scope.loginPassword
                };

                console.log(loginUser);


                authenticationService.Login(loginUser).then(function (data) {
                    
                    
                    switch(data["type"].toLowerCase()) {
                        case "student":
                            //document.location = "../#/question/" + data["unansweredQuestions"];
                            document.location = "/fatec/Student.html";
                            break;
                        
                        case "administrador":

                            document.location = "/fatec/Administrator.html";
                            break;
                        
                        case "psicologa":
                            document.location = "/fatec/Psicologa.html";
                            break;
                        default:
                            document.location = "/fatec/Login.html";
                            break;
                    }
                        

                    //document.location = "Administrator.html";

                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.

                    console.log(loginUser);
                    alert("Falha ao Logar\n -Verifique seu login!");
                    //document.location = "../#/question";
                    //$scope.loginState = false;
                });
            }

            $scope.errorClass = function () {
                if ($scope.loginState == true)
                    return 'display-hide';
                else
                    return 'display-show';
            };

        }]);