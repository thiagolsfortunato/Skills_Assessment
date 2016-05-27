publication.factory('RegisterService', ['$http', '$q', function ($http, $q) {

    return {

        userAdd: _userAdd,
        fatecList: _fatecList,
        fatecAdd: _fatecAdd,
        //userDelete: _userDelete,
        //userUpdate: _userUpdate
    };

    function _userAdd(dataObj) {

        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('enrolls'),
            data: JSON.stringify(dataObj),
            headers: { 'Content-Type' : 'application/json' }
            //headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            
        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

    function _fatecAdd(dataUser, dataFatec) {

        var deferred = $q.defer();
        
        var dataObj = { fatec: dataFatec, adm: dataUser };
        

        $http({
            method: 'POST',
            url: config.generateApiUrl('institution'),
            data: JSON.stringify(dataObj),
            headers: { 'Content-Type': 'application/json' }
            //headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }

        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

    function _fatecList() {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('institution/find/all'),
            headers: { 'Content-Type': 'application/json' }
            //headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }

        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }
/*
    function _userDelete(id) {

        var deferred = $q.defer();

        $http({
            method: 'DELETE',
            url: config.generateApiUrl('user'),
            params: { "codeUser": id }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _userUpdate(obj) {

        var deferred = $q.defer();

        $http({
            method: 'PUT',
            url: config.generateApiUrl('user'),
            header : {'Content-Type' : 'application/json; charset=UTF-8'},
            data: JSON.stringify(obj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }
                                                            */

}]);