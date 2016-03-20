FatecControllers.controller('LoginController',
    ['$scope', '$routeParams', 'AuthenticationService', 'localStorageService',
        function ($scope, $routeParams, authenticationService, localStorageService) {
            $scope.Login = _login;
            
            $scope.loginEmail;
            $scope.loginPassword;
            $scope.loginState = true;

            function _login() {
                var loginUser = {
                    email: $scope.loginEmail,
                    password: $scope.loginPassword
                };

                authenticationService.Login(loginUser).then(function (data) {

                    if (data["kindperson"] != "student")
                        document.location = "../#/question";

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

            $scope.errorClass = function () {
                if ($scope.loginState == true)
                    return 'display-hide';
                else
                    return 'display-show';
            };

        }]);