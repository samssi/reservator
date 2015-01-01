var reservator = angular.module('reservator', ['ngCookies']);

reservator.controller('reservationsController', ['$scope', '$cookieStore', '$http', function($scope, $cookieStore, $http) {
    var token = $cookieStore.get('authorization-token');
    console.log(token);
    $http.get('http://localhost:8080/reservator/v1/reservations/samssi/' + token).success(function (data){
        console.log('response: ' + data);
    }).error(function () {
        console.log('Not logged in!')
    });
}]);