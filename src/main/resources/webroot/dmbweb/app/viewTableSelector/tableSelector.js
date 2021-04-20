'use strict';

angular.module('myApp.tableSelector', ['ui.router'])

	.config(['$stateProvider', function($stateProvider) {
		$stateProvider.state('selectTable', {
			url: '/tableSelector?jsonObj',
			cache: false,
			params: {
				'jsonObj': null
			},
			templateUrl: 'viewTableSelector/tableSelector.html',
			controller: 'tableSelector'
		});
	}])

	.controller('tableSelector', ['$scope', '$rootScope', '$http', '$state', 'viewGridProvider', function($scope, $rootScope, $http, $state, viewGridProvider) {
		//console.log("$rootScope.showKey", $rootScope.showKey);
		$scope.dataUrl = 'model/tableIndex.json';
		if(!$rootScope.showKey1) {
			$rootScope.showFlag1 = true;
			$rootScope.showKey1 = "Model";
			$rootScope.showFlag2 = true;
			$rootScope.showKey2 = ""
			$rootScope.showFlag3 = true;
			$rootScope.showKey3 = ""
		}
		$scope.searchDates = [];
		//将下拉选的数据值赋值给文本框  
		viewGridProvider.httpCommit($scope.dataUrl).then(function success(res) {
			$scope.datas = res.data;
			console.log("|||||||||||||||||||||||",$scope.datas)
			var obj = [];
			var i = 0;
			for(var key1 in res.data) {
				for(var key2 in res.data[key1].rows) {
					obj[i] = {};
					obj[i] = res.data[key1].rows[key2];
					obj[i].id = key2;
					i++;
				}
			}
			angular.copy(obj, $scope.searchDates);
		}, function error() {
			alert("出错");
		});

		var showKey = {};
//		$scope.searchConditonChange = function(searchConditonText) {
//			if($rootScope.showKey != 'search') {
//				angular.copy({
//					"key": $rootScope.showKey
//				}, showKey);
//			}
//			if(searchConditonText) {
//				$rootScope.showKey = 'search';
//			} else {
//				$rootScope.showKey = showKey.key;
//
//			}
//		};
		$scope.colums1OnClick = function(key, showKey) {
			$rootScope.showKey1 = key;
			if(key == showKey) {
				$rootScope.showFlag1 = !$rootScope.showFlag1
			} else {
				$rootScope.showFlag1 = true
			}
		};
		$scope.colums2OnClick = function(key2, showKey2) {
			$rootScope.showKey2 = key2
			if(key2 == showKey2) {
				$rootScope.showFlag2 = !$rootScope.showFlag2
			} else {
				$rootScope.showFlag2 = true
			}
		};
		$scope.colums3OnClick = function(key3, showKey3) {
			$rootScope.showKey3 = key3
			if(key3 == showKey3) {
				$rootScope.showFlag3 = !$rootScope.showFlag3
			} else {
				$rootScope.showFlag3 = true
			}
		};
		$scope.tableOpenOnClick = function(data) {
			//------------------------与model层交互代码开始---------------
			var obj = {};
			obj.url = data.url;
			obj.tableName = data.tableName;
			obj.tableFormat = data.tableFormat;
			var jsonObj = angular.toJson(obj);

			//console.log("state go参数-->", jsonObj);
			//console.log(jsonObj.url);
			//console.log(jsonObj);
			$state.go("app/uiGrid", {
				"jsonObj": jsonObj
			}, {
				reload: true
			});
			//-------------------------与model层交互代码结束-------------

		};

	}]);