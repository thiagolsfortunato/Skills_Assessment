publication.factory('QuestionService', ['$http', '$q', function ($http, $q) {

    return {

        questionList: _questionList,

    };

    function _questionList() {

        var deferred = $q.defer();

        $http({ method: 'GET', url: config.generateApiUrl('hello') }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }
   

}]);