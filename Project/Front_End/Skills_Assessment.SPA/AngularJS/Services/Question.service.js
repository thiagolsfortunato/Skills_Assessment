publication.factory('QuestionService', ['$http', '$q', function ($http, $q) {

    return {

        questionList: _questionList,

    };

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
   

}]);