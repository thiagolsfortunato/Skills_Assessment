publication.factory('PsicologaService', ['$http', '$q', function ($http, $q) {

    var psicologaCurrent;

    return {
        getPsicologaCurrent: _getCurrent,
        setPsicologaCurrent: _setCurrent,
        getAllPsicologas: _getList,
        deletePsicologa: _delete,
        sendComment: _sendComment,

    };
    
    function _getCurrent() {
        return psicologaCurrent;
    }

    function _setCurrent(psicologa) {
        psicologaCurrent = psicologa;
    }

    function _sendComment(txt, studentCode ) {

        var deferred = $q.defer();

        $http({
            method: 'PUT',
            url: config.generateApiUrl('enrolls/comment'),
            params: { "studentCode": studentCode },
            data: JSON.stringify(txt)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    //IMPLEMENTAR NO BACK-END !
    function _getList() {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('search/all/psicologas')
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    // VER IMPLEMENTAÇÃO NO BACK-END !
    function _delete(id) {

        var deferred = $q.defer();

        $http({
            method: 'DELETE',
            url: config.generateApiUrl('psicologa/'),
            params: { "code": id }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

}]);