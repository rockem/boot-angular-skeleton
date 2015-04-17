'use strict';

angular.module('myApp.hello', ['ngRoute','ngResource'])

    .factory("Hello", function ($resource) {
        return $resource("/api/hello")
    })

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/hello', {
            templateUrl: 'components/hello/hello.html',
            controller: 'HelloCtrl'
        });
    }])

    .controller('HelloCtrl', function ($scope, Hello) {
        $scope.greeting = Hello.get(function(greeting) {
            $scope.greeting = greeting.greeting
        })
    });