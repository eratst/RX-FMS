app.controller('nodePanelController', function($scope){

  $scope.url = "panel_node_info.html";
  $scope.node = {};

  $scope.$on('node-detail-info', function(event, obj){
    console.log(obj);
    $scope.nodesInfo = area2ImgUrls(obj['areaTypeCode']);
    // FIXME 为了显示区域下每个节点有多少个的愚蠢循环，重写，要么找后台要接口，要么改前端的$scope
    if(obj['devices']){
      for(var i = 0; i < $scope.nodesInfo.length; i++){
        for(var j = 0; j < obj['devices'].length; j++){
          if ($scope.nodesInfo[i]['nodeNameCN']===obj['devices'][j]['deviceName']){
            $scope.nodesInfo[i]['count'] = obj['devices'][j]['deviceList'].length;
            break;
          }
        }
      }
    }
    $scope.node = obj;
  });

  $scope.showSecondMenu = function(nodeNameCN){
    // FIXME 另一个愚蠢的循环，只能改数据结构
    if($scope.node['devices']){
      for(var i = 0; i < $scope.node['devices'].length; i++){
        if ($scope.node['devices'][i]['deviceName'] === nodeNameCN){
          $scope.$emit('show-second-menu', $scope.node['devices'][i]);
        }
      }
    }
  };
});