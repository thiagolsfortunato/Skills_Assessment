publication.factory('EmployeeService', ['$http', '$q', function ($http, $q) {

    return {

        employeeAdd: _employeeAdd,
    };

    function _employeeAdd(dataObj) {

        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('user'),
            data: JSON.stringify(dataObj),
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }

        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

   
}]);