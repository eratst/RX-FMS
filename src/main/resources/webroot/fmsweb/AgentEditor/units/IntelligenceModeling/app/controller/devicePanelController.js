app.controller('devicePanelController', function($scope, $http){

  $scope.url = "template_device_main.html";
  $scope.basicInfoUrl = "";
  $scope.obj = {};
  $scope.mtr = {};
  $scope.msr = [];
	$scope.nodeUpdataOnClick=function(mtr){
		$scope.$emit('node-updata-on-click',{obj: $scope.obj,mtrl:mtr});
	}
  /**
   * broadcast from viewController
   */
  $scope.$on('panel-detail-info', function(event, obj){

    $scope.measureTempUrl = localStorage.getItem('serverUrl') + localStorage.getItem('getAllMsrUrl');//获取可能有的度量指标
    $scope.mtr = {}; //初始化物料和度量指标信息
    $scope.msr = [];
    //详细信息
    $scope.obj = obj;
    //FIXME: 不更新拓扑连接里的内容
    localStorage.setItem('deviceNodeCode',obj['nodeCode']);
    $('#tuopu-frame').attr('src', $('#tuopu-frame').attr('src'));
    //console.log(localStorage.getItem('deviceNodeCode'));
    console.log("Id类型",obj['nodeTypeId']);
    console.log("obj传送",obj);
    //下面的nodeTypeId From firstMenuController，在loadDevice是进行赋值，是前台自定义用于流程控制，不属于后台返回数据
    switch(parseInt(obj['nodeTypeId'])){
      case 1: //侧线
        $scope.basicInfoUrl = 'template_info_basic_sidelines.html';
        //下面的nodeTypeId是用于控制节点3d显示控制
        $scope.$broadcast('show-device-model', {nodeTypeId:1});
        $scope.measureTempUrl += 'sideLine';
        break;
      case 2: //罐
        $scope.basicInfoUrl = 'template_info_basic_tanks.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:2, tankTypeCode:parseInt(obj['tankTypeCode'])});
        $scope.measureTempUrl += 'tank';
        break;
      case 3: //料仓
      	$scope.basicInfoUrl = 'template_info_basic_silos.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:3});
        $scope.measureTempUrl += 'silo';
        break;
      case 4: //库位
      	$scope.basicInfoUrl = 'template_info_basic_stocks.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:4});
        $scope.measureTempUrl += 'stock';
        break;
      case 5: //进出厂点
        $scope.basicInfoUrl = 'template_info_basic_edgepoints.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:5});
        $scope.measureTempUrl += 'edgePoint';
        break;
      case 6: //采样点
      	$scope.basicInfoUrl = 'template_info_basic_samplePoints.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:6});
        $scope.measureTempUrl += 'samplePoint';
        break;
      case 7: //排放口
      $scope.basicInfoUrl = 'template_info_basic_outlets.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:7});
        $scope.measureTempUrl += 'outlet';
        break;
      case 8: //设备
        $scope.basicInfoUrl = 'template_info_basic_technics.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:8});
        $scope.measureTempUrl += 'equipment';
        break;
      case 9: //管段
      	$scope.basicInfoUrl = 'template_info_basic_tubulations.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:9});
        $scope.measureTempUrl += 'tubulation';
        break;
      case 10: //阀门
      	$scope.basicInfoUrl = 'template_info_basic_valves.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:10});
        $scope.measureTempUrl += 'valve';
        break;
      case 11: //盲板
      	$scope.basicInfoUrl = 'template_info_basic_plates.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:11});
        $scope.measureTempUrl += 'plate';
        break;
      case 12: //三通
      	$scope.basicInfoUrl = 'template_info_basic_tees.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:12});
        $scope.measureTempUrl += 'tee';
        break;
      case 13: //生产单元
      	$scope.basicInfoUrl = 'template_info_basic_cell.html';
        $scope.$broadcast('show-device-model', {nodeTypeId:13});
        $scope.measureTempUrl ='cell';
        break;
    }

    
    
    

    //读取可能有的度量指标
    if ($scope.measureTempUrl!='cell') {
    	
    	//读取物料
    $http({
      method: "GET",
      url: localStorage.getItem('serverUrl') + localStorage.getItem('getMtrUrl') + obj['nodeCode'],
      responseType: "json"
    }).then(function success(response){
      var resultArr = $.ET.toObjectArr(response.data);
      if (resultArr.length > 0){
        $scope.mtr = resultArr[0];
        
      } else { $scope.mtr.noElem = true; 
      console.log("物料信息devicepanelControl",$scope.mtr);
      console.log(localStorage.getItem('serverUrl') + localStorage.getItem('getMtrUrl') + obj['nodeCode']);
      }
    }, function error(){});
    	//读取可能有的度量指标
    	$http({
      method: "GET",
      url: $scope.measureTempUrl,
      responseType: "json"
    }).then(function success(res){
      var resultArr = $.ET.toObjectArr(res.data);
      if(resultArr.length > 0){
      	console.log("测后台已有的度量指标类型");
        console.log(resultArr);
        console.log($scope.measureTempUrl)
        //判断度量指标是属于节点还是生产单元，用于控制数据显示
        for (var i=0,len=resultArr.length;i<len;i++) {
        	resultArr[i].idxFlag=true
        }
        $scope.msr = resultArr;
      }
      //读取度量指标
      $http({
        method: "GET",
//      url: localStorage.getItem('serverUrl') + localStorage.getItem('getMsrUrl') + obj['nodeCode'],
        url: localStorage.getItem('serverUrl') + localStorage.getItem('getMsrUrlForNode') + obj['nodeCode'],
        responseType: "json"
      }).then(function success(response){
        var resultArr = $.ET.toObjectArr(response.data);
        if (resultArr.length > 0){
        	console.log("测获得的数据信息");
          console.log(resultArr);
          for(var i = 0; i < $scope.msr.length; i++){
            for(var j = 0; j < resultArr.length; j++){
              if($scope.msr[i]['idxTypeName'] === resultArr[j]['idxTypeName']){
                $scope.msr[i] = resultArr[j];
                $scope.msr[i]['hasInfo'] = true;
              }
            }
          }
        };
        console.log($scope.msr);
        
      }, function error(){});
    }, function error(res){});
    } else{
    	 $http({
        method: "GET",
//      url: localStorage.getItem('serverUrl') + localStorage.getItem('getMsrUrl') + obj['nodeCode'],
        url: localStorage.getItem('serverUrl') + '/prdtCellMeasurements?$cellCodes='+obj['cellCode'],
        responseType: "json"
      }).then(function success(response){
        var resultArr = $.ET.toObjectArr(response.data);
        console.log('度量指标',resultArr)
        if (resultArr.length > 0){
          for(var i = 0; i < resultArr.length; i++){
            for(var j = 0; j < resultArr.length; j++){
                $scope.msr[i] = resultArr[j];
                $scope.msr[i]['hasInfo'] = true;
            }
          }
        };
        console.log($scope.msr);
        
      }, function error(){});
    }
    
  });

  $scope.msrOnClick = function(measure, deviceName){
    $scope.$emit('msr-get-on-click', {'measure':measure, 'deviceName':deviceName});
  };

  $scope.msrAddOnClick = function(node,mtrl,mtr,index){
//  console.log(measure);
    console.log(node);
    $scope.$emit('msr-add-on-click', {'msr':node,'node':mtrl,'mtr':mtr,'index':index,'dscope':$scope});
  };
  $scope.msrUpdateOnClick = function(measure, node,index){
    console.log(measure);
    console.log(node);
    $scope.$emit('msr-update-on-click', {'measure':measure, 'node':node,'index':index,'dscope':$scope});
  };
  $scope.msrDeleteOnClick = function(measure, node,index){
    console.log(measure);
    console.log(node);
    $scope.$emit('msr-delete-on-click', {'measure':measure, 'node':node,'index':index,'dscope':$scope});
  };
  //下面是物料的功能操作
  $scope.mtrlAddOnClick = function(measure, node){
    console.log(measure);
    console.log(node);
    $scope.$emit('mtrl-add-on-click', {'measure':measure, 'node':node});
  };
  $scope.mtrlUpdateOnClick = function(measure, node){
    console.log(measure);
    console.log(node);
    $scope.$emit('mtrl-update-on-click', {'measure':measure, 'node':node});
  };
  $scope.mtrlDeleteOnClick = function(measure, node){
    console.log(measure);
    console.log(node);
    $scope.$emit('mtrl-delete-on-click', {'measure':measure, 'node':node});
  };
  
});

