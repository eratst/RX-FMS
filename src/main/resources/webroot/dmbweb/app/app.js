'use strict';

/**
 * 本地测试路由
 */
//localStorage.setItem('serverUrl', 'http://10.238.129.11:8081/DMBService'); //王森
//localStorage.setItem('serverUrl', 'http://10.238.129.225:8081/DMBService'); //张华琪
//localStorage.setItem('serverUrl', 'http://10.238.129.27:8081/DMBService');//鱼磊
//localStorage.setItem('serverUrl', 'http://10.238.129.43:8081/DMBService'); //张思珂
//localStorage.setItem('serverUrl', 'http://10.238.129.30:8081/DMBService'); //赵雅霖

/**
 * 正式环境路由
 */
//localStorage.setItem('serverUrl', 'http://10.238.255.101:32430/DMBService'); //live

//测试环境配置
// localStorage.setItem('serverUrl', 'http://10.238.220.85:8081/DMBService'); //test测试
   localStorage.setItem('serverUrl', '/FactoryModelService/DMBService'); //test发版时
localStorage.setItem('promaceDomian', '.promace.sinopec.com'); //服务应用配置发版域名
localStorage.setItem('UserCodeUrl', '/FactoryModelService/aaaUser'); //测试环境相对路径，【权限】

//生产环境配置
//localStorage.setItem('serverUrl', 'http://datamainsvc.platform.promace.sinopec.com/DMBService'); //test
//localStorage.setItem('promaceDomian', '.promace.sinopec.com');//服务应用配置发版域名
//localStorage.setItem('UserCodeUrl', '/aaaUser'); //正式环境相对路径，【权限】

localStorage.setItem('indexHtmlUrl', 'dmbweb/app/index.html'); //服务应用配置发版首页相对路径

localStorage.setItem('bizOrgUrl', '/bizorgDtls/'); //组织机构树根
localStorage.setItem('bizOrgChildUrl', '?$opertype=children'); //组织机构树子
localStorage.setItem('bizCode', '');
localStorage.setItem('rentCode', '');
localStorage.setItem('rentServerUrl', '');
/**
 * 本地测试逻辑多租时需要，发版时注释
 */
// localStorage.setItem('testRent', true); //test
// localStorage.setItem('testRentUrl', 'em.promace.sinopec.com'); //test

