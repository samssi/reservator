var login = angular.module('login', ['ngCookies']);

login.controller('loginController', ['$scope', '$cookieStore', '$http', '$window', function($scope, $cookieStore, $http, $window) {
    $scope.login = function () {
        $http.post('http://localhost:8080/reservator/v1/user/login', $scope.user).success(function(response) {
            $cookieStore.put('authorization-token', response.token);
            $cookieStore.put('username', $scope.user.username);
            $window.location.replace('http://localhost:8080/html/reservator.html');
        });
    }
}]);

login.controller('emailPasswordController', [function() {

}]);