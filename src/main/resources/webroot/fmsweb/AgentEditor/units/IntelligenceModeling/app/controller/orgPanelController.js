app.controller('orgPanelController', function($scope){

  $scope.url = "panel_org_info.html";
  $scope.org = {};

  $scope.$on('org-detail-info', function(event, obj){
    $scope.org = obj;
  });

});