var login = angular.module('login', ['restangular']);

login.config(function(RestangularProvider) {
        RestangularProvider.setBaseUrl('http://localhost:8080');
        RestangularProvider.setOnElemRestangularized(false);
    }
)


function loginController($scope, Restangular, $window) {
    $scope.login = function() {
        Restangular.one('reservator/v1/user/login').customPOST($scope.user, '').then(function(data) {
            console.log(data.token);
            $window.location.href = 'http://localhost:8080/html/reservator.html';
        });
    }
}

function emailPasswordController ($scope, Restangular) {
    $scope.requestPassword = function() {
        Restangular.one('reservator/v1/user/password/request').customPOST($scope.request, '');
    }
}