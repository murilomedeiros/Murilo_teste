/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var pageModule = angular.module("pageModule", []);

pageModule.run(['$rootScope', '$window', function ($rootScope, $window) {

        $rootScope.CTX_PATH = $window.CTX_PATH;


    }]);

pageModule.factory('postService', ['$http', function ($http) {
        return{
            query: function (selecionado, url) {
                var serializedData = $.param(selecionado);
                return $http({
                    method: 'POST',
                    url: url,
                    data: serializedData,
                    headers: {
                        'Content-type': 'application/x-www-form-urlencoded'
                    }
                });
            }
        };
    }]);

pageModule.controller('controllerIndex', function ($scope) {


});

pageModule.controller('controllerDriver', function ($scope, $rootScope, postService) {
    $scope.addDriver = function (nameD, dateD, cpfD, carD, genderD, statusD) {
        var dados = {action: "addDriver", nameD: nameD, dateD: dateD, cpfD: cpfD, carD: carD, genderD: genderD, statusD: statusD};
        var url = "../Prototipo_teste/data/driver.jsp";
        postService.query(dados, url).then(function (re) {
            $('#success').modal('show');
        }).catch(function () {
            alert("ERRO");
        });
    };

    $scope.back = function () {
        $scope.driverName = "";
        $scope.driverDate = "";
        $scope.driverCPF = "";
        $scope.driverCar = "";
        $scope.driverGender = "";
    };

    $scope.inscription = function (nameP, emailP, idEvent, nEvent) {
        $("#loader").css("display", "block");
    };

});

pageModule.controller('controllerClient', function ($scope, $rootScope, postService) {
    $scope.addClient = function (nameC, cpfC, dateC, genderC) {
        var dados = {action: "addClient", nameC: nameC, cpfC: cpfC, genderC: genderC, dateC: dateC};
        var url = "../Prototipo_teste/data/client.jsp";
        postService.query(dados, url).then(function (re) {
            $('#success').modal('show');
        }).catch(function () {
            alert("ERRO");
        });
    };

    $scope.back = function () {
        $scope.clientName = "";
        $scope.clientCPF = "";
        $scope.clientDate = "";
        $scope.clientGender = "";

    };

});

pageModule.controller('controllerRun', function ($scope, $rootScope, postService) {

    $scope.addRun = function (driverNameR, clientNameR, priceR) {
        var dados = {action: "addRun", driverNameR: driverNameR, clientNameR: clientNameR, priceR: priceR};
        var url = "../Prototipo_teste/data/run.jsp";
        postService.query(dados, url).then(function (re) {
            $('#success').modal('show');
        }).catch(function () {
            alert("ERRO");
        });
    };

    $scope.back = function () {
        $scope.driverNameR = "";
        $scope.clientNameR = "";
        $scope.priceR = "";

    };
});