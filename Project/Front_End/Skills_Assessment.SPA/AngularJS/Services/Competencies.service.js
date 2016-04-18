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

    function _competencieAdd(dataObj) {        

       var deferred = $q.defer();
       
        $http({
            method: 'POST',
            url: config.generateApiUrl('insertCompetence'),
            data: JSON.stringify(dataObj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;  
    }

    function _competencieDelete(id) {

        var deferred = $q.defer();

        $http({
            method: 'delete',
            url: config.generateApiUrl('deleteCompetence'),
            params: { "competenceCode": id }
            
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _competencieUpdate(dataObj) {

        var deferred = $q.defer();

        $http({
            method: 'PUT',
            url: config.generateApiUrl('updateCompetence'),
            data: JSON.stringify(dataObj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

}]);