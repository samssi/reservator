var login = angular.module('login', ['restangular']);

login.config(function(RestangularProvider) {
        RestangularProvider.setBaseUrl('http://localhost:8080');
        RestangularProvider.setOnElemRestangularized(false);
    }
)


function loginController($scope, Restangular) {
    $scope.login = function() {
        console.log($scope.user)
        Restangular.one('reservator/v1/user/login').customPUT($scope.user, '');
    }
}