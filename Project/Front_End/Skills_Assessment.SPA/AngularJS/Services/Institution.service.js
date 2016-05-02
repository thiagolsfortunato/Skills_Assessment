publication.factory('InstitutionService', ['$http', '$q', function ($http, $q) {

    return {

        institutionSave: _institutionSave,

    };

    function _institutionSave(obj) {

        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('institution'),
            data: JSON.stringify(obj),
            headers: { 'content-type': 'application/json' }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }


}]);