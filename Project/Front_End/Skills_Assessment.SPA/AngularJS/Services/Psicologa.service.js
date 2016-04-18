publication.factory('PsicologaService', ['$http', '$q', function ($http, $q) {

    return {

    };
    
/*
    return {

        courseList: _courseList,
        courseAdd: _courseAdd,
        courseDelete: _courseDelete,
        courseUpdate: _courseUpdate
    };

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
           });

        return deferred.promise;
    }

    function _courseDelete(id) {

        var deferred = $q.defer();

        $http({
            method: 'DELETE',
            url: config.generateApiUrl('course'),
            params: { "codeCourse": id }
        }).
           success(function (data, status, headers, config) {
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
*/
}]);