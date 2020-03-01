var takmicariApp = angular.module("takmicariApp", ["ngRoute"]);

takmicariApp.controller("TakmicariCtrl", function ($scope, $http, $location) {

    var urlSkakaonice = "/api/skakaonice";

    $scope.skakaonice = [];

    var getSkakaonice = function () {
        var promise = $http.get(urlSkakaonice);
        promise.then(
            function success(response) {
                $scope.skakaonice = response.data;
            },
            function error(response) {
                alert("Neuspesno dobavljanje skakaonica")
            }
        )
    }
    getSkakaonice();

    var urlTakmicari = "/api/takmicari";

    $scope.takmicari = [];

    $scope.takmicar = {};
    $scope.takmicar.imeIprezime = "";
    $scope.takmicar.drzava = "";
    $scope.takmicar.visina = "";
    $scope.takmicar.godinaRodjenja = "";
    $scope.takmicar.email = "";
    $scope.takmicar.skakaonicaId = "";

    $scope.trazeniTakmicar = {};
    $scope.trazeniTakmicar.imeIprezime = "";
    $scope.trazeniTakmicar.drzava = "";
    $scope.trazeniTakmicar.minGodiste = "";
    $scope.trazeniTakmicar.maxGodiste = "";

    $scope.page = 0;
    $scope.totalElements = 1;

    var getTakmicari = function () {

        var config = {
            params: {}
        }

        config.params.page = $scope.page;

        if ($scope.trazeniTakmicar.imeIprezime != "") {
            config.params.imeIprezime = $scope.trazeniTakmicar.imeIprezime;
        }
        if ($scope.trazeniTakmicar.drzava != "") {
            config.params.drzava = $scope.trazeniTakmicar.drzava;
        }
        if ($scope.trazeniTakmicar.minGodiste != "") {
            config.params.minGodiste = $scope.trazeniTakmicar.minGodiste;
        }
        if ($scope.trazeniTakmicar.maxGodiste != "") {
            config.params.maxGodiste = $scope.trazeniTakmicar.maxGodiste;
        }

        var promise = $http.get(urlTakmicari, config);
        promise.then(
            function success(response) {
                $scope.takmicari = response.data;
                $scope.totalPages = response.headers("totalPages")
            },
            function error(response) {
                alert("Neuspesno dobavljanje takmicara")
            }
        )

    }
    getTakmicari();

    $scope.pretraga = function () {
        $scope.page = 0;
        getTakmicari();
    }

    $scope.napred = function () {
        if ($scope.page < $scope.totalPages - 1) {
            $scope.page += 1;
            getTakmicari();
        }

    }

    $scope.nazad = function () {
        if ($scope.page > 0) {
            $scope.page -= 1;
            getTakmicari();
        }
    }

    $scope.dodaj = function () {
        var promise = $http.post(urlTakmicari, $scope.takmicar);
        promise.then(
            function success(response) {
                getTakmicari();
                $scope.takmicar.imeIprezime = "";
                $scope.takmicar.drzava = "";
                $scope.takmicar.visina = "";
                $scope.takmicar.godinaRodjenja = "";
                $scope.takmicar.email = "";
                $scope.takmicar.skakaonicaId = "";
            },
            function error(response) {
                alert("Neuspesno dodavanje takmicara")
            }
        )
    }

    $scope.obrisi = function (id) {
        var promise = $http.delete(urlTakmicari + "/" + id);
        promise.then(
            function success(response) {
                getTakmicari();
            },
            function error(response) {
                alert("Neuspesno brisanje takmicara")
            }
        )
    }

    $scope.izmeni = function (id) {
        $location.path("/takmicari/edit/" + id);
    }

    $scope.skok = function (id) {
        $location.path("/takmicari/skok/" + id);
    }

});

takmicariApp.controller("EditTakmicarCtrl", function ($scope, $http, $location, $routeParams) {

    var urlSkakaonice = "/api/skakaonice";

    $scope.skakaonice = [];

    var getSkakaonice = function () {
        var promise = $http.get(urlSkakaonice);
        promise.then(
            function success(response) {
                $scope.skakaonice = response.data;
            },
            function error(response) {
                alert("Neuspesno dobavljanje skakaonica")
            }
        )
    }
    getSkakaonice();

    var urlTakmicara = "/api/takmicari/" + $routeParams.id;

    $scope.takmicar = {};

    var getTakmicar = function () {
        var promise = $http.get(urlTakmicara);
        promise.then(
            function success(response) {
                $scope.takmicar = response.data;
            },
            function error(response) {
                alert("Neuspesno dobavljanje takmicara")
            }
        )
    }
    getTakmicar();

    $scope.izmeni = function () {
        var promise = $http.put(urlTakmicara, $scope.takmicar);
        promise.then(
            function success(response) {
                $location.path("/")
            },
            function error(response) {
                alert("Neuspesna izmena takmicara")
            }
        )
    }

});

takmicariApp.controller("SkokCtrl", function ($scope, $http, $location, $routeParams) {

    $scope.sudije = {};

    $scope.sudije.sudija1 = "";
    $scope.sudije.sudija2 = "";
    $scope.sudije.sudija3 = "";
    $scope.sudije.sudija4 = "";
    $scope.sudije.sudija5 = "";
    $scope.sudije.daljina = "";

    var urlSkok = "/api/skokovi/" + $routeParams.id;
    $scope.skok = function () {

        var data = {
            sudija1: $scope.sudije.sudija1,
            sudija2: $scope.sudije.sudija1,
            sudija3: $scope.sudije.sudija1,
            sudija4: $scope.sudije.sudija1,
            sudija5: $scope.sudije.sudija1,
            daljina: $scope.sudije.daljina
        };
        var promise = $http({
            url: urlSkok,
            method: "POST",
            params: data
        });
        promise.then(
            function success() {
                $location.path("/")
            },
            function error() {
                alert("Skok neuspesan")
            }
        )

    }

});


takmicariApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/app/html/takmicari.html',
            controller: "TakmicariCtrl"
        })
        .when('/takmicari/skok/:id', {
            templateUrl: '/app/html/skok.html',
            controller: "SkokCtrl"
        })
        .when('/takmicari/edit/:id', {
            templateUrl: '/app/html/edit-takmicar.html',
            controller: "EditTakmicarCtrl"
        })
        .otherwise({
            redirectTo: '/'
        });
}]);
