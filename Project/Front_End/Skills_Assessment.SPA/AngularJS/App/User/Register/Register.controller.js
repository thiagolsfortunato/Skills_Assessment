FatecControllers.controller('RegisterController',
    ['$scope', '$routeParams', 'localStorageService',
        function ($scope, $routeParams, localStorageService) {

            $scope.users = [ ];
            
            $scope.fatecs = [{ nome: "Jessen" }, { nome: "Jacarei" }, { nome: "Pindamonhangaba" }];
            
            $scope.cursos = [{ nome: "Aeronáutica" }, { nome: "Gestão Produção" }, { nome: "Logistica" }];

            $scope.loginState = true;
            $scope.Cadastrar = _cadastrar; //criar

            

            function _cadastrar(user) {
                if ($scope.user.senha1 != $scope.user.senha2) {
                    $scope.loginState = false;
                    console.log($scope.loginState);
                    console.log($scope.user.senha1 +" "+ $scope.user.senha2);

                } else {
                    $scope.loginState = true;
                    $scope.users.push(angular.copy(user));
                    console.log($scope.users);
                    delete $scope.user;
                    
                }
            }
/*
            function _login() {
                var loginUser = {
                    userName : $scope.loginEmail,
                    password: $scope.loginPassword
                };

                console.log(loginUser);


                authenticationService.Login(loginUser).then(function (data) {

                    if (data["type"] == "student")
                        document.location = "../#/question/" + data["unansweredQuestions"];

                    //document.location = "Administrator.html";

                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.

                    console.log(loginUser);
                    alert("We failed again!");
                    //document.location = "../#/question";
                    //$scope.loginState = false;
                });
            }
*/
            $scope.errorClass = function () {
                if ($scope.loginState == true)
                    return 'display-hide';
                else
                    return 'display-show';
            };

        }]);