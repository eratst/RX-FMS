app.controller('firstMenuController', function($scope, $http, factoryModel, orgTree) {

	//将这部分内容
	$scope.url = "component_menu_first.html";
	$scope.firstMenuTitle = factoryModel.menuTitle;
	//url 之后需要根据接口修改

	var rentCode = localStorage.getItem('rentCode');
	if(rentCode = '') {
		var topUrl = localStorage.getItem('serverUrl') + localStorage.getItem('bizOrgMainsUrl')
			+ localStorage.getItem('bizOrgCode')
			+ localStorage.getItem('bizOrgUrl')
			+ localStorage.getItem('getRootUrl');
		orgTree.treeLevel = 0;
		$scope.orgTree = orgTree;
		loadOrgTree(orgTree, topUrl, $http); //读取组织机构树
	} else {
		var bizUrl = localStorage.getItem('serverUrl') + '/bizOrgMains?isStandard=1';
		//获取标准业务域编码
		$http({
			method: "GET",
			url: bizUrl,
			responseType: "json",
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data); //格式化后台数据
			console.log("业务域",resultArr);
			if(resultArr.length > 0) { //如果resultArr.length === 0, 则没有下一级。
				localStorage.setItem('bizOrgCode','/' + resultArr[0]["bizCode"]) 
				console.log("bizOrgCode",localStorage.getItem('bizOrgCode'));
				
				var topUrl = localStorage.getItem('serverUrl') + localStorage.getItem('bizOrgMainsUrl')
					+ localStorage.getItem('bizOrgCode')
					+ localStorage.getItem('bizOrgUrl')
					+ localStorage.getItem('getRootUrl');
				orgTree.treeLevel = 0;
				$scope.orgTree = orgTree;
				loadOrgTree(orgTree, topUrl, $http); //读取组织机构树
			}
		}, function errorCallback(response) {
			//TODO: 可能会刷出多个网络异常警告，重新写一下这个错误处理
			//parent.layer.msg("网络异常，请刷新页面！错误码：" + textStatus);
		});
	}

	console.log(orgTree);

	$scope.AllMtrl = []; //物料信息存储变量
	//	loadMtrlMess($http, $scope); //读取所有的物料信息
	$scope.allMtrlType = []; //物料类型信息存储
	loadMtrlMessType($http, $scope);

	$scope.allOrgType = [];
	loadOrgMess($http, $scope); //读取所有的组织机构类型

	$scope.testHttpDataFlag = true; //初始必填项非空校验字段

	$scope.temp;
	$scope.orgTypeId_temp; //初始化组织机构新增所选的组织机构类型ID
	/**
	 * 展开/收起：各级组织机构
	 * @param org
	 */
	$scope.expandOrg = function(org) {
		org.ifExpand = !org.ifExpand;
	};

	/**
	 * 展开/收起：车间下级所有区域，第一次call时从服务器获取数据
	 * @param orgNode 车间级node
	 */
	$scope.expandArea = function(orgNode) {
		console.log('执行了000000000')
		if(false) {
			
		} else {
			orgNode.ifExpand = !orgNode.ifExpand;
			if(orgNode.loaded) {
//				orgNode.ifExpand = !orgNode.ifExpand;
				return;
			}
			orgNode.areas = [];
			orgNode.loaded = true;
			orgNode.ifExpand = true;
			loadAreas(orgNode, $http);
			console.log(orgNode);

		}
	};

	/**
	 * 展开/收起：区域下级所有设备，第一次call时从服务器获取数据
	 * @param areaNode
	 * @param orgCode
	 */
	$scope.expandDevice = function(areaNode, orgCode) {
		if(areaNode.loaded) {
			areaNode.ifExpand = !areaNode.ifExpand;
			console.log(areaNode);
			return;
		}
		areaNode.ifExpand = !areaNode.ifExpand;
		areaNode.loaded = true;
		var deviceUrlForCell = localStorage.getItem('serverUrl') + '/prdtcells?$plantCodes=';
		var deviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrlForNode') + '/' + orgCode;
		var url_name = [];
		switch(areaNode["areaTypeCode"]) {
			case 'plants': // 装置  |  plants
				deviceUrl += '/plants/' + areaNode["areaCode"];
				deviceUrlForCell += areaNode["areaCode"];
				url_name = [{
						'_url': '/silos',
						'_name': '料仓',
						'id': 3
					},
					{
						'_url': '/samplePoints',
						'_name': '采样点',
						'id': 6
					},
					{
						'_url': '/outlets',
						'_name': '排放口',
						'id': 7
					},
					{
						'_url': '/valves',
						'_name': '阀门',
						'id': 10
					},
					{
						'_url': '/equipments',
						'_name': '设备',
						'id': 8
					},
					{
						'_url': '/plates',
						'_name': '盲板',
						'id': 11
					},
					{
						'_url': '/tees',
						'_name': '三通',
						'id': 12
					},
					{
						'_url': '/sideLines',
						'_name': '侧线',
						'id': 1
					},
					{

						'_name': '生产单元',
						'flag': 'cell',
						'id': 13
					}

				];
				break;
			case 'tankAreas': // 罐区  | tankAreas
				deviceUrl += '/tankAreas/' + areaNode["areaCode"];
				url_name = [{
						'_url': '/samplePoints',
						'_name': '采样点',
						'id': 6
					},
					{
						'_url': '/outlets',
						'_name': '排放口',
						'id': 7
					},
					{
						'_url': '/equipments',
						'_name': '设备',
						'id': 8
					},
					{
						'_url': '/tubulations',
						'_name': '管段',
						'id': 9
					},
					{
						'_url': '/valves',
						'_name': '阀门',
						'id': 10
					},
					{
						'_url': '/plates',
						'_name': '盲板',
						'id': 11
					},
					{
						'_url': '/tees',
						'_name': '三通',
						'id': 12
					},

					{
						'_url': '/tanks',
						'_name': '罐',
						'id': 2
					}
				];
				break;
			case 'warehouses': // 仓库  | warehouses
				deviceUrl += '/warehouses/' + areaNode["areaCode"];
				url_name = [{
						'_url': '/stocks',
						'_name': '库位',
						'id': 4
					},
					{
						'_url': '/samplePoints',
						'_name': '采样点',
						'id': 6
					},
					{
						'_url': '/outlets',
						'_name': '排放口',
						'id': 7
					},
					{
						'_url': '/equipments',
						'_name': '设备',
						'id': 3
					},
					{
						'_url': '/tubulations',
						'_name': '管段',
						'id': 9
					},
					{
						'_url': '/valves',
						'_name': '阀门',
						'id': 10
					},
					{
						'_url': '/plates',
						'_name': '盲板',
						'id': 11
					},
					{
						'_url': '/tees',
						'_name': '三通',
						'id': 12
					}
				];
				break;
			case 'loadingDocks': // 装卸台 | loadingDocks
				deviceUrl += '/loadingDocks/' + areaNode["areaCode"];
				url_name = [

					{
						'_url': '/equipments',
						'_name': '设备',
						'id': 8
					},
					{
						'_url': '/tubulations',
						'_name': '管段',
						'id': 9
					},
					{
						'_url': '/valves',
						'_name': '阀门',
						'id': 10
					},
					{
						'_url': '/plates',
						'_name': '盲板',
						'id': 11
					},
					{
						'_url': '/tees',
						'_name': '三通',
						'id': 12
					},
					{
						'_url': '/outlets',
						'_name': '排放口',
						'id': 7
					},
					{
						'_url': '/edgePoints',
						'_name': '进出厂点',
						'id': 5
					}
				];
				break;
			case 'pipeNetworks': // 管网  | pipeNetworks
				deviceUrl += '/pipeNetworks/' + areaNode["areaCode"];
				url_name = [{
						'_url': '/samplePoints',
						'_name': '采样点',
						'id': 6
					},
					{
						'_url': '/outlets',
						'_name': '排放口',
						'id': 7
					},
					//					{
					//						'_url': '/equipments',
					//						'_name': '设备'
					//					},
					{
						'_url': '/tubulations',
						'_name': '管段',
						'id': 9
					},
					//					{
					//						'_url': '/valves',
					//						'_name': '阀门'
					//					},
					{
						'_url': '/plates',
						'_name': '盲板',
						'id': 11
					},
					{
						'_url': '/tees',
						'_name': '三通',
						'id': 12
					}
				];
				break;
			case 'administrations': // 办公区 | administrations
				deviceUrl += '/administrations/' + areaNode["areaCode"];
				url_name = [{
						'_url': '/samplePoints',
						'_name': '采样点',
						'id': 6
					},
					{
						'_url': '/equipments',
						'_name': '设备',
						'id': 8
					},
					{
						'_url': '/tubulations',
						'_name': '管段',
						'id': 9
					},
					{
						'_url': '/valves',
						'_name': '阀门',
						'id': 10
					},
					{
						'_url': '/plates',
						'_name': '盲板',
						'id': 11
					},
					{
						'_url': '/tees',
						'_name': '三通',
						'id': 12
					}
				];
				break;
			case 'communities': // 生活区 | communities
				deviceUrl += '/communities/' + areaNode["areaCode"];
				url_name = [{
						'_url': '/samplePoints',
						'_name': '采样点',
						'id': 6
					},
					{
						'_url': '/equipments',
						'_name': '设备',
						'id': 8
					},
					{
						'_url': '/tubulations',
						'_name': '管段',
						'id': 9
					},
					{
						'_url': '/valves',
						'_name': '阀门',
						'id': 10
					},
					{
						'_url': '/plates',
						'_name': '盲板',
						'id': 11
					},
					{
						'_url': '/tees',
						'_name': '三通',
						'id': 12
					}
				];
				break;
		}
		$scope.areaNode = areaNode;
		loadDevice(areaNode, deviceUrl, $http, url_name, $scope, deviceUrlForCell);
		console.log('查询节点列表数据', $scope.areaNode)
	};

	/**
	 * 点击设备时（罐/管段等列表项）展示二级菜单（罐列表，管段列表）
	 * @param device {{String: 'deviceName', List: [deviceList], String:'areaTypeId', String:'areaCode'}}
	 */
	$scope.deviceOnClick = function(device) {
		console.log(device);
		$scope.$emit('show-second-menu', device);
	};

	$scope.areaOnClick = function(area) {
		$scope.$emit('show-node-panel', area);
	};

	$scope.orgOnClick = function(orgNode) {
		console.log(orgNode)
		$scope.$emit('show-org-panel', orgNode);
	};

	//区域操作

	$scope.deleteAreaBtn = function(area, p, index) {
		$scope.deleteArea = area;
		console.log($scope.deleteArea);
		$scope.p = p;
		$scope.index = index;
	};

	$scope.updataAreaBtn = function(area) {
		$scope.updataArea = area;
		console.log($scope.updataArea);
		var url = "../app/model/public.json";
		$scope.popupAdd = getAreaUpdateFieldByState(3, url, $scope.updataArea);
		//数据回写
	};

	$scope.addAreaBtn = function(ele) {
		$scope.addarea = ele;
		console.log(ele);
		var url = "../app/model/public.json";
		$scope.popupAdd = getAreaFieldByState(3, url);
		console.log($scope.popupAdd);
	}
	//-------------增加区域开始------------------------------------------------

	//获取input输入框数据
	$scope.areaAddOnClick = function(orgNode) {
		$scope.addArray = [];
		console.log($scope.popupAdd);
		switch($scope.popupAdd[3].value) {
			case "warehouse":
				$scope.addArray[0] = {
					'warehouseId': '1',
					'warehouseCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'warehouseTypeId': '1',
					'technicId': '1',
					'capacity': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',

					"areaCode": $scope.popupAdd[0].value,
					"type": "warehouse",
					"areaTypeId": "3",
					"noChild": ""

				};
				break;
			case "tankArea":
				$scope.addArray[0] = {
					'tankAreaId': '1',
					'tankAreaCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'tankAreaTypeId': '0',
					'technicId': '1',
					'capacity': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',

					"areaCode": $scope.popupAdd[0].value,
					"type": "tankArea",
					"areaTypeId": "2",
					"noChild": ""

				};
				break;
			case "pipeNetwork":
				$scope.addArray[0] = {
					'pipeNetworkId': '1',
					'pipeNetworkCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'technicId': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',

					"areaCode": $scope.popupAdd[0].value,
					"type": "pipeNetwork",
					"areaTypeId": "5",
					"noChild": ""

				};
				break;
			case "loadingDock":
				$scope.addArray[0] = {
					'loadingDockId': '1',
					'loadingDockCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'technicId': '1',
					'capacity': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',

					"areaCode": $scope.popupAdd[0].value,
					"type": "loadingDock",
					"areaTypeId": "4",
					"noChild": ""

				};
				break;
			case "plant":
				$scope.addArray[0] = {
					'plantId': '1',
					'plantCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'unitTypeId': '1',
					'technicId': '1',
					'capacity': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',

					"areaCode": $scope.popupAdd[0].value,
					"type": "plant",
					"areaTypeId": "1",
					"noChild": ""

				};
				break;
			case "communitie":
				$scope.addArray[0] = {
					'communityCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'creatorId': '1',
					'creator': 'zysh',
					'editorId': '0',
					'editor': 'zysh',
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',

					"areaCode": $scope.popupAdd[0].value,
					"type": "communitie",
					"areaTypeId": "7",
					"noChild": ""

				};
				break;
			case "administration":
				$scope.addArray[0] = {
					'administrationCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'creatorId': '1',
					'creator': 'zysh',
					'editorId': '0',
					'editor': 'zysh',
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',

					"areaCode": $scope.popupAdd[0].value,
					"type": "administration",
					"areaTypeId": "6",
					"noChild": ""
				};
				break;
		}
		console.log($scope.addArray);
		//转成增加需要的json格式
		$scope.data = cj.parseCjArray($scope.addArray);
		console.log($scope.data);
		//增加url
		var areaAddUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
			orgNode["orgCode"] + '/' + $scope.popupAdd[3].value + 's';
		console.log(areaAddUrl);
		//增加请求

		testInputVoid();
		console.log($scope.testHttpDataFlag);
		if($scope.testHttpDataFlag == false) {
			alert("请输入完整数据！");
			$scope.testHttpDataFlag = true;
		} else {
			$http({
				method: "POST",
				url: areaAddUrl,
				responseType: "json",
				data: $scope.data
			}).then(function success(res) {
				console.log(res);
				angular.element("#areaAddModal").modal("hide");
				if(res.data.collection.error == undefined) {
					layer.msg('添加成功', {
						icon: 1,
						time: 1500
					});

					console.log("上级组织数据", $scope.addarea.areas);
					console.log($scope.data);
					var data = JSON.parse($scope.data).collection.templates[0].data;
					var obj = {};
					for(var i = 0; i < data.length; i++) {
						if(data[i].name == "noChild") {
							obj[data[i].name] = false;
						} else {
							obj[data[i].name] = data[i].value;
						}

					}
					console.log(obj);
					console.log("上级组织总数据", $scope.addarea);
					console.log("上级组织数据", $scope.addarea.areas);
					//					$scope.addarea.noChild=false;
					$scope.addarea.areas.unshift(obj);

				} else {
					var data1 = res.data.collection.error.message;
					layer.msg(data1, {
						icon: 0,
						time: 3000
					});
				}
			}, function error() {
				console.log("添加失败");
				layer.msg('添加失败', {
					icon: 2,
					time: 1500
				});
			});
		}
	}
	//-------------增加区域结束------------------------------------------------

	//-------------删除区域开始------------------------------------------------

	$scope.areaDeleteOnClick = function(deleteArea) {
		//拼删除区域的url
		if(deleteArea["type"] == 'community') {
			var deleteAreaUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
				deleteArea["factoryCode"] + '/' + 'communities/' + deleteArea["areaCode"];
		} else {
			var deleteAreaUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
				deleteArea["factoryCode"] + '/' + deleteArea["type"] + 's/' + deleteArea["areaCode"];
			console.log(deleteAreaUrl);
		}
		//删除请求
		$http({
			method: "DELETE",
			url: deleteAreaUrl
		}).then(function success(res) {
			console.log(res);
			if(res.data == '') {
				layer.msg('已删除', {
					icon: 0,
					time: 1500
				});
				$scope.p.splice($scope.index, 1);
			} else {
				var data1 = res.data.collection.error.message;
				layer.msg(data1, {
					icon: 0,
					time: 3000
				});
			}
		}, function error() {
			console.log("删除失败");
			layer.msg('删除失败', {
				icon: 0,
				time: 1500
			});
		});
	}
	//-------------删除区域结束------------------------------------------------

	//-------------更新区域开始------------------------------------------------
	//获取input输入数据
	$scope.areaUpdataOnClick = function(updataArea) {
		console.log(updataArea);
		$scope.updataArr = [];
		switch(updataArea.type) {
			case "warehouse":
				$scope.updataArr[0] = {
					'warehouseId': '1',
					'warehouseCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'warehouseTypeId': '1',
					'technicId': '1',
					'capacity': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',
				};
				break;
			case "loadingDock":
				$scope.updataArr[0] = {
					'loadingDockId': '1',
					'loadingDockCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'technicId': '1',
					'capacity': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',
				};
				break;
			case "tankArea":
				$scope.updataArr[0] = {
					'tankAreaId': '1',
					'tankAreaCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'tankAreaTypeId': '0',
					'technicId': '1',
					'capacity': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',
				};
				break;
			case "pipeNetwork":
				$scope.updataArr[0] = {
					'pipeNetworkId': '1',
					'pipeNetworkCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'technicId': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',
				};
				break;
			case "plant":
				$scope.updataArr[0] = {
					'plantId': '1',
					'plantCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'unitTypeId': '1',
					'technicId': '1',
					'capacity': '1',
					'creatorId': '1',
					'creator': 'zysh',
					'createTime': "",
					'editorId': '0',
					'editor': 'zysh',
					'maintainTime': "",
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',
				};
				break;
			case "communitie":
				$scope.updataArr[0] = {
					'communityCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'creatorId': '1',
					'creator': 'zysh',
					'editorId': '0',
					'editor': 'zysh',
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',
				};
				break;
			case "administration":
				$scope.updataArr[0] = {
					'administrationCode': $scope.popupAdd[0].value,
					'name': $scope.popupAdd[1].value,
					'shortName': $scope.popupAdd[2].value,
					'creatorId': '1',
					'creator': 'zysh',
					'editorId': '0',
					'editor': 'zysh',
					'sortNum': '1',
					'des': '0',
					'version': '1',
					'enabled': '0',
				};
				break;
			default:
				break;
		}

		console.log($scope.updataArr);
		$scope.data = cj.parseCjArray($scope.updataArr);
		console.log($scope.data);
		//拼更新区域的url
		if(updataArea["type"] == 'community') {
			var updataAreaUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
				updataArea["factoryCode"] + '/' + 'communities/' + updataArea["areaCode"];
		} else {
			var updataAreaUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
				updataArea["factoryCode"] + '/' + updataArea["type"] + 's/' + updataArea["areaCode"];
		}
		//请求更新
		testInputVoid();
		console.log($scope.testHttpDataFlag);
		if($scope.testHttpDataFlag == false) {
			alert("请输入完整数据！");
			$scope.testHttpDataFlag = true;
		} else {
			$http({
				method: "PUT",
				url: updataAreaUrl,
				//	    responseType: "json",
				data: $scope.data,
				transformResponse: function(response) {
					if(response.charAt(0) === '/') {
						return {
							'url': response
						};
					} else {
						return JSON.parse(response);
					}
				}
			}).then(function success(res) {
				console.log(res);
				angular.element("#areaModifyModal").modal("hide");
				if(res.data.url != null) {
					layer.msg('修改成功', {
						icon: 1,
						time: 1500
					});
					console.log()
					for(var i = 0; i < $scope.popupAdd.length; i++) {
						var key = $scope.popupAdd[i].attributeName;
						var value = $scope.popupAdd[i].value;
						$scope.updataArea[key] = value;
					}

				} else {
					var data1 = res.data.collection.error.message;
					layer.msg(data1, {
						icon: 0,
						time: 3000
					});
					//					layer.msg('修改失败', {
					//						icon: 2,
					//						time: 1500
					//					});
				}
			}, function error() {
				console.log("修改失败");
				layer.msg('修改失败', {
					icon: 2,
					time: 1500
				});
			});
		}
	}
	//-------------更新区域结束------------------------------------------------

	//--------------新增组织机构数据开始----------
	var orgUrl_temp;
	$scope.orgAddfunc = function(temp) {
		$scope.orgAddData = temp;
		console.log($scope.orgAddData);
		switch($scope.orgAddData.treeLevel) {
			case 1:
				orgUrl_temp = 'enterprises';
				break;
			case 2:
				orgUrl_temp = 'produceFactories';
				break;
			case 3:
				orgUrl_temp = 'workshops';
				break;
			case 4:
				orgUrl_temp = '';
				break;
			default:
				break;
		}
		console.log("fenjiexian");

		$scope.popupAdd = getFieldByState(3);
		console.log($scope.popupAdd);
		$scope.orgTy = orgUrl_temp;
	}
	//获取input输入框数据
	$scope.OrgAddOnClick = function(orgNode) {
		console.log($scope.orgTypeId_temp);
		var obja = {
			'orgTypeId': $scope.orgTypeId_temp,
			'dataStatus': '1',
			'fctrBlock': '1',
			'businessType': '1',
			'crtUserId': '1',
			'crtUserName': 'lyt',
			'crtDate': '2017-10-21 10:38:41',
			'mntUserId': '1',
			'mntUserName': 'lyt',
			'mntDate': '2017-10-22 10:38:41',
			'sortNum': '1',
			'des': '1',
			'version': '1',
			//			'orgType':'enterprise',
			'entrType': '1',

			'parentOrgCode': orgNode.orgCode,
			'dtlId': '100001',
			'expendFlag': '1',
			'bizId': '170702201'
		}
		//增加url
		var secondOrgAddUrl = localStorage.getItem('serverUrl') + '/' + $scope.orgTy;
		var secondOrgAddUrlbiz = localStorage.getItem('serverUrl') + '/bizOrgMains/' + 'standard' + '/bizOrgDTLs';
		console.log(secondOrgAddUrl);
		//增加请求

		testInputVoid();
		console.log($scope.testHttpDataFlag);
		if($scope.testHttpDataFlag == false) {
			alert("请输入完整数据！");
			$scope.testHttpDataFlag = true;
		} else {
			setHttpJson(obja, $scope.popupAdd);
			console.log("child833", $scope.orgAddData.children);
			httpRequest(secondOrgAddUrl, secondOrgAddUrlbiz, $scope.Orgdata)
		}
	}

	//--------------新增数据结束---------------

	//--------------修改组织机构数据开始---------------
	$scope.orgUpdata = function(temp) {
		$scope.orgUpdataData = temp;
		console.log($scope.orgUpdataData);
		switch($scope.orgUpdataData.treeLevel) {
			case 1:
				orgUrl_temp = 'headquarters';
				break;
			case 2:
				orgUrl_temp = 'enterprises';
				break;
			case 3:
				orgUrl_temp = 'produceFactories';
				break;
			case 4:
				orgUrl_temp = 'workshops';
				break;
			default:
				break;
		}
		$scope.orgTy = orgUrl_temp;
		$scope.orgType_temp = $scope.orgUpdataData.orgType;
		$scope.popupAdd = getFieldByState(4, $scope.orgUpdataData);
		console.log($scope.popupAdd);
	}

	//获取input输入框数据
	$scope.OrgModifyOnClick = function(orgNode) {
		obj = {
			'orgType': $scope.orgType_temp,
			'orgTypeId': $scope.orgUpdataData.orgTypeId,
			'entrType': '1',
			'dataStatus': '1',
			'fctrBlock': '1',
			'businessType': '1',
			'crtUserId': '1101',
			'crtUserName': 'admin',
			'crtDate': '2018-3-5 10:38:41',
			'mntUserId': '1101',
			'mntUserName': 'lyt',
			'mntDate': '2018-3-6 10:38:41',
			'sortNum': '1',
			'des': '描述',
			'version': '1',
		};
		var secondOrgModifyUrl = localStorage.getItem('serverUrl') + '/' + $scope.orgTy + '/' + orgNode.orgCode;
		console.log(secondOrgModifyUrl);
		testInputVoid();
		if($scope.testHttpDataFlag == false) {
			alert("请输入完整数据！");
			$scope.testHttpDataFlag = true;
		} else {
			setHttpJson(obj, $scope.popupAdd);
			console.log(secondOrgModifyUrl);
			$http({
				method: "PUT",
				url: secondOrgModifyUrl,
				responseType: "json",
				data: $scope.Orgdata
			}).then(function success(res) {
				angular.element("#orgModifyModal").modal("hide");
				if(res.data == null) {
					layer.msg('修改成功', {
						icon: 1,
						time: 1500
					});
					//                  console.log($scope.orgUpdataData);
					console.log($scope.popupAdd);
					for(var i = 0; i < $scope.popupAdd.length; i++) {
						var key = $scope.popupAdd[i].attributeName;
						var value = $scope.popupAdd[i].value;
						$scope.orgUpdataData[key] = value;
					}

				} else {
					var data1 = res.data.collection.error.message;
					layer.msg(data1, {
						icon: 0,
						time: 3000
					});
					//					layer.msg('修改失败', {
					//						icon: 2,
					//						time: 1500
					//					});
				}
				console.log(res);
			}, function error() {
				console.log("添加失败");
				layer.msg('修改失败', {
					icon: 2,
					time: 1500
				});
			})
		}
		//		loadAreas(orgNode, $http);
	}
	//--------------修改数据结束---------------

	//组织机构数据删除开始----------------------------------------------
	$scope.OrgDelete = function(orgNode, p, index) {
		$scope.OrgDeleteData = orgNode;
		$scope.p = p;
		$scope.index = index;
		console.log($scope.OrgDeleteData);
	}
	$scope.OrgDeleteConf = function(deleteOrg, p, index) {
		//拼删除的url
		var DeleteOrgUrl = localStorage.getItem('serverUrl') + '/enterprises/' + deleteOrg.orgCode;
		var DeleteOrgUrlbiz = localStorage.getItem('serverUrl') + '/bizOrgMains/' + 'standard' + '/bizOrgDTLs/' + deleteOrg.orgCode;
		console.log(DeleteOrgUrlbiz);
		//删除请求
		$http({
			method: "DELETE",
			url: DeleteOrgUrlbiz
		}).then(function success(res) {
			console.log(res);
			if(res.data == '') {
				layer.msg('已删除', {
					icon: 0,
					time: 1500
				});
				$scope.p.splice($scope.index, 1);
			} else {
				var data1 = res.data.collection.error.message;
				layer.msg(data1, {
					icon: 0,
					time: 3000
				});
			}
		}, function error() {
			console.log("删除失败");
			layer.msg('删除失败', {
				icon: 0,
				time: 1500
			});
		});
	}

	function setHttpJson(obj, popup) {
		for(var i = 0; i < popup.length; i++) {
			if(popup[i].hasOwnProperty("value")) {
				if(popup[i].value != undefined) {
					obj[popup[i].attributeName] = popup[i].value;
				}
			}
		}
		$scope.Orgdata = cj.parseCj(obj);
	};

	function httpRequest(urlGet, urlGetbiz, urlData) {
		$http({
			method: "POST",
			url: urlGet,
			responseType: "json",
			data: urlData
		}).then(function success(res) {
			console.log(res);

			$http({
				method: "POST",
				url: urlGetbiz,
				responseType: "json",
				data: urlData
			}).then(function success(res) {
				console.log(res);

				angular.element("#orgAddModal").modal("hide");
				if(res.data.collection.error == undefined) {
					layer.msg('添加成功', {
						icon: 1,
						time: 1500
					});
					console.log(JSON.parse(urlData));

					console.log(JSON.parse(urlData).collection.templates[0].data);
					var data = JSON.parse(urlData).collection.templates[0].data;
					var obj = {};
					for(var i = 0; i < data.length; i++) {
						obj[data[i].name] = data[i].value
					}
					console.log("children", $scope.orgAddData.children);
					console.log(obj);
					if($scope.orgAddData.children != undefined) {
						$scope.orgAddData.children.unshift(obj);
					} else {
						$scope.orgAddData.children = [];
						$scope.orgAddData.children.unshift(obj);
					}

				} else {

					var data1 = res.data.collection.error.message;
					layer.msg(data1, {
						icon: 0,
						time: 3000
					});
					//					layer.msg('添加失败', {
					//						icon: 2,
					//						time: 1500
					//					});
				}
			}, function error() {
				console.log("添加失败");
				layer.msg('添加失败', {
					icon: 2,
					time: 1500
				});
			})
		}, function error() {
			console.log("添加失败");
			layer.msg('添加失败', {
				icon: 2,
				time: 1500
			});
		})
	}

	function getFieldByState(state, data_temp) {
		var arr = [];
		$http({
			method: "GET",
			url: "../app/model/org.json",
		}).then(function success(response) {
			for(var key in response.data.attribute) {
				if(hasState(state, response.data.attribute[key].flag)) {
					response.data.attribute[key].attributeName = key;
					arr.push(response.data.attribute[key]);
				}
			}
			console.log("arr", arr);
			console.log(data_temp);
			if(data_temp != undefined) {
				for(var i = 0; i < arr.length; i++) {
					arr[i].value = data_temp[arr[i].attributeName];
				}
			}
		}, function error(response) {
			console.log(response);
		});
		return arr;
	}
	//获取区域公共数据json
	function getAreaFieldByState(state, url, data_temp) {
		var arr = [];
		$http({
			method: "GET",
			url: url,
		}).then(function success(response) {
			for(var key in response.data.attribute) {
				if(hasState(state, response.data.attribute[key].flag)) {
					response.data.attribute[key].attributeName = key;
					arr.push(response.data.attribute[key]);
				}
			}
			console.log("arr", arr);
			console.log(data_temp);
			if(data_temp != undefined) {
				for(var i = 0; i < arr.length; i++) {
					arr[i].value = data_temp[arr[i].attributeName];
				}
			}
		}, function error(response) {
			console.log(response);
		});
		return arr;
	}

	function getAreaUpdateFieldByState(state, url, data_temp) {
		var arr = [];
		$http({
			method: "GET",
			url: url,
		}).then(function success(response) {
			for(var key in response.data.attribute) {
				if(hasState(state, response.data.attribute[key].flag)) {
					response.data.attribute[key].attributeName = key;
					arr.push(response.data.attribute[key]);
				}
			}
			console.log("arr", arr);
			console.log(data_temp);
			if(data_temp != undefined) {
				arr[0].value = data_temp.areaCode;
				arr[1].value = data_temp.name;
				arr[2].value = data_temp.shortName;
				arr[3].value = data_temp.type;
			}
		}, function error(response) {
			console.log(response);
		});
		return arr;
	}

	function hasState(state, flag) {
		for(var i = 0; i < flag.length; i++) {
			if(state == flag[i]) {
				return true;
			}
		}
		return false;
	}

	function getPopupValue(addData, popup) {
		for(var i = 0; i < addData.length; i++) {
			addData[i].value = popup[i].value;
		}
		return addData;
	}

	function testInputVoid() {
		console.log($scope.popupAdd);
		for(var key in $scope.popupAdd) {
			if($scope.popupAdd[key].required == true) {
				if($scope.popupAdd[key].value === null || $scope.popupAdd[key].value === '') {
					$scope.testHttpDataFlag = false;
					return false;
				}
			}
		}
	}

	$scope.getOrgId = function(data) {
		$scope.orgTypeId_temp = data;
	}

	//下面方法只是暂时使用
	$scope.getAreaType = function() {
		$scope.areaType1 = ["warehouse", "loadingDock", "tankArea", "pipeNetwork", "plant", "communitie", "administration"]
	}

});

