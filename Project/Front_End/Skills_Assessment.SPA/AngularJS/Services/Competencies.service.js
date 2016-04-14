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
            url: config.generateApiUrl('searchAllCompetencies')
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _competencieAdd(data) {

        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('insertCompetence'),
            data: JSON.stringify(data)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _competencieDelete(id) {

        var deferred = $q.defer();

        $http({
            method: 'DELETE',
            url: config.generateApiUrl('deleteCompetence'),
            params: { "competenceCode": id }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _competencieUpdate(obj) {

        var deferred = $q.defer();

        $http({
            method: 'PUT',
            url: config.generateApiUrl('updateCompetence'),
            header : {'Content-Type' : 'application/json; charset=UTF-8'},
            data: JSON.stringify(obj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

}]);