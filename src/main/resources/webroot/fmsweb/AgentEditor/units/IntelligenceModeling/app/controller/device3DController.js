app.controller('device3DController', function($scope, $interval){

  $scope.url="component_device_3dmodel.html";
  $scope.nodeTypeId = 0;
  $scope.deviceTypeId = 0;
  $scope.modelName = "";
  $scope.modelUrl = "";

  /**
   * 获取该设备类型和子类型
   * broadcast from devicePanelController
   */
  $scope.$on('show-device-model', function(event, obj){
	//下面的nodeTypeId From devicePanelcontroller,是前台自定义的，不属于后台返回数据，用于流程判断，
    $scope.nodeTypeId = obj['nodeTypeId'];
    console.log(obj)
    switch (obj['nodeTypeId']) {
      case 1: //侧线
        $scope.url="component_device_3dmodel.html";
        $scope.modelName = "SideLine";
        $scope.modelUrl = "../3dModel/sideline";
        break;
      case 2: //罐
      	console.log('罐类型编码',obj['tankTypeCode'])
        $scope.url="component_device_3dmodel.html";
        $scope.modelName = "Tank#"+obj['tankTypeCode'];
        if (obj['tankTypeCode'] < 4) {
          console.log("It is a tank " + obj['tankTypeCode']);
          $scope.modelUrl = "../3dModel/tank_" + obj['tankTypeCode'];
        } else {
          $scope.modelUrl = "../3dModel/tank_0";
        }
        break;
      case 5: //进出厂点
        $scope.url = 'component_device_none3dmodel.html';
        return;
      default:
        return;
    }
    // Model Render Start
    $scope.deviceComponent = new OBJLoader2Example( document.getElementById( 'panel-3d-canvas' ) );
    var render = function () {
      requestAnimationFrame( render );
      $scope.deviceComponent.render();
    };

    var resizeWindow = function () {
      $scope.deviceComponent.resizeDisplayGL();
    };

    window.addEventListener( 'resize', resizeWindow, false );

    console.log( 'Starting initialisation phase...' );
    $scope.deviceComponent.initGL();
    $scope.deviceComponent.resizeDisplayGL();
    $scope.deviceComponent.initContent($scope.modelName, $scope.modelUrl);

    render();
    // FIXME: ng-hide会把canvas的size变成0，ng-show之后不会触发resizeWindow。蠢办法0.1秒刷一次
    // TODO: Cancel 是防止多次触发interval（我也不知道会不会，测一下）
    if ($scope.timer) {$interval.cancel($scope.timer)}
    $scope.timer = $interval(resizeWindow, 100, 0, false);

    // Model Render End
  });

  $scope.panelCollapse = function(){
    $scope.panel3dCollapse = !$scope.panel3dCollapse;
    if($scope.deviceComponent) {
      $scope.deviceComponent.resizeDisplayGL();
    }
  };

  var OBJLoader2Example = (function () {

    var Validator = THREE.LoaderSupport.Validator;

    function OBJLoader2Example( elementToBindTo ) {
      this.renderer = null;
      this.canvas = elementToBindTo;
      this.aspectRatio = 1;
      this.recalcAspectRatio();

      this.scene = null;
      this.cameraDefaults = {
        posCamera: new THREE.Vector3( 100.0, 60.0, 100.0 ),
        posCameraTarget: new THREE.Vector3( 0.0, 5.0, 0.0 ),
        near: 0.1,
        far: 10000,
        fov: 7
      };
      this.camera = null;
      this.cameraTarget = this.cameraDefaults.posCameraTarget;

      this.controls = null;
    }

    OBJLoader2Example.prototype.initGL = function () {
      this.renderer = new THREE.WebGLRenderer( {
        canvas: this.canvas,
        antialias: true,
        autoClear: true
      } );
      this.renderer.setClearColor( 0x050505 );

      this.scene = new THREE.Scene();

      this.camera = new THREE.PerspectiveCamera( this.cameraDefaults.fov, this.aspectRatio, this.cameraDefaults.near, this.cameraDefaults.far );
      this.resetCamera();
      this.controls = new THREE.OrbitControls( this.camera, this.renderer.domElement );
      this.controls.target = this.cameraDefaults.posCameraTarget;

      var ambientLight = new THREE.AmbientLight( 0x404040 );
      var directionalLight1 = new THREE.DirectionalLight( 0xC0C090 );
      var directionalLight2 = new THREE.DirectionalLight( 0xC0C090 );

      directionalLight1.position.set( -100, -50, 100 );
      directionalLight2.position.set( 100, 50, -100 );

      this.scene.add( directionalLight1 );
      this.scene.add( directionalLight2 );
      this.scene.add( ambientLight );

      var helper = new THREE.GridHelper( 1200, 60, 0xFF4444, 0x404040 );
      this.scene.add( helper );
    };

    OBJLoader2Example.prototype.initContent = function (modelName, filePath) {

      //this._reportProgress( { detail: { text: 'Loading: ' + modelName } } );

      var scope = this;
      var objLoader = new THREE.OBJLoader2();
      var callbackOnLoad = function ( event ) {
        scope.scene.add( event.detail.loaderRootNode );
        console.log( 'Loading complete: ' + event.detail.modelName );
        scope._reportProgress( { detail: { text: '' } } );
      };

      var onLoadMtl = function ( materials ) {
        objLoader.setModelName( modelName );
        objLoader.setMaterials( materials );
        objLoader.getLogger().setDebug( true );
        objLoader.load( filePath + '.obj', callbackOnLoad, null, null, null, false );
      };
      objLoader.loadMtl( filePath + '.mtl', null, onLoadMtl );
    };

    OBJLoader2Example.prototype._reportProgress = function( event ) {
      var output = Validator.verifyInput( event.detail.text, '' );
      console.log( 'Progress: ' + output );
      document.getElementById( 'panel-3d-feedback' ).innerHTML = output;
    };

    OBJLoader2Example.prototype.recalcAspectRatio = function () {
      this.aspectRatio = ( this.canvas.offsetHeight === 0 ) ? 1 : this.canvas.offsetWidth / this.canvas.offsetHeight;
    };

    OBJLoader2Example.prototype.resizeDisplayGL = function () {

      this.recalcAspectRatio();
      this.renderer.setSize( this.canvas.offsetWidth, this.canvas.offsetHeight, false );

      this.updateCamera();
    };

    OBJLoader2Example.prototype.resetCamera = function () {
      this.camera.position.copy( this.cameraDefaults.posCamera );
      this.cameraTarget.copy( this.cameraDefaults.posCameraTarget );

      this.updateCamera();
    };

    OBJLoader2Example.prototype.updateCamera = function () {
      this.camera.aspect = this.aspectRatio;
      this.camera.lookAt( this.cameraTarget );
      this.camera.updateProjectionMatrix();
    };

    OBJLoader2Example.prototype.render = function () {
      if ( ! this.renderer.autoClear ) this.renderer.clear();
      this.controls.update();
      this.renderer.setClearColor(0xffffff);
      this.renderer.render( this.scene, this.camera );
    };

    return OBJLoader2Example;

  })();
});
