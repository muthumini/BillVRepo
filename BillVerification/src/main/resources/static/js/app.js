var ibvsModule = angular.module('ibvsApp', ['ngRoute', 'ngResource']);

ibvsModule.config(function($routeProvider){
	$routeProvider
	
	.when('/', {
		templateUrl: 'pages/UserCreate.html',
		controller:'HomeController'
	})
	.when('/UserCreate', {
		templateUrl: 'pages/UserCreate.html',
		controller:'UserController'
	})
	.when('/Operator', {
		templateUrl: 'pages/NewOperator.html',
		controller:'OperatorController'
	})
	.otherwise({redirectTo: '/'});
});
//
//ibvsModule.controller('HomeController', function($scope){
//	$scope.message = 'Hello Home Controller';
//});

ibvsModule.controller('UserController', function($scope){
	$scope.message = 'Hello User Controller';
});
ibvsModule.controller('OperatorController', function($scope){
	$scope.message = 'Hello Operator Controller';
});
ibvsModule.controller('HomeController', function($scope){
	$scope.headingTitle = 'Home';
	$scope.addUser = function(){
		$scope.headingTitle = 'User';
	}
	$scope.message = 'Hello Home Controller';
});

ibvsModule.controller("NewUserProfileController", 
    function ($scope, $http)    {
        $scope.addNewUserProfile = function() {
            var request = $http( {
                method  : "POST",
                url     : "/userProfile/new",
                data    : {
                		"empID"		: $scope.empID,
                		"firstname"	: $scope.firstname,
                		"lastname"	: $scope.lastname,
                		"mobile"	: $scope.phone,
                		"email"		: $scope.email,
                		"userResource":{
                			"userName"	: $scope.userName,
                    		"password"	: $scope.password,
                    		"roles":[
                				{"id":1, "role":"ReadOnly"},
                				{"id":2, "role":"ReadWrite"}
                			]
                		}
                		
                }

            });
            request.success(
                function (response) {
                    angular.element(document.querySelector("#divstatus")).css("visibility", "visible");
                    if (response.status == "success")   {
                        angular.element(document.querySelector("#divstatus")).addClass("alert-success");
                        $scope.statusmsg="User profile has been added successfully!";
                        $scope.book = null; 
                    }
                    else    {
                        angular.element(document.querySelector("#divstatus")).addClass("alert-danger");
                        $scope.statusmsg="Error:" + response.detail;
                    }
                });
             
            request.error(
                function (response) {
                    angular.element(document.querySelector("#divstatus")).css("visibility", "visible");
                    angular.element(document.querySelector("#divstatus")).addClass("alert-danger");
                    $scope.statusmsg="Error:" + response.detail;
                });
                 
        }
     
    });



ibvsModule.controller("NewOperatorController", 
	    function ($scope, $http)    {
	        $scope.addNewOperator = function() {
	            var request = $http( {
	                method  : "POST",
	                url     : "/operator/new",
	                data    : {
	                	 "name": "Hutch",
	                	 "code": "350",
	                	 "no": "13",
	                	 "street": "1st lane",
	                	 "city": "Colombo",
	                	 "country": "Sri lanka",
	                	 "disputePercentage": 3,
	                	 "disputeCost": 15
	                }

	            });
	            request.success(
	                function (response) {
	                    angular.element(document.querySelector("#divstatus")).css("visibility", "visible");
	                    if (response.status == "success")   {
	                        angular.element(document.querySelector("#divstatus")).addClass("alert-success");
	                        $scope.statusmsg="User profile has been added successfully!";
	                        $scope.book = null; 
	                    }
	                    else    {
	                        angular.element(document.querySelector("#divstatus")).addClass("alert-danger");
	                        $scope.statusmsg="Error:" + response.detail;
	                    }
	                });
	             
	            request.error(
	                function (response) {
	                    angular.element(document.querySelector("#divstatus")).css("visibility", "visible");
	                    angular.element(document.querySelector("#divstatus")).addClass("alert-danger");
	                    $scope.statusmsg="Error:" + response.detail;
	                });
	                 
	        }
	     
	    });

