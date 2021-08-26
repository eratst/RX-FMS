'use strict';
var appUiGrid = angular.module('myApp.uiGrid', ['ui.router', 'ui.grid', 'ui.grid.selection', 'ui.grid.edit',
		'ui.grid.pagination', 'ui.grid.resizeColumns', 'ui.grid.autoResize', 'angularFileUpload'
	])
	.value('orgTree', {
		children: [],
		ifExpand: true
	})
	//打开页面路由与接收参数路由
	.config(['$stateProvider', function($stateProvider) {
		$stateProvider.state('uiGrid', {
			url: '/uiGrid/:id',
			cache: true,
			templateUrl: 'uiGrid/uiGrid.html',
			controller: 'uiGridCtrl',
			resolve: {
				tableIndex: function($http) {
					return $http({
						method: "GET",
						url: "model/tableIndex.json",
						responseType: "json"
					}).then(function success(res) {
						for(var key1 in res.data) {
							for(var key2 in res.data[key1].rows) {
								res.data[key1].rows[key2].key = key2;
								res.data[key1].rows[key2].type = key1;
							}
						}
						return res.data;
					}, function error(res) {
						console.info("error res-->", res);
					});
				}
			}
		})
		//			.state('uiGrid.parentModelSearch', {
		//				url: '/parentModelSearch/:idp',
		//				templateUrl: 'uiGrid/parentSearch.html',
		//				controller: 'uiparentModelSearchGridCtrl'
		//			});
	}])

	.controller('uiGridCtrl', ['$scope', '$filter', '$location', '$rootScope', '$http', 'viewGridProvider', 'orgTree',
		'$stateParams', '$state', 'FileUploader', '$interval', '$timeout', 'tableIndex', '$q',
		function($scope, $location, $filter, $rootScope, $http, viewGridProvider, orgTree, $stateParams, $state,
			FileUploader, $interval, $timeout, tableIndex, $q) {
			var jsonObj = viewGridProvider.getTableIndexJsonByKey(tableIndex, $stateParams.id);
			console.info("jsonObj init:", tableIndex);
			$scope.levelOrg = false;
			$scope.rentFlag = true;
			jsonObj.key = $stateParams.id;
			viewGridProvider.init();
			$scope.userCode = "ssh";
			//获取用户编码userCode,处理页面权限
//			$scope.authModfiy = true;
									$scope.authModfiy = false;
									initAuthority() ///初始化唯一方法

			$scope.measIndex = 3;
			//度量指标按钮
			$scope.measBtnShow = ($stateParams.id == 'T_PM_MEASINDEX') || ($stateParams.id == 'T_PM_ENMEASINDEX')
			//装置界区详情按钮
			$scope.detailBtnShow = ($stateParams.id == 'T_PM_UNITAREAREL')
			//禁止罐量计算配置基础分类，分类参数，公式参数的增删改查导入按钮显示
			$scope.BntShow = ($stateParams.id != 'T_IC_CNFG_CLASS_PARA') && ($stateParams.id !=
				'T_IC_CNFG_CLASS') && ($stateParams.id != 'T_IC_CNFG_FORMULA_PARA');
			//禁止导出按钮显示
			$scope.downloadBntShow = ($stateParams.id != 'T_IC_STDCMMM_CUAB') && ($stateParams.id !=
				'T_IC_STDDM_CUAB') && ($stateParams.id != 'T_IC_STDPRES_COEF') && ($stateParams.id !=
				'T_IC_VCF');
			//禁止内部组织机构新增，导入，编辑，多条删除功能显示，增加单条引入功能
			$scope.orgBtnShow = ($stateParams.id != 'T_PM_ORG')
			//禁止用户表新增，导入功能显示
			$scope.userBtnShow = ($stateParams.id != 'T_PM_USER')
			//禁止租户表新增，导入，删除功能显示
			$scope.rentBtnShow = ($stateParams.id != 'T_PM_RENT')
			//OUM机构单元增加多条引入功能
			$scope.oumOrgInsetShow = ($stateParams.id != 'T_PM_OUMORGUNIT')
			//OUM机构单元和OUM用户禁止新增，多条删除，导入，导出按钮显示
			$scope.removeBnt = ($stateParams.id != 'T_PM_OUMORGUNIT') && ($stateParams.id != 'T_PM_OUMUSER')
			//禁止组织机构层删除
			//		$scope.updateShow = ($stateParams.id != 'T_PM_HEADQUARTERS') && ($stateParams.id != 'T_PM_DIVISION') && ($stateParams.id != 'T_PM_ENTERPRISE') && ($stateParams.id != 'T_PM_OFFICES') && ($stateParams.id != 'T_PM_FACTORY') && ($stateParams.id != 'T_PM_DEPARTMENT') && ($stateParams.id != 'T_PM_WORKSHOP') && ($stateParams.id != 'T_PM_TEAM');
			$scope.updateShow = true

			$scope.error = '';
			$scope.main = {};
			$scope.main.vfunc = {};
			$scope.main.vfunc.onclick = {};
			$scope.main.vMember = {};
			$scope.main.vMember.sapc = {};
			$scope.main.vMember.sapc.tableType = {}
			$scope.main.vMember.state = {};
			$scope.main.vMember.state.title = '';
			$scope.main.vMember.state.content = [];
			//当前页面和子页面交互临时存放数据的地方
			$scope.main.vMember.temLink = {};
			/**
			 * ----------------------------------------初始化配置uigrid----------------------------------------------------------
			 * 
			 * */
			$scope.gridOptions = {
				data: [],
				enableSorting: true, //是否排序
				useExternalSorting: false, //是否使用自定义排序规则
				enableGridMenu: false, //是否显示右上角grid 菜单
				gridMenuTemplate: "ui-grid/uiGridMenu",
				showGridFooter: false, //是否显示grid footer-->totle Items;
				enableHorizontalScrollbar: 1, //grid水平滚动条是否显示, 0-不显示  1-显示
				enableVerticalScrollbar: 0, //grid垂直滚动条是否显示, 0-不显示  1-显示
				//-------- 分页属性 ----------------
				enablePagination: true, //是否分页，默认为true
				enablePaginationControls: false, //使用默认的底部分页
				enableColumnResizing: true,
				paginationPageSizes: [15, 30, 50], //每页显示个数可选项
				paginationCurrentPage: 1, //当前页码
				paginationPageSize: 15, //每页显示个数
				//paginationTemplate: "", //自定义底部分页代码
				totalItems: 99, // 总数量
				useExternalPagination: true, //是否使用分页按钮
				//----------- 选中 ----------------------
				enableFooterTotalSelected: true, // 是否显示选中的总数，默认为true, 如果显示，showGridFooter 必须为true
				enableFullRowSelection: true, //是否点击行任意位置后选中,默认为false,当为true时，checkbox可以显示但是不可选中
				enableRowHeaderSelection: false, //是否显示
				enableRowSelection: true, // 行选择是否可用，默认为true;
				enableSelectAll: true, // 选择所有checkbox是否可用，默认为true; 
				enableSelectionBatchEvent: true, //默认true
				isRowSelectable: function(row) { //GridRow
					if(row.entity.age > 45) {
						row.grid.api.selection.selectRow(row.entity); // 选中行
					}
				},
				modifierKeysToMultiSelect: false, //默认false,为true时只能 按ctrl或shift键进行多选, multiSelect 必须为true;
				multiSelect: true, // 是否可以选择多个,默认为true;
				noUnselect: false, //默认false,选中后是否可以取消选中
				selectionRowHeaderWidth: 30, //默认30 ，设置选择列的宽度；
				//selectionRowHeaderClass:"blue",
				//---------------api---------------------
				onRegisterApi: function(gridApi) {
					$scope.gridApi = gridApi;
					//分页按钮事件
					gridApi.pagination.on.paginationChanged($scope, function(curPage, pageSize) {
						if(initGridOptionsData) {
							initGridOptionsData(curPage, pageSize);
						}
					});
					//行选中事件
					$scope.gridApi.selection.on.rowSelectionChanged($scope, function(row, event) {
						if(row) {
							//$scope.testRow = row.entity;
							//console.log($scope.gridApi);
							//row.grid.api.selection.selectRow(row.entity);
						}
					});
				}
			};
			/**
			 * 全选-取消全选之间切换
			 */
			$scope.main.vfunc.onclick.selectAllRows = function() {
				if(!$scope.gridApi.grid.appScope.selection.selectAll) {
					$scope.gridApi.selection.clearSelectedRows();
				} else {
					$scope.gridApi.selection.selectAllRows();
				}
			};
			/**
			 * 跳转首页
			 */
			$scope.main.vfunc.onclick.goFirstPage = function() {
				$scope.gridOptions.paginationCurrentPage = 1;
			}
			/**
			 * 跳转尾页
			 */
			$scope.main.vfunc.onclick.goLastPage = function() {
				var a = [];
				var b = [];
				b[0] = $scope.gridApi.pagination.getTotalPages();
				angular.copy(b, a);
				$scope.gridOptions.paginationCurrentPage = a[0];
			}
			$scope.userOptionsAdd = {
				data: [],
				enableSorting: true, //是否排序
				useExternalSorting: false, //是否使用自定义排序规则
				enableGridMenu: false, //是否显示右上角grid 菜单
				showGridFooter: false, //是否显示grid footer-->totle Items;
				enableHorizontalScrollbar: 1, //grid水平滚动条是否显示, 0-不显示  1-显示
				enableVerticalScrollbar: 0, //grid垂直滚动条是否显示, 0-不显示  1-显示
				//-------- 分页属性 ----------------
				enablePagination: true, //是否分页，默认为true
				enablePaginationControls: false, //使用默认的底部分页
				enableColumnResizing: true,
				paginationPageSizes: [10, 20, 30], //每页显示个数可选项
				paginationCurrentPage: 1, //当前页码
				paginationPageSize: 10, //每页显示个数
				totalItems: 99, // 总数量
				useExternalPagination: true, //是否使用分页按钮
				//----------- 选中 ----------------------
				enableFooterTotalSelected: true, // 是否显示选中的总数，默认为true, 如果显示，showGridFooter 必须为true
				enableFullRowSelection: true, //是否点击行任意位置后选中,默认为false,当为true时，checkbox可以显示但是不可选中
				enableRowHeaderSelection: false, //是否显示
				enableRowSelection: true, // 行选择是否可用，默认为true;
				enableSelectAll: false, // 选择所有checkbox是否可用，默认为true; 
				enableSelectionBatchEvent: true, //默认true
				isRowSelectable: function(row) { //GridRow
					if(row.entity.age > 45) {
						row.grid.api.selection.selectRow(row.entity); // 选中行
					}
				},
				modifierKeysToMultiSelect: false, //默认false,为true时只能 按ctrl或shift键进行多选, multiSelect 必须为true;
				multiSelect: true, // 是否可以选择多个,默认为true;
				noUnselect: false, //默认false,选中后是否可以取消选中
				selectionRowHeaderWidth: 30, //默认30 ，设置选择列的宽度；
				//---------------api---------------------
				onRegisterApi: function(userAGridApi) {
					$scope.userAGridApi = userAGridApi;
					//分页按钮事件
					userAGridApi.pagination.on.paginationChanged($scope, function(curPage, pageSize) {
						if(initAGridOptionsUserRelation) {
							initAGridOptionsUserRelation(curPage, pageSize, '');
						}
					});
					//行选中事件
					$scope.userAGridApi.selection.on.rowSelectionChanged($scope, function(row, event) {
						if(row) {}
					});
				}
			};

			/**
			 * 跳转首页
			 */
			$scope.main.vfunc.onclick.goFirstPageUserA = function() {
				$scope.userOptionsAdd.paginationCurrentPage = 1;
			}
			/**
			 * 跳转尾页
			 */
			$scope.main.vfunc.onclick.goLastPageUserA = function() {
				var a = [];
				var b = [];
				b[0] = $scope.userAGridApi.pagination.getTotalPages();
				angular.copy(b, a);
				$scope.userOptionsAdd.paginationCurrentPage = a[0];
			}
			$scope.userOptionsUpdate = {
				data: [],
				enableSorting: true, //是否排序
				useExternalSorting: false, //是否使用自定义排序规则
				enableGridMenu: false, //是否显示右上角grid 菜单
				showGridFooter: false, //是否显示grid footer-->totle Items;
				enableHorizontalScrollbar: 1, //grid水平滚动条是否显示, 0-不显示  1-显示
				enableVerticalScrollbar: 0, //grid垂直滚动条是否显示, 0-不显示  1-显示
				//-------- 分页属性 ----------------
				enablePagination: true, //是否分页，默认为true
				enablePaginationControls: false, //使用默认的底部分页
				enableColumnResizing: true,
				paginationPageSizes: [10, 20, 30], //每页显示个数可选项
				paginationCurrentPage: 1, //当前页码
				paginationPageSize: 10, //每页显示个数
				totalItems: 99, // 总数量
				useExternalPagination: true, //是否使用分页按钮
				//----------- 选中 ----------------------
				enableFooterTotalSelected: true, // 是否显示选中的总数，默认为true, 如果显示，showGridFooter 必须为true
				enableFullRowSelection: true, //是否点击行任意位置后选中,默认为false,当为true时，checkbox可以显示但是不可选中
				enableRowHeaderSelection: false, //是否显示
				enableRowSelection: true, // 行选择是否可用，默认为true;
				enableSelectAll: false, // 选择所有checkbox是否可用，默认为true; 
				enableSelectionBatchEvent: true, //默认true
				isRowSelectable: function(row) { //GridRow
					if(row.entity.age > 45) {
						row.grid.api.selection.selectRow(row.entity); // 选中行
					}
				},
				modifierKeysToMultiSelect: false, //默认false,为true时只能 按ctrl或shift键进行多选, multiSelect 必须为true;
				multiSelect: true, // 是否可以选择多个,默认为true;
				noUnselect: false, //默认false,选中后是否可以取消选中
				selectionRowHeaderWidth: 30, //默认30 ，设置选择列的宽度；
				//---------------api---------------------
				onRegisterApi: function(userUGridApi) {
					$scope.userUGridApi = userUGridApi;
					//分页按钮事件
					userUGridApi.pagination.on.paginationChanged($scope, function(curPage, pageSize) {
						if(initUGridOptionsUserRelation) {
							initUGridOptionsUserRelation(curPage, pageSize, '');
						}
					});
					//行选中事件
					$scope.userUGridApi.selection.on.rowSelectionChanged($scope, function(row, event) {
						if(row) {}
					});
				}
			};

			/**
			 * 跳转首页
			 */
			$scope.main.vfunc.onclick.goFirstPageUserU = function() {
				$scope.userOptionsUpdate.paginationCurrentPage = 1;
			}
			/**
			 * 跳转尾页
			 */
			$scope.main.vfunc.onclick.goLastPageUserU = function() {
				var a = [];
				var b = [];
				b[0] = $scope.userUGridApi.pagination.getTotalPages();
				angular.copy(b, a);
				$scope.userOptionsUpdate.paginationCurrentPage = a[0];
			}
			/**
			 * ----------------------------------------初始化配置uigrid结束----------------------------------------------------------
			 * 
			 * */
			/**
			 * 点击checkBox时不转换状态的bug修复
			 */
			$scope.main.vfunc.onclick.checkBox = function(row) {
				$scope.insertOumOrg = false; //$scope.insertOumOrg用于判断OUM机构单元表是否引入属性为“是”时不能引入
				//$scope.insertTeam = false; //$scope.insertTeam用于判断用户表用户所属机构属性不一致时不能加入
				row.isSelected = !row.isSelected;
			};
			/**
			 * 初始化-----获取权限---------仅在刷新页面时候调用一次
			 */
			$scope.welcomeUser = ""

			function initAuthority() {
				if(localStorage.getItem('testRent')) {
					$scope.authModfiy = false;
					getAuthModfiy()
				} else {
					var getUserCodeUrl = localStorage.getItem('UserCodeUrl');
					viewGridProvider.httpCommit(getUserCodeUrl).then(function success(res) {
						var userCode = $.ET.toObjectArr(res.data);
						$scope.welcomeUser = userCode
						$scope.userCode = userCode[0].username
						//localStorage.setItem('userCode', userCode[0].username);
						$scope.authModfiy = false;
						getAuthModfiy()
					}, function error(res) {});

				}
			}

			function getAuthModfiy() {
				var authUrl = viewGridProvider.httpPort() + '/aaaProperties' + '?userCode=' + $scope.userCode;
				viewGridProvider.httpCommit(authUrl).then(function success(res) {
					var authArr = $.ET.toObjectArr(res.data);
					console.log('权限属性返回', authArr);
					for(var i = 0; i < authArr.length; i++) {
						if(jsonObj.key == "T_SYSTEM_RESOURCE" || jsonObj.key == "T_SYSTEM_RECIPIENT" ||
							jsonObj.key == "T_SYSTEM_MESSAGECONFIG") {
							if(authArr[i].value == "FMS_T_PM_RENT") {
								$scope.authModfiy = true;
								break;
							}
						} else if(jsonObj.hasOwnProperty("bizType")) {
							//							jsonObj.key == "T_PM_UNITAREA" || jsonObj.key == "T_PM_UNITAREAREL"|| jsonObj.key == "T_PM_ENPIPENET"|| jsonObj.key == "T_PM_ENNODE"
							//业务模型权限取决于物料表
							if(authArr[i].value == "FMS_T_PM_MTRL") {
								$scope.authModfiy = true;
								break;
							}
						} else if(jsonObj.key == "T_PM_STATION") {
							if(authArr[i].value == "FMS_T_PM_TANKAREA") {
								$scope.authModfiy = true;
								break;
							}
						} else {
							if(authArr[i].value == "FMS_" + jsonObj.key) {
								$scope.authModfiy = true;
								break;
							}
						}
					}
					initGridOptionsColumnDefs(jsonObj); //仅在初始化调用了一次，其余活动未重复调用
					initGridOptionsUserA("T_PM_USER.json"); //仅在新增弹框的初始化调用
					initGridOptionsUserU("T_PM_USER.json"); //仅在编辑弹框的初始化调用
					if($scope.authModfiy != true && jsonObj.key == "T_PM_RENT")
						$scope.rentFlag = false;
				}, function error(res) {});
			}
//			initGridOptionsColumnDefs(jsonObj); //仅在初始化调用了一次，其余活动未重复调用
//			initGridOptionsUserA("T_PM_USER.json"); //仅在新增弹框的初始化调用
//			initGridOptionsUserU("T_PM_USER.json"); //仅在编辑弹框的初始化调用
			/**
			 * 初始化页面-----封装数据格式和JSON，初始化表格---------仅在刷新页面调用一次
			 */
			function initGridOptionsColumnDefs(jsonObj) {

				var fileName = 'model/' + jsonObj.tableFormat;
				viewGridProvider.httpCommit(fileName).then(function success(res) {
					//1.隐藏修改人创建人
					//2.parentModel转parent
					viewGridProvider.hideCrtMnt(res.data);
					//度量指标
					//					viewGridProvider.hideCrtMnt($scope.main.vMember.sapc.tableType);

					//1.储存数据
					$scope.main.vMember.sapc.tableType = res.data;
					//2.给原数据增加jsonObj
					$scope.main.vMember.sapc.tableType.jsonObj = jsonObj;
					//3.原数据类型
					$scope.main.vMember.sapc.tableType.type = jsonObj.type;
					console.log("当前表json", $scope.main.vMember.sapc.tableType);
					initGridOptionsData(1, $scope.gridOptions.paginationPageSize); //增删改查也调用一次
					/**构建gridOptions.columnDefs*/
					//2.字段转换，将原字段设置成columDefs中需要的字段
					$scope.gridOptions.columnDefs = viewGridProvider.formatGridOptionsColumnDefs(res.data
						.attribute, $scope.BntShow, $scope.updateShow, $scope.removeBnt, $scope
						.orgBtnShow, $scope.authModfiy, $scope.rentBtnShow);
				}, function error(res) {
					cj.Error(res);
				});
			};
			/**
			 * 增删改查导入导出引入成功后调用此方法刷新页面数据---特例：租户表
			 */
			function initGridOptionsData(curPage, pageSize) {
				let bizUrl;
				let tableType = $scope.main.vMember.sapc.tableType
				let obj = {
					"busiArea": 'fms_mtrl',
					"energyMng": 'fms_em',
					"operMng": 'fms_ope'
				}
				if(tableType.jsonObj.hasOwnProperty("bizType")) {
					//					bizUrl = '/bizs/fms_mtrl' + jsonObj.url
					bizUrl = '/bizs/' + obj[tableType.jsonObj.bizType] + jsonObj.url //测试能源管理时租户、biz为fms_em
				} else {
					bizUrl = jsonObj.url
				}
				if($scope.rentFlag) {
					var interUrl = viewGridProvider.httpPort() + bizUrl +
						'?$skip=' + (curPage - 1) * pageSize + '&$top=' + pageSize +
						viewGridProvider.getSearchKVUrl(tableType);
				} else {
					var interUrl = viewGridProvider.httpPort() + jsonObj.url + "/" + localStorage.getItem(
							'rentCode') +
						'?$skip=' + (curPage - 1) * pageSize + '&$top=' + pageSize +
						viewGridProvider.getSearchKVUrl(tableType);
				}
				if($scope.levelOrg) {
					interUrl = interUrl + '&isRecursive=1';
				} else if(!$scope.levelOrg) {
					interUrl = interUrl + '&isRecursive=0';
				}
				if(tableType.jsonObj.key == "T_PM_MEASINDEX" || tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
					//度量指标        区域1      组织机构类型2  节点3 
					interUrl = $scope.measIndex == 1 ? interUrl + '&ofMeasindexType=1' : $scope.measIndex == 2 ? interUrl + '&ofMeasindexType=2' : interUrl + '&ofMeasindexType=3';
				}

//				console.log("查询当前表数据所用url", interUrl);
				viewGridProvider.httpCommit(interUrl).then(function success(res) {
					for(var i = 0; i < res.data.collection.page.data.length; i++) {
						if(res.data.collection.page.data[i].name == "recordCount" ||
							res.data.collection.page.data[i].name == "totalElements") {
							$scope.gridOptions.totalItems = res.data.collection.page.data[i].value;
						}
					}
					$scope.gridOptions.data = cj.Parse(res.data, true);
//					console.log('当前页数据', $scope.gridOptions.data);
					viewGridProvider.changeBooleanToCh($scope.main.vMember.sapc.tableType, $scope
						.gridOptions.data);
					$scope.gridApi.selection.clearSelectedRows();
					$scope.main.vfunc.onclick.serchButton();
				}, function error(res) {
					cj.Error(res);
				});
			};
			/**
			 * "增加"按钮
			 * 1.获取关联表信息
			 * 2.清空编辑框绑定的数据
			 * ---------------------------------------新增开始---------------------------------------------------------
			 */
			/**
			 * "增加"初始化
			 */
			$scope.main.vfunc.onclick.addButton = function() {
				var tableType = $scope.main.vMember.sapc.tableType;
				viewGridProvider.setPartFlag($scope.main.vMember.sapc.tableType, $scope.measIndex);
				viewGridProvider.initParentTable($scope.main.vMember.sapc.tableType, tableIndex);
				viewGridProvider.setDefalutValue($scope.main.vMember.sapc.tableType, 'proAdd',$scope.measIndex);
				console.log('$scope.main.vMember.sapc.tableType', $scope.main.vMember.sapc.tableType)
				$scope.areaAlias = [];
				$scope.flagIsFamula = true;

				if(tableType.jsonObj.key == "T_PM_USER") {
					for(var key in tableType.attribute) {
						if(key == 'userCode' || key == 'userName' || key == 'userOrgAlias' || key ==
							'jobDesc' || key == 'email' ||
							key == 'tel' || key == 'mobile' || key == 'sex' || key == 'birthday' || key ==
							'employeeId' || key == 'sortNum' || key == 'des' || key == 'inUse') {
							tableType.attribute[key].proAdd.show = true;
							tableType.attribute[key].proAdd.edit = true;
						} else if(key == 'orgName' || key == 'orgCode') {
							tableType.attribute[key].proAdd.show = false;
						}
					}
				} else if(tableType.jsonObj.key == "T_PM_MEASINDEX" || tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
					for(var key in tableType.attribute) {
						//						if((key == "areaAlias" && $scope.measIndex) || (key == "nodeAlias" && !$scope.measIndex)) {
						//							tableType.attribute[key].proAdd.show = true;
						//						} else if((key == "areaAlias" && !$scope.measIndex) || (key == "nodeAlias" && $scope.measIndex)) {
						//							tableType.attribute[key].proAdd.show = false;
						//						}
						//先判断新增是否显示，再消除之前的影响
						if((key == "nodeAlias" && $scope.measIndex == 3) || (key == "areaAlias" && $scope.measIndex == 1) || (key == "orgAlias" && $scope.measIndex == 2)) {
							tableType.attribute[key].proAdd.show = true;
						} else if((key == "nodeAlias" && $scope.measIndex != 3) || (key == "areaAlias" && $scope.measIndex != 1) || (key == "orgAlias" && $scope.measIndex != 2)) {
							tableType.attribute[key].proAdd.show = false;
						}

					}
				}
			}
			$scope.main.vfunc.onclick.logout = function() {
					window.location.href = "logout"
				},
				$scope.main.vfunc.onclick.addButtonS = function() {
					viewGridProvider.setPartFlag($scope.main.vMember.sapc.tableType);
					viewGridProvider.initParentTable($scope.main.vMember.sapc.tableType, tableIndex);
					viewGridProvider.setDefalutValue($scope.main.vMember.sapc.tableType);
					$scope.areaAlias = [];
					$scope.flagIsFamula = true;
					if($scope.main.vMember.sapc.tableType.jsonObj.key == "T_PM_USER") {
						var tableType = $scope.main.vMember.sapc.tableType;
						for(var key in tableType.attribute) {
							if(key == 'userCode' || key == 'userName' || key == 'userOrgAlias' || key ==
								'jobDesc' || key == 'email' ||
								key == 'tel' || key == 'mobile' || key == 'sex' || key == 'birthday' || key ==
								'employeeId' || key == 'sortNum' || key == 'des' || key == 'inUse') {
								tableType.attribute[key].proAdd.show = false;
								tableType.attribute[key].proAdd.edit = false;
							} else if(key == 'orgName' || key == 'orgCode') {
								tableType.attribute[key].proAdd.show = true;
							}
						}
					}
				}
			/**
			 * "增加"确认事件
			 */
			$scope.main.vfunc.onclick.addDatasEnsure = function() {
				var tableType = $scope.main.vMember.sapc.tableType;
				$scope.tempreset = "增加中";
				$scope.error = '';
				for(var key in tableType.attribute) {
					if(key == 'capacity' || key == 'initialAssetValue' || key == 'netAssetValue') {
						if(tableType.attribute[key].proAdd.data) {
							tableType.attribute[key].proAdd.data = viewGridProvider.changeData(tableType
								.attribute[key].proAdd.data);
						}
					} else if(key == 'parentOrgAlias') {
						if(tableType.attribute[key].proAdd.data == '') {
							tableType.attribute['parentOrgAlias'].proAdd.data = '';
							tableType.attribute['parentOrgCode'].proAdd.data = '';
						}
					} else if(key == 'startTime' || key == 'birthday') {
						if(tableType.attribute[key].proAdd.data) {
							tableType.attribute[key].proAdd.data = document.getElementById('getAddTime').value +
								" 00:00:00";
						}
					} else if(key == 'crtUserId' || key == 'crtUserCode' || key == 'crtUserName' ||
						key == 'mntUserId' || key == 'mntUserCode' || key == 'mntUserName') {
						tableType.attribute[key].proAdd.data = $scope.userCode;
					} else if(key == 'bizCode') {
						localStorage.setItem('bizCode', tableType.attribute[key].proAdd.data);
					} else if(key == 'ofMeasindexType') {
						//						tableType.attribute[key].proAdd.data = $scope.measIndex ? 1 : 0;
						tableType.attribute[key].proAdd.data = $scope.measIndex;
					} else if(key == 'userCode') {
						tableType.attribute[key].proAdd.show = true;
					}
				}
				var json;
				if($scope.selectUserARelation.length > 0) {
					for(let i = 0; i < $scope.selectUserARelation.length; i++) {
						$scope.selectUserARelation[i].positionId = tableType.attribute['positionId'].proAdd
							.data;
						$scope.selectUserARelation[i].positionName = tableType.attribute['positionName'].proAdd
							.data;
						$scope.selectUserARelation[i].positionCode = tableType.attribute['positionCode'].proAdd
							.data;
							
//						$scope.selectUserARelation[i].orgName = tableType.attribute['orgName'].proAdd.data;
							
						$scope.selectUserARelation[i].orgCode = tableType.attribute['orgCode'].proAdd.data;
						$scope.selectUserARelation[i].orgId = tableType.attribute['orgId'].proAdd.data;
						$scope.selectUserARelation[i].inUse = tableType.attribute['inUse'].proAdd.data;
						$scope.selectUserARelation[i].inUse = tableType.attribute['isReliable'].proAdd.data;
						$scope.selectUserARelation[i].sortNum = 1;
						$scope.selectUserARelation[i].crtUserCode = $scope.userCode;
						$scope.selectUserARelation[i].crtUserName = $scope.userCode;
						$scope.selectUserARelation[i].mntUserCode = $scope.userCode;
						$scope.selectUserARelation[i].mntUserName = $scope.userCode;
						$scope.selectUserARelation[i].des = tableType.attribute['des'].proAdd.data;
					}
					console.info("增加所用restful-->", cj.parseCjArray($scope.selectUserARelation));
					json = cj.parseCjArray($scope.selectUserARelation);
				} else if($scope.selectUserARelation.length == 0) {
					json = viewGridProvider.getHttpData('proAdd', $scope.main.vMember.sapc.tableType);
					console.info("增加所用restful-->", JSON.stringify(json));
				}
				var alterUrl;
				if(tableType.jsonObj.hasOwnProperty("bizType")) {
					alterUrl = '/bizs/' + tableType.attribute['bizCode'].proAdd.data + jsonObj.url
					if(tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
						alterUrl = $scope.measIndex == 1 ? alterUrl + '?ofMeasindexType=1' : $scope.measIndex == 2 ? alterUrl + '?ofMeasindexType=2' : alterUrl + '?ofMeasindexType=3';
					}
				} else if(tableType.jsonObj.key == "T_PM_MEASINDEX") {
					//					alterUrl = $scope.measIndex ? jsonObj.url + '?ofMeasindexType=1' : jsonObj.url + '?ofMeasindexType=0';
					alterUrl = $scope.measIndex == 1 ? jsonObj.url + '?ofMeasindexType=1' : $scope.measIndex == 2 ? jsonObj.url + '?ofMeasindexType=2' : jsonObj.url + '?ofMeasindexType=3';
				} else {
					alterUrl = jsonObj.url
				}
				console.log("增加所用url-->", viewGridProvider.httpPort() + alterUrl);
				viewGridProvider.httpPost(alterUrl, json).then(function success(res) {
					console.info("增加返回数据res-->", res);
					if(res.data.collection.hasOwnProperty("templates")) {
						$scope.tempreset = "增加成功";
						$timeout(function() {
							angular.element("#tempresetModel").modal("hide")
						}, 1000);
						//新增成功后清空数据
						for(var key in tableType.attribute) {
							$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = '';
							if(key == "sortNum") {
								$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = 1;
							}
						}
						/*刷新数据*/
						angular.element("#insertWindow").modal("hide")
						initGridOptionsData($scope.gridOptions.paginationCurrentPage, $scope.gridOptions
							.paginationPageSize);
					} else {}
				}, function error(res) {
					console.info("增加失败error res-->", res);
					$scope.tempreset = "增加失败";
					for(var key in tableType.attribute) {
						if(key == 'capacity' || key == 'initialAssetValue' || key == 'netAssetValue') {
							if(tableType.attribute[key].proAdd.data) {
								tableType.attribute[key].proAdd.data /= 10000;
							}
						}
					}
					$scope.error = res.data.collection.error.message;
				});
			};
			/*
			 *新增弹框点击取消后清空模态框数据
			 */
			$scope.main.vfunc.onclick.addDatasRemove = function() {
				var tableType = $scope.main.vMember.sapc.tableType;
				$scope.areaAlias = [];
				for(var key in tableType.attribute) {
					$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = '';
					if(key == "sortNum") {
						$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = 1;
					}
				}
			}
			/**
			 * ---------------------------------------新增结束---------------------------------------------------------------
			 */
			/**
			 * OUM机构单元多条引入
			 */
			$scope.main.vfunc.onclick.multiInsertOrg = function() {
				$scope.insertOumOrg = false;
				$scope.selectedList = $scope.gridApi.selection.getSelectedRows()
				for(let i = 0; i < $scope.selectedList.length; i++) {
					if($scope.selectedList[i]['isDeploy'] == '是') {
						$scope.insertOumOrg = true;
					}
				}
			}
			/**
			 * "引入"按钮
			 * 1.内部组织机构
			 * 2.单条引入
			 * ---------------------------------------单条引入开始------------------------------------------------------------
			 */
			/**
			 * 引入组装数据
			 */
			$scope.insButtonOver = function() {
				$scope.insertUrl = '/orgs';
				var tableType = $scope.main.vMember.sapc.tableType;
				tableType.attribute['orgCode'].proAdd.data = tableType.attribute['orgUnitCode'].proAdd.data;
				tableType.attribute['orgName'].proAdd.data = tableType.attribute['orgUnitName'].proAdd.data;
				tableType.attribute['orgAlias'].proAdd.data = tableType.attribute['orgUnitAlia'].proAdd.data;
				tableType.attribute['sortNum'].proAdd.data = 1;
				for(let key in tableType.attribute) {
					if(key == 'orgTypeCode') {
						//组织机构类型为企业，企业性质类型赋默认值
						if(tableType.attribute[key].proAdd.data == "1003") {
							tableType.attribute['entrTypeId'].proAdd.data = 1;
							tableType.attribute['entrTypeName'].proAdd.data = '股份';
						}
						//组织机构类型为生产工厂，工艺类型，所属板块类型赋默认值
						if(tableType.attribute[key].proAdd.data == "1005") {
							tableType.attribute['businessTypeId'].proAdd.data = 1;
							tableType.attribute['businessTypeName'].proAdd.data = '炼油';
							tableType.attribute['fctrBlockTypeId'].proAdd.data = 1;
							tableType.attribute['fctrBlockTypeName'].proAdd.data = '炼油板块';
						}
					}
				}
			}
			/**
			 * 引入确定事件
			 */
			$scope.main.vfunc.onclick.insDatasEnsure = function() {
				$scope.tempreset = "引入中";
				$scope.error = '';
				var tableType = $scope.main.vMember.sapc.tableType;
				var json = viewGridProvider.getHttpData('proAdd', $scope.main.vMember.sapc.tableType);
				console.info("引入所用数据-->", JSON.stringify(json));
				console.log("引入所用url-->", viewGridProvider.httpPort() + $scope.insertUrl);
				viewGridProvider.httpPost($scope.insertUrl, json).then(function success(res) {
					console.info("引入返回数据res-->", res);
					if(res.data.collection.hasOwnProperty("templates")) {
						$scope.tempreset = "引入成功";
						$timeout(function() {
							angular.element("#tempresetModel").modal("hide")
						}, 1000);
						for(var key in tableType.attribute) {
							$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = '';
							if(key == "sortNum") {
								$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = 1;
							}
						}
						angular.element("#insWindow").modal("hide")
						initGridOptionsData($scope.gridOptions.paginationCurrentPage, $scope.gridOptions
							.paginationPageSize);
					} else {}
				}, function error(res) {
					$scope.tempreset = "引入失败";
					$scope.error = res.data.collection.error.message;
					console.log("引入失败返回数据", res.data);
				});
			};
			/**
			 * ---------------------------------------单条引入结束------------------------------------------------------------
			 */
			/**
			 * "引入"按钮
			 * 1.OUM机构单元
			 * 2.多条引入
			 * ---------------------------------------多条引入开始------------------------------------------------------------
			 */
			/**
			 * 引入组装数据
			 */
			$scope.multiInsButtonOver = function() {
				$scope.selectedList = $scope.gridApi.selection.getSelectedRows()
				var tableType = $scope.main.vMember.sapc.tableType;
				if(tableType.jsonObj.key == "T_PM_OUMORGUNIT") {
					$scope.insertUrl = '/orgs';
					for(let i = 0; i < $scope.selectedList.length; i++) {
						for(let keys in $scope.selectedList[i]) {
							if(keys == 'orgUnitCode') {
								$scope.selectedList[i]['orgCode'] = $scope.selectedList[i][keys]
							}
							if(keys == 'orgUnitName') {
								$scope.selectedList[i]['orgName'] = $scope.selectedList[i][keys]
							}
							if(keys == 'orgUnitAlia') {
								$scope.selectedList[i]['orgAlias'] = $scope.selectedList[i][keys]
							}
						}
						$scope.selectedList[i]['sortNum'] = 1;
						$scope.selectedList[i]['mntUserId'] = $scope.userCode;
						$scope.selectedList[i]['mntUserName'] = $scope.userCode;
						$scope.selectedList[i]['orgTypeId'] = tableType.attribute['orgTypeId'].proAdd.data;
						$scope.selectedList[i]['orgTypeCode'] = tableType.attribute['orgTypeCode'].proAdd.data;
						$scope.selectedList[i]['orgTypeName'] = tableType.attribute['orgTypeName'].proAdd.data;
						$scope.selectedList[i]['upperOrgCode'] = tableType.attribute['upperOrgCode'].proAdd
							.data;
						$scope.selectedList[i]['upperOrgAlias'] = tableType.attribute['upperOrgAlias'].proAdd
							.data;
						if($scope.selectedList[i]['orgTypeCode'] == "1003") {
							$scope.selectedList[i]['entrTypeId'] = 1;
							$scope.selectedList[i]['entrTypeName'] = '股份';
						}
						if($scope.selectedList[i]['orgTypeCode'] == "1005") {
							$scope.selectedList[i]['businessTypeId'] = 1;
							$scope.selectedList[i]['businessTypeName'] = '炼油';
							$scope.selectedList[i]['fctrBlockTypeId'] = 1;
							$scope.selectedList[i]['fctrBlockTypeName'] = '炼油板块';
						}
					}
				} else if(tableType.jsonObj.key == "T_PM_OUMUSER") {
					$scope.insertUrl = '/users';
					for(let i = 0; i < $scope.selectedList.length; i++) {
						$scope.selectedList[i]['sortNum'] = 1;
						$scope.selectedList[i]['mntUserCode'] = $scope.userCode;
						$scope.selectedList[i]['mntUserName'] = $scope.userCode;
						$scope.selectedList[i]['crtUserCode'] = $scope.userCode;
						$scope.selectedList[i]['crtUserName'] = $scope.userCode;
						$scope.selectedList[i]['orgId'] = $scope.selectedList[i]['orgUnitId'];
						$scope.selectedList[i]['userOrgNames'] = 'admin';
						$scope.selectedList[i]['userOrgAlias'] = 'admin';
						$scope.selectedList[i]['userOrgCodes'] = 'admin';
					}
				}
			}
			$scope.main.vfunc.onclick.multiInsDatasEnsure = function() {
				$scope.tempreset = "引入中";
				$scope.error = '';
				var tableType = $scope.main.vMember.sapc.tableType;
				for(let i = 0; i < $scope.selectedList.length; i++) {
					if($scope.selectedList[i]['sex'] == "女") {
						$scope.selectedList[i]['sex'] = 2;
					} else if($scope.selectedList[i]['sex'] == "男") {
						$scope.selectedList[i]['sex'] = 1;
					}
				}
				console.info("引入所用数据-->", cj.parseCjArray($scope.selectedList));
				console.log("引入所用url-->", viewGridProvider.httpPort() + $scope.insertUrl);
				viewGridProvider.httpPost($scope.insertUrl, cj.parseCjArray($scope.selectedList)).then(
					function success(res) {
						console.info("引入返回数据res-->", res);
						if(res.data.collection.hasOwnProperty("templates")) {
							$scope.tempreset = "引入成功";
							$timeout(function() {
								angular.element("#tempresetModel").modal("hide")
							}, 1000);
							for(var key in tableType.attribute) {
								$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = '';
								if(key == "sortNum") {
									$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = 1;
								}
							}
							angular.element("#multiInsertWindow").modal("hide")
							initGridOptionsData($scope.gridOptions.paginationCurrentPage, $scope.gridOptions
								.paginationPageSize);
						} else {}
					},
					function error(res) {
						$scope.tempreset = "引入失败";
						$scope.error = res.data.collection.error.message;
						console.log("引入失败返回数据", res.data);
					});
			};
			/**
			 * ---------------------------------------多条引入结束------------------------------------------------------------
			 */
			/**
			 * 用户表多条加入班组
			 */
			$scope.main.vfunc.onclick.multiInsertTeam = function() {
				//				$scope.insertTeam = false;
				$scope.selectedListTeam = $scope.gridApi.selection.getSelectedRows()
				//console.log('当前选择数据', $scope.selectedListTeam)
				//				for(let i = 0; i < $scope.selectedListTeam.length; i++) {
				//					if($scope.selectedListTeam.length > 1) {
				//						if($scope.selectedListTeam[i]['userOrgAlias'] != $scope.selectedListTeam[i + 1]['userOrgAlias']) {
				//							$scope.insertTeam = true;
				//						}
				//					}
				//					break
				//				}
				//				$scope.main.vMember.sapc.tableType.attribute['userOrgCodes'].proAdd.data = $scope.selectedListTeam[0]['userOrgCodes']
			}
			/**
			 * "加入班组"按钮
			 * 1.用户
			 * 2.多条加入
			 * ---------------------------------------多条加入开始------------------------------------------------------------
			 */
			/**
			 * 加入组装数据
			 */
			$scope.multiInsTeamOver = function() {
				//				console.log('$scope.main.vMember.sapc.tableType', $scope.main.vMember.sapc.tableType)
				//				$scope.selectedListTeam = $scope.gridApi.selection.getSelectedRows()
				$scope.insertTeamUrl = '/teamAndUsers';
				var tableType = $scope.main.vMember.sapc.tableType;
				for(let i = 0; i < $scope.selectedListTeam.length; i++) {
					$scope.selectedListTeam[i]['inUse'] = $scope.selectedListTeam[i]['inUse'] == '是' ? 1 : 0;
					$scope.selectedListTeam[i]['orgName'] = tableType.attribute['orgName'].proAdd.data;
					$scope.selectedListTeam[i]['orgCode'] = tableType.attribute['orgCode'].proAdd.data;
					$scope.selectedListTeam[i]['orgId'] = tableType.attribute['orgId'].proAdd.data;
					$scope.selectedListTeam[i]['sortNum'] = 1;
				}
			}
			$scope.main.vfunc.onclick.multiInsTeamEnsure = function() {
				$scope.tempreset = "加入中";
				$scope.error = '';
				var tableType = $scope.main.vMember.sapc.tableType;
				console.info("加入所用数据-->", cj.parseCjArray($scope.selectedListTeam));
				console.log("加入所用url-->", viewGridProvider.httpPort() + $scope.insertTeamUrl);
				viewGridProvider.httpPost($scope.insertTeamUrl, cj.parseCjArray($scope.selectedListTeam)).then(
					function success(res) {
						console.info("加入返回数据res-->", res);
						if(res.data.collection.hasOwnProperty("templates")) {
							$scope.tempreset = "加入成功";
							$timeout(function() {
								angular.element("#tempresetModel").modal("hide")
							}, 1000);
							for(var key in tableType.attribute) {
								$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = '';
								if(key == "sortNum") {
									$scope.main.vMember.sapc.tableType.attribute[key].proAdd.data = 1;
								}
							}
							angular.element("#multiInsertTeam").modal("hide")
							initGridOptionsData($scope.gridOptions.paginationCurrentPage, $scope.gridOptions
								.paginationPageSize);
						} else {}
					},
					function error(res) {
						console.info("加入失败error res-->", res);
						$scope.tempreset = "加入失败";
						$scope.error = res.data.collection.error.message;
					});
			};
			/**
			 * ---------------------------------------多条加入结束------------------------------------------------------------
			 */
			/**
			 * “删除”查询条件
			 * ---------------------------------------------------删除开始-------------------------------------------------------
			 */
			$scope.main.vfunc.onclick.deleteButton = function(row) {
				row.isSelected = false;
			}

			/**
			 * “删除”确认
			 */
			$scope.main.vfunc.onclick.deleteEnsure = function() {
				var selectedRows = $scope.gridApi.selection.getSelectedRows();
				for(var i = 0; i < selectedRows.length; i++) {
					deleteData(i);
				}
			}

			function deleteData(i) {
				var tableType = $scope.main.vMember.sapc.tableType
				var alterKeyDel = encodeURIComponent($scope.gridApi.selection.getSelectedRows()[i][tableType.alterKey])
				var nodeADel = encodeURIComponent($scope.gridApi.selection.getSelectedRows()[i][tableType.nodeA])
				var nodeBDel = encodeURIComponent($scope.gridApi.selection.getSelectedRows()[i][tableType.nodeB])
				var nodeCDel = encodeURIComponent($scope.gridApi.selection.getSelectedRows()[i][tableType.nodeC])
				var singleCodeUrl = '/' + alterKeyDel
				var singleBizUrl = '/' + nodeADel
				var doubleCodeUrl = '/' + tableType.nodeA + '/' + nodeADel + '/' + tableType.nodeB + '/' + nodeBDel
				var tripleCodeUrl = '?' + tableType.nodeA + '=' + nodeADel + '&' + tableType.nodeB + '=' + nodeBDel
				var bizUrl = '/bizs/' + nodeCDel
				var upUrl
				if(tableType.jsonObj.hasOwnProperty("bizType")) {
					if(tableType.jsonObj.key == "T_PM_UNITAREAREL") {
						//						upUrl = bizUrl + jsonObj.url + tripleCodeUrl
						upUrl = bizUrl + jsonObj.url + singleBizUrl
					} else {
						upUrl = bizUrl + jsonObj.url + singleCodeUrl
						if(tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
							upUrl = $scope.measIndex == 1 ? upUrl  + '?ofMeasindexType=1' : $scope.measIndex == 2 ? upUrl + '?ofMeasindexType=2' : upUrl  + '?ofMeasindexType=3';
						}
					}

				} else if(tableType.jsonObj.key == "T_SYSTEM_MESSAGECONFIG") {
					upUrl = jsonObj.url + tripleCodeUrl
				} else if(tableType.jsonObj.key == "T_PM_MEASINDEX") {
					//					upUrl = $scope.measIndex ? jsonObj.url + singleCodeUrl + '?ofMeasindexType=1' : jsonObj.url + singleCodeUrl + '?ofMeasindexType=0';
					upUrl = $scope.measIndex == 1 ? jsonObj.url + singleCodeUrl + '?ofMeasindexType=1' : $scope.measIndex == 2 ? jsonObj.url + singleCodeUrl + '?ofMeasindexType=2' : jsonObj.url + singleCodeUrl + '?ofMeasindexType=3';
				} else {
					upUrl = jsonObj.url + singleCodeUrl
				}
				console.log("删除所用url-->", viewGridProvider.httpPort() + upUrl);
				viewGridProvider.httpDelete(upUrl, cj.parseCj($scope.prodAddData)).then(function success(res) {
					console.info("删除返回数据res-->", res);
					if(res.data == "") {
						$scope.tempreset = "删除成功";
						$timeout(function() {
							angular.element("#tempresetModel").modal("hide")
						}, 1000);
						$scope.error = '';
						initGridOptionsData($scope.gridOptions.paginationCurrentPage, $scope.gridOptions
							.paginationPageSize);
					} else {}

				}, function error(res) {
					console.info("删除失败error res-->", res);
					$scope.tempreset = "删除失败";
					$scope.error = res.data.collection.error.message;
				});
			}
			//“删除”模态框，点击取消按钮取消行选中
			$scope.main.vfunc.onclick.rowCancel = function() {
				$scope.gridApi.selection.clearSelectedRows();
			}
			/**
			 * ----------------------------------------------------删除结束----------------------------------------------------
			 */
			/**
			 * 修改按钮
			 * ---------------------------------------修改开始------------------------------------------------------------
			 */
			/**
			 * 点击修改初始化弹窗数据
			 */
			$scope.alterValue = '';
			$scope.main.vfunc.onclick.updateButton = function(row) {
				console.log('row修改+++',row)
				row.isSelected = true;
				var tableType = $scope.main.vMember.sapc.tableType;
				var alterKey = tableType.alterKey;
				var nodeA = tableType.nodeA;
				var nodeB = tableType.nodeB;
				var nodeC = tableType.nodeC;
				$scope.alterValue = encodeURIComponent(row.entity[alterKey])
				$scope.nodeA = encodeURIComponent(row.entity[nodeA])
				$scope.nodeB = encodeURIComponent(row.entity[nodeB])
				$scope.nodeC = encodeURIComponent(row.entity[nodeC])
							console.log("当前行数据", row);
				for(let key in row.entity) {
					if(key == 'startTime' || key == 'birthday') {
						if(row.entity[key]) {
							let nowTimes = row.entity[key];
							var ymd = nowTimes.slice(0, 10); //获取数据库年月日
							row.entity[key] = new Date(ymd); //将数据库的时间格式转化为模态框显示格式
						}
					}
					if(key == 'isUseFormula') {
						$scope.flagIsFamula = (row.entity[key] == '否') ? true : false
					}
				}
				viewGridProvider.setPartFlag($scope.main.vMember.sapc.tableType, $scope.measIndex);
				viewGridProvider.initParentTable($scope.main.vMember.sapc.tableType, tableIndex);
				$scope.areaAlias = [];
				viewGridProvider.setDefalutValue($scope.main.vMember.sapc.tableType);
				var tableType = $scope.main.vMember.sapc.tableType;
				//单罐配置获取基础分类
				if(tableType.jsonObj.key == "T_IC_CNFG_TANK") {
					var tankType = tableType.attribute['tankTypeCode'].proUpdate.data;
					var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_IC_CNFG_CLASS").url;
					var areaUrl = localStorage.getItem('serverUrl') + url + '?' + 'tankType=' + tankType;
					setcnfgClass(areaUrl);
					//罐量计算基础配置获取基础分类
				} else if(tableType.jsonObj.key == "T_IC_CNFG_BASE") {
					var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_IC_CNFG_CLASS").url;
					var areaUrl = localStorage.getItem('serverUrl') + url;
					setcnfgClass(areaUrl);
					//班组用户关联获取用户
				} else if(tableType.jsonObj.key == "T_PM_TEAMANDUSER") {
					var orgAlias = tableType.attribute['userOrgAlias'].proUpdate.data;
					var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_USER").url;
					var userUrl = localStorage.getItem('serverUrl') + url + '?' + 'orgAlias=' + orgAlias;
					setUserName(userUrl);
				} else if(tableType.jsonObj.key == "T_PM_MEASINDEX" || tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
					for(var key in tableType.attribute) {
						//判断修改是否显示，再消除之前影响
						if((key == "nodeAlias" && $scope.measIndex == 3) || (key == "areaAlias" && $scope.measIndex == 1) || (key == "orgAlias" && $scope.measIndex == 2)) {
							tableType.attribute[key].proUpdate.show = true;
						} else if((key == "nodeAlias" && $scope.measIndex != 3) || (key == "areaAlias" && $scope.measIndex != 1) || (key == "orgAlias" && $scope.measIndex != 2)) {
							tableType.attribute[key].proUpdate.show = false;
						}
					}
				}
				viewGridProvider.setUpdateData($scope.gridApi.selection.getSelectedRows()[0], $scope.main
					.vMember.sapc.tableType);
			}
			/**
			 * “修改”确认事件
			 */
			$scope.main.vfunc.onclick.updataEnsure = function() {
				var tableType = $scope.main.vMember.sapc.tableType;
				$scope.tempreset = "修改中";
				$scope.error = '';
				for(var key in tableType.attribute) {
					if(key == 'capacity' || key == 'initialAssetValue' || key == 'netAssetValue') {
						if(tableType.attribute[key].proUpdate.data) {
							tableType.attribute[key].proUpdate.data = viewGridProvider.changeData(tableType
								.attribute[key].proUpdate.data);
						}
					} else if(key == 'parentOrgAlias') {
						if(tableType.attribute[key].proUpdate.data == '') {
							tableType.attribute['parentOrgAlias'].proUpdate.data = '';
							tableType.attribute['parentOrgCode'].proUpdate.data = '';
						}
					} else if(key == 'startTime' || key == 'birthday') {
						if(tableType.attribute[key].proUpdate.data) {
							tableType.attribute[key].proUpdate.data = document.getElementById('getUpdateTime')
								.value + " 00:00:00"
						}
					} else if(key == 'mntUserId' || key == 'mntUserCode' || key == 'mntUserName') {
						tableType.attribute[key].proUpdate.data = $scope.userCode;
					} else if(key == 'bizCode') {
						localStorage.setItem('bizCode', tableType.attribute[key].proUpdate.data);
					} else if((tableType.jsonObj.key == 'T_PM_UNITAREAREL' || tableType.jsonObj.key == 'T_PM_ENNODE') && key == 'rentCode') {
						tableType.attribute[key].proUpdate.data = localStorage.getItem('rentCode')
					}
				}
				//获取修改数据
				var tableType = $scope.main.vMember.sapc.tableType;
				let obj = {
					"busiArea": 'fms_mtrl',
					"energyMng": 'fms_em',
					"operMng": 'fms_ope'
				}
				var json = viewGridProvider.getHttpData('proUpdate', $scope.main.vMember.sapc.tableType);
				console.info("修改所用restful-->", JSON.stringify(json));
				var singleCodeUrl = '/' + $scope.alterValue
				var doubleCodeUrl = '/' + tableType.nodeA + '/' + $scope.nodeA + '/' + tableType.nodeB + '/' +
					$scope.nodeB
				var tripleCodeUrl = '?' + tableType.nodeA + '=' + $scope.nodeA + '&' + tableType.nodeB + '=' +
					$scope.nodeB
				var bizUrl = '/bizs/' + $scope.nodeC
				var alterUrl

				if(tableType.jsonObj.hasOwnProperty("bizType")) {
					var bizUrl = '/bizs/' + obj[tableType.jsonObj.bizType]
					if(tableType.jsonObj.key == "T_PM_UNITAREAREL") {
						alterUrl = bizUrl + jsonObj.url + tripleCodeUrl
					} else {
						alterUrl = bizUrl + jsonObj.url + singleCodeUrl
						if(tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
							alterUrl = $scope.measIndex == 1 ? alterUrl + '?ofMeasindexType=1' : $scope.measIndex == 2 ? alterUrl + '?ofMeasindexType=2' : alterUrl + '?ofMeasindexType=3';
						}
					}
				} else if(tableType.jsonObj.key == "T_SYSTEM_MESSAGECONFIG") {
					alterUrl = jsonObj.url + tripleCodeUrl
				} else if(tableType.jsonObj.key == "T_PM_MEASINDEX") {
					//					alterUrl = $scope.measIndex ? jsonObj.url + singleCodeUrl + '?ofMeasindexType=1' : jsonObj.url + singleCodeUrl + '?ofMeasindexType=0';
					alterUrl = $scope.measIndex == 1 ? jsonObj.url + singleCodeUrl + '?ofMeasindexType=1' : $scope.measIndex == 2 ? jsonObj.url + singleCodeUrl + '?ofMeasindexType=2' : jsonObj.url + singleCodeUrl + '?ofMeasindexType=3';
				} else {
					alterUrl = jsonObj.url + singleCodeUrl
				}
				console.log("修改所用url-->", viewGridProvider.httpPort() + alterUrl);
				viewGridProvider.httpUpdate(alterUrl, json).then(function success(res) {
					console.info("修改返回数据res-->", res);
					if(res.data.url) {
						$scope.tempreset = "修改成功";
						//console.log('bizCode', localStorage.getItem('bizCode'))
						$timeout(function() {
							angular.element("#tempresetModel").modal("hide")
						}, 1000);
						/*刷新数据*/
						angular.element("#updateWindow").modal("hide")
						initGridOptionsData($scope.gridOptions.paginationCurrentPage, $scope.gridOptions
							.paginationPageSize);
					} else {}
				}, function error(res) {
					console.info("修改失败error res-->", res);
					$scope.tempreset = "修改失败";
					for(var key in tableType.attribute) {
						if(key == 'capacity' || key == 'initialAssetValue' || key == 'netAssetValue') {
							if(tableType.attribute[key].proUpdate.data) {
								tableType.attribute[key].proUpdate.data /= 10000;
							}
						}
					}
					$scope.error = res.data.collection.error.message;
				});
			}
			/*
			 *修改弹框点击取消后清空模态框数据
			 */
			$scope.main.vfunc.onclick.updateDatasRemove = function() {
				initGridOptionsData($scope.gridOptions.paginationCurrentPage, $scope.gridOptions
					.paginationPageSize);

			}

			/**
			 * 详情按钮
			 * ---------------------------------------详情开始------------------------------------------------------------
			 */
			/**
			 * 点击详情初始化弹窗数据
			 */
			//			$scope.alterValue = '';
			//			$scope.main.vfunc.onclick.detailButton = function(row) {
			//				row.isSelected = true;
			//				var tableType = $scope.main.vMember.sapc.tableType;
			//				var alterKey = tableType.alterKey;
			//				var nodeA = tableType.nodeA;
			//				var nodeB = tableType.nodeB;
			//				var nodeC = tableType.nodeC;
			//				$scope.alterValue = row.entity[alterKey]
			//				$scope.nodeA = row.entity[nodeA]
			//				$scope.nodeB = row.entity[nodeB]
			//				$scope.nodeC = row.entity[nodeC]
			//				//			console.log("当前行数据", row);
			//				for (let key in row.entity) {
			//					if (key == 'startTime' || key == 'birthday') {
			//						if (row.entity[key]) {
			//							let nowTimes = row.entity[key];
			//							var ymd = nowTimes.slice(0, 10); //获取数据库年月日
			//							row.entity[key] = new Date(ymd); //将数据库的时间格式转化为模态框显示格式
			//						}
			//					}
			//					if (key == 'isUseFormula') {
			//						$scope.flagIsFamula = (row.entity[key] == '否') ? true : false
			//					}
			//				}
			//				viewGridProvider.setPartFlag($scope.main.vMember.sapc.tableType,$scope.measIndex);
			//				viewGridProvider.initParentTable($scope.main.vMember.sapc.tableType, tableIndex);
			//				$scope.areaAlias = [];
			//				viewGridProvider.setDefalutValue($scope.main.vMember.sapc.tableType);
			//				var tableType=$scope.main.vMember.sapc.tableType;
			//				//单罐配置获取基础分类
			//				if (tableType.jsonObj.key == "T_IC_CNFG_TANK") {
			//					var tankType = tableType.attribute['tankTypeCode'].proUpdate.data;
			//					var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_IC_CNFG_CLASS").url;
			//					var areaUrl = localStorage.getItem('serverUrl') + url + '?' + 'tankType=' + tankType;
			//					setcnfgClass(areaUrl);
			//					//罐量计算基础配置获取基础分类
			//				} else if (tableType.jsonObj.key == "T_IC_CNFG_BASE") {
			//					var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_IC_CNFG_CLASS").url;
			//					var areaUrl = localStorage.getItem('serverUrl') + url;
			//					setcnfgClass(areaUrl);
			//					//班组用户关联获取用户
			//				} else if (tableType.jsonObj.key == "T_PM_TEAMANDUSER") {
			//					var orgAlias = tableType.attribute['userOrgAlias'].proUpdate.data;
			//					var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_USER").url;
			//					var userUrl = localStorage.getItem('serverUrl') + url + '?' + 'orgAlias=' + orgAlias;
			//					setUserName(userUrl);
			//				}else if(tableType.jsonObj.key == "T_PM_MEASINDEX"){
			//					for(var key in tableType.attribute){
			//									if((key == "nodeAlias" && $scope.measIndex ==3) || (key == "areaAlias" && $scope.measIndex ==1) || (key == "orgAlias" && $scope.measIndex ==2)) {
			//										tableType.attribute[key].proUpdate.show = true;
			//									} else if((key == "nodeAlias" && $scope.measIndex !=3) || (key == "areaAlias" && $scope.measIndex !=1) || (key == "orgAlias" && $scope.measIndex !=2)) {
			//										tableType.attribute[key].proUpdate.show = false;
			//									}
			//					}
			//				}
			//				viewGridProvider.setUpdateData($scope.gridApi.selection.getSelectedRows()[0], $scope.main
			//					.vMember.sapc.tableType);
			//			}

			/**
			 * ---------------------------------------修改结束------------------------------------------------------------
			 */
			/**
			 * 查询 按钮
			 * ----------------------------------------------------查询开始----------------------------------------------------
			 */
			/*
			 有组织机构树的查询框，控制是否包含下级组织机构
			 * */
			$scope.sM = {};
			$scope.sM.vMember = {};
			$scope.sM.vMember.sapc = {};
			$scope.sKey;
			$scope.main.vfunc.onclick.tableSearchOnClick = function(value, key) {
				$scope.sKey = key;
				var sjsonObj = viewGridProvider.getTableIndexJsonByKey(tableIndex, value.parent);
				var deffered = $q.defer();
				var promise = deffered.promise;
				viewGridProvider.setValueTV($scope.sM.vMember.sapc, sjsonObj, deffered);
				promise.then(function() {
					viewGridProvider.initParentTable($scope.sM.vMember.sapc.parentType, tableIndex);
				});
				var qUrl;
				if($scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "生产单元度量指标") {
					qUrl = '/prdtcells'
				} else if($scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "拓扑关系明细") {
					qUrl = '/nodeTopMains'
				}
				var queryUrl = viewGridProvider.httpPort() + qUrl + '?inUse=1&dataStatus=1';
				//console.log('queryUrl',queryUrl)
				viewGridProvider.httpCommit(queryUrl).then(function success(res) {
					$scope.queryData = $.ET.toObjectArr(res.data)
				}, function errorCallback(response) {});
			}
			$scope.main.vfunc.onclick.setTemlData = function(data) {
				//console.log(data)
				$scope.main.vMember.sapc.tableType.attribute[$scope.sKey]['proSearch'].data = data[$scope.sKey];
				angular.element("#connectTable").modal("hide")
			}
			$scope.main.vfunc.onclick.selectMeas = function(measIndex) {
				let tableType = $scope.main.vMember.sapc.tableType;
				showHide(tableType,measIndex)
				

				//				tableType.attribute["areaCode"].hide = tableType.attribute["areaAlias"].hide = !measIndex
				//				tableType.attribute["nodeCode"].hide = tableType.attribute["nodeAlias"].hide = tableType.attribute["nodeTypeName"].hide = measIndex
				$scope.measIndex = measIndex
				initGridOptionsData(1, $scope.gridOptions.paginationPageSize);
				$scope.gridOptions.columnDefs = viewGridProvider.formatGridOptionsColumnDefs(tableType
					.attribute, $scope.BntShow, $scope.updateShow, $scope.removeBnt, $scope
					.orgBtnShow, $scope.authModfiy, $scope.rentBtnShow);
			}
			function showHide(tableType,measIndex){
				if(measIndex == 3) {
					tableType.attribute["nodeCode"].hide = tableType.attribute["nodeAlias"].hide = tableType.attribute["nodeTypeName"].hide = false
					tableType.attribute["areaCode"].hide = tableType.attribute["areaAlias"].hide = true
					tableType.attribute["orgCode"].hide = tableType.attribute["orgAlias"].hide = true
				} else if(measIndex == 1) {
					tableType.attribute["nodeCode"].hide = tableType.attribute["nodeAlias"].hide = tableType.attribute["nodeTypeName"].hide = true
					tableType.attribute["areaCode"].hide = tableType.attribute["areaAlias"].hide = false
					tableType.attribute["orgCode"].hide = tableType.attribute["orgAlias"].hide = true
				} else if(measIndex == 2) {
					tableType.attribute["nodeCode"].hide = tableType.attribute["nodeAlias"].hide = tableType.attribute["nodeTypeName"].hide = true
					tableType.attribute["areaCode"].hide = tableType.attribute["areaAlias"].hide = true
					tableType.attribute["orgCode"].hide = tableType.attribute["orgAlias"].hide = false
				}
			}
			$scope.main.vfunc.onclick.selectLevel = function(levelOrg) {
				$scope.levelOrg = levelOrg
			}
			$scope.main.vfunc.onclick.serchButton = function() {
				viewGridProvider.setSearchFlag($scope.main.vMember.sapc.tableType, $scope.main.vMember.sapc
					.tableType.jsonObj, $scope.measIndex)
			}
			$scope.main.vfunc.onclick.serchOver = function() {
				let tableType = $scope.main.vMember.sapc.tableType;
				for(let item in tableType.attribute) {
					if(item == 'userOrgAlias' && tableType.jsonObj.nameAlias == "用户") {
						if(!tableType.attribute[item].proSearch.data) {
							tableType.attribute['userOrgCodes'].proSearch = {};
							tableType.attribute['userOrgCodes'].proSearch.data = '';
						}
					} else if(item == 'orgAlias' && (tableType.jsonObj.nameAlias == "装置" || tableType.jsonObj
							.nameAlias == "罐区" || tableType.jsonObj.nameAlias == "仓库" ||
							tableType.jsonObj.nameAlias == "装卸台" || tableType.jsonObj.nameAlias ==
							"管网(非能源管网)" || tableType.jsonObj.nameAlias == "生活区" ||
							tableType.jsonObj.nameAlias == "办公区" || tableType.jsonObj.nameAlias == "侧线" ||
							tableType.jsonObj.nameAlias == "罐" || tableType.jsonObj.nameAlias == "设备" ||
							tableType.jsonObj.nameAlias == "进出厂点" || tableType.jsonObj.nameAlias == "料仓" ||
							tableType.jsonObj.nameAlias == "库位" || tableType.jsonObj.nameAlias == "采样点" ||
							tableType.jsonObj.nameAlias == "排放口" || tableType.jsonObj.nameAlias == "管段" ||
							tableType.jsonObj.nameAlias == "阀门" || tableType.jsonObj.nameAlias == "盲板" ||
							tableType.jsonObj.nameAlias == "三通" || tableType.jsonObj.nameAlias == "装卸点" ||
							tableType.jsonObj.nameAlias == "罐量计算基础配置" || tableType.jsonObj.nameAlias ==
							"罐量计算单罐配置")) {
						if(!tableType.attribute[item].proSearch.data) {
							tableType.attribute['orgCode'].proSearch = {};
							tableType.attribute['orgCode'].proSearch.data = '';
						}
					} else if(item == 'orgAlias' && tableType.jsonObj.nameAlias == "多业务组织层次明细") {
						if(!tableType.attribute[item].proSearch.data) {
							tableType.attribute['orgCode'].proSearch = {};
							tableType.attribute['orgCode'].proSearch.data = '';
							tableType.attribute['orgTypeName'].proSearch = {
								show: true
							};
							tableType.attribute['orgTypeName'].proSearch.data = '';

						}
					} else if(item == 'bizCode') {
						tableType.attribute['bizCode'].hasOwnProperty("proSearch") && localStorage.setItem('bizCode', tableType.attribute['bizCode'].proSearch.data);
					}
				}
			}
			/**
			 * 查询确认
			 */
			$scope.main.vfunc.onclick.serchEnsure = function() {
				var tableType = $scope.main.vMember.sapc.tableType;
				let obj = {
					"busiArea": 'fms_mtrl',
					"energyMng": 'fms_em',
					"operMng": 'fms_ope'
				}
				$scope.tempreset = "查询中";
				var str = viewGridProvider.getSearchKVUrl(tableType);
				if(!str) {
					$scope.tempreset = "条件为空"
					//return 0;
				}
				$scope.gridOptions.paginationCurrentPage = 1;

				if(tableType.jsonObj.hasOwnProperty("bizType")) {
					bizUrl = '/bizs/' + obj[tableType.jsonObj.bizType]
					str = viewGridProvider.httpPort() + bizUrl + jsonObj.url + '?$skip=' + ($scope.gridOptions.paginationCurrentPage - 1) * $scope.gridOptions
						.paginationPageSize +
						'&$top=' + $scope.gridOptions.paginationPageSize + str;
					if(tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
						str = $scope.measIndex == 1 ? str + '&ofMeasindexType=1' : $scope.measIndex == 2 ? str + '&ofMeasindexType=2' : str + '&ofMeasindexType=3';
					}
				} else if(tableType.jsonObj.key == "T_PM_MEASINDEX") {
					var sUrl = viewGridProvider.httpPort() + jsonObj.url +
						'?$skip=' + ($scope.gridOptions.paginationCurrentPage - 1) * $scope.gridOptions
						.paginationPageSize + '&$top=' + $scope.gridOptions.paginationPageSize + str;
					//					str = $scope.measIndex ? sUrl + '&ofMeasindexType=1' : sUrl + '&ofMeasindexType=0';
					str = $scope.measIndex == 1 ? sUrl + '&ofMeasindexType=1' : $scope.measIndex == 2 ? sUrl + '&ofMeasindexType=2' : sUrl + '&ofMeasindexType=3';
				} else {
					str = viewGridProvider.httpPort() + jsonObj.url +
						'?$skip=' + ($scope.gridOptions.paginationCurrentPage - 1) * $scope.gridOptions
						.paginationPageSize +
						'&$top=' + $scope.gridOptions.paginationPageSize + str;
				}

				if($scope.levelOrg) {
					str = str + '&isRecursive=1';
				} else if(!$scope.levelOrg) {
					str = str + '&isRecursive=0';
				}
				console.log("查询所用url", str);
				$scope.error = '';
				$scope.tempreset = '';
				viewGridProvider.httpCommit(str).then(function success(res) {
					//console.log("查询返回", res.data);
					if(res.data.collection.hasOwnProperty('items')) {
						if(res.data.collection.items.length > 0) {
							$scope.error = '';
							$scope.tempreset = "查询成功";
							//						console.log('bizCode', localStorage.getItem('bizCode'))
							$timeout(function() {
								angular.element("#tempresetModel").modal("hide")
							}, 500);
							/*分页总记录数*/
							for(var i = 0; i < res.data.collection.page.data.length; i++) {
								if(res.data.collection.page.data[i].name == "recordCount" ||
									res.data.collection.page.data[i].name == "totalElements") {
									$scope.gridOptions.totalItems = res.data.collection.page.data[i]
										.value;
								}
							}
							$scope.gridOptions.data = cj.Parse(res.data, true);
							//查询成功后将数据库里的boolean型数值转换为对应的字符串
							viewGridProvider.changeBooleanToCh($scope.main.vMember.sapc.tableType,
								$scope.gridOptions.data);
						} else {
							angular.element("#tempresetModel").modal("show");
							$scope.tempreset = "查询失败";
							$scope.error = '没有该记录';
							initGridOptionsData($scope.gridOptions.paginationCurrentPage, $scope
								.gridOptions.paginationPageSize);
						}
					} else {
						angular.element("#tempresetModel").modal("show");
						$scope.tempreset = "查询失败";
						$scope.error = res.data.collection.error.message;
					}
				}, function error(res) {
					cj.Error(res);
					$scope.tempreset = "查询失败";
					$scope.error = res.data.collection.error.message;
				});
			}
			/**
			 * ----------------------------------------------------查询结束----------------------------------------------------
			 */
			/*
			 * ----------------------------------------------------导入开始---------------------------------------------------
			 */

			$scope.excelName = "未选择";
			$scope.mTable = {};
			var jsonObj = angular.fromJson(angular.toJson(jsonObj));
			var fileName = "model/" + jsonObj.tableFormat;
			var uploader = $scope.uploader = new FileUploader();
			var tableType = $scope.main.vMember.sapc.tableType;
			var obj = {
				"busiArea": 'fms_mtrl',
				"energyMng": 'fms_em',
				"operMng": 'fms_ope'
			}
			var bizUrl
			uploader.onAfterAddingFile = function(fileItem) {
				if(!fileItem.file) {
					return;
				}
				var name = $scope.excelName = fileItem.file.name;
				//校验上传文件类型
				var suffix = name.substring(name.lastIndexOf(".") + 1, name.length);
				if(!(suffix == "xls" || suffix == "xlsx")) {
					$scope.excelName = "未选择";
					$scope.mTable.tableValue = {};
					return alert("文件类型不正确");
				}
				var files = fileItem._file;
				var fileReader = new FileReader();
				fileReader.readAsArrayBuffer(files); // 以二进制方式打开文件
				fileReader.onload = function(ev) {
					try {
						var data = ev.target.result;
						var wb = XLSX.read(data, {
							type: 'array'
						});
						var jsonArray = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
						jsonArray.splice(0, 1);
						$scope.mTable.tableValue = jsonArray;
					} catch(e) {
						console.error(e);
						return alert('文件类型不正确!');
					}
				};
			};

			$scope.main.vfunc.onclick.importFile = function() {
				$scope.importBtnName = "导入";
				$scope.imBtnDisable = false;
				//存放导入成功的数据行号
				$scope.imSuccess = [];
				//存放导入失败的数据行号和失败信息
				$scope.imError = [];
				viewGridProvider.httpCommit(fileName).then(function success(res) {
					$scope.mTable.tableType = res.data.attribute;
				}, function error() {});
			};

			$scope.main.vfunc.onclick.importEnsure = function() {
				var alterKey = $scope.main.vMember.sapc.tableType.alterKey;
				var nodeA, nodeB, nodeC, nodeD, nodeM, nodeS;
				if(jsonObj.key == 'T_PM_ASSOCIATIVE' || jsonObj.key == "T_SYSTEM_MESSAGECONFIG" || jsonObj.key == "T_PM_UNITAREAREL") {
					nodeA = $scope.main.vMember.sapc.tableType.nodeA;
					nodeB = $scope.main.vMember.sapc.tableType.nodeB;
				} else if(jsonObj.key == 'T_PM_BIZORG_DTL') {
					nodeC = $scope.main.vMember.sapc.tableType.nodeC;
					nodeD = $scope.main.vMember.sapc.tableType.nodeD;
				} else if(jsonObj.key == 'T_IC_SIMPLE_NODE_MAP') {
					nodeM = $scope.main.vMember.sapc.tableType.nodeM;
					nodeS = $scope.main.vMember.sapc.tableType.nodeS;
				}

				$scope.imBtnDisable = true;
				for(var i = 0; i < $scope.mTable.tableValue.length; i++) {
					for(var attr in $scope.mTable.tableType) {
						if(attr == 'crtUserId' || attr == 'crtUserCode' || attr == 'crtUserName' ||
							attr == 'mntUserId' || attr == 'mntUserCode' || attr == 'mntUserName') {
							$scope.mTable.tableValue[i][attr] = $scope.userCode
						}
						if($scope.mTable.tableValue[i][attr] || $scope.mTable.tableValue[i][attr] === 0) {
							$scope.mTable.tableValue[i][attr] = $scope.mTable.tableValue[i][attr].toString();
						}
					}
					let tableValue = $scope.mTable.tableValue[i]
					for(var key in tableValue) {
						tableValue[key] = tableValue[key].replace(/^\s*|\s*$/g, "");
						if(key == 'capacity' || key == 'initialAssetValue' || key == 'netAssetValue') {
							tableValue[key] = viewGridProvider.changeData(Number(tableValue[key]));
						}
						if(key == 'inoutTypeId') {
							if(tableValue[key] == "出") {
								tableValue[key] = '1';
							} else if(tableValue[key] == "进") {
								tableValue[key] = '0';
							} else if(tableValue[key] == "进出") {
								tableValue[key] = '2';
							}
						}
						if(key == 'slineInoutTypeId') {
							if(tableValue[key] == "出") {
								tableValue[key] = '1';
							} else if(tableValue[key] == "进") {
								tableValue[key] = '0';
							} else if(tableValue[key] == "消耗") {
								tableValue[key] = '2';
							}
						}
						if(key == 'enabled') {
							tableValue[key] = tableValue[key] == "启用" ? '1' : '0';
						}
						if(key == 'sex') {
							tableValue[key] = tableValue[key] == "男" ? '1' : '2';
						}
						if(key == 'inUse' || key == 'techcardCtlFlag' || key == 'isPublic' || key ==
							'isInnerOp' ||
							key == 'monFlag' || key == 'ptrlFlag' || key == 'htPretTank' || key ==
							'dataStatus' ||
							key == 'tankIdt' || key == 'isUseFormula' || key == 'isReliable') {
							tableValue[key] = tableValue[key] == "是" ? '1' : '0';
						}
					}
					let importJson = cj.parseCj(tableValue)
					var repeatUrl;
					var tableType = $scope.main.vMember.sapc.tableType;

					//导入先查数据，数据库有数据走修改接口，无走新增
					if(jsonObj.key == 'T_PM_ASSOCIATIVE' || jsonObj.key == "T_SYSTEM_MESSAGECONFIG") {
						repeatUrl = viewGridProvider.httpPort() + jsonObj.url + '/' + encodeURIComponent($scope.mTable.tableValue[
							i][nodeA]) + '/' + encodeURIComponent($scope.mTable.tableValue[i][nodeB]);
					} else if(jsonObj.key == 'T_PM_BIZORG_DTL') {
						repeatUrl = viewGridProvider.httpPort() + jsonObj.url + '/' + encodeURIComponent($scope.mTable.tableValue[
							i][nodeC]) + '/' + encodeURIComponent($scope.mTable.tableValue[i][nodeD]);
					} else if(jsonObj.key == 'T_IC_SIMPLE_NODE_MAP') {
						repeatUrl = viewGridProvider.httpPort() + jsonObj.url + '/' + encodeURIComponent($scope.mTable.tableValue[
							i][nodeS]) + '/' + encodeURIComponent($scope.mTable.tableValue[i][nodeM]);
					} else if(tableType.jsonObj.hasOwnProperty("bizType")) {
						bizUrl = '/bizs/' + obj[tableType.jsonObj.bizType]
						if(tableType.jsonObj.key == "T_PM_UNITAREAREL") {
							repeatUrl = viewGridProvider.httpPort() + bizUrl + jsonObj.url + '/' + encodeURIComponent($scope.mTable.tableValue[
								i][nodeA]) + '/' + encodeURIComponent($scope.mTable.tableValue[i][nodeB]);
						} else {
							repeatUrl = viewGridProvider.httpPort() + bizUrl + jsonObj.url + '/' + encodeURIComponent($scope.mTable.tableValue[
								i][alterKey]);
							if(jsonObj.key == "T_PM_ENMEASINDEX") {
								repeatUrl = $scope.measIndex == 1 ? repeatUrl + '?ofMeasindexType=1' : $scope.measIndex == 2 ? repeatUrl + '?ofMeasindexType=2' : repeatUrl + '?ofMeasindexType=3';
							}
						}
					} else if(jsonObj.key == "T_PM_MEASINDEX") {
						//						repeatUrl = $scope.measIndex ? viewGridProvider.httpPort() + jsonObj.url + '/' + $scope.mTable.tableValue[
						//							i][alterKey] + '?ofMeasindexType=1' : viewGridProvider.httpPort() + jsonObj.url + '/' + $scope.mTable.tableValue[
						//							i][alterKey] + '?ofMeasindexType=0';
						var reapeatStr = viewGridProvider.httpPort() + jsonObj.url + '/' + encodeURIComponent($scope.mTable.tableValue[
							i][alterKey])
						repeatUrl = $scope.measIndex == 1 ? reapeatStr + '?ofMeasindexType=1' : $scope.measIndex == 2 ? reapeatStr + '?ofMeasindexType=2' : reapeatStr + '?ofMeasindexType=3';
					} else {
						repeatUrl = viewGridProvider.httpPort() + jsonObj.url + '/' + encodeURIComponent($scope.mTable.tableValue[
							i][alterKey]);
					}
					console.log('repeatUrl', repeatUrl)
					if(jsonObj.type == "Dict" || jsonObj.type == "Msr") {
						importPostData(i, importJson, jsonObj.url)
					} else {
						searchImport(repeatUrl, importJson, i, alterKey, encodeURIComponent($scope.mTable.tableValue[i][alterKey]),
							nodeA, encodeURIComponent($scope.mTable.tableValue[i][nodeA]), nodeB, 
							encodeURIComponent($scope.mTable.tableValue[i][nodeB]), nodeC, encodeURIComponent($scope.mTable.tableValue[i][nodeC]), nodeD, 
							encodeURIComponent($scope.mTable.tableValue[i][nodeD]), nodeM, encodeURIComponent($scope.mTable.tableValue[i][nodeM]), nodeS, 
							encodeURIComponent($scope.mTable.tableValue[i][nodeS]))
					}
				};
			}

			function searchImport(repeatUrl, importJson, index, alterKey, alterValue, nodeA, nodeAValve, nodeB,
				nodeBValve, nodeC, nodeCValve, nodeD, nodeDValve, nodeM, nodeMValve, nodeS, nodeSValve) {
				viewGridProvider.httpCommit(repeatUrl).then(function success(res) {
					console.log("repeatUrl", res)
					var tableType = $scope.main.vMember.sapc.tableType;
					let obj = {
						"busiArea": 'fms_mtrl',
						"energyMng": 'fms_em',
						"operMng": 'fms_ope'
					}
					var bizUrl = '/bizs/' + obj[tableType.jsonObj.bizType]
					var tripleCodeUrl = '?' + tableType.nodeA + '=' + encodeURIComponent($scope.nodeA) + '&' + tableType.nodeB + '=' +
						encodeURIComponent($scope.nodeB)
					if((alterValue || nodeAValve || nodeCValve || nodeSValve) && res.data.collection.items
						.length == 1) {
						var putUrl;
						if(jsonObj.key == 'T_PM_ASSOCIATIVE') {
							putUrl = jsonObj.url + '/' + nodeAValve + '/' + nodeBValve;
						} else if(jsonObj.key == 'T_PM_BIZORG_DTL') {
							putUrl = jsonObj.url + '/' + nodeCValve + '/' + nodeDValve;
						} else if(jsonObj.key == 'T_IC_SIMPLE_NODE_MAP') {
							putUrl = jsonObj.url + '/' + nodeSValve + '/' + nodeMValve;
						} else if(tableType.jsonObj.hasOwnProperty("bizType")) {
							if(tableType.jsonObj.key == "T_PM_UNITAREAREL") {
								putUrl = bizUrl + jsonObj.url + tripleCodeUrl
							} else {
								putUrl = bizUrl + jsonObj.url + '/' + alterValue
								if(tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
									putUrl = $scope.measIndex == 1 ? putUrl + '?ofMeasindexType=1' : $scope.measIndex == 2 ? putUrl + '?ofMeasindexType=2' : putUrl + '?ofMeasindexType=3';
								}
							}
						} else if(tableType.jsonObj.key == "T_PM_MEASINDEX") {
							//							putUrl = $scope.measIndex ? jsonObj.url + '/' + alterValue + '?ofMeasindexType=1' : jsonObj.url + '/' + alterValue + '?ofMeasindexType=0';
							putUrl = $scope.measIndex == 1 ? jsonObj.url + '/' + alterValue + '?ofMeasindexType=1' : $scope.measIndex == 2 ? jsonObj.url + '/' + alterValue + '?ofMeasindexType=2' : jsonObj.url + '/' + alterValue + '?ofMeasindexType=3';
						} else if(tableType.jsonObj.key == "T_SYSTEM_MESSAGECONFIG") {
							putUrl = jsonObj.url + '?' + tableType.nodeA + '=' + nodeAValve + '&' + tableType.nodeB + '=' + nodeBValve
						} else {
							putUrl = jsonObj.url + '/' + alterValue;
						}
						importPutData(index, importJson, putUrl)
					} else {
						var postUrl
						if(tableType.jsonObj.hasOwnProperty("bizType")) {
							postUrl = bizUrl + jsonObj.url
							if(tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
								postUrl = $scope.measIndex == 1 ? postUrl + '?ofMeasindexType=1' : $scope.measIndex == 2 ? postUrl + '?ofMeasindexType=2' : postUrl + '?ofMeasindexType=3';
							}
						} else if(tableType.jsonObj.key == "T_PM_MEASINDEX") {
							//							postUrl = $scope.measIndex ? jsonObj.url + '?ofMeasindexType=1' : jsonObj.url + '?ofMeasindexType=0';
							postUrl = $scope.measIndex == 1 ? jsonObj.url + '?ofMeasindexType=1' : $scope.measIndex == 2 ? jsonObj.url + '?ofMeasindexType=2' : jsonObj.url + '?ofMeasindexType=3';
						} else {
							postUrl = jsonObj.url;
						}
						importPostData(index, importJson, postUrl)
					}

				}, function error(res) {});
			}

			function importPostData(i, importJson, postUrl) {
				console.log("新增导入所用url-->", viewGridProvider.httpPort() + postUrl);
				console.info("新增导入所用数据-->", JSON.stringify(importJson));
				viewGridProvider.httpPost(postUrl, JSON.stringify(importJson)).then(function success(res) {
					if(res.data.collection.hasOwnProperty("templates")) {
						$scope.mTable.tableValue[i].imTrue = true
						$scope.mTable.tableValue[i].imFalse = false
						$scope.imSuccess.push(i + 1)
						if($scope.imSuccess.length + $scope.imError.length == $scope.mTable.tableValue
							.length) {
							$scope.importBtnName = "导入结束";
						}
					} else {}
				}, function error(res) {
					$scope.mTable.tableValue[i].imTrue = false
					$scope.mTable.tableValue[i].imFalse = true
					$scope.imError.push({
						num: i + 1,
						errorMes: res.data.collection.error.message
					})
					if($scope.imSuccess.length + $scope.imError.length == $scope.mTable.tableValue
						.length) {
						$scope.importBtnName = "导入结束";
					}
					console.log($scope.imError)
					console.info("导入失败error res-->", res);
				});
			}

			function importPutData(i, importJson, putUrl) {
				console.log("修改导入所用url-->", viewGridProvider.httpPort() + putUrl);
				console.info("修改导入所用数据-->", JSON.stringify(importJson));
				viewGridProvider.httpUpdate(putUrl, JSON.stringify(importJson)).then(function success(res) {
					if(res.data.url) {
						$scope.mTable.tableValue[i].imTrue = true
						$scope.mTable.tableValue[i].imFalse = false
						$scope.imSuccess.push(i + 1)
						if($scope.imSuccess.length + $scope.imError.length == $scope.mTable.tableValue
							.length) {
							$scope.importBtnName = "导入结束";
						}

					} else {}
				}, function error(res) {
					$scope.mTable.tableValue[i].imTrue = false
					$scope.mTable.tableValue[i].imFalse = true
					$scope.imError.push({
						num: i + 1,
						errorMes: res.data.collection.error.message
					})
					if($scope.imSuccess.length + $scope.imError.length == $scope.mTable.tableValue
						.length) {
						$scope.importBtnName = "导入结束";
					}
					console.log('失败信息', $scope.imError)
					console.info("导入失败error res-->", res);
				});
			}
			$scope.main.vfunc.onclick.importClose = function() {
				angular.element("#tableImportShow").modal("hide");
				angular.element("#tableImport").modal("hide");
				initGridOptionsData($scope.gridOptions.paginationCurrentPage, $scope.gridOptions
					.paginationPageSize);
				var importFile = document.getElementById('fileupload');
				importFile.value = '';
				$scope.excelName = "未选择";
				$scope.mTable.tableValue = [];
				$scope.importBtnName = "导入";
				$scope.imBtnDisable = false;
				$scope.imSuccess = [];
				$scope.imError = [];
			}
			//下载导入报告
			$scope.main.vfunc.onclick.downloadMsg = function() {
				var dtitle = {
					num: '错误行号',
					errorMes: '错误原因'
				};
				var dData = [];
				var obj = {};
				dData.push(dtitle)
				for(var i = 0; i < $scope.imError.length; i++) {
					for(var key in $scope.imError[i]) {
						if(key == 'num') {
							obj[key] = $scope.imError[i][key]
						}
						if(key == 'errorMes') {
							obj[key] = $scope.imError[i][key]
						}
					}
					dData.push(obj);
					var obj = {};
				}
				downloadExcel(dData, '导入报告');
			}
			$scope.main.vfunc.onclick.cancelImport = function() {
				var importFile = document.getElementById('fileupload');
				importFile.value = '';
				$scope.excelName = "未选择";
				$scope.mTable.tableValue = [];
			};
			/*
			 * ----------------------------------------导入结束---------------------------------------------------------------
			 */
			/*
			 * ------------------------------------------导出------------------------------------------------------------------
			 */
			$scope.main.vfunc.onclick.downloadExl = function(whichBtn, curPage, pageSize) {
				var str = viewGridProvider.getSearchKVUrl($scope.main.vMember.sapc.tableType);
				var strDown = str.replace('&', '?');
				var interUrl, pagination;
				var tableType = $scope.main.vMember.sapc.tableType;
				var obj = {
					"busiArea": 'fms_mtrl',
					"energyMng": 'fms_em',
					"operMng": 'fms_ope'
				}
				let bizUrl
				if(whichBtn == 'allData') {
					if(tableType.jsonObj.hasOwnProperty("bizType")) {
						bizUrl = '/bizs/' + obj[tableType.jsonObj.bizType]
						interUrl = viewGridProvider.httpPort() + bizUrl + jsonObj.url + strDown;

						if(tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
							interUrl = $scope.measIndex == 1 ? interUrl + '?ofMeasindexType=1' : $scope.measIndex == 2 ? interUrl + '?ofMeasindexType=2' : interUrl + '?ofMeasindexType=3';
						}

					} else if(tableType.jsonObj.key == "T_PM_MEASINDEX") {
						//						interUrl = $scope.measIndex ? viewGridProvider.httpPort() + jsonObj.url + strDown + '?ofMeasindexType=1' : viewGridProvider.httpPort() + jsonObj.url + strDown + '?ofMeasindexType=0';
						var inteStr = viewGridProvider.httpPort() + jsonObj.url + strDown
						interUrl = $scope.measIndex == 1 ? inteStr + '?ofMeasindexType=1' : $scope.measIndex == 2 ? inteStr + '?ofMeasindexType=2' : inteStr + '?ofMeasindexType=3';
					} else {
						interUrl = viewGridProvider.httpPort() + jsonObj.url + strDown;
					}
				} else if(whichBtn == 'currentPage') {
					pagination = '?$skip=' + (curPage - 1) * pageSize + '&$top=' + pageSize
					if(tableType.jsonObj.hasOwnProperty("bizType")) {
						bizUrl = '/bizs/' + obj[tableType.jsonObj.bizType]
						interUrl = viewGridProvider.httpPort() + bizUrl + jsonObj.url + pagination + str
						if(tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
							interUrl = $scope.measIndex == 1 ? interUrl + '?ofMeasindexType=1' : $scope.measIndex == 2 ? interUrl + '?ofMeasindexType=2' : interUrl + '?ofMeasindexType=3';
						}
					} else if(tableType.jsonObj.key == "T_PM_MEASINDEX") {
						//						interUrl = $scope.measIndex ? viewGridProvider.httpPort() + jsonObj.url + pagination + str + '?ofMeasindexType=1' : viewGridProvider.httpPort() + jsonObj.url + pagination + str + '?ofMeasindexType=0';
						var interStr = viewGridProvider.httpPort() + jsonObj.url + pagination + str
						interUrl = $scope.measIndex == 1 ? interStr + '?ofMeasindexType=1' : $scope.measIndex == 2 ? interStr + '?ofMeasindexType=2' : interStr + '?ofMeasindexType=3';
					} else {
						interUrl = viewGridProvider.httpPort() + jsonObj.url + pagination + str
					}
				}
				console.log('导出所用url', interUrl);
				var fileName = 'model/' + jsonObj.tableFormat;
				var title = {};
				viewGridProvider.httpCommit(fileName).then(function success(res) {
					title = res.data.attribute;
				}, function error(res) {
					cj.Error(res);
				});
				viewGridProvider.httpCommit(interUrl).then(function success(res) {

					var checkTitle = [];
					var dtitle = {};
					//当需要导出的字段属性为true时,将其push进数组
					for(var key in title) {
						if(title[key].inOut == true) {
							checkTitle.push(key);
							dtitle[key] = title[key].display;
						}
					};
					//获取总数据
					var dContent = cj.Parse(res.data);
					viewGridProvider.changeBooleanToCh($scope.main.vMember.sapc.tableType, dContent);
					if(whichBtn == 'selected') {
						dContent = $scope.gridApi.selection.getSelectedRows()
					}
					//导出需要的数据
					var dData = [];
					var obj = {};
					for(var i = 0; i < dContent.length; i++) {
						for(var key in dContent[i]) {
							for(var j = 0, len = checkTitle.length; j < len; j++) {
								if(checkTitle[j] == key) {
									obj[key] = dContent[i][key]
								}
							}
						}
						dData.push(obj);
						var obj = {};
					}
					//添加标题
					dData.splice(0, 0, dtitle);
					console.log("导出的数据", dData);
					//				downloadXls(dData);
					downloadExcel(dData, '');
				}, function error(res) {
					cj.Error(res);
				});

			};
			$scope.main.vfunc.onclick.downloadSelected = function() {
				var fileName = 'model/' + jsonObj.tableFormat;
				var title = {};
				viewGridProvider.httpCommit(fileName).then(function success(res) {
					title = res.data.attribute;
					var checkTitle = [];
					var dtitle = {};
					//当需要导出的字段属性为true时,将其push进数组
					for(var key in title) {
						if(title[key].inOut == true) {
							checkTitle.push(key);
							dtitle[key] = title[key].display;
						}
					};
					//获取总数据
					var dContent = $scope.gridApi.selection.getSelectedRows();
					//导出需要的数据
					var dData = [];
					var obj = {};
					for(var i = 0; i < dContent.length; i++) {
						for(var key in dContent[i]) {
							for(var j = 0; j < checkTitle.length; j++) {
								if(checkTitle[j] == key) {
									obj[key] = dContent[i][key]
								}
							}
						}
						dData.push(obj);
						var obj = {};
					}
					//添加标题
					dData.splice(0, 0, dtitle);
					console.log("导出的数据", dData);
					//				downloadXls(dData);
					downloadExcel(dData, '');

				}, function error(res) {
					cj.Error(res);
				});

			};

			function downloadExcel(dData, msg) {
				const ws = XLSX.utils.json_to_sheet(dData)
				ws['!cols'] = [{
					wch: 10
				}, {
					wch: 50
				}]
				const wb = XLSX.utils.book_new()
				XLSX.utils.book_append_sheet(wb, ws, jsonObj.tableName)
				XLSX.writeFile(wb, jsonObj.tableName + msg + ".xlsx")
			};

			const wopts = {
				bookType: 'xlsx',
				bookSST: false,
				type: 'binary'
			};

			function downloadXls(data) {
				const wb = {
					SheetNames: ['Sheet1'],
					Sheets: {},
					Props: {}
				};
				wb.Sheets['Sheet1'] = XLSX.utils.json_to_sheet(data); //通过json_to_sheet转成单页(Sheet)数据
				saveAs(new Blob([s2ab(XLSX.write(wb, wopts))], {
					type: "application/octet-stream"
				}), jsonObj.tableName + '.' + (wopts.bookType == "biff2" ? "xls" : wopts.bookType));
			}

			function saveAs(obj, fileName) {
				var tmpa = document.createElement("a");
				tmpa.download = fileName || "下载";
				tmpa.href = URL.createObjectURL(obj); //绑定a标签
				tmpa.click(); //模拟点击实现下载
				setTimeout(function() { //延时释放
					URL.revokeObjectURL(obj); //用URL.revokeObjectURL()来释放这个object URL
				}, 100);
			}

			function s2ab(s) {
				Uint8Array.prototype.slice = Array.prototype.slice;
				if(typeof ArrayBuffer !== 'undefined') {
					var buf = new ArrayBuffer(s.length);
					var view = new Uint8Array(buf);
					for(var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
					return buf;
				} else {
					var buf = new Array(s.length);
					for(var i = 0; i != s.length; ++i) buf[i] = s.charCodeAt(i) & 0xFF;
					return buf;
				}
			}
			/**
			 * ------------------------------------------------导出结束---------------------------------------------------------
			 */
			/**
			 * ------------------------------------------------同步---------------------------------------------------------
			 */
			$scope.main.vfunc.onclick.syncButton = function() {
				var tableType = $scope.main.vMember.sapc.tableType;
				let obj = {
					"busiArea": 'fms_mtrl',
					"energyMng": 'fms_em',
					"operMng": 'fms_ope'
				}
				angular.element("#tempresetModel").modal("show")
				$scope.tempreset = "已执行";
				var syncUrl;
				if(tableType.jsonObj.hasOwnProperty("bizType")) {
					bizUrl = '/bizs/' + obj[tableType.jsonObj.bizType]
					syncUrl = viewGridProvider.httpPort() + bizUrl + jsonObj.url + '?dataSynchronization=1';
					if(tableType.jsonObj.key == "T_PM_ENMEASINDEX") {
						syncUrl = $scope.measIndex == 1 ? syncUrl + '&ofMeasindexType=1' : $scope.measIndex == 2 ? syncUrl + '&ofMeasindexType=2' : syncUrl + '&ofMeasindexType=3';
					}
				} else if(tableType.jsonObj.key == "T_PM_MEASINDEX") {
					var sUrl = viewGridProvider.httpPort() + jsonObj.url + '?dataSynchronization=1';
					//					syncUrl = $scope.measIndex ? sUrl + '&ofMeasindexType=1' : sUrl + '&ofMeasindexType=0';
					syncUrl = $scope.measIndex == 1 ? sUrl + '&ofMeasindexType=1' : $scope.measIndex == 2 ? sUrl + '&ofMeasindexType=2' : sUrl + '&ofMeasindexType=3';
				} else {
					syncUrl = viewGridProvider.httpPort() + jsonObj.url + '?dataSynchronization=1';
				}
				if($scope.levelOrg) {
					syncUrl = syncUrl + '&isRecursive=1';
				} else if(!$scope.levelOrg) {
					syncUrl = syncUrl + '&isRecursive=0';
				}
				console.log("同步所用url", syncUrl);
				$scope.error = '';
				$scope.tempreset = '已执行';
				viewGridProvider.httpCommit(syncUrl).then(function success(res) {
					$scope.tempreset = '已执行';
					$timeout(function() {
						angular.element("#tempresetModel").modal("hide")
					}, 1000);
				}, function error(res) {
					$scope.tempreset = "已执行";
					$timeout(function() {
						angular.element("#tempresetModel").modal("hide")
					}, 1000);
				});
			}

			/**
			 * ------------------------------------------------同步结束---------------------------------------------------------
			 */

			/**
			 * 多业务层次明细表----上级组织机构赋空----4.3.1版本未使用
			 */
			$scope.removeParentOrg = function(pro) {
				var tableType = $scope.main.vMember.sapc.tableType;
				for(let key in tableType.attribute) {
					if(key == 'parentOrgAlias' || key == 'parentOrgCode') {
						tableType.attribute[key][pro].data = '';
					}
				}
			}
			/**
			 * 多业务组织层次明细表-----新增时组织机构编码和上级组织机构编码是否一致
			 */
			$scope.buttonOver = function(whichflag) {
				var tableType = $scope.main.vMember.sapc.tableType;
				if(tableType.jsonObj.key == 'T_PM_BIZORG_DTL') {
					if(tableType.attribute['orgCode'][whichflag].data == tableType.attribute['parentOrgCode'][
							whichflag
						].data) {
						if(tableType.attribute['orgCode'][whichflag].data != null && tableType.attribute[
								'parentOrgCode'][whichflag].data != '') {
							alert("组织机构不能和上级组织机构相同");
						}
					}
				}
			}

			/*
			 * 选择框的下拉框的点击事件,仅字典表在用这个方法，
			 * 1.将改字段值赋给prodAddData
			 * 2.将下拉框中值的主键（columnDef.parentType.alterKey） 对应的赋给 prodAddData
			 */
			//		$scope.cnfgClassCode = '';
			$scope.main.vfunc.onclick.checkAddData = function(pData, key, value, proS) {
				//			console.log("选中的key", key);
				//			console.log("选中的value", value);
				//选中的值放入存储增加/修改 值  的地方
				value[proS].data = pData[key];
				var alterKey = value.parentType.alterKey;
				var mainKey = value.parentType.mainKey;
				if($scope.main.vMember.sapc.tableType.attribute[alterKey]) {
					if(!$scope.main.vMember.sapc.tableType.attribute[alterKey][proS]) {
						$scope.main.vMember.sapc.tableType.attribute[alterKey][proS] = {};
					}
					$scope.main.vMember.sapc.tableType.attribute[alterKey][proS].data = pData[alterKey];
					//				console.log("alterKey", pData[alterKey])
				}
				if($scope.main.vMember.sapc.tableType.attribute[mainKey]) {
					if(!$scope.main.vMember.sapc.tableType.attribute[mainKey][proS]) {
						$scope.main.vMember.sapc.tableType.attribute[mainKey][proS] = {};
					}
					$scope.main.vMember.sapc.tableType.attribute[mainKey][proS].data = pData[mainKey];
					//				console.log("mainKey", pData[mainKey])
				}
			}
			$scope.main.vfunc.onclick.checkData = function(pData, key, value, proS) {
				value[proS].data = pData[key];
				for(var key in $scope.main.vMember.sapc.tableType.attribute) {
					if(key == 'cnfgClassId') {
						var cnfgClassNames = value[proS].data;
						for(var i = 0; i < $scope.cnfgClass.length; i++) {
							if($scope.cnfgClass[i].cnfgClassName == cnfgClassNames) {
								$scope.main.vMember.sapc.tableType.attribute['cnfgClassCode'][proS].data =
									$scope.cnfgClass[i].cnfgClassCode;
								$scope.main.vMember.sapc.tableType.attribute['cnfgClassId'][proS].data = $scope
									.cnfgClass[i].cnfgClassId;
							}
						}
					}
				}
			}
			/**
			 * 关联表查询弹框，点击新增或修改弹框的'放大镜'按钮才会出现
			 * 1.将新增&修改弹框需求的属性值传到关联表弹框
			 * 2.点击查询获取关联表数据
			 * 3.选中数据返回至新增&修改弹框
			 */
			//关联表弹层树据
			$scope.pM = {};
			$scope.pM.vMember = {};
			$scope.pM.vMember.sapc = {};
			$scope.pM.vMember.pSearch = {};
			$scope.pM.vMember.pSearch.searchState = {};
			$scope.pM.vMember.pSearch.page = {
				currentPage: 1,
				pageSize: 10,
				totalItems: 0
			};
			$scope.svFunc = {};
			$scope.svFunc.onclick = {};
			$scope.main.vfunc.onclick.setTemLinkemKey = function(key, value, proWho) {
				$scope.showTable = false;
				$scope.main.vMember.temLink.selfKV = {
					"kt": key,
					'vt': value,
					'proWho': proWho
				};
				var areaAlias, orgAlias, cnfgClassName, rentCode, cnfgClassCode;
				var tableType = $scope.main.vMember.sapc.tableType;
				for(var key in tableType.attribute) {
					if(key == "areaAlias") {
						areaAlias = tableType.attribute[key][proWho].data
						//					areaAlias = ($scope.main.vMember.temLink.selfKV.proWho == 'proAdd') ?
						//						tableType.attribute[key].proAdd.data : tableType.attribute[key].proUpdate.data;
					} else if(key == "orgAlias") {
						orgAlias = tableType.attribute[key][proWho].data
					} else if(key == "rentCode") {
						rentCode = tableType.attribute[key][proWho].data
					} else if(key == "cnfgClassCode") {
						cnfgClassCode = tableType.attribute[key][proWho].data
					} else if(key == "cnfgClassName") {
						cnfgClassName = tableType.attribute[key][proWho].data
					}
				}
				var pjsonObj = viewGridProvider.getTableIndexJsonByKey(tableIndex, value.parent); //封装关联表json
				var deffered = $q.defer();
				var promise = deffered.promise;
				viewGridProvider.setValueTV($scope.pM.vMember.sapc, pjsonObj, deffered);
				promise.then(function() {
					viewGridProvider.initParentTable($scope.pM.vMember.sapc.parentType, tableIndex);
					let pmTableType = $scope.pM.vMember.sapc.parentType
					showHide(pmTableType,$scope.measIndex)
					console.log('-------------------------------------',$scope.pM.vMember.sapc)
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
						//					if(key == "orgTypeName" && ($scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "班组用户关联" || $scope.main.vMember.sapc.tableType.jsonObj.nameAlias == "用户")) {
						//						searchTableType.attribute[key].proSearch.data = '班组';
						//					}
					}
					//		将新增||修改页面上的数据赋给查询模态框结束
				});
			}
			$scope.svFunc.onclick.checkOptionData = function(pData, key, value) {
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
				var json = viewGridProvider.getHttpData('proSearch', $scope.pM.vMember.sapc.parentType);
				var tableName = $scope.main.vMember.sapc.tableType.jsonObj.key;
				if(tableName == 'T_IC_LIE_CUBA') {
					var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
						.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize +
						'&tankTypeName=' + '卧罐' + '&inUse=1&dataStatus=1';
				} else if(tableName == 'T_IC_GLB_CUBA' || tableName == 'T_IC_GLBPRECOEF') {
					var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
						.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize +
						'&tankTypeName=' + '球罐' + '&inUse=1&dataStatus=1';
				} else if(tableName == 'T_PM_ORG') {
					var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
						.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize +
						'&isDeploy=' + '0' + '&inUse=1&dataStatus=1';
				} else if(tableName == 'T_IC_STDDM_CUAB' || tableName == 'T_IC_STDCMMM_CUAB' || tableName ==
					'T_IC_STD_SEC' || tableName == 'T_IC_FLTPER_CUAB' || tableName == 'T_IC_STDPRES_COEF') {
					var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
						.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize +
						'&tankTypeName=' + '立罐' + '&inUse=1&dataStatus=1';
				} else if(tableName == 'T_IC_CNFG_TANK' || tableName == 'T_IC_CNFG_BASE') {
					var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
						.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize +
						'&inUse=1&dataStatus=1';
				} else if(tableName == 'T_PM_ASSOCIATIVE') {
					var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
						.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize +
						'&leaf=leaf' + '&inUse=1&dataStatus=1';
				} else if(tableName == 'T_PM_BIZORG_DTL') {
					var searchUrl = viewGridProvider.httpOrgPort() + $scope.pM.vMember.sapc.parentType.jsonObj
						.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize +
						'&inUse=1&dataStatus=1';
				} else if(tableName == 'T_IC_SIMPLE_NODE_MAP') {
					if($scope.pM.vMember.sapc.parentType.jsonObj.url == '/nodes') {
						var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
							.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope
							.pageSize + '&notNodeTypeCode=6' + '&inUse=1&dataStatus=1';
					} else {
						var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
							.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope
							.pageSize + '&inUse=1&dataStatus=1';
					}
				} else if(tableName == 'T_PM_UNITAREAREL') {
					if($scope.pM.vMember.sapc.parentType.jsonObj.url == '/unitAreas') {
						var searchUrl = viewGridProvider.httpPort() + '/bizs/fms_mtrl' + $scope.pM.vMember.sapc
							.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize +
							'&$top=' + $scope.pageSize + '&inUse=1&dataStatus=1';
					} else {
						var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
							.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope
							.pageSize + '&inUse=1&dataStatus=1';
					}

				} else if(tableName == 'T_PM_ENPIPENET' || tableName == 'T_PM_ENNODE') {
					//					表的搜索模态框不止一个,所以分情况
					if($scope.pM.vMember.sapc.parentType.jsonObj.url == '/enPipeNets' || $scope.pM.vMember.sapc.parentType.jsonObj.url == '/enNodeTypes') {
						var searchUrl = viewGridProvider.httpPort() + '/bizs/fms_em' + $scope.pM.vMember.sapc
							.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize +
							'&$top=' + $scope.pageSize + '&inUse=1&dataStatus=1';
					} else {
						var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
							.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope
							.pageSize + '&inUse=1&dataStatus=1';
					}
				} else if(tableName == 'T_PM_ENMEASINDEX') {
					var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
						.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope
						.pageSize + '&inUse=1&dataStatus=1';
					searchUrl = $scope.measIndex == 1 ? searchUrl + '&ofMeasindexType=1' : $scope.measIndex == 2 ? searchUrl + '&ofMeasindexType=2' : searchUrl + '&ofMeasindexType=3';
				} else if(tableName == 'T_PM_OPERCOMPONENT') {
					if($scope.pM.vMember.sapc.parentType.jsonObj.url == '/operSamples') {
						var searchUrl = viewGridProvider.httpPort() + '/bizs/fms_ope' + $scope.pM.vMember.sapc
							.parentType.jsonObj.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize +
							'&$top=' + $scope.pageSize + '&inUse=1&dataStatus=1';
					} else {
						var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
							.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope
							.pageSize + '&inUse=1&dataStatus=1';
					}
				} else {
					var searchUrl = viewGridProvider.httpPort() + $scope.pM.vMember.sapc.parentType.jsonObj
						.url + '?$skip=' + ($scope.selPage - 1) * $scope.pageSize + '&$top=' + $scope.pageSize +
						'&inUse=1&dataStatus=1';
				}
				for(var key in json) {
					searchUrl += '&' + key + '=' + json[key];
				}
				console.log('搜索框查询所用url', searchUrl);
				viewGridProvider.httpCommit(searchUrl).then(function success(res) {
					$scope.pM.vMember.pSearch.searchState.result = "成功";
					$scope.pM.vMember.pSearch.page.totalItems = viewGridProvider.getRecordCount(res);
					$scope.pM.vMember.sapc.parentValue = cj.Parse(res.data, true);
					//-- -- -- -- -- -- -- -- --分页开始-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
					//分页总数
					//设置表格数据源(分页)
					$scope.pages = Math.ceil($scope.pM.vMember.pSearch.page.totalItems / $scope
						.pageSize); //分页数
					$scope.newPages = $scope.pages > 10 ? 10 : $scope.pages;
					$scope.pageList = [];
					$scope.setData = function() {
						$scope.items = $scope.pM.vMember.sapc.parentValue.slice(($scope.pageSize * (
							$scope.selPage - 1)), ($scope.selPage * $scope
							.pageSize)); //通过当前页数筛选出表格当前显示数据
					}
					$scope.items = $scope.pM.vMember.sapc.parentValue.slice(0, $scope.pageSize);
					for(let i = 0; i < $scope.items.length; i++) {
						for(let key in $scope.items[i]) {
							if(key == 'dataStatus' || key == 'inUse' || key == 'isReliable') {
								$scope.items[i][key] = $scope.items[i][key] == 1 ? '是' : '否';
							}
						}
					}
					//分页要repeat的数组
					for(var i = $scope.selPage - 1; i < ($scope.newPages + $scope.selPage - 1 > $scope
							.pages ? $scope.pages : $scope.newPages + $scope.selPage - 1); i++) {
						$scope.pageList.push(i + 1);
					}
				}, function error(res) {
					cj.Error(res);
				});
			};
			//打印当前选中页索引
			$scope.svFunc.onclick.selectPage = function(page) {
				if(page < 1 || page > $scope.pages) return;
				if(page > 9) {
					var newpageList = [];
					for(var i = (page - 10); i < (page > $scope.pages ? $scope.pages : page); i++) {
						newpageList.push(i + 1);
					}
					$scope.pageList = newpageList;
				}
				$scope.selPage = page;
				$scope.setData();
				$scope.svFunc.onclick.isActivePage(page);
				$scope.svFunc.onclick.searchEnsure();
			};
			//设置当前选中页样式
			$scope.svFunc.onclick.isActivePage = function(page) {
				return $scope.selPage == page;
			};
			//上一页
			$scope.svFunc.onclick.Previous = function() {
				$scope.svFunc.onclick.selectPage($scope.selPage - 1);
			}
			//下一页
			$scope.svFunc.onclick.Next = function() {
				$scope.svFunc.onclick.selectPage($scope.selPage + 1);
			};
			//-- -- -- -- -- -- -- -- --分页结束-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
			$scope.svFunc.onclick.setTemlData = function(data) {
				if(data.isUseFormula == 0) {
					$scope.flagIsFamula = true;
				}
				if(data.isUseFormula == 1) {
					$scope.flagIsFamula = false;
				};
				var proValue = $scope.main.vMember.temLink.selfKV.proWho;
				if(!$scope.main.vMember.temLink.selfKV.vt.linkKeys) {
					var selfKey = $scope.main.vMember.temLink.selfKV.kt;
					var mainKey = $scope.pM.vMember.sapc.parentType.mainKey;
					var alterKey = $scope.pM.vMember.sapc.parentType.alterKey;
					$scope.main.vMember.sapc.tableType.attribute[selfKey][proValue].data = data[selfKey];
					if($scope.main.vMember.sapc.tableType.attribute.hasOwnProperty(mainKey)) {
						$scope.main.vMember.sapc.tableType.attribute[mainKey][proValue].data = data[mainKey];
					}
					if($scope.main.vMember.sapc.tableType.attribute.hasOwnProperty(alterKey)) {
						$scope.main.vMember.sapc.tableType.attribute[alterKey][proValue].data = data[alterKey];
					}
				} else {
					var linkKeys = $scope.main.vMember.temLink.selfKV.vt.linkKeys;
					for(var key in linkKeys) {
						$scope.main.vMember.sapc.tableType.attribute[key][proValue].data = data[linkKeys[key]];
					}
				}
				angular.element("#parentSearchModel").modal("hide")
			}
			/**
			 * 格式化模态框中收集的updateData
			 */
			function formatUpadateData() {
				var obj = {};
				angular.copy($scope.prodAddData, obj);
				for(var i = 0; i < $scope.gridOptions.columnDefs.length; i++) {
					if($scope.gridOptions.columnDefs[i].boxType == 'checkBox') {
						if($scope.prodAddData[$scope.gridOptions.columnDefs[i].field] == '是') {
							obj[$scope.gridOptions.columnDefs[i].field] = '1';
						}
						if($scope.prodAddData[$scope.gridOptions.columnDefs[i].field] == '否') {
							obj[$scope.gridOptions.columnDefs[i].field] = '0';
						}
					}
				}
				return cj.parseCj(obj);
			}
			/**
			 * 加载树形图
			 * ---------------------------------------加载树形图开始------------------------------------------------------------
			 */
			//组织机构展开树方法
			$scope.expandOrg = function(org) {
				org.ifExpand = !org.ifExpand;
			};
			$scope.bizFlag = false;
			/**
			 * ------------------------------------------------加载标准树形图-----------------------------------------------
			 */
			$scope.main.vfunc.onclick.treeButtonStd = function(valueProStd, proStdFlag, treeFlag) {
				$scope.valueProStd = valueProStd;
				$scope.treeFlag = treeFlag;
				$scope.proStdFlag = proStdFlag;
				var bizUrl = '';
				//			if(jsonObj.key = "T_PM_RENT") {

				//			} else {
				bizUrl = viewGridProvider.httpPort() + '/orgTrees?isPlatformRent=0';
				//			}
				viewGridProvider.httpCommit(bizUrl).then(function success(res) {
					$scope.stdTreeList = []
					$scope.stdTreeRes = $.ET.toObjectArr(res.data)
					for(let i = 0; i < $scope.stdTreeRes.length; i++) {
						//为根节点
						if($scope.stdTreeRes[i].upperOrgId == '' || $scope.stdTreeRes[i].upperOrgId ==
							0) {
							let obj = {}
							obj.ifExpand = true;
							obj.orgAlias = $scope.stdTreeRes[i].orgAlias
							obj.orgCode = $scope.stdTreeRes[i].orgCode
							obj.orgId = $scope.stdTreeRes[i].orgId
							obj.orgName = $scope.stdTreeRes[i].orgName
							obj.orgTypeId = $scope.stdTreeRes[i].orgTypeId
							obj.orgTypeCode = $scope.stdTreeRes[i].orgTypeCode
							obj.orgTypeName = $scope.stdTreeRes[i].orgTypeName
							obj.upperOrgAlias = $scope.stdTreeRes[i].upperOrgAlias
							obj.upperOrgCode = $scope.stdTreeRes[i].upperOrgCode
							obj.upperOrgName = $scope.stdTreeRes[i].upperOrgName
							obj.upperOrgId = $scope.stdTreeRes[i].upperOrgId
							obj.children = []
							treeStdChi(obj)
							$scope.stdTreeList.push(obj)
						}
					}
				}, function errorCallback(response) {});

			}
			//子节点
			function treeStdChi(obj) {
				for(let i = 0; i < $scope.stdTreeRes.length; i++) {
					if($scope.stdTreeRes[i].upperOrgId === obj.orgId) {
						let menu = {}
						menu.ifExpand = true
						menu.orgAlias = $scope.stdTreeRes[i].orgAlias
						menu.orgCode = $scope.stdTreeRes[i].orgCode
						menu.orgId = $scope.stdTreeRes[i].orgId
						menu.orgName = $scope.stdTreeRes[i].orgName
						menu.orgTypeId = $scope.stdTreeRes[i].orgTypeId
						menu.orgTypeCode = $scope.stdTreeRes[i].orgTypeCode
						menu.orgTypeName = $scope.stdTreeRes[i].orgTypeName
						menu.upperOrgAlias = $scope.stdTreeRes[i].upperOrgAlias
						menu.upperOrgCode = $scope.stdTreeRes[i].upperOrgCode
						menu.upperOrgName = $scope.stdTreeRes[i].upperOrgName
						menu.upperOrgId = $scope.stdTreeRes[i].upperOrgId
						menu.children = []
						treeStdChi(menu)
						obj.children.push(menu)
					}
				}
			}
			/**
			 * ----------------------------------------------选中某组织机构并返回结果----------------------------------------
			 */
			$scope.main.vfunc.onclick.orgStdOnClick = function(orgStd) {
				console.log("zuzhijigou名字",orgStd)
				//			$scope.levelOrg=false;
				for(let item in $scope.main.vMember.sapc.tableType.attribute) {
					if((item == 'upperOrgCode' || item == 'parentOrgCode') && $scope.treeFlag == 'parTree') {
						if($scope.proStdFlag == 'proSearch') {
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch = {};
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch.data = orgStd.orgCode;
						} else {
							$scope.main.vMember.sapc.tableType.attribute[item][$scope.proStdFlag].data = orgStd
								.orgCode;
						}
					} else if((item == 'upperOrgName' || item == 'parentOrgName') && $scope.treeFlag == 'parTree') {
						if($scope.proStdFlag == 'proSearch') {
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch = {};
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch.data = orgStd.orgName;
						} else {
							$scope.main.vMember.sapc.tableType.attribute[item][$scope.proStdFlag].data = orgStd
								.orgName;
						}
					} 
					else if(item == 'orgCode' && $scope.treeFlag == 'chilTree') {
						if($scope.proStdFlag == 'proSearch') {
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch = {};
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch.data = orgStd.orgCode;
							if($scope.main.vMember.sapc.tableType.jsonObj.key == "T_PM_BIZORG_DTL") {
								$scope.main.vMember.sapc.tableType.attribute['orgTypeName'].proSearch = {
									show: true
								};
								$scope.main.vMember.sapc.tableType.attribute['orgTypeName'].proSearch.data =
									orgStd.orgTypeName;
							}
						} else {
							console.log("$scope.proStdFlag+++",$scope.proStdFlag)
							$scope.main.vMember.sapc.tableType.attribute[item][$scope.proStdFlag].data = orgStd
								.orgCode;
							if($scope.main.vMember.sapc.tableType.jsonObj.key == "T_PM_BIZORG_DTL") {
								$scope.main.vMember.sapc.tableType.attribute['orgTypeName'][$scope.proStdFlag]
									.data = orgStd.orgTypeName;
								$scope.main.vMember.sapc.tableType.attribute['parentOrgAlias'][$scope
									.proStdFlag
								].data = orgStd.upperOrgAlias;
								$scope.main.vMember.sapc.tableType.attribute['parentOrgCode'][$scope.proStdFlag]
									.data = orgStd.upperOrgCode;
							}
						}
					} else if(item == 'orgName' && $scope.treeFlag == 'chilTree'){
							$scope.main.vMember.sapc.tableType.attribute[item][$scope.proStdFlag].data = orgStd.orgName;
					}else if(item == 'userOrgCodes' && $scope.treeFlag == 'chilUserTree') {
						if($scope.proStdFlag == 'proSearch') {
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch = {};
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch.data = orgStd.orgCode;
						} else {
							$scope.main.vMember.sapc.tableType.attribute[item][$scope.proStdFlag].data = orgStd
								.orgCode;
						}
					}
				}
				$scope.valueProStd.data = orgStd.orgAlias;
				angular.element("#orgTreeStd").modal("hide");
			}
			/**
			 * 组织机构树点击事件
			 */
			$scope.main.vfunc.onclick.treeButton = function(valuePro, proFlag) {
				$scope.bizFlag = false;
				$scope.proFlag = proFlag;
				$scope.bizCode = '';
				$scope.selectBizName = '';
				$scope.valuePro = valuePro;
				var bizUrl = viewGridProvider.httpPort() + '/bizorgMains';
				viewGridProvider.httpCommit(bizUrl).then(function success(res) {

					$scope.resultBizArr = $.ET.toObjectArr(res.data);
					orgTree.treeLevel = 0;
					$scope.orgTree = orgTree;
					//				if(localStorage.getItem('bizCode')) {
					//					$scope.bizOrgTree({
					//						'bizCode': localStorage.getItem('bizCode')
					//					})
					//				}
				}, function errorCallback(response) {});
			}
			$scope.bizOrgTree = function(biz) {
				$scope.selectBizName = biz.bizName;
				$scope.bizCode = biz.bizCode;
				var topUrl;
				orgTree.children = [];
				if(biz.bizCode == localStorage.getItem('rentCode') + '_SYSTEM_STANDARD_BIZ') {
					topUrl = viewGridProvider.httpPort() + '/orgTrees?isPlatformRent=0';
					$scope.main.vfunc.onclick.treeBizDtl(orgTree, topUrl); //读取组织机构树
				} else {
					topUrl = viewGridProvider.httpPort() + localStorage.getItem('bizOrgUrl') + $scope.bizCode +
						'/tree?$opertype=root';
					$scope.loadOrgTree(orgTree, topUrl, $http); //读取组织机构树
				}

			}
			$scope.main.vfunc.onclick.treeBizDtl = function(_orgTree, _url) {
				viewGridProvider.httpCommit(_url).then(function success(res) {
					$scope.bizFlag = true;
					_orgTree.children = []
					$scope.stdTreeRes = $.ET.toObjectArr(res.data)
					for(let i = 0; i < $scope.stdTreeRes.length; i++) {
						if($scope.stdTreeRes[i].upperOrgId == '' || $scope.stdTreeRes[i].upperOrgId ==
							0) {
							let obj = {}
							obj.ifExpand = true;
							obj.orgAlias = $scope.stdTreeRes[i].orgAlias
							obj.orgCode = $scope.stdTreeRes[i].orgCode
							obj.orgId = $scope.stdTreeRes[i].orgId
							obj.orgName = $scope.stdTreeRes[i].orgName
							obj.orgTypeId = $scope.stdTreeRes[i].orgTypeId
							obj.orgTypeCode = $scope.stdTreeRes[i].orgTypeCode
							obj.orgTypeName = $scope.stdTreeRes[i].orgTypeName
							obj.children = []
							treeStdChi(obj)
							_orgTree.children.push(obj)
						}
					}
				}, function errorCallback(response) {});

			}

			$scope.loadOrgTree = function(_orgTree, _url, $http) {
				viewGridProvider.httpCommit(_url).then(function success(res) {
					$scope.bizFlag = true;
					var resultArr = $.ET.toObjectArr(res.data); //格式化后台数据
					if(resultArr.length > 0) { //如果resultArr.length === 0, 则没有下一级。
						_orgTree.children = [];
						for(var i = 0; i < resultArr.length; i++) {
							var childUrl = viewGridProvider.httpPort() + localStorage.getItem(
									'bizOrgUrl') + $scope.bizCode + '/tree/' +
								resultArr[i]["orgCode"] + localStorage.getItem('bizOrgChildUrl');
							resultArr[i].treeLevel = _orgTree.treeLevel + 1;
							resultArr[i].ifExpand = true;
							$scope.loadOrgTree(resultArr[i], childUrl, $http);
							_orgTree.children.push(resultArr[i]);
						}
					}
				}, function errorCallback(response) {});
			}
			/**
			 * 选中某组织机构并返回结果
			 */
			$scope.main.vfunc.onclick.orgOnClick = function(forthElem) {
				console.log('选中组织机构', forthElem);
				for(let item in $scope.main.vMember.sapc.tableType.attribute) {
					if(item == 'orgCode' && $scope.main.vMember.sapc.tableType.jsonObj.nameAlias != "用户" &&
						$scope.main.vMember.sapc.tableType.jsonObj.nameAlias != "班组用户关联") {
						if($scope.proFlag == 'proSearch') {
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch = {};
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch.data = forthElem
								.orgCode;
						} else {
							$scope.main.vMember.sapc.tableType.attribute[item][$scope.proFlag].data = forthElem
								.orgCode;
						}
					} else if(item == 'userOrgCodes' && ($scope.main.vMember.sapc.tableType.jsonObj
							.nameAlias == "用户" || $scope.main.vMember.sapc.tableType.jsonObj.nameAlias ==
							"班组用户关联")) {
						if($scope.proFlag == 'proSearch') {
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch = {};
							$scope.main.vMember.sapc.tableType.attribute[item].proSearch.data = forthElem
								.orgCode;
						} else {
							$scope.main.vMember.sapc.tableType.attribute[item][$scope.proFlag].data = forthElem
								.orgCode;
						}
					}
				}
				$scope.valuePro.data = forthElem.orgAlias;
				angular.element("#orgTree").modal("hide");
			}
			/**
			 * 根据选中的组织机构下拉显示所属区域
			 */
			$scope.areaType = "";
			$scope.main.vfunc.onclick.areaOnClick = function(proWhich) {
				if($scope.main.vMember.sapc.tableType.attribute['orgCode'][proWhich].data != '') {
					var urlCode = $scope.main.vMember.sapc.tableType.attribute['orgCode'][proWhich].data;
				}
				//判断节点属于哪个区域
				var tableType = $scope.main.vMember.sapc.tableType;
				var url = '';
				var nodeType = '';
				if(tableType.jsonObj.key == "T_PM_SIDELINE") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_UNIT").url;
					nodeType = 'sideLine';
					$scope.areaType = "T_PM_UNIT";
				} else if(tableType.jsonObj.key == "T_PM_TANK" || tableType.jsonObj.key == "T_IC_CNFG_BASE" ||
					tableType.jsonObj.key == "T_IC_CNFG_TANK") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_TANKAREA").url;
					nodeType = 'tank';
					$scope.areaType = "T_PM_TANKAREA";
				} else if(tableType.jsonObj.key == "T_PM_EQUIPMENT") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_AREA").url;
					nodeType = 'equipment';
					$scope.areaType = "T_PM_AREA";
				} else if(tableType.jsonObj.key == "T_PM_SHIPMENT") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_LOADRACK").url;
					nodeType = 'edgePoint';
					$scope.areaType = "T_PM_LOADRACK";
				} else if(tableType.jsonObj.key == "T_PM_SILO") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_UNIT").url;
					nodeType = 'silo';
					$scope.areaType = "T_PM_UNIT";
				} else if(tableType.jsonObj.key == "T_PM_LOCATION") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_WAREHOUSE").url;
					nodeType = 'stock';
					$scope.areaType = "T_PM_WAREHOUSE";
				} else if(tableType.jsonObj.key == "T_PM_SAMPLEPOINT") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_AREA").url;
					nodeType = 'samplePoint';
					$scope.areaType = "T_PM_AREA";
				} else if(tableType.jsonObj.key == "T_PM_DRAINAGEPORT") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_AREA").url;
					nodeType = 'outlet';
					$scope.areaType = "T_PM_AREA";
				} else if(tableType.jsonObj.key == "T_PM_TUBULATION") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_AREA").url;
					nodeType = 'tubulation';
					$scope.areaType = "T_PM_AREA";
				} else if(tableType.jsonObj.key == "T_PM_VALVE") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_AREA").url;
					nodeType = 'valve';
					$scope.areaType = "T_PM_AREA";
				} else if(tableType.jsonObj.key == "T_PM_CLOSURE") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_AREA").url;
					nodeType = 'plate';
					$scope.areaType = "T_PM_AREA";
				} else if(tableType.jsonObj.key == "T_PM_TEE") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_AREA").url;
					nodeType = 'tee';
					$scope.areaType = "T_PM_AREA";
				} else if(tableType.jsonObj.key == "T_PM_LOADPOINT") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_LOADRACK").url;
					nodeType = 'loadpoint';
					$scope.areaType = "T_PM_LOADRACK";
				} else if(tableType.jsonObj.key == "T_PM_PRDTCELL") {
					url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_UNIT").url;
					nodeType = '';
					$scope.areaType = "T_PM_UNIT";
				}
				var areaUrl;
				if(proWhich == 'proSearch') {
					if($scope.levelOrg) {
						areaUrl = viewGridProvider.httpPort() + url +
							"?inUse=1&dataStatus=1&isRecursive=1&orgCode=" + urlCode + '&$nodeType=' + nodeType;
					} else if(!$scope.levelOrg) {
						areaUrl = viewGridProvider.httpPort() + url +
							"?inUse=1&dataStatus=1&isRecursive=0&orgCode=" + urlCode + '&$nodeType=' + nodeType;
					}
				} else {
					areaUrl = viewGridProvider.httpPort() + url +
						"?inUse=1&dataStatus=1&isRecursive=1&orgCode=" + urlCode + '&$nodeType=' + nodeType;
				}
				viewGridProvider.httpCommit(areaUrl).then(function success(res) {
					var resultArr = $.ET.toObjectArr(res.data);
					$scope.areaAlias = [{
							typeName: "装置",
							areaTypeCode: "plants",
							tableName: "T_PM_UNIT",
							value: []
						},
						{
							typeName: "罐区",
							areaTypeCode: "tankAreas",
							tableName: "T_PM_TANKAREA",
							value: []
						},
						{
							typeName: "仓库",
							areaTypeCode: "warehouses",
							tableName: "T_PM_WAREHOUSE",
							value: []
						},
						{
							typeName: "装卸台",
							areaTypeCode: "loadingDocks",
							tableName: "T_PM_LOADRACK",
							value: []
						},
						{
							typeName: "管网",
							areaTypeCode: "pipeNetworks",
							tableName: "T_PM_PIPENET",
							value: []
						},
						{
							typeName: "生活区",
							areaTypeCode: "communities",
							tableName: "T_PM_COMMUNITY",
							value: []
						},
						{
							typeName: "办公区",
							areaTypeCode: "administrations",
							tableName: "T_PM_ADMINISTRATION",
							value: []
						}
					];
					for(var i = 0; i < resultArr.length; i++) {
						for(var j = 0; j < $scope.areaAlias.length; j++) {
							if($scope.areaType == $scope.areaAlias[j].tableName) {
								$scope.areaAlias[j].value.push({
									areaAlias: resultArr[i].areaAlias,
									orgCode: resultArr[i].orgCode,
									areaCode: resultArr[i].areaCode,
									areaName: resultArr[i].areaName
								})
							} else if($scope.areaType == "T_PM_AREA") {
								if(resultArr[i].areaTypeCode == $scope.areaAlias[j].areaTypeCode) {
									$scope.areaAlias[j].value.push({
										areaAlias: resultArr[i].areaAlias,
										orgCode: resultArr[i].orgCode,
									areaCode: resultArr[i].areaCode,
									areaName: resultArr[i].areaName
									
									})
								}
							}
						}
					}
				}, function errorCallback(response) {});
			}
			$scope.main.vfunc.onclick.areaSelected = function(proWhich, areaAlias, areaCode,areaName,orgCode) {
				var tableType = $scope.main.vMember.sapc.tableType;
				for(var key in tableType.attribute) {
					if(key == 'areaAlias') {
						tableType.attribute[key][proWhich].data = areaAlias;
					} else if(key == 'areaCode') {
						tableType.attribute[key][proWhich].data = areaCode;
					} else if(key == 'areaName') {
						tableType.attribute[key][proWhich].data = areaName;
					} else if(key == 'orgCode') {
						tableType.attribute[key][proWhich].data = orgCode;
					}
				}
			}

			/**
			 * 能源节点表、装置与装置界区关系表
			 * 新增修改
			 * 下拉显示装置和虚拟装置
			 */

			$scope.main.vfunc.onclick.unitOnClick = function(proWhich) {
				var tableType = $scope.main.vMember.sapc.tableType;
				var obj = {
					"busiArea": 'fms_mtrl',
					"energyMng": 'fms_em',
					"operMng": 'fms_ope'
				}
				$scope.unitAlias = [{
						typeName: "装置",
						url: "/plants",
						value: []
					},
					{
						typeName: "虚拟装置",
						url: '/bizs/' + obj[tableType.jsonObj.bizType] + '/ywUnits',
						value: []
					}
				];

				$scope.unitAlias.forEach(item => {
					let searchUrl = viewGridProvider.httpPort() + item.url + '?inUse=1&dataStatus=1';
					viewGridProvider.httpCommit(searchUrl).then(function success(res) {
						item.value = $.ET.toObjectArr(res.data)
					}, function errorCallback(response) {});
				})
			}
			$scope.main.vfunc.onclick.unitSelected = function(proWhich, typeName, areaAlias, areaCode, areaName) {
				var tableType = $scope.main.vMember.sapc.tableType;
				for(var key in tableType.attribute) {
					if(key == 'areaAlias') {
						tableType.attribute[key][proWhich].data = areaAlias;
					} else if(key == 'areaCode') {
						tableType.attribute[key][proWhich].data = areaCode;
					} else if(key == 'areaName') {
						tableType.attribute[key][proWhich].data = areaName;
					} else if(key == 'markOfVirtual') {
						tableType.attribute[key][proWhich].data = typeName == "虚拟装置" ? 1 : 0
					} else if(key == 'ofFms') {
						tableType.attribute[key][proWhich].data = typeName == "虚拟装置" ? 0 : 1
					}
				}
			}

			/**
			 *------------------------------------------------获取基础分类-----------------------------------------------
			 * */
			$scope.main.vfunc.onclick.classOnClick = function(forthElem) {
				if($scope.main.vMember.sapc.tableType.jsonObj.key == "T_IC_CNFG_TANK") {
					var tankType = $scope.main.vMember.sapc.tableType.attribute['tankTypeCode'][forthElem].data;
					var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_IC_CNFG_CLASS").url;
					var areaUrl = localStorage.getItem('serverUrl') + url + '?inUse=1&dataStatus=1' +
						'&tankType=' + tankType;
					setcnfgClass(areaUrl);
				} else if($scope.main.vMember.sapc.tableType.jsonObj.key == "T_IC_CNFG_BASE") {
					var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_IC_CNFG_CLASS").url;
					var areaUrl = localStorage.getItem('serverUrl') + url + '?inUse=1&dataStatus=1';
					setcnfgClass(areaUrl);
				}
			}

			function setcnfgClass(areaUrl) {
				viewGridProvider.httpCommit(areaUrl).then(function success(res) {
					var resultArr = $.ET.toObjectArr(res.data);
					//					console.log('基础配置返回值', resultArr);
					$scope.cnfgClass = [];
					for(var i = 0; i < resultArr.length; i++) {
						$scope.cnfgClass.push({
							cnfgClassId: resultArr[i].cnfgClassId,
							cnfgClassCode: resultArr[i].cnfgClassCode,
							cnfgClassName: resultArr[i].cnfgClassName
						})
					}
				}, function errorCallback(response) {});
			}
			/**
			 *----------------------------------多业务层次明细表查询区业务域下拉框获取数据-----------------------------------------------
			 * */
			$scope.main.vfunc.onclick.bizCodeOnClick = function() {
				var bizUrl = viewGridProvider.httpPort() + '/bizorgMains' + '?inUse=1&dataStatus=1';
				//console.log('bizUrl',bizUrl)
				viewGridProvider.httpCommit(bizUrl).then(function success(res) {
					$scope.bizCodeList = $.ET.toObjectArr(res.data);
					for(let i = 0; i < $scope.bizCodeList.length; i++) {
						if($scope.bizCodeList[i].bizCode.indexOf("_SYSTEM_STANDARD_BIZ") > -1) {
							$scope.bizCodeList.splice(i, 1)
						}
					}
					console.log('业务域返回', $scope.bizCodeList);
				}, function errorCallback(response) {});
			}

			/**
			 *------------------------------------------------获取用户-----------------------------------------------
			 * */
			$scope.main.vfunc.onclick.userOnClick = function(forthElem) {
				var userUrl;
				var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_PM_USER").url;
				if($scope.main.vMember.sapc.tableType.jsonObj.key == "T_PM_TEAMANDUSER") {
					var userOrgCodes = $scope.main.vMember.sapc.tableType.attribute['userOrgCodes'][forthElem]
						.data;
					userUrl = localStorage.getItem('serverUrl') + url + '?inUse=1&dataStatus=1' +
						'&userOrgCodes=' + userOrgCodes;
				} else if($scope.main.vMember.sapc.tableType.jsonObj.key == "T_PM_USERPOSITION") {
					userUrl = localStorage.getItem('serverUrl') + url + '?inUse=1&dataStatus=1';
				}
				setUserName(userUrl);
			}

			function setUserName(userUrl) {
				//console.log('查询用户所用url', userUrl);
				viewGridProvider.httpCommit(userUrl).then(function success(res) {
					var resultArr = $.ET.toObjectArr(res.data);
					$scope.userNameList = [];
					for(var i = 0; i < resultArr.length; i++) {
						$scope.userNameList.push({
							userId: resultArr[i].userId,
							userCode: resultArr[i].userCode,
							userName: resultArr[i].userName
						})
					}
					//console.log('查询用户返回数组', $scope.userNameList);
				}, function errorCallback(response) {});
			}
			//选择单条用户，带出用户Id，code，name
			$scope.main.vfunc.onclick.selectUserName = function(pro, userName) {
				for(let i = 0; i < $scope.userNameList.length; i++) {
					if(userName == $scope.userNameList[i].userName) {
						$scope.main.vMember.sapc.tableType.attribute['userId'][pro].data = $scope.userNameList[
							i].userId;
						$scope.main.vMember.sapc.tableType.attribute['userCode'][pro].data = $scope
							.userNameList[i].userCode
						$scope.main.vMember.sapc.tableType.attribute['userName'][pro].data = $scope
							.userNameList[i].userName
					}
				}
			}
			$scope.main.vfunc.onclick.changeBaseClass = function(key, cnfgClassName) {
				if($scope.main.vMember.sapc.tableType.attribute[key].proUpdate.data != cnfgClassName) {
					$scope.main.vMember.sapc.tableType.attribute['cnfgParaShowvalue'].proUpdate.data = '';
					$scope.main.vMember.sapc.tableType.attribute['isUseFormula'].proUpdate.data = null;
					$scope.main.vMember.sapc.tableType.attribute['classParaId'].proUpdate.data = null;
					$scope.main.vMember.sapc.tableType.attribute['formula'].proUpdate.data = '';
					$scope.main.vMember.sapc.tableType.attribute['cnfgParaValue'].proUpdate.data = null;
					$scope.main.vMember.sapc.tableType.attribute[key].proUpdate.data = cnfgClassName;
				}
				for(var key in $scope.main.vMember.sapc.tableType.attribute) {
					if(key == 'cnfgClassId') {
						for(var i = 0; i < $scope.cnfgClass.length; i++) {
							if($scope.cnfgClass[i].cnfgClassName == cnfgClassName) {
								$scope.main.vMember.sapc.tableType.attribute['cnfgClassCode'].proUpdate.data =
									$scope.cnfgClass[i].cnfgClassCode;
								$scope.main.vMember.sapc.tableType.attribute['cnfgClassId'].proUpdate.data =
									$scope.cnfgClass[i].cnfgClassId;
							}
						}
					}
				}
			}
			/*
			 * 公式校验
			 * 
			 */
			$scope.checkFormula = function(string, obj) {
				// 剔除空白符
				string = string.replace(/\s/g, '');
				// 错误情况，空字符串
				if("" === string) {
					return false;
				}
				// 错误情况，运算符连续
				if(/[\+\-\*\/]{2,}/.test(string)) {
					return false;
				}
				// 空括号
				if(/\(\)/.test(string)) {
					return false;
				}
				// 错误情况，括号不配对
				var stack = [];
				for(var i = 0, item; i < string.length; i++) {
					item = string.charAt(i);
					if('(' === item) {
						stack.push('(');
					} else if(')' === item) {
						if(stack.length > 0) {
							stack.pop();
						} else {
							return false;
						}
					}
				}
				if(0 !== stack.length) {
					return false;
				}
				// 错误情况，(后面是运算符 
				if(/\([\+\-\*\/]/.test(string)) {
					return false;
				}
				// 错误情况，)前面是运算符
				if(/[\+\-\*\/]\)/.test(string)) {
					return false;
				}
				// 错误情况，(前面不是运算符
				if(/[^\+\-\*\/]\(/.test(string)) {
					return false;
				}
				// 错误情况，)后面不是运算符
				if(/\)[^\+\-\*\/]/.test(string)) {
					return false;
				}
				// 错误情况，变量没有来自“待选公式变量”
				var tmpStr = string.replace(/[\(\)\+\-\*\/]{1,}/g, '`');
				var array = tmpStr.split('`');
				for(var i = 0, item; i < array.length; i++) {
					item = array[i];
					if(/[A-Z]/i.test(item) && 'undefined' === typeof(obj[item])) {
						return false;
					}
				}
				return true;
			}
			/*
			 * 公式编辑器开始
			 */
			//显示右侧表

			$scope.cnfgFormulaPara = [];
			$scope.main.vfunc.onclick.formulaButton = function(formulaValue) {
				if($scope.cnfgFormulaPara.length == 0 || formulaValue != $scope.formulaValue.data) {
					$scope.formulaValue = formulaValue;
					$scope.formula = $scope.formulaValue.data;
				}
				if($scope.cnfgFormulaPara.length == 0) {
					var url = viewGridProvider.getTableIndexJsonByKey(tableIndex, "T_IC_CNFG_FORMULA_PARA").url;
					var areaUrl = viewGridProvider.httpPort() + url + '?inUse=1&dataStatus=1';
					//console.log('areaUrl',areaUrl)
					viewGridProvider.httpCommit(areaUrl).then(function success(res) {
						$scope.resultArr = $.ET.toObjectArr(res.data);
						//console.log('计算器返回', $scope.resultArr);
						for(var i = 0; i < $scope.resultArr.length; i++) {
							$scope.cnfgFormulaPara.push({
								formulaParaCode: $scope.resultArr[i].formulaParaCode,
								formulaParaName: $scope.resultArr[i].formulaParaName
							});
						}
					}, function errorCallback(response) {});
					//console.log('$scope.cnfgFormulaPara', $scope.cnfgFormulaPara);
				}
			}
			//文本框显示公式
			$scope.undoStack = [];
			$scope.newStack = [];
			//	文本框显示
			$scope.main.vfunc.onclick.num = function(num) {
				$scope.formula += num;
				$scope.newStack.push($scope.formula);
				$scope.undoStack = $scope.newStack;
				//console.log('总数组', $scope.undoStack);
			}
			//	公式确认
			$scope.main.vfunc.onclick.ensure = function(formula) {
				var fields = {};
				for(var i = 0; i < $scope.cnfgFormulaPara.length; i++) {
					var code = $scope.cnfgFormulaPara[i].formulaParaCode;
					fields[code] = 1;
				}
				var flag = $scope.checkFormula(formula, fields);
				if(flag) {
					$scope.formulaValue.data = $scope.formula;
					angular.element("#formulaEdit").modal("hide");
				} else {
					alert("公式有误，请修改");
				}

			}
			//	文本框重置
			$scope.main.vfunc.onclick.remove = function() {
				$scope.formula = "";
				$scope.undoStack = [];
				$scope.newStack = [];
			}
			//	撤销
			$scope.main.vfunc.onclick.back = function(formula) {
				if($scope.formula != '') {
					var curr = $scope.undoStack.indexOf(formula) - 1;
					for(var i = 0; i < curr + 1; i++) {
						$scope.newStack[i] = $scope.undoStack[i];
					}
					if(curr < 0) {
						$scope.formula = '';
						$scope.undoStack = [];
						$scope.newStack = [];
					} else {
						$scope.formula = $scope.undoStack[curr];
					}
					console.log(curr);
				} else {
					alert('no');
				}
			}
			//	恢复
			$scope.main.vfunc.onclick.recover = function(formula) {
				var curr = $scope.undoStack.indexOf(formula) + 1; //下一个下标
				if(curr > $scope.undoStack.length - 1) {
					$scope.formula = $scope.undoStack[$scope.undoStack.length - 1];
				} else {
					$scope.formula = $scope.undoStack[curr];
				}
				$scope.newStack = $scope.undoStack;
			}
			/**
			 * 树形图结束，公式编辑器结束
			 */
			/**
			 * ---------------------------------------用户与岗位关联表，新增弹窗。用户属性表格的加载和数据展示-----------------------------------------------
			 */
			//初始化表格
			function initGridOptionsUserA(jsonObj) {
				var fileName = 'model/' + jsonObj;
				viewGridProvider.httpCommit(fileName).then(function success(res) {
					viewGridProvider.hideCrtMnt(res.data);
					$scope.userOptionsAdd.columnDefs = viewGridProvider.formatGridOptionsColumnDefs(res.data
						.attribute, false, $scope.updateShow, $scope.removeBnt, $scope.orgBtnShow);
				}, function error(res) {
					cj.Error(res);
				});
			};
			//点击新增模态框用户名称图标
			/**
			 *初始化选中行数，
			 *初始化查询条件框，
			 *获取数据
			 */
			$scope.main.vfunc.onclick.userARelation = function(key, value) {
				$scope.main.vfunc.onclick.treeButton('', ''); //很重要，删除以后数据表格就不显示了
				$scope.userAGridApi.grid.selection.selectedCount = 0;
				viewGridProvider.clearSearch($scope.main.vMember.sapc.tableType.attribute.userName.parentType);
				$scope.main.vfunc.onclick.serchAUserEnsure();
			};
			//获取数据，初始化数据，查询数据，分页都调用此方法
			function initAGridOptionsUserRelation(curPage, pageSize, searchStr) {
				if(searchStr) {
					var userRelationUrl = viewGridProvider.httpPort() +
						'/users?$skip=' + (curPage - 1) * pageSize + '&$top=' + pageSize + searchStr +
						'&inUse=1&dataStatus=1';
				} else {
					var userRelationUrl = viewGridProvider.httpPort() +
						'/users?$skip=' + (curPage - 1) * pageSize + '&$top=' + pageSize + '&inUse=1&dataStatus=1';
				}
				//console.log("查询所用url", userRelationUrl)
				viewGridProvider.httpCommit(userRelationUrl).then(function success(res) {
					if(res.data.collection.hasOwnProperty('items')) {
						if(res.data.collection.items.length > 0) {
							for(var i = 0; i < res.data.collection.page.data.length; i++) {
								if(res.data.collection.page.data[i].name == "recordCount" || res.data
									.collection.page.data[i].name == "totalElements") {
									$scope.userOptionsAdd.totalItems = res.data.collection.page.data[i]
										.value;
								}
							}
							$scope.userOptionsAdd.data = cj.Parse(res.data, true);
							viewGridProvider.changeBooleanToCh($scope.main.vMember.sapc.tableType.attribute
								.userName.parentType, $scope.userOptionsAdd.data);
						} else {
							angular.element("#tempresetModel").modal("show");
							$scope.tempreset = "查询失败";
							$scope.error = '没有该记录';
						}
					}
				}, function error(res) {});
			};
			//将选中数据返回到上级
			$scope.selectUserARelation = []; //选中行
			$scope.main.vfunc.onclick.userAddEnsure = function() {
				$scope.selectUserARelation = $scope.userAGridApi.selection.getSelectedRows();
				var tableType = $scope.main.vMember.sapc.tableType;
				if($scope.selectUserARelation.length > 0) {
					for(var key in tableType.attribute) {
						if(key == "userCode") {
							tableType.attribute['userCode'].proAdd.data = $scope.selectUserARelation[0][
								'userCode'
							];
						} else if(key == "userId") {
							tableType.attribute['userId'].proAdd.data = $scope.selectUserARelation[0]['userId'];
						} else if(key == "userName") {
							tableType.attribute['userName'].proAdd.data = $scope.selectUserARelation[0][
								'userName'
							];
						}
					}
				}
			}
			//查询
			$scope.main.vfunc.onclick.serchAUserEnsure = function() {
				var str = viewGridProvider.getSearchKVUrl($scope.main.vMember.sapc.tableType.attribute.userName
					.parentType);
				initAGridOptionsUserRelation(1, $scope.userOptionsAdd.paginationPageSize, str);

			}
			/**
			 * ------------------------------------------------------------结束--------------------------------------------------------
			 */
			/**
			 * ---------------------------------------用户与岗位关联表，修改弹窗。用户属性表格的加载和数据展示-----------------------------------------------
			 */
			//初始化表格
			function initGridOptionsUserU(jsonObj) {
				var fileName = 'model/' + jsonObj;
				viewGridProvider.httpCommit(fileName).then(function success(res) {
					viewGridProvider.hideCrtMnt(res.data);
					$scope.userOptionsUpdate.columnDefs = viewGridProvider.formatGridOptionsColumnDefs(res
						.data.attribute, false, $scope.updateShow, $scope.removeBnt, $scope.orgBtnShow);
				}, function error(res) {
					cj.Error(res);
				});
			};
			//点击编辑模态框用户名称图标
			/**
			 *初始化选中行数，
			 *初始化查询条件框，
			 *获取数据
			 */
			$scope.main.vfunc.onclick.userURelation = function(key, value) {
				$scope.main.vfunc.onclick.treeButton('', ''); //很重要，删除以后数据表格就不显示了
				$scope.userUGridApi.grid.selection.selectedCount = 0;
				viewGridProvider.clearSearch($scope.main.vMember.sapc.tableType.attribute.userName.parentType);
				$scope.main.vfunc.onclick.serchUUserEnsure();
			};
			//获取数据，初始化数据，查询数据，分页都调用此方法
			function initUGridOptionsUserRelation(curPage, pageSize, searchStr) {
				if(searchStr) {
					var userRelationUrl = viewGridProvider.httpPort() +
						'/users?$skip=' + (curPage - 1) * pageSize + '&$top=' + pageSize + searchStr +
						'&inUse=1&dataStatus=1';;
				} else {
					var userRelationUrl = viewGridProvider.httpPort() +
						'/users?$skip=' + (curPage - 1) * pageSize + '&$top=' + pageSize + '&inUse=1&dataStatus=1';;
				}
				//console.log("查询所用url", userRelationUrl)
				viewGridProvider.httpCommit(userRelationUrl).then(function success(res) {
					//console.log("查询返回数据==", res)
					if(res.data.collection.hasOwnProperty('items')) {
						if(res.data.collection.items.length > 0) {
							for(var i = 0; i < res.data.collection.page.data.length; i++) {
								if(res.data.collection.page.data[i].name == "recordCount" || res.data
									.collection.page.data[i].name == "totalElements") {
									$scope.userOptionsUpdate.totalItems = res.data.collection.page.data[i]
										.value;
								}
							}
							$scope.userOptionsUpdate.data = cj.Parse(res.data, true);
							viewGridProvider.changeBooleanToCh($scope.main.vMember.sapc.tableType.attribute
								.userName.parentType, $scope.userOptionsUpdate.data);
						} else {
							angular.element("#tempresetModel").modal("show");
							$scope.tempreset = "查询失败";
							$scope.error = '没有该记录';
						}
					}
				}, function error(res) {});
			};
			//将选中数据返回到上级		
			$scope.selectUserURelation = [];
			$scope.main.vfunc.onclick.userUpdateEnsure = function() {
				$scope.selectUserURelation = $scope.userUGridApi.selection.getSelectedRows();
				var tableType = $scope.main.vMember.sapc.tableType;
				if($scope.selectUserURelation.length > 0) {
					for(var key in tableType.attribute) {
						if(key == "userCode") {
							tableType.attribute['userCode'].proUpdate.data = $scope.selectUserURelation[0][
								'userCode'
							];
						} else if(key == "userId") {
							tableType.attribute['userId'].proUpdate.data = $scope.selectUserURelation[0][
								'userId'
							];
						} else if(key == "userName") {
							tableType.attribute['userName'].proUpdate.data = $scope.selectUserURelation[0][
								'userName'
							];
						}
					}
				}
			}

			//查询
			$scope.main.vfunc.onclick.serchUUserEnsure = function() {
				var str = viewGridProvider.getSearchKVUrl($scope.main.vMember.sapc.tableType.attribute.userName
					.parentType);
				initUGridOptionsUserRelation(1, $scope.userOptionsUpdate.paginationPageSize, str);
			}
			/**
			 * ---------------------------------------------------------结束------------------------------------------------------------
			 */
		}
	])