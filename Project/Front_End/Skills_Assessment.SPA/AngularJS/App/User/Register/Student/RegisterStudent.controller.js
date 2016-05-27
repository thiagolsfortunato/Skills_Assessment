FatecControllers.controller('RegisterStudentController',
    ['$scope', '$routeParams', 'localStorageService', 'RegisterService', 'CourseService',
        function ($scope, $routeParams, localStorageService, registerService, courseService) {

            //Declaração de funções
            $scope.userAdd = _userAdd;
            $scope.fatecList = _fatecsList;
            $scope.courseList = _courseList;

            $scope.registerState = false;
            $scope.user = {};
            $scope.fatecs; // = [{ nome: "Jessen" }, { nome: "Jacarei" }, { nome: "Pindamonhangaba" }];
            $scope.cursos; // = [{ nome: "Aeronáutica" }, { nome: "Gestão Produção" }, { nome: "Logistica" }];

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
                $scope.courseList();
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

            function _courseList() {
                
                courseService.courseList().then(function (data) {

                    console.log(data);

                    $scope.cursos = data;

                });

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
           

        }]);