//
// Declare app level module
angular.module('myApp', [
		'ui.router',
		'myApp.uiGrid',
		'myApp.tableSelector'
		//		'myApp.version'
	])

	.config(['$locationProvider', '$urlRouterProvider', '$stateProvider', function($locationProvider, $urlRouterProvider, $stateProvider) {
		//		$locationProvider.hashPrefix('!');
		$urlRouterProvider.otherwise("/app/uiGrid/T_PM_TANK");
		$stateProvider.state('app', {
				url: "/app",
				abstract: true,
				templateUrl: "viewTableSelector/tableSelector.html",
				controller: 'tableSelector'
			}).state('appGrid', {
				url: "/appGrid",
				abstract: true,
				templateUrl: "viewTableSelector/tableSelectorGrid.html",
				controller: 'tableSelector'
			})
			.state('app.uiGrid', {
				url: "/uiGrid/:id",
				templateUrl: "uiGrid/uiGrid.html",
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
			.state('appGrid.uiGrid', {
				url: "/uiGrid/:id",
				templateUrl: "uiGrid/uiGrid.html",
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
		//			.state('app.uiGrid.parentModelSearch', {
		//				url: '/parentModelSearch/:idp',
		//				templateUrl: 'uiGrid/parentSearch.html',
		//				controller: 'uiparentModelSearchGridCtrl'
		//			})
		//			.state('appGrid.uiGrid.parentModelSearch', {
		//				url: '/parentModelSearch/:idp',
		//				templateUrl: 'uiGrid/parentSearch.html',
		//				controller: 'uiparentModelSearchGridCtrl'
		//			});
	}])
	/**
	 * provider模块化配置服务
	 */
	.provider('viewGridProvider', function() {
		this.$get = function($http, $filter, $location) {
			var rentCode;
			var serverUrl;
			var isOnLine = false;
			return {
				init: function() {
					var url = $location.host();
					//console.log('host', url)
					///////////////////////////处理上架后情况,如test2.promace.sinopec.com//////////////////
					var index = url.indexOf(localStorage.getItem('promaceDomian'))
					if(index > -1) {
						rentCode = url.slice(0, index)
						serverUrl = localStorage.getItem('serverUrl') + '/rents/' + rentCode

						localStorage.setItem('rentCode', rentCode)
						localStorage.setItem('rentServerUrl', serverUrl)
						isOnLine = true;
						return
					}
					///////////////////////////前后端合并后指定租户方式，如http://127.0.0.1/DMBService/rents/test2/web/app/index.html//////////////////
					url = $location.absUrl();
					//console.log('absUrl', url)
					var testStr = '/rents/'
					index = url.indexOf(testStr)

					var index2 = url.indexOf(localStorage.getItem('indexHtmlUrl'))
					//					localStorage.setItem('serverUrl', 'http://10.238.129.11:8081/DMBService'); //王森
					if(index > -1 && index2 > index) {
						rentCode = url.slice(index + testStr.length, index2 - 1)
						serverUrl = localStorage.getItem('serverUrl') + '/rents/' + rentCode
						localStorage.setItem('rentCode', rentCode)
						localStorage.setItem('rentServerUrl', serverUrl)
						return
					}
					///////////////////////////前后端分离或未指定租户方式，如http://127.0.0.1/web/app/index.html//////////////////

					var lcoalUrl = localStorage.getItem('testRentUrl');
					if(lcoalUrl == null || lcoalUrl == '') {
						rentCode = ''
						serverUrl = localStorage.getItem('serverUrl')
						localStorage.setItem('rentCode', rentCode)
						localStorage.setItem('rentServerUrl', serverUrl)
						return
					} else {
						//console.log('lcoalUrl', lcoalUrl)
						var index = lcoalUrl.indexOf(localStorage.getItem('promaceDomian'))
						if(index == -1) {
							rentCode = ''
							serverUrl = localStorage.getItem('serverUrl')
							localStorage.setItem('rentCode', rentCode)
							localStorage.setItem('rentServerUrl', serverUrl)
						} else {
							rentCode = lcoalUrl.slice(0, index)
							serverUrl = localStorage.getItem('serverUrl') + '/rents/' + rentCode
							localStorage.setItem('rentCode', rentCode)
							localStorage.setItem('rentServerUrl', serverUrl)
						}
					}

					///////获取登录用户///////////////////////xiayl237  admin
					localStorage.setItem('userCode', "ssh")
					//http://10.238.255.204:8099/FactoryModelService/aaaUser

				},
				getRentCode: function() {
					return localStorage.getItem('rentCode')
				},
				httpPort: function() {
					//					this.init();
					return localStorage.getItem('rentServerUrl')
				},
				httpOrgPort: function() {
					return localStorage.getItem('rentServerUrl')
				},
				///////////////////////////////////////////////////封装增删改查请求///////////////////////////////////////////////////////////
				/**
				 * 获取数据
				 */
				httpCommit: function(fileName) {
					return $http({
						method: "GET",
						url: fileName,
						responseType: "json"
					});
				},
				/**
				 * 新增数据
				 */
				httpPost: function(mUrl, mRestFul) {
					return $http({
						method: "POST",
						url: this.httpPort() + mUrl,
						data: mRestFul
					});
				},
				/**
				 * 更新数据
				 */
				httpUpdate: function(mUrl, mRestFul) {
					return $http({
						method: "PUT",
						url: this.httpPort() + mUrl,
						data: mRestFul,
						transformResponse: function(response) {
							if(response.charAt(0) === '/') {
								return {
									'url': response
								};
							} else {
								return JSON.parse(response);
							}
						}
					});
				},
				/**
				 * 删除数据
				 */
				httpDelete: function(mUrl) {
					return $http({
						method: "DELETE",
						url: this.httpPort() + mUrl
					});
				},
				//				setShowColumData: function(jsonObj, tableType, deffered) {
				//					var fileName = 'model/' + jsonObj.tableFormat;
				//					this.httpCommit(fileName).then(function success(res) {
				//						tableType = res.data;
				//						deffered.resolve();
				//					}, function error(res) {
				//						cj.Error(res);
				//					});
				//				},
				///////////////////////////////////////////////////////////初始化模态框，默认值以及属性//////////////////////////////////////////
				/**
				 * 配置默认值
				 */
				setDefalutValue: function(tableType) {
					for(var key in tableType.attribute) {
						if(key == 'parentIdxTypeId' || key == 'rlsUserId' || key == 'rlsUserName' || key == 'cmtUserId' ||
							key == 'cmtUserName' || key == 'extpara1' || key == 'extpara2' || key == 'extpara3' || key == 'extpara4') {
							tableType.attribute[key].defalut = '1';
						}
						if(key == 'rlsDate' || key == 'cmtDate') {
							tableType.attribute[key].defalut = '2018-01-12 00:00:00';
						}
						if(key == 'technicAlias') {
							tableType.attribute[key].defalut = '简称';
						}
					}
				},
				/**
				 * 隐藏属性
				 */
				hideCrtMnt: function(tableType) {
					var obj = {}
					for(var key in tableType.attribute) {
						if(key == 'crtUserId' || key == 'crtUserCode' || key == 'mntUserCode' || key == 'mntUserId' ||
							key == 'rlsUserId' || key == 'cmtUserId' || key == 'version') {
							tableType.attribute[key].hide = true;
							tableType.attribute[key].required = false;
						}
						if(tableType.attribute[key].parentModel) {
							tableType.attribute[key].parent = tableType.attribute[key].parentModel;
						}
					}
				},
				/**
				 * 新增，修改，查询，封装数据
				 */
				getHttpData: function(flag, tableType) {
					var obj = {};
					if(flag == 'proAdd') {
						for(var key in tableType.attribute) {
							//如果有值
							if(tableType.attribute[key].proAdd.data || tableType.attribute[key].proAdd.data === 0) {
								obj[key] = tableType.attribute[key].proAdd.data.toString();
							}
							if(tableType.attribute[key].defalut) {
								obj[key] = tableType.attribute[key].defalut;
							}
						}
					}
					if(flag == 'proUpdate') {
						for(var key in tableType.attribute) {
							//如果有值
							if(tableType.attribute[key].proUpdate.data || tableType.attribute[key].proUpdate.data === 0) {
								if(tableType.attribute[key].proUpdate.data == "出") {
									tableType.attribute[key].proUpdate.data = 1;
								} else if(tableType.attribute[key].proUpdate.data == "进") {
									tableType.attribute[key].proUpdate.data = 0;
								} else if(tableType.attribute[key].proUpdate.data == "进出") {
									tableType.attribute[key].proUpdate.data = 2;
								} else if(tableType.attribute[key].proUpdate.data == "消耗") {
									tableType.attribute[key].proUpdate.data = 2;
								} else if(tableType.attribute[key].proUpdate.data == "是") {
									tableType.attribute[key].proUpdate.data = 1;
								} else if(tableType.attribute[key].proUpdate.data == "否") {
									tableType.attribute[key].proUpdate.data = 0;
								} else if(tableType.attribute[key].proUpdate.data == "男") {
									tableType.attribute[key].proUpdate.data = 1;
								} else if(tableType.attribute[key].proUpdate.data == "女") {
									tableType.attribute[key].proUpdate.data = 2;
								} else if(tableType.attribute[key].proUpdate.data == "扩展组织机构") {
									tableType.attribute[key].proUpdate.data = 1;
								} else if(tableType.attribute[key].proUpdate.data == "标准组织机构") {
									tableType.attribute[key].proUpdate.data = 0;
								} else if(tableType.attribute[key].proUpdate.data == "全部" && tableType.attribute[key].display == "适用罐类型") {
									tableType.attribute[key].proUpdate.data = 0;
								} else if(tableType.attribute[key].proUpdate.data == "卧罐" && tableType.attribute[key].display == "适用罐类型") {
									tableType.attribute[key].proUpdate.data = 1;
								} else if(tableType.attribute[key].proUpdate.data == "球罐" && tableType.attribute[key].display == "适用罐类型") {
									tableType.attribute[key].proUpdate.data = 2;
								} else if(tableType.attribute[key].proUpdate.data == "立罐" && tableType.attribute[key].display == "适用罐类型") {
									tableType.attribute[key].proUpdate.data = 3;
								}
								obj[key] = tableType.attribute[key].proUpdate.data.toString();
							}
							if(tableType.attribute[key].defalut) {
								obj[key] = tableType.attribute[key].defalut;
							}
						}
					}
					if(flag == 'proSearch') {
						for(var key in tableType.attribute) {
							//如果有值
							if(tableType.attribute[key].proSearch) {
								if(tableType.attribute[key].proSearch.data || tableType.attribute[key].proSearch.data === 0) {
									obj[key] = tableType.attribute[key].proSearch.data.toString();
								}
							}
						}
						return obj;
					}
					return cj.parseCj(obj);
				},
				/**
				 * 将选中行数据绑定到修改对话框
				 */
				setUpdateData: function(data, tableType) {
					for(var key in tableType.attribute) {
						tableType.attribute[key].proUpdate.data = data[key];
					}
				},
				/**
				 * 新增，修改模态框，初始化获取关联表json及数据
				 */
				initParentTable: function(tableType, tableIndex) {
					for(var key in tableType.attribute) {
						if(tableType.attribute[key].parent) {
							var jsonObj = this.getTableIndexJsonByKey(tableIndex, tableType.attribute[key].parent);
							console.log('初始化', jsonObj);
							this.setValueTV(tableType.attribute[key], jsonObj, null, tableType, key);
						}
					}
				},
				/**
				 * 获取当前表配置属性
				 */
				getTableIndexJsonByKey: function(tableIndex, key) {
					for(var key1 in tableIndex) {
						for(var key2 in tableIndex[key1].rows) {
							if(key == key2) {
								return tableIndex[key1].rows[key2];
							}
						}
					}
				},
				/**
				 * 若属性有关联表，查询关联表页面属性，表格数据
				 */
				setValueTV: function(value, jsonObj, deffered, tableType, key) {
					//隐藏
					//					this.hideCrtMnt(pObj);
					var fileName = 'model/' + jsonObj.tableFormat;
					var httpCommit = this.httpCommit;
					var httpPort = this.httpPort();
					var httpOrgPort = this.httpOrgPort();
					var setSearchConditons = this.setSearchConditons;
					//var setOnlyFilter = this.setOnlyFilter;
					var rentCode = this.getRentCode();
					httpCommit(fileName).then(function success(res) {
						value.parentType = {};
						value.parentType = res.data;
						value.parentType.type = jsonObj.type;
						var interUrl = '';
						if(jsonObj.key == 'T_PM_RENT') {
							interUrl = httpOrgPort
							if(rentCode == '') {
								interUrl = interUrl + jsonObj.url
							} else if(isOnLine) {
								interUrl = interUrl
							}

						} else if((jsonObj.type == 'Dict' && jsonObj.key != 'T_IC_CNFG_CLASS_PARA') || jsonObj.type == 'Rel' || jsonObj.type == 'Sys') {
							let bizUrl;
							if(jsonObj.key == "T_PM_UNITAREA" || jsonObj.key == "T_PM_UNITAREAREL") {
								bizUrl = '/bizs/fms_mtrl' + jsonObj.url
							} else {
								bizUrl = jsonObj.url
							}
							interUrl = httpPort + bizUrl + '?inUse=1&dataStatus=1';
						} else if(jsonObj.type == 'Model' || jsonObj.type == 'Res' || jsonObj.type == 'Msr' || jsonObj.type == 'Use' || jsonObj.key == 'T_IC_CNFG_CLASS_PARA') {
							interUrl = httpPort + jsonObj.url + '?$codeList=0';
						}
						console.log('interUrl', interUrl)
						httpCommit(interUrl).then(function success(resu) {
							var resuArr = cj.Parse(resu.data, true)
							if(jsonObj.key == "T_PM_BIZORG_MAIN") {
								for(let i = 0; i < resuArr.length; i++) {
									if(resuArr[i].bizCode.indexOf("_SYSTEM_STANDARD_BIZ") > -1) {
										resuArr.splice(i, 1)
									}
								}
							}
							value.parentValue = resuArr;
							value.parentType.jsonObj = jsonObj;
							//**主要是为了给第一级表设置ProSearch
							for(var i = 0; i < resu.data.collection.queries.length; i++) {
								if(resu.data.collection.queries[i].rel == 'condition') {
									setSearchConditons(value.parentType, resu.data.collection.queries[i].data);
								}
							}
							if(deffered) {
								deffered.resolve();
							}
						}, function error(resu) {
							cj.Error(res);
						});

					});

				},
				//				setOnlyFilter: function(value, tableType, key) {
				//					if(value.hasOwnProperty('onFltDict')) {
				//						var obj = {};
				//						obj[key] = value.onFltDict;
				//						console.log('value', value);
				//						console.log('obj', obj);
				//						var a = $filter("filter")(value.parentValue, obj);
				//						console.log('a', a);
				//						value.parentValue = a;
				//						var alterKey = value.parentType.alterKey;
				//						var mainKey = value.parentType.mainKey;
				//						console.log("onFltDict", a);
				//						console.log(obj);
				//						console.log(alterKey);
				//						console.log(mainKey);
				//						if(a.length != 1) {
				//							//alert("onFilter错误:" + key + "  此为临时提示,请不用测试");
				//						}
				//						value.proAdd.data = a[0][key];
				//						if(alterKey && tableType.attribute.hasOwnProperty(alterKey)) {
				//							tableType.attribute[alterKey].proAdd.data = value.parentValue[0][alterKey];
				//						}
				//						if(mainKey && tableType.attribute.hasOwnProperty(mainKey)) {
				//							tableType.attribute[mainKey].proAdd.data = value.parentValue[0][mainKey];
				//						}
				//
				//					}
				//				},
				/**
				 * 设置关联表条件查询显示
				 */
				setSearchConditons: function(parentType, queriesData) {
					queriesData.pop();
					queriesData.pop();
					var arr = [];
					for(var key in parentType.attribute) {
						if(!parentType.attribute[key].hide) {
							for(var i = 0; i < queriesData.length; i++) {
								if(queriesData[i].name == key) {
									if(!parentType.attribute[key].proSearch) {
										parentType.attribute[key].proSearch = {};
										parentType.attribute[key].proSearch.show = true;
										parentType.searchCount++;

									}
								}
							}
						}
					}
				},
				/**
				 * 初始化设置属性是否显示和可编辑
				 */
				setPartFlag: function(tableType) {
					var obj = {};
					for(var key in tableType.attribute) {
						/**增加及修改的pro初始化开始*/
						if(tableType.attribute[key].hasOwnProperty('proAdd')) {
							//如果有则按原本的值
						} else {
							tableType.attribute[key].proAdd = {};
							if(tableType.attribute[key].hide) { //如果hide是true							
								tableType.attribute[key].proAdd.show = false;
								tableType.attribute[key].proAdd.edit = false;
							} else {
								tableType.attribute[key].proAdd.show = true;
								tableType.attribute[key].proAdd.edit = true;
							}
						}
						if(tableType.attribute[key].hasOwnProperty('proUpdate')) {
							//如果有则按原本的值
						} else {
							tableType.attribute[key].proUpdate = {};
							if(tableType.attribute[key].hide) { //如果hide是true							
								tableType.attribute[key].proUpdate.show = false;
								tableType.attribute[key].proUpdate.edit = false;
							} else {
								tableType.attribute[key].proUpdate.show = true;
								tableType.attribute[key].proUpdate.edit = true;
							}
						}
						/**增加及修改的pro初始化结束*/
						/**对字典表和模型表的id/code做校验开始*/
						//						if(!tableType.attribute[key].proAdd.hasOwnProperty('uniqueCheckNeed')) {
						//							tableType.attribute[key].proAdd.uniqueCheckNeed = false;
						//							if(tableType.type == 'Dict' || tableType.type == 'Model') {
						//								if(!tableType.attribute[key].hide) {
						//									if(key == tableType.alterKey || key == tableType.mainKey) {
						//										tableType.attribute[key].proAdd.uniqueCheckNeed = true;
						//									}
						//								}
						//							}
						//						}
						/**对字典表和模型表的id/code做校验结束*/
					}
				},
				/**
				 * 初始化页面设置查询显示
				 */
				setSearchFlag: function(tableType, jsonObj) {
					if(tableType.searchCount) {
						return 0;
					}
					tableType.searchCount = 0;
					let bizUrl;
					if(tableType.jsonObj.key == "T_PM_UNITAREA" || tableType.jsonObj.key == "T_PM_UNITAREAREL") {
						bizUrl = '/bizs/fms_mtrl' + jsonObj.url
					} else {
						bizUrl = jsonObj.url
					}
					var url = this.httpPort() + bizUrl + "?$skip=0&$top=1";
					this.httpCommit(url).then(function success(res) {
						for(var i = 0; i < res.data.collection.queries.length; i++) {
							if(res.data.collection.queries.rel = 'condition') {
								var queriesData = res.data.collection.queries[i].data;
							}
						}
						for(var key in tableType.attribute) {
							if(!tableType.attribute[key].hide) {
								for(var i = 0; i < queriesData.length; i++) {
									if(queriesData[i].name == key) {
										if(!tableType.attribute[key].hasOwnProperty("proSearch")) {
											tableType.attribute[key].proSearch = {};
											tableType.attribute[key].proSearch.show = true;
											tableType.searchCount++
										}
									}
								}
							}
						}
					}, function error(res) {
						cj.Error(res);
					});
				},
				//增加确认时 发送数据前 检查有唯一约束的键是否已存在
				//				checkUniqueDataS: function(tableType, deffered) {
				//					var httpPort = this.httpPort();
				//					var flag = false; //是否有需要检查的字段
				//					for(var key in tableType.attribute) {
				//						if(tableType.attribute[key].proAdd.uniqueCheckNeed) {
				//							flag = true;
				//							this.checkUniqueData(tableType, key, deffered);
				//						}
				//					}
				//					if(!flag) {
				//						deffered.resolve();
				//					}
				//				},
				//				checkUniqueData: function(tableType, key, deffered) {
				//					var curl = this.httpPort() + tableType.jsonObj.url + '?' + key + '=' + tableType.attribute[key].proAdd.data;
				//					//console.log("curl", curl);
				//					this.httpCommit(curl).then(function success(res) {
				//						tableType.attribute[key].proAdd.uniqueCheckResult = true;
				//						if(res.data.collection.hasOwnProperty('items')) {
				//							if(res.data.collection.items.length == 1) {
				//								//如果有改返回值
				//								tableType.attribute[key].proAdd.uniqueCheckResult = false;
				//							}
				//						}
				//						//console.log("唯一性检查结束")
				//						deffered.resolve();
				//					}, function error(resu) {
				//						cj.Error(res);
				//					});
				//				},
				/**
				 * 转换查询条件格式和封装查询路由
				 */
				getSearchKVUrl: function(tableType) {
					var json = this.getHttpData('proSearch', tableType);
					var str = "";
					var isIndntify = true;
					for(var key in json) {
						if(tableType.jsonObj.tree == true && (key == "orgAlias" || key == "userOrgAlias")) {
							delete json[key];
						}
						if(isIndntify) {
							if((key.indexOf("Code") != -1) || (key.indexOf("Name") != -1) || (key.indexOf("Alias") != -1)) {
								str = str + "&identify";
								isIndntify = false;
							}
						}
						if(json[key]) {
							json[key] = encodeURIComponent(json[key])
							str = str + "&" + key + "=" + json[key];
						}
					}
					return str;
				},
				///////////////////////////////////////////////初始化表格//////////////////////////////////////////////////////////////////////
				formatGridOptionsColumnDefs: function(json, actionColShow, updateShow, removeBnt, orgBtnShow, authModfiy, rentBtnShow) {
					var arr = [];
					var i = 0;
					/** 是否必填，是否输出，是否有关联表，是否是boolean类型(选择框)*/
					for(var key in json) {
						if(!json[key].hide) {
							var obj = {};
							obj.field = key;
							obj.displayName = json[key].display;
							obj.cellClass = 'cellClass ' + json[key].class;
							if(json[key].required == true) {
								obj.required = true;
							}
							if(json[key].outPutN == true) {
								obj.outPutN = true;
							}
							if(json[key].auState) {
								obj.auState = json[key].auState;
							}
							if(json[key].hasOwnProperty("parent")) {
								obj.parent = json[key].parent;
								obj.boxType = "optionBox"
							}
							if(json[key].hasOwnProperty("parentModel")) {
								obj.parentModel = json[key].parentModel;
								obj.boxType = "parentModel"
							}
							if(json[key].type == "boolean") {
								obj.boxType = "checkBox"
							}
							if(json[key].dataType == "number") {
								obj.boxType = "number"
							}
							if(json[key].hasOwnProperty('width')) {
								obj.width = parseInt(json[key].width);
							}

							obj.enableColumnMenu = false;
							obj.enableCellEdit = false;
							obj.enableHeaderCellEdit = false;
							obj.headerCellClass = "tableHeader";
							arr.push(obj);
						}
					}
					var obj = {
						cellTemplate: 'uiGrid/rowCheckBox.html'
					};
					var obj2 = {};
					if(!authModfiy) {
						obj2 = {
							cellTemplate: 'uiGrid/operationDetail.html'
						}
					} else if(updateShow && orgBtnShow && removeBnt && rentBtnShow) {
						obj2 = {
							cellTemplate: 'uiGrid/operation.html'
						}
					} else if(!orgBtnShow || !removeBnt) {
						obj2 = {
							cellTemplate: 'uiGrid/operationDel.html'
						}
					} else if(!updateShow || !rentBtnShow) {
						obj2 = {
							cellTemplate: 'uiGrid/operationUpe.html'
						}
					}
					obj.displayName = "";
					obj.field = " ";
					obj.enableCellEdit = false; //取消编辑
					obj.enableColumnMenu = false; // 是否显示列头部菜单按钮
					obj.enableSorting = false; //取消排序
					obj.headerCellTemplate = 'uiGrid/titleCheckBox.html';
					obj.headerCellClass = "tableHeader";
					obj.width = '3%';

					obj2.displayName = "操作";
					obj2.field = "操作";
					obj2.enableCellEdit = false; //取消编辑
					obj2.enableColumnMenu = false; // 是否显示列头部菜单按钮
					obj2.enableSorting = false; //取消排序
					obj2.headerCellTemplate = 'uiGrid/titleOperation.html'
					obj2.headerCellClass = "tableHeader";
					obj2.width = '70';

					if(actionColShow) {
						arr.unshift(obj2);
					}
					arr.unshift(obj);
					return arr;
				},
				clearSearch: function(tableType) {
					for(var key in tableType.attribute) {
						if(tableType.attribute[key].proSearch) {
							tableType.attribute[key].proSearch.data = ''
						}
					}
				},
				/**
				 * 获取时间并转换格式
				 */
				changeTimeFormat: function(time) {
					while(time.indexOf('/') != -1) {
						time = time.replace('/', '-')
					}
					return time
				},
				/**
				 * 装置表资产原值和资产净值数据格式转换
				 */
				changeData: function(dataNow) {
					var data = 0.00000001;
					data = data + dataNow;
					data = data * 10000;
					return Math.floor(data);
				},
				/**
				 * 数据库数据转页面显示数据
				 */
				changeBooleanToCh: function(tableType, data) {
					for(var key in tableType.attribute) {
						if(tableType.attribute[key].type == 'boolean' ||
							(tableType.attribute[key].display == '是否自定义公式' && tableType.attribute[key].type == 'string')) {
							for(var i = 0; i < data.length; i++) {
								data[i][key] = data[i][key] ? '是' : '否';
							}
						}
						if(tableType.attribute[key].display == '性别') {
							for(var i = 0; i < data.length; i++) {
								if(data[i][key] == 1) {
									data[i][key] = '男'
								}
								if(data[i][key] == 2) {
									data[i][key] = '女'
								}
							}
						}
						if(tableType.attribute[key].valueShowSt) {
							for(var i = 0; i < data.length; i++) {
								data[i][key] = data[i][key] ? '启用' : '禁用';
							}
						}
						if(tableType.attribute[key].valueShowCh) {
							for(var i = 0; i < data.length; i++) {
								data[i][key] = data[i][key] ? '出' : '进';
							}
						}
						if(tableType.attribute[key].valueShowChLoad) {
							for(var i = 0; i < data.length; i++) {
								if(data[i][key] == "1" || data[i][key] == 1) {
									data[i][key] = '出';
								} else if(data[i][key] == "0" || data[i][key] == 0) {
									data[i][key] = '进';
								} else if(data[i][key] == "2" || data[i][key] == 2) {
									data[i][key] = '进出';
								}
							}
						}
						if(tableType.attribute[key].valueShowLine) {
							for(var i = 0; i < data.length; i++) {
								if(data[i][key] == "1" || data[i][key] == 1) {
									data[i][key] = '出';
								} else if(data[i][key] == "0" || data[i][key] == 0) {
									data[i][key] = '进';
								} else if(data[i][key] == "2" || data[i][key] == 2) {
									data[i][key] = '消耗';
								}
							}
						}
						if(tableType.attribute[key].tenThousand) {
							for(var i = 0; i < data.length; i++) {
								if(data[i][key] != '') {
									data[i][key] = data[i][key] / 10000;
								}
							}
						}
						//						if(tableType.attribute[key].valueShowOrg) {
						//							for(var i = 0; i < data.length; i++) {
						//								data[i][key] = data[i][key] ? '扩展组织机构' : '标准组织机构';
						//
						//							}
						//						}
						if(tableType.attribute[key].valueShowTank) {
							for(var i = 0; i < data.length; i++) {
								if(data[i][key] == 0) {
									data[i][key] = '全部'
								}
								if(data[i][key] == 1) {
									data[i][key] = '卧罐'
								}
								if(data[i][key] == 2) {
									data[i][key] = '球罐'
								}
								if(data[i][key] == 3) {
									data[i][key] = '立罐'
								}
							}
						}
					}
				},
				//				arryHasKey: function(arr, key) {
				//					for(var i = 0; i < arr.length; i++) {
				//						if(arr[i] == key) {
				//							return true;
				//						}
				//					}
				//					return false;
				//				},
				//				getTableIndexTypeByKey: function(tableIndex, key2) {
				//
				//					for(var key1 in tableIndex) {
				//						if(tableIndex[key1].rows.hasOwnProperty(key2)) {
				//
				//							return key1;
				//						}
				//					}
				//				},
				getRecordCount: function(res) {
					return res.data.collection.page.data[0].value;
				}
			};
		}
	});
$(document).ready(function() {
	$("#insertWindow").draggable();
});