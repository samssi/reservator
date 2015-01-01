var reservator = angular.module('reservator', ['ngCookies']);

reservator.controller('reservationsController', ['$scope', '$cookieStore', '$http', '$window', function($scope, $cookieStore, $http, $window) {
    var username = $cookieStore.get('username');
    var token = $cookieStore.get('authorization-token');
    $http.get('http://localhost:8080/reservator/v1/reservations/' + username + '/' + token).success(function(data){
        console.log('response: ' + data);
    }).error(function() {
        $window.location.replace('http://localhost:8080/html/index.html')
    });
}]);

reservator.controller('userController', ['$scope', '$cookieStore', function($scope, $cookieStore) {
    $scope.username = $cookieStore.get('username');
}]);

reservator.controller('logoutController', ['$scope', '$http', '$cookieStore', '$window', function($scope, $http, $cookieStore, $window) {
    var username = $cookieStore.get('username');
    var token = $cookieStore.get('authorization-token');

    $scope.logout = function() {
        $http.delete('http://localhost:8080/reservator/v1/user/logout/' + username + "/" + token).success(function() {
            $cookieStore.remove('authorization-token');
            $cookieStore.remove('username');
            $window.location.replace('http://localhost:8080/html/index.html');
        });
    }
}]);
