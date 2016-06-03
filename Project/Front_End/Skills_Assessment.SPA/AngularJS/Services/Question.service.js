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

    function _questionList(alunoId) {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('quiz/question'),
            headers: { "token": alunoId },
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

    function _saveAnswer(quiz, alunoId) {
        
        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('quiz/'),
            headers: { 'token': alunoId }, //neste caso ta mandando o codigo do aluno, mas será o token futuramente..
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
            method: 'POST',
            url: config.generateApiUrl('question'),
            header: { 'Content-Type': 'application/json; charset=UTF-8' },
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
            method: 'PUT',
            header: { 'Content-Type': 'application/json; charset=UTF-8' },
            url: config.generateApiUrl('question'),
            data: JSON.stringify(dataObj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

   

}]);