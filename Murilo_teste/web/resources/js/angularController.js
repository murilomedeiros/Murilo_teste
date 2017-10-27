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

pageModule.controller('controllerIndex', function ($scope, $rootScope, postService, $http, $timeout) {

    $rootScope.getDrivers = function () {
        $http.get("/Murilo_teste/data/driver.jsp?action=getDrivers")
                .then((response) => {
                    $scope.drivers = [];
                    $scope.drivers = response.data.drivers;
                    if ($scope.drivers == "") {
                        $scope.selectError = response.data.re;
                        $rootScope.viewD = true;
                    } else {
                        $scope.selectError = null;
                        $rootScope.viewD = false;
                    }
                });
    };

    $rootScope.getClients = function () {
        $http.get("/Murilo_teste/data/client.jsp?action=getClients")
                .then((response) => {
                    $scope.clients = [];
                    $scope.clients = response.data.clients;
                    if ($scope.clients == "") {
                        $scope.selectError = response.data.re;
                        $rootScope.viewC = true;
                        
                    } else {
                        $scope.selectError = null;
                        $rootScope.viewC = false;
                    }
                });
    };

    $scope.changeStatusDriver = function (status, sDriver) {
        $rootScope.statusD = status;
        $scope.BackUp = angular.copy(sDriver);
        $rootScope.selectDriver = $scope.BackUp;
        switch ($scope.statusD) {
            case "true":
                $rootScope.statusDName = "Ativo";
                break;
            case "false":
                $rootScope.statusDName = "Inativo";
                break;
        }
        if ($rootScope.statusDName != null) {
            $("#confirmStatus").modal('show');
        }
    };

    $rootScope.confirmedStatus = function (status, idDriver) {
        var dados = {action: "changeStatus", status: status, idDriver: idDriver};
        $("#loader").css("display", "block");
        var url = "../Murilo_teste/data/driver.jsp";
        postService.query(dados, url).then(function (re) {
            $("#confirmStatus").modal('hide');
            $("#loader").css("display", "none");
            $("#success-change").modal('show');
            $timeout(function () {
                $("#success-change").modal('hide');
            }, 2000);

            $rootScope.getDrivers();
        }).catch(function () {
            $("#confirmStatus").modal('hide');
            $("#loader").css("display", "none");
            alert("Error");
        });
    };

    $rootScope.getDrivers();
    $rootScope.getClients();


});

pageModule.controller('controllerDriver', function ($scope, $rootScope, postService, $http) {
    $scope.addDriver = function (nameD, dateD, cpfD, carD, genderD) {
        $("#loader").css("display", "block");
        var dados = {action: "addDriver", nameD: nameD, dateD: dateD, cpfD: cpfD, carD: carD, genderD: genderD};
        var url = "../Murilo_teste/data/driver.jsp";
        postService.query(dados, url).then(function (re) {
            $("#loader").css("display", "none");
            $('#success').modal('show');
        }).catch(function () {
            $("#loader").css("display", "none");
            $('#erro-modal').modal('show');
        });
    };

    $scope.back = function () {
        $scope.driverName = "";
        $scope.driverDate = "";
        $scope.driverCPF = "";
        $scope.driverCar = "";
        $scope.driverGender = "";
    };

});

pageModule.controller('controllerClient', function ($scope, $rootScope, postService) {
    $scope.addClient = function (nameC, cpfC, dateC, genderC) {
        $("#loader").css("display", "block");
        var dados = {action: "addClient", nameC: nameC, cpfC: cpfC, genderC: genderC, dateC: dateC};
        var url = "../Murilo_teste/data/client.jsp";
        postService.query(dados, url).then(function (re) {
            $("#loader").css("display", "none");
            $('#success').modal('show');
        }).catch(function () {
            $("#loader").css("display", "none");
            $('#erro-modal').modal('show');
        });
    };

    $scope.back = function () {
        $scope.clientName = "";
        $scope.clientCPF = "";
        $scope.clientDate = "";
        $scope.clientGender = "";

    };

});

pageModule.controller('controllerRun', function ($scope, $rootScope, postService, $http) {

    $scope.addRun = function (driverIdR, clientCpfR, priceR) {
        $("#loader").css("display", "block");
        var dados = {action: "addRun", driverIdR: driverIdR, clientCpfR: clientCpfR, priceR: priceR};
        var url = "../Murilo_teste/data/run.jsp";
        postService.query(dados, url).then(function () {
            $("#loader").css("display", "none");
            $('#success').modal('show');
        }).catch(function () {
            $("#loader").css("display", "none");
            $('#erro-modal').modal('show');
        });
    };

    $http.get("../Murilo_teste/data/run.jsp?action=selectDrivers")
            .then(function (response) {
                $scope.driverArray = [];
                $scope.driverArray = response.data.driverArray;
            });

    $scope.back = function () {
        $scope.driverNameR = "";
        $scope.clientNameR = "";
        $scope.priceR = "";

    };
});