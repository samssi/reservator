var notesongo = angular.module('notesongo', ['restangular', 'ui.utils']);

notesongo.config(function(RestangularProvider) {
        RestangularProvider.setBaseUrl('http://localhost:8080');
        RestangularProvider.setOnElemRestangularized(false);
    }
)


function noteController($scope, $timeout, Restangular) {
    $scope.loadNote = function() {
        Restangular.one('note/foo').get().then(function(note){
            $scope.note.note = note.note;
            $scope.note.userId = note.userId;
        });
    }

    $scope.updateNote = function() {
        $scope.note.userId = 'foo';
        console.log($scope.note)
        Restangular.one('note').customPUT($scope.note, '');
    }

    $scope.onTimeout = function(){
        $scope.loadNote()
        mytimeout = $timeout($scope.onTimeout,1000);
    }
    var mytimeout = $timeout($scope.onTimeout,1000);

}