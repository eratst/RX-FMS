app.controller('secondMenuController', function ($scope) {

  $scope.url = "component_menu_second.html";

  /**
   * 显示节点设备信息（具体某个罐，侧线，进出厂点等）
   * emit to viewController
   */
  $scope.deviceElemOnClick = function(elem) {
  	console.log(elem);
    $scope.$emit('show-panel', elem);
  };

  $scope.deviceDeleteOnClick = function(elem, areaTypeId, areaCode, elemIndex, deviceName,deviceList,nodeAlias) {
    $scope.$emit('device-delete-on-click', {elem: elem, areaTypeId: areaTypeId, areaCode: areaCode, elemIndex:elemIndex,deviceName: deviceName,deviceList:deviceList,nodeAlias:nodeAlias});
    console.log(elem);
  };

  $scope.deviceAddOnClick = function(areaTypeId, areaCode, deviceName) {
    $scope.$emit('device-add-on-click', {areaTypeId : areaTypeId, areaCode : areaCode, deviceName : deviceName});
  };

});