/**
 * 获取组织机构，递归生成树
 * @param _orgTree Empty object for root node
 * @param _url     The node's Children's url
 * @param $http    AngularJS for calling XHR, to replace jQuery
 */
function loadOrgTree(_orgTree, _url, $http) {
	console.log('tree_url', _url)
	$http({
		method: "GET",
		url: _url,
		responseType: "json",
	}).then(function successCallback(response) {
		var resultArr = $.ET.toObjectArr(response.data); //格式化后台数据
		//		console.log("递归数据",resultArr);
		if(resultArr.length > 0) { //如果resultArr.length === 0, 则没有下一级。
			_orgTree.children = [];
			for(var i = 0; i < resultArr.length; i++) {
//				var childUrl = localStorage.getItem('serverUrl') + localStorage.getItem('bizOrgUrl') + '?orgCode=' +
//					resultArr[i]["orgCode"] + localStorage.getItem('bizOrgChildUrl');
					
				var childUrl = localStorage.getItem('serverUrl') + localStorage.getItem('bizOrgMainsUrl')
					+ localStorage.getItem('bizOrgCode')
					+ localStorage.getItem('bizOrgUrl') + '?orgCode=' +
					resultArr[i]["orgCode"] + localStorage.getItem('bizOrgChildUrl');
					
					
				if(resultArr[i]["orgCode"] == 2) {
					console.log("url", childUrl)
				}
				resultArr[i].treeLevel = _orgTree.treeLevel + 1;
				resultArr[i].ifExpand = false;
				loadOrgTree(resultArr[i], childUrl, $http);
				_orgTree.children.push(resultArr[i]);
			}
		}
	}, function errorCallback(response) {
		//TODO: 可能会刷出多个网络异常警告，重新写一下这个错误处理
		//parent.layer.msg("网络异常，请刷新页面！错误码：" + textStatus);
	});
}

