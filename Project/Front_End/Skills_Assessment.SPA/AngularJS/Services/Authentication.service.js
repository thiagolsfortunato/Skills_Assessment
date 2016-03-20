publication.factory('AuthenticationService', ['$http', '$q', 'localStorageService',
    function ($http, $q, localStorageService) {
        return {
            Login: _Login,
            Logout: _Logout
        };

        function _Login(loginUser) {

            //var data = "grant_type=password&UserName=" + loginUser.Email + "&Password=" + loginUser.Password;
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: config.generateApiUrl('token'),
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
                data: '{email: "edu", password:"1234"}' /*JSON.stringify(loginUser)*/

            }).
               success(function (data, status, headers, config, response) {

                   localStorageService.set('authorizationData', { token: headers()['token'] });

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

            $http({ method: 'GET', url: config.generateApiUrl('/Authenticate/Auth') }).
                success(function (data, status, headers, config) {
                    localStorageService.remove('authorizationData');

                    deferred.resolve(data);
                });

            return deferred.promise;
        }
    }]);