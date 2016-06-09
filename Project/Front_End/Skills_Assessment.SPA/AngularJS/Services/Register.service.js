publication.factory('RegisterService', ['$http', '$q', function ($http, $q) {

    return {

        userAdd: _userAdd,
        fatecList: _fatecList,
        fatecAdd: _fatecAdd,
        validate: _validate,
        //userDelete: _userDelete,
        //userUpdate: _userUpdate
    };
    
    function _validate(user){
    	
    	var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('enrolls/search/student/validate'),
            params:{'email':user.userName, 'ra':user.ra},
            headers: { 'Content-Type' : 'application/json' }
        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

    function _userAdd(dataObj) {

        var deferred = $q.defer();

        $http({
            method: 'POST',
            url: config.generateApiUrl('enrolls/'),
            data: JSON.stringify(dataObj),
            headers: { 'Content-Type' : 'application/json' }
            //headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            
        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

    function _fatecAdd(dataUser, dataFatec) {

        var deferred = $q.defer();
        
        var dataObj = { fatec: dataFatec, adm: dataUser };
        

        $http({
            method: 'POST',
            url: config.generateApiUrl('institution/'),
            data: JSON.stringify(dataObj),
            headers: { 'Content-Type': 'application/json' }
            //headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }

        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

    function _fatecList() {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('institution/find/all/courses'),
            //headers: { 'Content-Type': 'application/json' }
            //headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }

        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }


}]);