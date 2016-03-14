angular.module('registercibiogas')
  .factory('BaseUrl', function($location) {
     return 'http://' + $location.host() + ':9000' ;
   });