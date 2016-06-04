﻿publication.factory('StudentService', ['$http', '$q', function ($http, $q) {

    //EDITAR CONTEUDO

    var studentCurrent;

    return {
        getStudentCurrent: _getCurrent,
        setStudentCurrent: _setCurrent,
        studentsList: _studentsList,
        studentFindCode: _studentFindCode,
        studentUpdate: _studentUpdate,

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
            url: config.generateApiUrl('enrolls/search/student/code'),//searchStudentsByCode
            params: { "idStudent": idStudent },
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }

        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }

    //atualiza um aluno -- REALIZAR TESTES E FUNCIONALIDADE -- IMPLEMENTAR NO BACK-END!
    //FAZER PREPARAÇÃO DO JSON PARA ENVIO.
    function _studentUpdate() {

        var deferred = $q.defer();

        $http({
            method: 'PUT',
            url: config.generateApiUrl('student')

        }).
           success(function (dataObj, status, headers, config) {
               deferred.resolve(dataObj);
           });

        return deferred.promise;
    }


}]);