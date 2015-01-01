var login = angular.module('login', ['ngCookies']);

login.controller('loginController', ['$scope', '$cookieStore', '$http', function($scope, $cookieStore, $http) {
    $scope.login = function () {
        $http.post('http://localhost:8080/reservator/v1/user/login', $scope.user).success(function(data) {
            console.log(data.token);
            $cookieStore.put('authorization-token', data.token);
        });
    }
}]);

login.controller('emailPasswordController', [function() {

}]);