/**
 * 获得车间下的区域
 * @param _orgNode 最底级组织机构节点（车间）
 * @param $http    AngularJS for calling XHR, to replace jQuery
 */
function loadAreas(_orgNode, $http) {
	var areaUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') +
		_orgNode["orgCode"];
	console.log(areaUrl);
	$http({
		method: "GET",
		url: areaUrl,
		responseType: "json"
	}).then(function successCallback(response) {
		var resultArr = $.ET.toObjectArr(response.data);
		if(resultArr.length === 0) {
			_orgNode.noChild = true;
			return;
		} else {
			_orgNode.noChild = false;
		}
		for(var i = 0; i < resultArr.length; i++) {
			resultArr[i].ifExpand = false;
			_orgNode.areas.push(resultArr[i]);
		}
		console.log(_orgNode);
	}, function errorCallback(response) {
		console.log(response);
	});
}

/**
 * 递归获取区域下的设备or属性，包括罐区下的罐，仓库下的库位等。
 * @param _areaNode  该区域节点
 * @param _commonUrl 设备的url的前部
 * @param $http      XHR
 * @param _url_name  {[{String : '设备url尾部', String : '设备名称'}]}
 */
function loadDevice(_areaNode, _commonUrl, $http, _url_name, $scope, _urlForCell) {
	//	_areaNode=$scope.areaNode;
	var _urlCell;
	if(_url_name.length <= 0) { //递归到最后
		if(_areaNode.devices) { //有设备数据
			_areaNode.noChild = false;
			_areaNode.ifExpand = true;
		} else { //没有设备数据
			_areaNode.noChild = true;
		}

		$scope.areaOnClick(_areaNode);
		return;
	}
	//多次循环删除数组元素，返回删除的元素用于拼url
	var thisUrlName = _url_name.pop();
	console.log(_url_name);
	console.log(_commonUrl + thisUrlName['_url']);
	if(thisUrlName['flag'] == 'cell') {
		_urlCell = _urlForCell
	} else {
		_urlCell = _commonUrl + thisUrlName['_url']
	}
	$http({
		method: "GET",
		url: _urlCell,
		responseType: "json"
	}).then(function successCallback(response) {
		var resultArr = $.ET.toObjectArr(response.data);
		//自行创建一个节点类型Id，用于后续页面判断与操作
		for(var i = 0, len = resultArr.length; i < len; i++) {
			resultArr[i]['nodeTypeId'] = thisUrlName['id']
		}
		console.log("wodeUrl", _commonUrl + thisUrlName['_url'])
		if(!_areaNode.devices) _areaNode.devices = [];
		var tempObj = {
			'deviceName': thisUrlName['_name'],
			//			'areaTypeId': _areaNode['areaTypeId'],
			'areaTypeCode': _areaNode['areaTypeCode'],
			'orgCode': _areaNode['orgCode'],
			'areaType': _areaNode['areaTypeName'],
			//			'areaId': _areaNode['areaDictionaryId'],
			'deviceList': resultArr
		};
		_areaNode.devices.push(tempObj);
		//		    $scope.areaOnClick(_areaNode);
		loadDevice(_areaNode, _commonUrl, $http, _url_name, $scope);
	}, function errorCallback(response) {});

};

