var login = angular.module('login', ['restangular']);

login.config(function(RestangularProvider) {
        RestangularProvider.setBaseUrl('http://localhost:8080');
        RestangularProvider.setOnElemRestangularized(false);
    }
)


function loginController($scope, Restangular) {
    $scope.login = function() {
        Restangular.one('reservator/v1/user/login').customPOST('user', $scope.user).then(function(token) {
            console.log('foo');
            console.log(token)
        });
    }
}

function emailPasswordController ($scope, Restangular) {
    $scope.requestPassword = function() {
        Restangular.one('reservator/v1/user/password/request').customPOST($scope.request, '');
    }
}