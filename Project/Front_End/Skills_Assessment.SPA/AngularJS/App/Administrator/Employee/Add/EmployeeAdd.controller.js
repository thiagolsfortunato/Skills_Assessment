FatecControllers.controller('EmployeeAddController',
    ['$scope', '$routeParams', 'EmployeeService','RegisterService',
     function ($scope, $routeParams, employeeService, registerService) {


            $scope.employeeAdd = _employeeAdd;
            $scope.user = {};
            $scope.fatecs;

            function init () {
                $scope.fatecLists();
            }


            $scope.fatecLists  = function () {
                registerService.fatecList().then(function (data) {
                    $scope.fatecs = data;
                    console.log($scope.fatecs);

                });
            }

            function _employeeAdd(user) {
                user.instCode = user.instCode.codeInstitution
                // courseAdd é o obj que chama a função da service
                if (user.password != user.passwordConfirm) {
                    delete user["passwordConfirm"];
                    alert("Senhas não conferem");
                    return;
                } else {
                    console.log(user);
                    delete user["passwordConfirm"];
                    employeeService.employeeAdd(user).then(function (data) {

                        alert("Salvouuu");
                    });
                }
            }

            init();
        }]);