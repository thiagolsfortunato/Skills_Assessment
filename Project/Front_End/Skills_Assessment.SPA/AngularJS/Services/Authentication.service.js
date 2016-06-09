publication.factory('AuthenticationService', ['$http', '$q', 'localStorageService',
    function ($http, $q, localStorageService) {
        return {
            Login: _Login,
            Validation: _validation,
            Logout: _Logout,
        };


        function _Login(loginUser) {

            //var data = "grant_type=password&UserName=" + loginUser.Email + "&Password=" + loginUser.Password;
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: config.generateApiUrl('token'),
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
                data: JSON.stringify(loginUser)

            }).
               success(function (data, status, headers, config, response) {

                   //TODO: Deve pegar token do header, não do objeto.

                   localStorageService.set('authorizationData', { token: data.token });

                   var user = data;
                   delete user['token'];

                   localStorageService.set('user', user);

                   deferred.resolve(data);

                   

               }).then(function successCallback(response) {
                   // this callback will be called asynchronously
                   // when the response is available
               }, function errorCallback(response) {
                   // called asynchronously if an error occurs
                   // or server returns response with an error status.
                   deferred.reject("no authentication");
               });

            return deferred.promise;
        }

        function _Logout() {
            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: config.generateApiUrl('/Authenticate/Auth')
            }).
                success(function (data, status, headers, config) {
                    localStorageService.remove('authorizationData');

                    deferred.resolve(data);
                });

            return deferred.promise;
        }

        function _validation(type) {
            var identify = localStorageService.get('user'); //identifica o usuario logado

            if (identify == null) {
                document.location.href = '/fatec/Login.html'; //se não houver identificação redireciona para login
            }
            else if (identify.type.toLowerCase() != type) { //se não for aluno redireciona para login
                document.location.href = '/fatec/Login.html';
            } else {
                return identify; //se der tudo certo irá retornar os dados de quem está logado chamando a função especifica
            }
        }
    }]);