app.controller('viewController', function($scope, layer, $http, $location) {

	/**
	 * 0 - 都不显示
	 * 1 - 显示二级菜单
	 * 2 - 显示二级菜单以及设备详细信息
	 * 3 - 显示节点详细信息
	 * 4 - 显示组织机构详细信息
	 * @type {number} 控制view显示二级菜单，还是组织机构信息，还是节点信息
	 */
	console.log($location.host());

	initServerUrl();

	function initServerUrl() {
		var url = $location.host();
		console.log('host', url);
		var rentCode;
		var serverUrl;

		///////////////////////////处理上架后情况,如test2.promace.sinopec.com//////////////////
		var index = url.indexOf(localStorage.getItem('promaceDomian'))
		if(index > -1) {
			rentCode = url.slice(0, index)
			serverUrl = ''
			localStorage.setItem('rentCode', rentCode)
			localStorage.setItem('serverUrl', serverUrl)
			return
		}
		///////////////////////////前后端合并后指定租户方式，如http://127.0.0.1/DMBService/rents/test2/web/app/index.html//////////////////
		url = $location.absUrl();
		console.log('absUrl', url);
		console.log('serverUrl', localStorage.getItem('serverUrl'));
		
		var testStr = '/rents/'
		index = url.indexOf(testStr)

		var index2 = url.indexOf(localStorage.getItem('indexHtmlUrl'))
		if(index > -1 && index2 > index) {
			rentCode = url.slice(index + testStr.length, index2 - 1)
			serverUrl = localStorage.getItem('serverUrl') + '/rents/' + rentCode
			localStorage.setItem('rentCode', rentCode)
			localStorage.setItem('serverUrl', serverUrl)
			return
		}
		///////////////////////////前后端分离或未指定租户方式，如http://127.0.0.1/web/app/index.html//////////////////

		var lcoalUrl = localStorage.getItem('testRentUrl');
		if(lcoalUrl == null || lcoalUrl == '') {
			rentCode = ''
			serverUrl = localStorage.getItem('serverUrl')
			localStorage.setItem('rentCode', rentCode)
			localStorage.setItem('serverUrl', serverUrl)
			return
		} else {
			console.log('lcoalUrl', lcoalUrl)
			var index = lcoalUrl.indexOf(localStorage.getItem('promaceDomian'))
			if(index == -1) {
				rentCode = ''
				serverUrl = localStorage.getItem('serverUrl')
				localStorage.setItem('rentCode', rentCode)
				localStorage.setItem('serverUrl', serverUrl)
			} else {
				rentCode = lcoalUrl.slice(0, index)
				serverUrl = localStorage.getItem('serverUrl') + '/rents/' + rentCode
				localStorage.setItem('rentCode', rentCode)
				localStorage.setItem('serverUrl', serverUrl)
			}

		}
	}

	$scope.searchDevice; //此变量用于节点搜索预定义，不能删除
	$scope.searchOrg; //此变量用于组织机构搜索预定义，不能删除 
	$scope.viewSwitch = 0;
	$scope.pm = "";
	$scope.pc = {};
	$scope.popupAdd = [];
	$scope.testHttpDataFlag = true;
	$scope.mtrltypeId_temp; //所选的物料类型Id变量
	//$scope.deviceType;//设备类型信息承载
	//$scope.devicetypeId;//承载节点对应信息类型传送后台
	$scope.devicetypeId_temp; //相关节点类型的Id；
	$scope.sideMtrltypeId_temp; //侧线物料类型临时的Id；
	$scope.allTransType = []; //进出厂运输类型
	$scope.allTankType = []; //罐类型信息
	$scope.deviceInOutId_temp; //进出类型Id
	$scope.allsideLineType = []; //侧线类型信息
	$scope.allsamplePointType = []; //采样点类型信息
	$scope.allsiloType = []; //采样点类型信息
	$scope.allequipmentType = []; //设备类型信息
	$scope.alloutletType = []; //标志牌类型信息
	$scope.allsideMtrlType = []; //侧线物料类型信息

	//加载各个节点的相关类型信息
	//     loadTransMessType($http,$scope); 
	//     loadTankMessType($http,$scope);

	/**
	 * 显示设备列表（罐列表等）
	 * emit from firstMenuController
	 */
	$scope.$on('show-second-menu', function(event, obj) {
		$scope.viewSwitch = 1;
		console.log(obj);
		$scope.secondMenuObj = obj;
	});

	/**
	 * 显示节点详细信息
	 * emit from firstMenuController
	 */
	$scope.$on('show-node-panel', function(event, obj) {
		$scope.viewSwitch = 3;
		console.log('show-node-panel from viewController');
		$scope.$broadcast('node-detail-info', obj);
	});

	/**
	 * 显示组织机构详细信息
	 * emit from firstMenuController
	 */
	$scope.$on('show-org-panel', function(event, obj) {
		$scope.viewSwitch = 4;
		$scope.$broadcast('org-detail-info', obj);
	});

	/**
	 * 显示节点设备信息（具体某个罐，侧线，进出厂点等）
	 * emit from secondMenuController
	 */
	$scope.$on('show-panel', function(event, obj) {
		$scope.viewSwitch = 2;
		console.log('show-panel from viewController');
		$scope.$broadcast('panel-detail-info', obj);
		$scope.nodeInfo = obj;
		console.log("展示节点点击信息", obj);
	});
	/**
	 * 获得所有的物料信息
	 * emit from firstMenuController
	 */
	$scope.$on('mtrl-all-get', function(event, obj) {
		$scope.mtrl_temp = obj;
	});
	/**
	 * 获得所有的物料类型信息
	 * emit from firstMenuController
	 */
	$scope.$on('mtrlType-all-get', function(event, obj) {
		$scope.mtrlType_temp = obj;
	});
	/**
	 * 获得所有的组织机构信息
	 * emit from firstMenuController
	 */
	$scope.$on('org-all-get', function(event, obj) {
		$scope.org_temp = obj;
	});
	/**
	 * 获得所有的罐类型信息
	 * emit from firstMenuController
	 */
	// $scope.$on('tankType-all-get', function(event, obj){
	// $scope.tankType_temp=obj;
	//});
	//

	/**
	 * 弹窗显示度量指标信息
	 * emit from devicePanelController
	 */
	$scope.$on('msr-get-on-click', function(event, obj) {
		$scope.pc = obj['measure'];
		layer.open({
			type: 1,
			title: obj['deviceName'] + obj['measure']['nodeTypeName'] + '度量指标: ' + obj['measure']['idxTypeName'],
			contentUrl: 'component_popup_measure_detail.html',
			area: ['600px', '300px'],
			btn: ['确定'],
			yes: function() {
				layer.closeAll();
			},
			scope: $scope
		});
		console.log(obj['measure']);
		$scope.$broadcast('popupMessageBroadcast', obj);
	});

	/**
	 * 弹窗删除设备
	 */
	$scope.$on('device-delete-on-click', function(event, obj) {
		console.log(obj);
		$scope.pc = obj['elem'];
		$scope.deleteNodecode = obj.elem.nodeCode;
		layer.confirm('确认删除' + obj['elem']['nodeAlias'] + '吗？', {
				title: '提示',
				btn: ['是', '否']
			},
			function(index) {

				var deleteDeviceUrl = deleteUrl(obj.elem);
				$http({
					method: "DELETE",
					url: deleteDeviceUrl,
					responseType: "json",
				}).then(function success(res) {
					console.log(res);
					if(res.data == null) {
						layer.msg('已删除', {
							icon: 0,
							time: 1500
						});
						console.log(obj);
						for(var i = 0; i < obj.deviceList.length; i++) {
							if(obj.deviceList[i].nodeAlias == obj.nodeAlias) {
								$scope.secondMenuObj['deviceList'].splice(i, 1);
								return 0;
							}
						}

					} else {
						var data1 = res.data.collection.error.message;
						layer.msg(data1, {
							icon: 0,
							time: 3000
						});
					}
				}, function error(res) {
					layer.msg('删除失败', {
						icon: 0,
						time: 1500
					});
					console.log(res);
				});
			},
			function() {
				layer.closeAll();
				console.log("------");
			}
		);
	});
	/**
   * 弹窗修改设备
   * 1	侧线
		 2	罐
		 3	料仓
		 4	库位
		 5	进出厂点
		 6	采样点
		 7	排放口
		 8	设备
		 9	管段
		 10	阀门
		 11	盲板
		 12	三通
   * 
   */
	$scope.$on('node-updata-on-click', function(event, obj) {
		console.log(obj);
		console.log($scope.popupUpdata);
		var temp = [];
		//为了保证在修改时未选择数据时清空上一次变量的赋值。
		$scope.devicetypeId_temp = undefined;
		$scope.sideMtrltypeId_temp = undefined;

		//选择节点
		var url = pickUpJsonUpdata(obj.obj.nodeTypeId);

		$scope.popupUpdata = getFieldByState(2, url, obj);
		layer.open({
			type: 1,
			title: ["修改信息", 'font-size:20px;text-align:center;color:#fff;font-weight:normal;background:#2cabe3;'],
			contentUrl: 'component_popup_updata.html',
			btn: ['修改', '取消'],
			area: ['700px', '500px'],
			offset: ['150px', '500px'],
			yes: function(index) {
				switch(obj.obj['nodeTypeId']) {
					case 1:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' + $scope.secondMenuObj['areaTypeCode'] +
							'/sideLines/' + $scope.nodeInfo['nodeCode'];
						console.log($scope.popupUpdata);

						// 		 for (var i=0;i<$scope.mtrl_temp.length;i++) {
						//  		 	if ($scope.popupUpdata[5].value===$scope.mtrl_temp[i].mtrlName) {
						//  		 		var mtrlId_temp_line=$scope.mtrl_temp[i].mtrlId;
						//  		 		console.log("test");
						//  		 	}
						//  		 };
						if($scope.devicetypeId_temp == undefined) {
							$scope.devicetypeId_sideLine = obj.obj.sidelineTypeId;
						} else {
							$scope.devicetypeId_sideLine = $scope.devicetypeId_temp;
						}
						if($scope.sideMtrltypeId_temp == undefined) {
							$scope.devicetypeId_sideMtrl = obj.obj.slineMtrlTypeId;
						} else {
							$scope.devicetypeId_sideMtrl = $scope.sideMtrltypeId_temp;
						}
						//下面的物料信息暂时有问题
						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],

							"mtrlId": obj.obj.mtrlId,
							//					"slineInoutTypeId" : "1",//不需要了
							"nodeTypeId": "1",
							"nodeTypeName": "sideLine",
							"sidelineTypeId": $scope.devicetypeId_sideLine,

							//					"sidelineTypeName" : "物料域",
							"slineMtrlTypeId": $scope.devicetypeId_sideMtrl,
							//					"sideMtrlTypeName" : "物料",
							"idList": "",
							"areaForm": "",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};

						setHttpJson(obja, $scope.popupUpdata);
						break;
						//罐
					case 2:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' + $scope.secondMenuObj['areaTypeCode'] +
							'/tanks/' + $scope.nodeInfo['nodeCode'];
						console.log(updataDeviceUrl);
						//格式化后台所需数据

						if($scope.devicetypeId_temp == undefined) {
							$scope.devicetypeId_tank = obj.obj.tankTypeId;
						} else {
							$scope.devicetypeId_tank = $scope.devicetypeId_temp;
						}
						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"tankTypeId": $scope.devicetypeId_tank,
							"nodeTypeId": "2",
							"nodeTypeName": "tank",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
						};

						setHttpJson(obja, $scope.popupUpdata);
						break;
					case 3:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/silos/' + $scope.nodeInfo['nodeCode'];;
						console.log(updataDeviceUrl);

						if($scope.devicetypeId_temp == undefined) {
							$scope.devicetypeId_silo = obj.obj.siloTypeId;
						} else {
							$scope.devicetypeId_silo = $scope.devicetypeId_temp;
						}

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"nodeTypeId": "3",
							"nodeTypeName": "silo",
							"siloTypeId": $scope.devicetypeId_silo,
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};

						setHttpJson(obja, $scope.popupUpdata);
						break;
					case 4:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/stocks/' + $scope.nodeInfo['nodeCode'];;
						console.log(updataDeviceUrl);

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"nodeTypeId": "4",
							"nodeTypeName": "stock",
							"locationDomain": '1',
							"mtrlId": '',
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};

						setHttpJson(obja, $scope.popupUpdata);
						break;
					case 5:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/edgePoints/' + $scope.nodeInfo['nodeCode'];;
						console.log(updataDeviceUrl);

						if($scope.devicetypeId_temp == undefined) {
							$scope.devicetypeId_ep = obj.obj.transTypeId;
						} else {
							$scope.devicetypeId_ep = $scope.devicetypeId_temp;
						};
						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"transTypeId": $scope.devicetypeId_ep,
							"nodeTypeId": "5",
							"nodeTypeName": "edgePoint",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"formula": '',
							"areaCode": $scope.secondMenuObj['areaTypeCode']
						};

						setHttpJson(obja, $scope.popupUpdata);
						break;

					case 6:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/samplePoints/' + $scope.nodeInfo['nodeCode'];;
						console.log(updataDeviceUrl);

						if($scope.devicetypeId_temp == undefined) {
							$scope.devicetypeId_samplePoint = obj.obj.samplepointTypeId;
						} else {
							$scope.devicetypeId_samplePoint = $scope.devicetypeId_temp;
						}

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"mtrlId": '1',
							"nodeTypeId": "6",
							"nodeTypeName": "samplePoint",
							"samplepointTypeId": $scope.devicetypeId_samplePoint,
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};
						setHttpJson(obja, $scope.popupUpdata);
						break;

					case 7:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/outlets/' + $scope.nodeInfo['nodeCode'];;
						console.log(updataDeviceUrl);

						if($scope.devicetypeId_temp == undefined) {
							$scope.devicetypeId_outlet = obj.obj.signboardTypeId;
						} else {
							$scope.devicetypeId_outlet = $scope.devicetypeId_temp;
						}

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"mtrlId": '',
							"nodeTypeId": "7",
							"nodeTypeName": "outlet",
							"signboardTypeId": $scope.devicetypeId_outlet,
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};
						setHttpJson(obja, $scope.popupUpdata);
						break;
					case 8:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/equipments/' + $scope.nodeInfo['nodeCode'];;
						console.log(updataDeviceUrl);

						if($scope.devicetypeId_temp == undefined) {
							$scope.devicetypeId_equipment = obj.obj.technicId;
						} else {
							$scope.devicetypeId_equipment = $scope.devicetypeId_temp;
						}

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"mtrlId": '',
							"nodeTypeId": "8",
							"nodeTypeName": "equipment",
							"technicId": $scope.devicetypeId_equipment,
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};
						setHttpJson(obja, $scope.popupUpdata);
						break;
					case 9:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/tubulations/' + $scope.nodeInfo['nodeCode'];
						console.log($scope.popupUpdata[0].value);
						console.log($scope.popupUpdata);
						//	          for (var i=0;i<$scope.mtrl_temp.length;i++) {

						//      		 	if ($scope.popupUpdata[3].value===$scope.mtrl_temp[i].mtrlName) {
						//      		 		var mtrlId_temp_tubu=$scope.mtrl_temp[i].mtrlId;
						//      		 		console.log("test");
						//      		 	}
						//      		 };
						console.log(updataDeviceUrl);
						//下面的物料Id有问题，需要修改
						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"nodeTypeId": "9",
							"nodeTypeName": "tubulation",
							//		        	"mtrlId"					 : mtrlId_temp_tubu,
							"mtrlId": "1",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};

						setHttpJson(obja, $scope.popupUpdata);
						break;
					case 10:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/valves/' + $scope.nodeInfo['nodeCode'];;
						console.log(updataDeviceUrl);

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"mtrlId": '',
							"nodeTypeId": "10",
							"nodeTypeName": "valve",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};
						setHttpJson(obja, $scope.popupUpdata);
						break;
					case 11:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/plates/' + $scope.nodeInfo['nodeCode'];;
						console.log(updataDeviceUrl);

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"mtrlId": '',
							"nodeTypeId": "11",
							"nodeTypeName": "plate",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};
						setHttpJson(obja, $scope.popupUpdata);
						break;
					case 12:
						var updataDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/tees/' + $scope.nodeInfo['nodeCode'];;
						console.log(updataDeviceUrl);

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"nodeTypeId": "12",
							"nodeTypeName": "tee",
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode']
						};

						setHttpJson(obja, $scope.popupUpdata);
						break;
				}
				testInputVoid($scope.popupUpdata);
				if($scope.testHttpDataFlag == false) {
					alert("请输入完整数据！");
					$scope.testHttpDataFlag = true;
				} else {
					//请求修改
					$http({
						method: "PUT",
						url: updataDeviceUrl,
						//      	responseType: "json",
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

						if(res.data.url != null) {
							layer.msg('修改成功', {
								icon: 1,
								time: 1500
							});
							console.log(obj);
							for(var i = 0; i < $scope.popupUpdata.length; i++) {
								var key = $scope.popupUpdata[i].attributeName;

								if($scope.popupUpdata[i].attributeName == "inoutTypeId" || $scope.popupUpdata[i].attributeName == "slineInoutTypeId") {
									if($scope.popupUpdata[i].value == "出") {
										$scope.popupUpdata[i].value = "1";
									} else {
										$scope.popupUpdata[i].value = "0";
									}
								} else {}
								var value = $scope.popupUpdata[i].value;
								obj.obj[key] = value;
								console.log(key, value);
							}

						} else {
							var mes;
							var data1 = res.data.collection.error.message;
							switch(data1) {
								case "该nodeCode已存在！":
									mes = "修改数据编码已经存在！"
									break;
								case "该nodeNode已存在":
									mes = "修改数据名称已经存在！"
									break;
								case "该nodeAlias已存在":
									mes = "修改数据简称已经存在！"
									break;
								default:
									mes = "修改失败！"
									break;
							}
							layer.msg(mes, {
								icon: 2,
								time: 1500
							});
						};
						layer.close(index);
					}, function error(res) {
						console.log(res);
						layer.close(index);
						layer.msg('修改失败', {
							icon: 2,
							time: 1500
						});
					});
				}
			},
			btn2: function() {
				layer.closeAll();
			},
			scope: $scope
		});
	});
	/**
	 * 弹窗添加设备
	 */
	$scope.$on('device-add-on-click', function(event, obj) {
		console.log(obj);
		console.log($scope.secondMenuObj);

		var url = pickUpJsonAdd(obj.deviceName);
		//3-16改动罐测试
		//0：基本信息展示
		//1：增加需要输入字段
		//2：修改需要输入字段
		//$scope.popupAddDisplay用于增加
		getFieldByState(1, url);
		layer.open({
			type: 1,
			title: ["添加新" + obj['deviceName'], 'font-size:20px;text-align:center;color:#fff;font-weight:normal;background:#2cabe3;'],
			contentUrl: 'component_popup_add.html',
			btn: ['添加', '取消'],
			area: ['700px', '500px'],
			offset: ['150px', '500px'],
			yes: function(index) {
				//TODO: $http add new element
				//区分各个节点
				switch(obj.deviceName) {
					case "罐":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/tanks';
						console.log(addDeviceUrl);
						//格式化后台所需的数据，nodeTypeId nodeTypeName为确定不变的的字段

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"tankTypeId": $scope.devicetypeId_temp,
							"nodeTypeId": '2',
							"nodeTypeName": "tank",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
						};

						setHttpJson(obja, $scope.popupAdd);
						break;
					case "进出厂点":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/edgePoints';
						console.log(addDeviceUrl);

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],

							"transTypeId": $scope.devicetypeId_temp,
							"inoutTypeId": $scope.deviceInOutId_temp,
							"nodeTypeId": '5',
							"nodeTypeName": "edgePoint",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"formula": '',
							"areaCode": $scope.secondMenuObj['areaTypeCode']
						};

						setHttpJson(obja, $scope.popupAdd);
						// 						$scope.deviceInOutId_temp=undefined;
						break;
					case "侧线":

						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/sideLines';
						console.log(addDeviceUrl);
						console.log($scope.mtrl_temp);
						console.log($scope.popupAdd);
						//      		 for (var i=0;i<$scope.mtrl_temp.length;i++) {
						//      		 	if ($scope.popupAdd[5].value===$scope.mtrl_temp[i].mtrlName) {
						//      		 		var mtrlId_temp=$scope.mtrl_temp[i].mtrlId;
						//      		 		console.log("test");
						//      		 	}
						//      		 };

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							//							"mtrlId"					 : mtrlId_temp,
							"mtrlId": '1',

							"nodeTypeId": '1',
							"nodeTypeName": "sideLine",
							"slineInoutTypeId": $scope.deviceInOutId_temp,
							"sidelineTypeId": $scope.devicetypeId_temp,
							//						"sidelineTypeName" : "物料域",
							"slineMtrlTypeId": $scope.sideMtrltypeId_temp,
							//						"sideMtrlTypeName" : "物料",
							"idList": "",
							"areaForm": "",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode']

						};

						setHttpJson(obja, $scope.popupAdd);
						break;
					case "三通":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/tees';
						console.log(addDeviceUrl);

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],

							"nodeTypeId": '12',
							"nodeTypeName": "tee",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};

						setHttpJson(obja, $scope.popupAdd);
						break;
					case "采样点":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/samplePoints';
						console.log(addDeviceUrl);

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],

							"nodeTypeId": '6',
							"nodeTypeName": "samplePoint",
							"samplepointTypeId": $scope.devicetypeId_temp,
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};

						setHttpJson(obja, $scope.popupAdd);
						break;
					case "料仓":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/silos';
						console.log(addDeviceUrl);

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],

							"nodeTypeId": '3',
							"nodeTypeName": "silo",
							"siloTypeId": $scope.devicetypeId_temp,
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};

						setHttpJson(obja, $scope.popupAdd);
						break;
					case "管段":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/tubulations';
						console.log(addDeviceUrl);
						console.log($scope.popupAdd[1].value);
						console.log($scope.popupAdd);
						//      		 for (var i=0;i<$scope.mtrl_temp.length;i++) {
						//
						//      		 	if ($scope.popupAdd[3].value===$scope.mtrl_temp[i].mtrlName) {
						//      		 		var mtrlId_temp=$scope.mtrl_temp[i].mtrlId;
						//      		 		console.log("test");
						//      		 	}
						//      		 };

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],

							//		        	"mtrlId"					 : mtrlId_temp,
							"mtrlId": "1",
							"nodeTypeId": '9',
							"nodeTypeName": "tubulation",
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};

						setHttpJson(obja, $scope.popupAdd);
						break;
					case "库位":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/stocks';
						console.log(addDeviceUrl);

						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],

							"nodeTypeId": '4',
							"nodeTypeName": "stock",
							"locationDomain": '1',
							"mtrlId": '',
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};

						setHttpJson(obja, $scope.popupAdd);
						break;
					case "盲板":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/plates';
						console.log(addDeviceUrl);
						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"nodeTypeId": '11',
							"nodeTypeName": "plate",
							"mtrlId": '',
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};
						setHttpJson(obja, $scope.popupAdd);
						break;
					case "阀门":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/valves';
						console.log(addDeviceUrl);
						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"nodeTypeId": '10',
							"nodeTypeName": "valve",
							"mtrlId": '',
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};
						setHttpJson(obja, $scope.popupAdd);
						break;
					case "设备":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/equipments';
						console.log(addDeviceUrl);
						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"nodeTypeId": '8',
							"nodeTypeName": "equipment",
							"technicId": $scope.devicetypeId_temp,
							"mtrlId": '',
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};
						setHttpJson(obja, $scope.popupAdd);
						break;
					case "排放口":
						var addDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
							$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
							$scope.secondMenuObj['areaTypeCode'] + '/outlets';
						console.log(addDeviceUrl);
						var obja = {
							"areaId": $scope.secondMenuObj['areaId'],
							"nodeTypeId": '7',
							"nodeTypeName": "outlet",
							"signboardTypeId": $scope.devicetypeId_temp,
							"mtrlId": '',
							"mntUserName": '1',
							"mntUserId": '管理员',
							"crtUserId": '1',
							"crtUserName": '管理员',
							"des": '0',
							"dataStatus": '1',
							"areaCode": $scope.secondMenuObj['areaTypeCode'],
						};
						setHttpJson(obja, $scope.popupAdd);
						break;
				}

				testInputVoid($scope.popupAdd);
				if($scope.testHttpDataFlag == false) {
					alert("请输入完整数据！");
					$scope.testHttpDataFlag = true;
				} else {
					$http({
						method: "POST",
						url: addDeviceUrl,
						responseType: "json",
						data: $scope.data
					}).then(function success(res) {
						console.log(res);
						if(res.data.collection.error == undefined) {
							layer.msg('添加成功', {
								icon: 1,
								time: 1500
							});

							var data = JSON.parse($scope.data).collection.templates[0].data;
							console.log(data);
							var obj = {};
							for(var i = 0; i < data.length; i++) {
								obj[data[i].name] = data[i].value
							}
							console.log(obj);
							//											 $scope.orgAddData.children.unshift(obj);
							$scope.secondMenuObj['deviceList'].unshift(obj);

						} else {
							var mes;
							var data1 = res.data.collection.error.message;
							switch(data1) {
								case "nodeCode不能重复！":
									mes = "新增数据编码已经存在！"
									break;
								case "nodeName不能重复！":
									mes = "新增数据名称已经存在！"
									break;
								case "nodeAlias不能重复！":
									mes = "新增数据简称已经存在！"
									break;
								default:
									mes = "添加失败！"
									break;
							}

							//											layer.msg(data1, {
							//												icon: 0,
							//												time: 3000
							//											});
							layer.msg(mes, {
								icon: 2,
								time: 3000
							});
						};
						layer.close(index);
					}, function error(res) {
						layer.msg('添加请求失败', {
							icon: 2,
							time: 1500
						});
						console.log(res);
						layer.close(index);
					});
				}
			},
			btn2: function() {
				layer.closeAll();
			},
			scope: $scope
		});
	});

	/**
	 * 弹窗添加度量指标
	 */
	$scope.$on('msr-add-on-click', function(event, obj) {
		//  $scope.popupAdd = genMsrPopup();
		$scope.popupAdd = getMsrFieldByState(1);
		var msrAddNodeId_temp = obj.node.nodeId;
		var dIndex = obj.index;
		var msr = obj.dscope.msr;
		console.log(obj)
		layer.open({
			type: 1,
			title: ["启用" + '添加指标', 'font-size:1.5rem;text-align:center;color:#fff;font-weight:normal;background:#2cabe3;'],
			contentUrl: 'component_popup_addm.html',
			btn: ['添加', '取消'],
			area: ['700px', '300px'],
			offset: ['250px', '500px'],
			yes: function(index) {
				var obja = {
					'idxTypeId': obj.msr.idxTypeId,
					'nodeId': msrAddNodeId_temp,
					//			  	 'mtrlId':obj.mtr.mtrlId,
					'mtrlId': '1600243',
					'dimensionId': '1000',

					'dataStatus': '1',
					'crtUserId': '1',
					'crtUserName': 'lyt',
					'crtDate': '2017-10-21 10:38:41',
					'mntUserId': '1',
					'mntUserName': 'lyt',
					'mntDate': '2017-10-21 10:38:41',
					'sortNum': '1',
					'des': '1',
					'sourceDataType': '1',
					'idxTypeName': '',
					'nodeTypeName': '',
					'nodeCode': ''
				};
				testInputVoid($scope.popupAdd);
				if($scope.testHttpDataFlag == false) {
					alert("请输入完整数据！");
					$scope.testHttpDataFlag = true;
				} else {
					setHttpJson(obja, $scope.popupAdd);
					$http({
						method: "POST",
						url: localStorage.getItem('serverUrl') + '/measurements',
						data: $scope.data,
					}).then(function success(response) {
						console.log(response);

						if(response.data.collection.error == undefined) {
							layer.msg('添加成功', {
								icon: 1,
								time: 1500
							});
							console.log(msr);
							var data = JSON.parse($scope.data).collection.templates[0].data;
							var obj1 = {};
							console.log(data);
							for(var i = 0; i < data.length; i++) {
								console.log();
								obj1[data[i].name] = data[i].value
							}
							obj1["idxTypeName"] = obj.msr.idxTypeName;
							console.log(obj1);
							obj1.hasInfo = true;
							msr[dIndex] = obj1;

						} else {
							var data1 = res.data.collection.error.message;
							layer.msg(data1, {
								icon: 0,
								time: 3000
							});
							//								  layer.msg('添加失败', {icon: 2, time: 1500});
						}
						layer.close(index);
					}, function error(res) {
						console.log(res);
						layer.msg('添加失败', {
							icon: 2,
							time: 1500
						});
						layer.close(index);
					});
				}
			},
			btn2: function() {
				layer.closeAll();
			},
			scope: $scope
		});
	});

	//修改度量指标--------------------------------------------------------------
	$scope.$on('msr-update-on-click', function(event, obj) {

		$scope.popupAdd = getMsrFieldByState(1, obj.measure);
		var idxtypeId = obj.measure.idxTypeId;
		var nodeId_temp = obj.node.nodeId;
		console.log($scope.popupAdd);
		var dIndex = obj.index;
		var msr = obj.dscope.msr;
		//获取idxCode来凑URL
		var temp_idxcode = obj.measure.idxCode;

		layer.open({
			type: 1,
			title: ["启用" + obj['measure']['idxTypeName'] + '指标', 'font-size:1.5rem;text-align:center;color:#fff;font-weight:normal;background:#2cabe3;'],
			contentUrl: 'component_popup_msr_update.html',
			btn: ['修改', '取消'],
			area: ['700px', '300px'],
			offset: ['250px', '500px'],
			yes: function(index) {
				//TODO: $http add new element
				var obja = {
					'idxTypeId': idxtypeId,
					'nodeId': nodeId_temp,
					'mtrlId': '1020',
					'dimensionId': '1000',

					'dataStatus': '1',
					'crtUserId': '1',
					'crtUserName': 'lyt',
					'crtDate': '2017-10-21 10:38:41',
					'mntUserId': '1',
					'mntUserName': 'lyt',
					'mntDate': '2017-10-21 10:38:41',
					'sortNum': '1',
					'des': '1',
					'sourceDataType': '1',
					'idxTypeName': '',
					'nodeTypeName': '',
					'nodeCode': ''
				};
				//转成增加需要的json格式

				//			  	$scope.MsrUpdatedata=cj.parseCjArray($scope.MsrupdateArray);
				testInputVoid($scope.popupAdd);
				if($scope.testHttpDataFlag == false) {
					alert("请输入完整数据！");
					$scope.testHttpDataFlag = true;
				} else {
					setHttpJson(obja, $scope.popupAdd);
					$http({
						method: "PUT",
						url: localStorage.getItem('serverUrl') + '/measurements/' + temp_idxcode,
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
					}).then(function successCallback(response) {
						console.log(response);
						if(response.data.url != null) {
							layer.msg('修改成功', {
								icon: 1,
								time: 1500
							});
							for(var i = 0; i < $scope.popupAdd.length; i++) {
								var key = $scope.popupAdd[i]['attributeName'];
								var value = $scope.popupAdd[i].value;
								msr[dIndex][key] = value;
							}
						} else {
							var data1 = res.data.collection.error.message;
							layer.msg(data1, {
								icon: 0,
								time: 3000
							});
							//								  layer.msg('修改失败', {icon: 2, time: 1500});
						}
						layer.close(index);
					}, function errorCallback(response) {
						console.log(response);
						layer.msg('修改失败', {
							icon: 2,
							time: 1500
						});
						layer.close(index);
					});
				}
			},
			btn2: function() {
				layer.closeAll();
			},
			scope: $scope
		});
	})

	//删除度量指标----------------------------
	$scope.$on('msr-delete-on-click', function(event, obj) {
		$scope.popupAdd = genMsrAddPopup();
		console.log($scope.popupAdd);
		console.log(obj);
		console.log(event);
		$scope.pc = obj['elem'];
		var dIndex = obj.index;
		var msr = obj.dscope.msr;
		layer.confirm('确认删除' + obj.measure.idxTypeName + '吗？', {
				title: '提示',
				btn: ['是', '否']
			},
			function(index) {
				//TODO: $http delete this element
				var deletemeasureUrl = localStorage.getItem('serverUrl') + '/measurements/' + obj.measure.idxCode;
				$http({
					method: "DELETE",
					url: deletemeasureUrl,
					responseType: "json",
				}).then(function success(res) {
					console.log(res);
					if(res.data == null) {
						layer.msg('已删除', {
							icon: 0,
							time: 1500
						});
						//下面是用来实时更新数据的
						msr[dIndex] = {};
					} else {
						var data1 = res.data.collection.error.message;
						layer.msg(data1, {
							icon: 0,
							time: 3000
						});
					}
				}, function error() {
					console.log("删除失败！");
					layer.msg('删除失败', {
						icon: 0,
						time: 1500
					});
				});
				console.log(deletemeasureUrl);

				layer.close(index);
			},
			function() {
				layer.closeAll();
			}
		);
	})

	//物料新增---------
	$scope.$on('mtrl-add-on-click', function(event, obj) {

		$scope.popupAdd = getMtrlFieldByState(1);
		console.log($scope.popupAdd);
		console.log($scope.mtrl_temp);
		layer.open({
			type: 1,
			title: ["启用" + '添加指标', 'font-size:1.5rem;text-align:center;color:#fff;font-weight:normal;background:#2cabe3;'],
			contentUrl: 'component_popup_addm.html',
			btn: ['添加', '取消'],
			area: ['700px', '300px'],
			offset: ['250px', '500px'],
			yes: function(index) {
				//					var mtrltypeId_temp;
				//        for (var i=0;i<$scope.mtrl_temp.length;i++) {
				//      		 	if ($scope.popupAdd[3].value===$scope.mtrl_temp[i].mtrlTypeName) {
				//      		 		mtrltypeId_temp=$scope.mtrl_temp[i].mtrlTypeId;
				//      		 		console.log("test");
				//      		 	}
				//      		};
				var obja = {
					'upperMtrlCode': '0',
					'mtrlTypeId': $scope.mtrltypeId_temp,
					'dec': '2',
					'dimensioneId': '3',
					'tankIdt': '1',
					'crtUserId': '1',
					'crtUserName': 'lyt',
					'crtDate': '2017-10-21 10:38:41',
					'mntUserId': '1',
					'mntUserName': 'lyt',
					'mntDate': '2017-10-21 10:38:41',
					'sortNum': '1',
					'des': '1',
					'nodeId': obj.node.nodeId,
					'vcfTypeName': '1',
					'nodeTypeName': '1',
					'nodeCode': '1'
				};
				testInputVoid($scope.popupAdd);
				if($scope.testHttpDataFlag == false) {
					alert("请输入完整数据！");
					$scope.testHttpDataFlag = true;
				} else {
					setHttpJson(obja, $scope.popupAdd);
					$http({
						method: "POST",
						url: localStorage.getItem('serverUrl') + '/materials',
						data: $scope.data
					}).then(function successCallback(response) {
						console.log(response);
						if(response.data.collection.error == undefined) {
							layer.msg('添加成功', {
								icon: 1,
								time: 1500
							});
						} else {
							var data1 = res.data.collection.error.message;
							layer.msg(data1, {
								icon: 0,
								time: 3000
							});
							//									  layer.msg('添加失败', {icon: 2, time: 1500});
						}
						layer.close(index);
					}, function errorCallback(response) {
						console.log(response);
						layer.msg('添加失败', {
							icon: 2,
							time: 1500
						});
						layer.close(index);
					});
				}
			},
			btn2: function() {
				layer.closeAll();
			},
			scope: $scope
		});
	});
	//物料新增结束---------

	//物料修改----------
	$scope.$on('mtrl-update-on-click', function(event, obj) {
		var mtrlCodeUrl = obj.measure.mtrlCode;
		$scope.popupAdd = getMtrlFieldByState(1, obj.measure);
		layer.open({
			type: 1,
			title: ["启用" + '修改指标', 'font-size:1.5rem;text-align:center;color:#fff;font-weight:normal;background:#2cabe3;'],
			contentUrl: 'component_popup_addm.html',
			btn: ['添加', '取消'],
			area: ['700px', '300px'],
			offset: ['250px', '500px'],
			yes: function(index) {
				//       var mtrltypeId_temp;
				//        for (var i=0;i<$scope.mtrl_temp.length;i++) {
				//      		 	if ($scope.popupAdd[3].value===$scope.mtrl_temp[i].mtrlTypeName) {
				//      		 		mtrltypeId_temp=$scope.mtrl_temp[i].mtrlTypeId;
				//      		 		console.log("test");
				//      		 	}
				//      		};
				var obja = {
					'upperMtrlCode': '0',
					'mtrlTypeId': $scope.mtrltypeId_temp,
					'dec': '2',
					'dimensioneId': '3',
					'tankIdt': '1',

					'crtUserId': '1',
					'crtUserName': 'lyt',
					'crtDate': '2017-10-21 10:38:41',
					'mntUserId': '1',
					'mntUserName': 'lyt',
					'mntDate': '2017-10-21 10:38:41',
					'sortNum': '1',
					'des': '1',
					'nodeId': obj.node.nodeId,
					'mtrlTypeName': '1',
					'vcfTypeName': '1',
					'nodeTypeName': '1',
					'nodeCode': '1'
				};

				//转成增加需要的json格式
				testInputVoid($scope.popupAdd);
				if($scope.testHttpDataFlag == false) {
					alert("请输入完整数据！");
					$scope.testHttpDataFlag = true;
				} else {
					setHttpJson(obja, $scope.popupAdd);
					$http({
						method: "PUT",
						url: localStorage.getItem('serverUrl') + '/materials/' + mtrlCodeUrl,
						//				responseType: "json",
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
					}).then(function successCallback(response) {
						console.log(response);
						if(response.data.url != null) {
							layer.msg('修改成功', {
								icon: 1,
								time: 1500
							});
						} else {
							var data1 = res.data.collection.error.message;
							layer.msg(data1, {
								icon: 0,
								time: 3000
							});
							//								  layer.msg('修改失败', {icon: 2, time: 1500});
						}
						layer.close(index);
					}, function errorCallback(response) {
						layer.msg('修改失败', {
							icon: 2,
							time: 1500
						});
						layer.close(index);
					});
				}
			},
			btn2: function() {
				layer.closeAll();
			},
			scope: $scope
		});
	});
	//物料修改结束----------

	//物料删除
	$scope.$on('mtrl-delete-on-click', function(event, obj) {
		$scope.popupAdd = genMsrAddPopup();
		console.log($scope.popupAdd);
		console.log(obj);
		layer.confirm('确认删除' + '吗？', {
				title: '提示',
				btn: ['是', '否']
			},
			function(index) {
				//TODO: $http delete this element
				var deleteMtrlUrl = localStorage.getItem('serverUrl') + '/materials/' + obj.measure.mtrlCode;
				$http({
					method: "DELETE",
					url: deleteMtrlUrl,
					responseType: "json",
				}).then(function success(res) {
					console.log(res);
					if(res.data == null) {
						layer.msg('已删除', {
							icon: 0,
							time: 1500
						});
					} else {
						var data1 = res.data.collection.error.message;
						layer.msg(data1, {
							icon: 0,
							time: 3000
						});
					}
				}, function error() {
					console.log("删除失败！");
					layer.msg('删除失败', {
						icon: 0,
						time: 1500
					});
				});
				layer.close(index);
			},
			function() {
				layer.closeAll();
			}
		);
	})

	function setHttpJson(obj, popup) {
		for(var i = 0; i < popup.length; i++) {
			if(popup[i].hasOwnProperty("value")) {
				if(popup[i].value != undefined) {

					if(popup[i].attributeName == "inoutTypeId") {
						if(popup[i].value == "出") {
							obj[popup[i].attributeName] = 1;
						} else {
							obj[popup[i].attributeName] = "0";
						}
					} else if(popup[i].attributeName == "slineInoutTypeId") {
						if(popup[i].value == "出") {
							obj[popup[i].attributeName] = 1;
						} else {
							obj[popup[i].attributeName] = "0";
						}
					} else {
						obj[popup[i].attributeName] = popup[i].value;
					}
				}
			}
		}
		$scope.data = cj.parseCj(obj);
		console.log($scope.data);
	}

	function getFieldByState(state, _url, obj) {
		var arr = [];
		$http({
			method: "GET",
			url: _url,
		}).then(function success(response) {

			for(var key in response.data.attribute) {
				if(hasState(state, response.data.attribute[key].flag)) {
					response.data.attribute[key].attributeName = key;
					arr.push(response.data.attribute[key]);
				}
			}
			console.log("arr", arr);
			if(state == 1) {
				$scope.popupAdd = arr;
				console.log($scope.popupAdd);
			} else if(state == 2) {
				$scope.popupUpdata = arr;
				updataDisplay(obj.obj, $scope.popupUpdata);
			}
		}, function error(response) {
			console.log(response);
		})
	}

	function hasState(state, flag) {
		for(var i = 0; i < flag.length; i++) {
			if(state == flag[i]) {
				return true;
			}
		}
		return false;
	}
	//回显修改所需的数据
	function updataDisplay(updataObj, popup) {
		console.log(updataObj);
		for(var i = 0; i < popup.length; i++) {
			if(updataObj.hasOwnProperty(popup[i].attributeName)) {
				if(popup[i].attributeName == "inoutTypeId") {
					if(updataObj[popup[i].attributeName] == "1") {
						popup[i].value = "出";
					} else {
						popup[i].value = "进";
					}
				} else if(popup[i].attributeName == "slineInoutTypeId") {
					if(updataObj[popup[i].attributeName] == "1") {
						popup[i].value = "出";
					} else {
						popup[i].value = "进";
					}
				} else {
					popup[i].value = updataObj[popup[i].attributeName];
				}
			}
		}
		console.log(popup);
	}

	function pickUpJsonUpdata(num) {
		var url = "";
		num = parseInt(num);
		switch(num) {
			case 1:
				url = "../app/model/sideLine.json";
				break;
			case 2:
				url = "../app/model/tank.json";
				break;
			case 3:
				url = "../app/model/silo.json";
				break;
			case 4:
				url = "../app/model/stock.json";
				break;
			case 5:
				url = "../app/model/edgePoint.json";
				break;
			case 6:
				url = "../app/model/samplePoint.json";
				break;
			case 7:
				url = "../app/model/outlet.json";
				break;
			case 8:
				url = "../app/model/equipment.json";
				break;
			case 9:
				url = "../app/model/tubulation.json";
				break;
			case 10:
				url = "../app/model/valve.json";
				break;
			case 11:
				url = "../app/model/plate.json";
				break;
			case 12:
				url = "../app/model/tee.json";
				break;
		}
		return url;
	}

	function pickUpJsonAdd(nodeName) {
		var url = "";
		switch(nodeName) {
			case "罐":
				url = "../app/model/tank.json";
				break;
			case "侧线":
				url = "../app/model/sideLine.json";
				break;
			case "料仓":
				url = "../app/model/silo.json";
				break;
			case "库位":
				url = "../app/model/stock.json";
				break;
			case "进出厂点":
				url = "../app/model/edgePoint.json";
				break;
			case "采样点":
				url = "../app/model/samplePoint.json";
				break;
			case "排放口":
				url = "../app/model/outlet.json";
				break;
			case "设备":
				url = "../app/model/equipment.json";
				break;
			case "管段":
				url = "../app/model/tubulation.json";
				break;
			case "阀门":
				url = "../app/model/valve.json";
				break;
			case "盲板":
				url = "../app/model/plate.json";
				break;
			case "三通":
				url = "../app/model/tee.json";
				break;
		}
		return url;
	}

	function deleteUrl(obj) {
		var deleteDeviceUrl = localStorage.getItem('serverUrl') + localStorage.getItem('areaUrl') + '/' +
			$scope.secondMenuObj['orgCode'] + '/' + $scope.secondMenuObj['areaType'] + 's/' +
			$scope.secondMenuObj['areaTypeCode'] + '/' + obj.nodeTypeName + 's/' + $scope.deleteNodecode;
		console.log(deleteDeviceUrl);
		return deleteDeviceUrl;
	}

	function getMsrFieldByState(state, data_temp) {
		var arr = [];
		$http({
			method: "GET",
			url: "../app/model/measure.json",
		}).then(function success(response) {
			for(var key in response.data.attribute) {
				if(hasState(state, response.data.attribute[key].flag)) {
					response.data.attribute[key].attributeName = key;
					arr.push(response.data.attribute[key]);
				}
			}
			console.log("arr", arr);
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

	function getMtrlFieldByState(state, data_temp) {
		var arr = [];
		$http({
			method: "GET",
			url: "../app/model/mtrl.json",
		}).then(function success(response) {
			for(var key in response.data.attribute) {
				if(hasState(state, response.data.attribute[key].flag)) {
					response.data.attribute[key].attributeName = key;
					arr.push(response.data.attribute[key]);
				}
			}
			console.log("arr", arr);
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

	function hasState(state, flag) {
		for(var i = 0; i < flag.length; i++) {
			if(state == flag[i]) {
				return true;
			}
		}
		return false;
	}

	function testInputVoid(data) {
		for(var key in data) {
			if(data[key].required == true) {
				if(data[key].value === '' || data[key].value === null) {
					$scope.testHttpDataFlag = false;
					return false;
				}
			}
			console.log($scope.testHttpDataFlag);
		}
	}
	//获取所选取的物料类型Id，from component_popup_addm.html
	$scope.getMtrlId = function(data) {
		$scope.mtrltypeId_temp = data;
	}
	//获取设备Id，from component_popup_add.html
	$scope.getDeviceId = function(data) {
		$scope.devicetypeId_temp = data;
	}
	//获取侧线物料类型Id，from component_popup_add.html
	$scope.getSideMtrlId = function(data) {
		$scope.sideMtrltypeId_temp = data;
	}
	//获取设备Id，from component_popup_add.html
	$scope.getInOutId = function(data) {
		$scope.deviceInOutId_temp = data;
	}

	//加载运输类型
	$scope.loadTransMessType = function() {
		var tankUrl = localStorage.getItem('serverUrl') + '/transTypes';
		$http({
			method: "GET",
			url: tankUrl,
			responseType: "json"
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data);

			//获取到的组织机构信息
			for(var i = 0; i < resultArr.length; i++) {
				$scope.allTransType.push({
					deviceTypeId: resultArr[i].transTypeId,
					deviceTypeName: resultArr[i].transTypeName,
					deviceTypeCode: resultArr[i].transTypeCode,
				});
			};
		}, function errorCallback(response) {});
	}
	//加载所有的罐类型
	$scope.loadTankMessType = function() {
		var tankUrl = localStorage.getItem('serverUrl') + '/tankTypes';
		$http({
			method: "GET",
			url: tankUrl,
			responseType: "json"
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data);
			$scope.allTankType = [];
			//获取到的组织机构信息
			for(var i = 0; i < resultArr.length; i++) {
				$scope.allTankType.push({
					deviceTypeId: resultArr[i].tankTypeId,
					deviceTypeName: resultArr[i].tankTypeName,
					deviceTypeCode: resultArr[i].tankTypeCode,
				});
			};
		}, function errorCallback(response) {});
	}
	//加载所有的测线类型
	$scope.loadsidelineMessType = function() {
		var tankUrl = localStorage.getItem('serverUrl') + '/sidelineTypes';
		$http({
			method: "GET",
			url: tankUrl,
			responseType: "json"
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data);
			$scope.allsideLineType = [];
			//获取到的组织机构信息
			for(var i = 0; i < resultArr.length; i++) {
				$scope.allsideLineType.push({
					deviceTypeId: resultArr[i].sidelineTypeId,
					deviceTypeName: resultArr[i].sidelineTypeName,
					deviceTypeCode: resultArr[i].sidelineTypeCode,
				});
			};
		}, function errorCallback(response) {});
	}
	//获取所有的采样点类型
	$scope.loadsamplePointMessType = function() {
		var tankUrl = localStorage.getItem('serverUrl') + '/samplepointTypes';
		$http({
			method: "GET",
			url: tankUrl,
			responseType: "json"
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data);
			$scope.allsamplePointType = [];
			//获取到的组织机构信息
			for(var i = 0; i < resultArr.length; i++) {
				$scope.allsamplePointType.push({
					deviceTypeId: resultArr[i].samplepointTypeId,
					deviceTypeName: resultArr[i].samplepointTypeName,
					deviceTypeCode: resultArr[i].samplepointTypeCode,
				});
			};
		}, function errorCallback(response) {});
	}
	//获取所有的采样点类型
	$scope.loadsiloMessType = function() {
		var tankUrl = localStorage.getItem('serverUrl') + '/siloTypes';
		$http({
			method: "GET",
			url: tankUrl,
			responseType: "json"
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data);
			$scope.allsiloType = [];
			//获取到的组织机构信息
			for(var i = 0; i < resultArr.length; i++) {
				$scope.allsiloType.push({
					deviceTypeId: resultArr[i].siloTypeId,
					deviceTypeName: resultArr[i].siloTypeName,
					deviceTypeCode: resultArr[i].siloTypeCode,
				});
			};
		}, function errorCallback(response) {});
	}
	//获取所有的设备类型
	$scope.loadequipmentMessType = function() {
		var tankUrl = localStorage.getItem('serverUrl') + '/equTechnics';
		$http({
			method: "GET",
			url: tankUrl,
			responseType: "json"
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data);
			$scope.allequipmentType = [];
			//获取到的组织机构信息
			for(var i = 0; i < resultArr.length; i++) {
				$scope.allequipmentType.push({
					deviceTypeId: resultArr[i].technicId,
					deviceTypeName: resultArr[i].technicName,
					deviceTypeCode: resultArr[i].technicCode,
				});
			};
		}, function errorCallback(response) {});
	}
	//获取所有的标识牌类型
	$scope.loadoutletMessType = function() {
		var tankUrl = localStorage.getItem('serverUrl') + '/signboardTypes';
		$http({
			method: "GET",
			url: tankUrl,
			responseType: "json"
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data);
			$scope.alloutletType = [];
			//获取到的组织机构信息
			for(var i = 0; i < resultArr.length; i++) {
				$scope.alloutletType.push({
					deviceTypeId: resultArr[i].signboardTypeId,
					deviceTypeName: resultArr[i].signboardTypeName,
					deviceTypeCode: resultArr[i].signboardTypeCode,
				});
			};
		}, function errorCallback(response) {});
	}
	//获取所有的区域类型
	$scope.loadareaMessType = function() {
		var tankUrl = localStorage.getItem('serverUrl') + '/areaTypes';
		$http({
			method: "GET",
			url: tankUrl,
			responseType: "json"
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data);
			$scope.allareaType = [];
			//获取到的组织机构信息
			for(var i = 0; i < resultArr.length; i++) {
				$scope.allareaType.push({
					deviceTypeId: resultArr[i].areaTypeId,
					deviceTypeName: resultArr[i].areaTypeName,
					deviceTypeCode: resultArr[i].areaTypeCode,
				});
			};
		}, function errorCallback(response) {});
	}
	//获取所有的侧线物料类型
	$scope.loadsideMtrlMessType = function() {
		var tankUrl = localStorage.getItem('serverUrl') + '/sideMtrlTypes';
		$http({
			method: "GET",
			url: tankUrl,
			responseType: "json"
		}).then(function successCallback(response) {
			var resultArr = $.ET.toObjectArr(response.data);
			$scope.allsideMtrlType = [];
			//获取到的组织机构信息
			for(var i = 0; i < resultArr.length; i++) {
				$scope.allsideMtrlType.push({
					deviceTypeId: resultArr[i].sideMtrlTypeId,
					deviceTypeName: resultArr[i].sideMtrlTypeName,
					deviceTypeCode: resultArr[i].sideMtrlTypeCode,
				});
			};
		}, function errorCallback(response) {});
	}

});