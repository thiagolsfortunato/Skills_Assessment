publication.factory('CourseService', ['$http', '$q', function ($http, $q) {

    return {

        courseList: _courseList,
        courseAdd: _courseAdd,
        courseDelete: _courseDelete,
        courseUpdate: _courseUpdate,
        courseFindId: _findId
    };

    function _findId(code) {
        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('course/search/code'),
            params: { code: code }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           }).
            error(function (data, status, headers, config) {
                deferred.resolve(status);
            });

        return deferred.promise;
    }

    function _courseList() {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('searchAllCourses')
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _courseAdd(dataObj) {

        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('course'),
            data: JSON.stringify(dataObj),
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            
        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           }).
            error(function (data, status, headers, config) {
                deferred.resolve(status);
           });;

        return deferred.promise;
    }

    function _courseDelete(id, idFatec) {

        var deferred = $q.defer();

        $http({
            method: 'DELETE',
            url: config.generateApiUrl('course'),
            params: { "codeCourse": id, "codeFatec": idFatec }
        }).
           success(function (data, status, headers, config) {
               console.log(data);
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    function _courseUpdate(obj) {

        var deferred = $q.defer();

        $http({
            method: 'PUT',
            url: config.generateApiUrl('course'),
            header : {'Content-Type' : 'application/json; charset=UTF-8'},
            data: JSON.stringify(obj)
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

}]);