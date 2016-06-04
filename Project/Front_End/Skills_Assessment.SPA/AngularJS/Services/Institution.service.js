publication.factory('InstitutionService', ['$http', '$q', function ($http, $q) {

    return {

        institutionSave: _institutionSave,
        institutionList: _institutionsList,
        institutionDelete: _institutionDelete,
        institutionUpdate: _institutionUpdate,
        institutionFindCode: _institutionFindCode

    };

    function _institutionFindCode(code) {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('institution/find/code'),
            params: { code: code }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           }).
            error(function (data, status, headers, config) {
                deferred.resolve(status);
            });

        return deferred.promise;
    }

    function _institutionSave(obj) {

        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('institution/'),
            data: JSON.stringify(obj),
            headers: { 'content-type': 'application/json' }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }


    function _institutionsList() {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('institution/find/all'),
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           }).
            error(function (data, status, headers, config) {
                deferred.resolve(status);
            });

        return deferred.promise;
    }

    function _institutionDelete(id) {

        var deferred = $q.defer();

        $http({
            method: 'DELETE',
            url: config.generateApiUrl('institution/'),
            params: { "code": id }

        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           }).
            error(function (dat, status, headers, config) {
                deferred.resolve(status);
            });

        return deferred.promise;
    }

    function _institutionUpdate(dataObj) {

        var deferred = $q.defer();

        $http({
            method: 'PUT',
            url: config.generateApiUrl('institution/'),
            data: JSON.stringify(dataObj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }
}]);