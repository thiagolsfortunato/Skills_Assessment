publication.factory('QuestionService', ['$http', '$q', function ($http, $q) {

    return {

        questionList: _questionList,
        questionAdd: _questionAdd,
        questionGetAll: _questionGetAll
    };


    function _questionGetAll() {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('question/find/all'),
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _questionList(token) {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('getQuizQuestion'),
            params: { "token": token }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _questionAdd(dataObj) {

        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('question'),
            data: JSON.stringify(dataObj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

   

}]);