//获取所有物料数据消息,有一部分用于物料新增时的判断是哪种类型
function loadMtrlMess($http, $scope) {
	var mtrlUrl = localStorage.getItem('serverUrl') + '/materials';
	$http({
		method: "GET",
		url: mtrlUrl,
		responseType: "json"
	}).then(function successCallback(response) {
		var resultArr = $.ET.toObjectArr(response.data);
		//获取到的物料信息
		console.log(resultArr);
		for(var i = 0; i < resultArr.length; i++) {
			$scope.AllMtrl.push({
				mtrlId: resultArr[i].mtrlId,
				mtrlName: resultArr[i].mtrlName,
				mtrlTypeName: resultArr[i].mtrlTypeName,
				mtrlTypeId: resultArr[i].mtrlTypeId
			});
		};
		console.log($scope.AllMtrl);
		$scope.$emit('mtrl-all-get', $scope.AllMtrl);
	}, function errorCallback(response) {});

}
//获取所有的组织机构类型信息用于组织机构新增
function loadOrgMess($http, $scope) {
	var orgUrl = localStorage.getItem('serverUrl') + '/orgTypes';
	$http({
		method: "GET",
		url: orgUrl,
		responseType: "json"
	}).then(function successCallback(response) {
		var resultArr = $.ET.toObjectArr(response.data);

		//获取到的组织机构信息
		for(var i = 0; i < resultArr.length; i++) {
			$scope.allOrgType.push({
				orgTypeId: resultArr[i].orgTypeId,
				orgTypeName: resultArr[i].orgTypeName,
				des: resultArr[i].des
				//					mtrlTypeId:resultArr[i].mtrlTypeId
			});
		};
		console.log($scope.allOrgType);
		$scope.$emit('org-all-get', $scope.allOrgType);
	}, function errorCallback(response) {});

}

function loadMtrlMessType($http, $scope) {
	var orgUrl = localStorage.getItem('serverUrl') + '/mtrlTypes';
	$http({
		method: "GET",
		url: orgUrl,
		responseType: "json"
	}).then(function successCallback(response) {
		var resultArr = $.ET.toObjectArr(response.data);

		//获取到的组织机构信息
		for(var i = 0; i < resultArr.length; i++) {
			$scope.allMtrlType.push({
				mtrlTypeId: resultArr[i].mtrlTypeId,
				mtrlTypeName: resultArr[i].mtrlTypeName,
				mtrlTypeCode: resultArr[i].mtrlTypeCode,
				des: resultArr[i].des
			});
		};
		console.log($scope.allMtrlType);
		$scope.$emit('mtrlType-all-get', $scope.allMtrlType);
	}, function errorCallback(response) {});
}