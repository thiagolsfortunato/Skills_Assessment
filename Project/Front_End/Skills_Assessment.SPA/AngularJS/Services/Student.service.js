publication.factory('StudentService', ['$http', '$q', function ($http, $q) {

    //EDITAR CONTEUDO

    var studentCurrent;

    return {
        getStudentCurrent: _getCurrent,
        setStudentCurrent: _setCurrent,
        studentsList: _studentsList,
        studentFindCode: _studentFindCode,

    };
    function _getCurrent() {
        return studentCurrent;
    }
    function _setCurrent(Student) {
        studentCurrent = Student;
    }
    //retorna todos alunos de uma determinada fatec
    function _studentsList(fatecCode) {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('search/students/all/fatec'),
            params: { "fatecCode": fatecCode }
        }).
           success(function (data, status, headers, config) {
               deferred.resolve(data);
           });

        return deferred.promise;
    }

    //retorna um aluno por um determinado codigo
    function _studentFindCode(idStudent) {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: config.generateApiUrl('search/student/code'),//searchStudentsByCode
            params: { "idStudent": idStudent },
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }

        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }



}]);