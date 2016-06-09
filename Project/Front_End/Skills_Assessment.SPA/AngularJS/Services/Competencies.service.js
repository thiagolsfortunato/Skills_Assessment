publication.factory('CompetenciesService', ['$http', '$q', function ($http, $q) {

    return {

        competenciesList: _competenciesList,
        competencieAdd: _competencieAdd,
        competencieDelete: _competencieDelete,
        competencieUpdate: _competencieUpdate
    };

    function _competenciesList() {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('competence/search/all')
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           })
            .error(function (status) {
               deferred.resolve(status);
           });

        return deferred.promise;
    }

    function _competencieAdd(dataObj) {        

       var deferred = $q.defer();
       
        $http({
            method: 'POST',
            url: config.generateApiUrl('competence/'),
            data: JSON.stringify(dataObj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(status);
           })
            .error(function (data, status) {
                deferred.resolve(status);
            });

        return deferred.promise;  
    }

    function _competencieDelete(id) {

        var deferred = $q.defer();

        $http({
            method: 'DELETE',
            url: config.generateApiUrl('competence/'),
            params: { "competenceCode": id }
            
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(status);
           })
            .error(function (data, status) {
                deferred.resolve(status);
            });

        return deferred.promise;
    }

    function _competencieUpdate(dataObj) {

        var deferred = $q.defer();

        $http({
            method: 'PUT',
            url: config.generateApiUrl('competence/'),
            data: JSON.stringify(dataObj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(status);
           })
            .error(function (data, status) {
               deferred.resolve(status);
           });

        return deferred.promise;
    }

}]);