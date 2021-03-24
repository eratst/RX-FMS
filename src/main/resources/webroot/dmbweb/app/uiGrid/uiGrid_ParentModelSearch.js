'use strict';
appUiGrid.controller('uiparentModelSearchGridCtrl', ['$scope', '$filter', '$rootScope', '$http', 'viewGridProvider', '$stateParams', '$state', 'FileUploader', '$interval', '$timeout', 'tableIndex', '$q', function($scope, $filter, $rootScope, $http, viewGridProvider, $stateParams, $state, FileUploader, $interval, $timeout, tableIndex, $q) {
	//如果不是从uiGrid页面传来的数据为空则返回Uigrid
	if(!$scope.$parent.main.vMember.temLink.selfKV) {
		$state.go('app.uiGrid', {
			id: $stateParams.id
		}, {
			reload: false
		});
	}
	//	获取增加,编辑模态框的数据
	var areaAlias = '';
	var orgAlias = '';
	var cnfgClassName = '';
	var rentCode = '';
	var cnfgClassCode = '';
	//	var userOrgCodes = '';
	var tableType = $scope.main.vMember.sapc.tableType;
	console.log('tableIndex',tableIndex)
	console.log('$scope.main.vMember.sapc.tableType', $scope.main.vMember.sapc.tableType)
	for(var key in tableType.attribute) {
		if(key == "areaAlias") {
			areaAlias = ($scope.$parent.main.vMember.temLink.selfKV.proWho == 'proAdd') ?
				tableType.attribute[key].proAdd.data : tableType.attribute[key].proUpdate.data;
		}
		if(key == "orgAlias") {
			orgAlias = ($scope.$parent.main.vMember.temLink.selfKV.proWho == 'proAdd') ?
				tableType.attribute[key].proAdd.data : tableType.attribute[key].proUpdate.data;
		}
		if(key == "rentCode") {
			rentCode = ($scope.$parent.main.vMember.temLink.selfKV.proWho == 'proAdd') ?
				tableType.attribute[key].proAdd.data : tableType.attribute[key].proUpdate.data;
		}
		if(key == "cnfgClassCode") {
			cnfgClassCode = ($scope.$parent.main.vMember.temLink.selfKV.proWho == 'proAdd') ?
				tableType.attribute[key].proAdd.data : tableType.attribute[key].proUpdate.data;
		}
		if(key == "cnfgClassName") {
			cnfgClassName = ($scope.$parent.main.vMember.temLink.selfKV.proWho == 'proAdd') ?
				tableType.attribute[key].proAdd.data : tableType.attribute[key].proUpdate.data;
		}
		//		if(key == "userOrgCodes" && ($scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "班组用户关联" || $scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "用户")) {
		//			userOrgCodes = ($scope.$parent.main.vMember.temLink.selfKV.proWho == 'proAdd') ?
		//				tableType.attribute[key].proAdd.data : tableType.attribute[key].proUpdate.data;
		//		}
	}
	//	获取增加模态框的数据结束	
	var jsonObj = viewGridProvider.getTableIndexJsonByKey(tableIndex, $stateParams.idp);
	console.log("子路由jsonObj", jsonObj);
	angular.element("#parentSearchModel").modal("show");
	$scope.pM = {}
	$scope.pM.vMember = {}
	$scope.pM.vMember.sapc = {};
	$scope.pM.vMember.pSearch = {};
	$scope.pM.vMember.pSearch.searchState = {};
	$scope.pM.vMember.pSearch.page = {
		currentPage: 1,
		pageSize: 10,
		totalItems: 0
	};
	var deffered = $q.defer();
	var promise = deffered.promise;
	viewGridProvider.setValueTV($scope.pM.vMember.sapc, jsonObj, deffered);
	promise.then(function() {
		viewGridProvider.initParentTable($scope.pM.vMember.sapc.parentType, tableIndex);
		console.log('$scope.pM.vMember.sapc.parentType', $scope.pM.vMember.sapc.parentType);
		//		将新增页面上的数据赋给查询模态框
		var searchTableType = $scope.pM.vMember.sapc.parentType;
		for(var key in searchTableType.attribute) {
			if(key == "areaAlias") {
				searchTableType.attribute[key].proSearch = {
					show: true,
					edit: false
				};
				searchTableType.attribute[key].proSearch.data = areaAlias;
			}
			if(key == "orgAlias") {
				searchTableType.attribute[key].proSearch = {
					show: true,
					edit: false
				};
				searchTableType.attribute[key].proSearch.data = orgAlias;
			}
			if(key == "rentCode") {
				searchTableType.attribute[key].proSearch = {};
				searchTableType.attribute[key].proSearch.data = rentCode;
			}
			if(key == "cnfgClassCode") {
				searchTableType.attribute[key].proSearch = {
					show: true,
					edit: false
				};
				searchTableType.attribute[key].proSearch.data = cnfgClassCode;
			}
			if(key == "cnfgClassName") {
				searchTableType.attribute[key].proSearch = {
					show: true,
					edit: false
				};
				searchTableType.attribute[key].proSearch.data = cnfgClassName;
			}
			//			if(key == "parentOrgCode" && ($scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "班组用户关联" || $scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "用户")) {
			//				searchTableType.attribute[key].proSearch.data = userOrgCodes;
			//			}
			if(key == "orgTypeName" && ($scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "班组用户关联" || $scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "用户")) {
				searchTableType.attribute[key].proSearch.data = '班组';
			}
		}
		//		将新增页面上的数据赋给查询模态框结束
	});

	$scope.svFunc = {};
	$scope.svFunc.onclick = {};
	$scope.svFunc.onclick.checkOptionData = function(pData, key, value) {
		//console.log("value", value);
		//console.log("pData[key]", pData[key]);
		//console.log('key', key);
		value.proSearch.data = pData[key];
		$scope.pM.vMember.pSearch.nowKey = key;
		$scope.svFunc.onclick.searchEnsure();
	};
	$scope.svFunc.onclick.searchEnsure = function() {
		$scope.showTable = true;
		$scope.pageSize = 10;
		if($scope.selPage == undefined) {
			$scope.selPage = 1;
		}
		viewGridProvider.hideCrtMnt($scope.pM.vMember.sapc.parentType);
		//console.log("$scope.pM.vMember.sapc.parentType", $scope.pM.vMember.sapc.parentType);
		//console.log($scope.pM.vMember.sapc);
		var json = viewGridProvider.getHttpData('proSearch', $scope.pM.vMember.sapc.parentType);
		//		var cnfgClassCode = $rootScope.cnfgClassCode;
		var tableName = $scope.main.vMember.sapc.tableType.jsonObj.key;
		if(tableName == 'T_IC_LIE_CUBA') {
			var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize + '&tankTypeName=' + '卧罐'+ '&inUse=1&dataStatus=1';
		} else if(tableName == 'T_IC_GLB_CUBA' || tableName == 'T_IC_GLBPRECOEF') {
			var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize + '&tankTypeName=' + '球罐'+ '&inUse=1&dataStatus=1';
		} else if(tableName == 'T_PM_ORG') {
			var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize + '&isDeploy=' + '0'+ '&inUse=1&dataStatus=1';
		} else if(tableName == 'T_IC_STDDM_CUAB' || tableName == 'T_IC_STDCMMM_CUAB' || tableName == 'T_IC_STD_SEC' || tableName == 'T_IC_FLTPER_CUAB' || tableName == 'T_IC_STDPRES_COEF') {
			var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize + '&tankTypeName=' + '立罐'+ '&inUse=1&dataStatus=1';
		} else if(tableName == 'T_IC_CNFG_TANK' || tableName == 'T_IC_CNFG_BASE') {
			var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize+ '&inUse=1&dataStatus=1';
		} else if(tableName == 'T_PM_ASSOCIATIVE') {
			var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize + '&leaf=leaf'+ '&inUse=1&dataStatus=1';
		} else if(tableName == 'T_PM_BIZORG_DTL') {
			var searchUrl = viewGridProvider.httpOrgPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize+ '&inUse=1&dataStatus=1';
		} else if(tableName == 'T_IC_SIMPLE_NODE_MAP') {
			if($scope.pM.vMember.sapc.parentType.jsonObj.url == '/nodes') {
				var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize + '&notNodeTypeCode=6'+ '&inUse=1&dataStatus=1';
			} else {
				var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize+ '&inUse=1&dataStatus=1';
			}
		} else {
			var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize+ '&inUse=1&dataStatus=1';
		}
		for(var key in json) {
			searchUrl += '&' + key + '=' + json[key];
		}
		//		if($scope.main.vMember.sapc.tableType.jsonObj.nameAlias == '班组用户关联') {
		//			var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj.url + '?rentCode=' + $scope.pM.vMember.sapc.parentType.attribute['rentCode'].proSearch.data;
		//		}
		console.log('搜索框查询所用url', searchUrl);
		viewGridProvider.httpCommit(searchUrl).then(function success(res) {
			$scope.pM.vMember.pSearch.searchState.result = "成功";
			$scope.pM.vMember.pSearch.page.totalItems = viewGridProvider.getRecordCount(res);
			$scope.pM.vMember.sapc.parentValue = cj.Parse(res.data, true);
			//-- -- -- -- -- -- -- -- --分页开始-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
			//分页总数
			//console.log("总条数", $scope.pM.vMember.pSearch.page.totalItems);
			//设置表格数据源(分页)
			$scope.pages = Math.ceil($scope.pM.vMember.pSearch.page.totalItems / $scope.pageSize); //分页数
			$scope.newPages = $scope.pages > 10 ? 10 : $scope.pages;
			$scope.pageList = [];
			$scope.setData = function() {
				$scope.items = $scope.pM.vMember.sapc.parentValue.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize)); //通过当前页数筛选出表格当前显示数据
			}
			$scope.items = $scope.pM.vMember.sapc.parentValue.slice(0, $scope.pageSize);
			//console.log('items', $scope.items);
			for(let i = 0; i < $scope.items.length; i++) {
				for(let key in $scope.items[i]) {
					if(key == 'dataStatus' || key == 'inUse') {
						$scope.items[i][key] = $scope.items[i][key] == 1 ? '是' : '否';
					}
				}
			}
			//分页要repeat的数组
			for(var i = $scope.selPage - 1; i < ($scope.newPages + $scope.selPage - 1 > $scope.pages ? $scope.pages : $scope.newPages + $scope.selPage - 1); i++) {
				$scope.pageList.push(i + 1);
			}
		}, function error(res) {
			cj.Error(res);
		});
	};
	//打印当前选中页索引
	$scope.selectPage = function(page) {
		//不能小于1大于最大
		if(page < 1 || page > $scope.pages) return;
		//最多显示分页数5
		if(page > 9) {
			//因为只显示5个页数，大于2页开始分页转换
			var newpageList = [];
			for(var i = (page - 10); i < (page > $scope.pages ? $scope.pages : page); i++) {
				newpageList.push(i + 1);
			}
			$scope.pageList = newpageList;

		}
		$scope.selPage = page;
		$scope.setData();
		$scope.isActivePage(page);
		//console.log('$scope.pageList', $scope.pageList);
		//console.log("选择的页：" + page);
		$scope.svFunc.onclick.searchEnsure();
	};
	//设置当前选中页样式
	$scope.isActivePage = function(page) {
		return $scope.selPage == page;
	};
	//上一页
	$scope.Previous = function() {
		$scope.selectPage($scope.selPage - 1);
	}
	//下一页
	$scope.Next = function() {
		$scope.selectPage($scope.selPage + 1);
	};
	//-- -- -- -- -- -- -- -- --分页结束-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
	//console.log("$scope.$parent.main.vMember.temLink", $scope.$parent.main.vMember.temLink);
	$scope.svFunc.onclick.setTemlData = function(data) {
		//如果没有linkValue.linkKeys,如果有则按linkKeys中的来
		//$rootScope.tankTypeCode = data.tankTypeCode;
		if(data.isUseFormula == 0) {
			$rootScope.flagIsFamula = true;
		}
		if(data.isUseFormula == 1) {
			$rootScope.flagIsFamula = false;
		};
		//console.log($rootScope.flagIsFamula);
		var proValue = $scope.$parent.main.vMember.temLink.selfKV.proWho;
		//console.log('输出proValue:', proValue);
		if(!$scope.$parent.main.vMember.temLink.selfKV.vt.linkKeys) {
			var selfKey = $scope.$parent.main.vMember.temLink.selfKV.kt;
			var mainKey = $scope.pM.vMember.sapc.parentType.mainKey;
			var alterKey = $scope.pM.vMember.sapc.parentType.alterKey;
			//console.log("selfKey", selfKey);
			//console.log("mainKey", mainKey);
			//console.log("alterKey", alterKey);

			$scope.$parent.main.vMember.sapc.tableType.attribute[selfKey][proValue].data = data[selfKey];
			if($scope.$parent.main.vMember.sapc.tableType.attribute.hasOwnProperty(mainKey)) {
				$scope.$parent.main.vMember.sapc.tableType.attribute[mainKey][proValue].data = data[mainKey];
			}
			if($scope.$parent.main.vMember.sapc.tableType.attribute.hasOwnProperty(alterKey)) {
				$scope.$parent.main.vMember.sapc.tableType.attribute[alterKey][proValue].data = data[alterKey];
			}
		} else {
			var linkKeys = $scope.$parent.main.vMember.temLink.selfKV.vt.linkKeys;
			for(var key in linkKeys) {
				$scope.$parent.main.vMember.sapc.tableType.attribute[key][proValue].data = data[linkKeys[key]];
			}
		}

		$state.go('app.uiGrid', {
			id: $stateParams.id
		}, {
			reload: false
		})
	}
	$scope.svFunc.onclick.cancel = function() {
		$state.go('app.uiGrid', {
			id: $stateParams.id
		}, {
			reload: false
		})
	}

}])