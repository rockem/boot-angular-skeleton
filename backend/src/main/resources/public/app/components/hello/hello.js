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
        Hello.get(function(result) {
            $scope.greeting = result.greeting
        })
    });