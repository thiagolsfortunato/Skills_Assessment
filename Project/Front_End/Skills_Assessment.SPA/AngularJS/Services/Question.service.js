publication.factory('QuestionService', ['$http', '$q', function ($http, $q) {

    return {

        questionList: _questionList,
        questionAdd: _questionAdd,
        questionGetAll: _questionGetAll,
        questionUpdate: _questionUpdate,
        questionDelete: _questionDelete,
        saveAnswer: _saveAnswer
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

    function _questionList() {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('getQuizQuestion')
            //params: { "token": token }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _questionDelete(code) {

        var deferred = $q.defer();

        $http({
            method: 'DELETE',
            url: config.generateApiUrl('question'),
            params: { "code": code}
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _saveAnswer(quiz) {

        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('quiz'),
            data: JSON.stringify(quiz)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }


    function _questionAdd(dataObj) {

        var deferred = $q.defer();

        $http({
            header : {'Content-Type' : 'application/json; charset=UTF-8'},
            method: 'POST',
            url: config.generateApiUrl('question'),
            data: JSON.stringify(dataObj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

    function _questionUpdate(dataObj) {

        var deferred = $q.defer();

        $http({
            header : {'Content-Type' : 'application/json; charset=UTF-8'},
            method: 'PUT',
            url: config.generateApiUrl('question'),
            data: JSON.stringify(dataObj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

   

}]);