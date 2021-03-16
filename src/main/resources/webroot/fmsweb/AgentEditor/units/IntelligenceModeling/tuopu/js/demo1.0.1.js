////第几步骤
//var stepNum = 11;
////计时器 跑马灯效果
//var timerNum = 1;
////原本颜色
//var origColorArr = [];
//$.each($(".chart-demo .window,.window3,.window2"), function(index, val) {
//	var idIndex = index + 1;
//	origColorArr[index] = $('#chartWindow' + idIndex).css("background-color");
//});
////setInterval
//var int1 = '';
//
//$('.startBox').on("click", function() {
//	stepNum = $("#stepNum").val();
//	int1 = setInterval("changColor()", 1000);
//});
//$('.stopBox').on("click", function() {
//	clearInterval(int1);
//});

//function changColor() {
//
//	var preIndex = 0;
//	if(timerNum > stepNum) {
//		timerNum = 1;
//		preIndex = stepNum;
//	} else {
//		preIndex = timerNum - 1;
//	}
//
//	//初始化所有默认颜色
//	$('#chartWindow' + preIndex).css('background-color', origColorArr[preIndex - 1]);
//	$('#chartWindow' + timerNum).css('background-color', 'red');
//	timerNum++;
//}

jsPlumb.ready(function() {

	var color = "#94ccff";

	var instance = jsPlumb.getInstance({
		// notice the 'curviness' argument to this Bezier curve.  the curves on this page are far smoother
		// than the curves on the first demo, which use the default curviness value.
		Connector: ["Flowchart", { curviness: 50 }],
		DragOptions: { cursor: "pointer", zIndex: 2000 },
		PaintStyle: { stroke: color, strokeWidth: 2 },
		EndpointStyle: { radius: 1, fill: 'transparent' },
		HoverPaintStyle: { stroke: "#ec9f2e" },
		EndpointHoverStyle: { fill: "#ec9f2e" },
		Container: "canvas"
	});

	// suspend drawing and initialise.
	instance.batch(function() {
		// declare some common values:
		var arrowCommon = { foldback: 1.0, fill: color, width: 10 },
			// use three-arg spec to create two different arrows with the common values:
			overlays = [
				["Arrow", { location: 1.0 }, arrowCommon]
			];

		// add endpoints, giving them a UUID.
		// you DO NOT NEED to use this method. You can use your library's selector method.
		// the jsPlumb demos use it so that the code can be shared between all three libraries.
		var windows = jsPlumb.getSelector(".chart-demo .window,.window3,.window2");
		for(var i = 0; i < windows.length; i++) {
			instance.addEndpoint(windows[i], {
				uuid: windows[i].getAttribute("id") + "-right",
				anchor: "Right",
				maxConnections: -1
			});
			instance.addEndpoint(windows[i], {
				uuid: windows[i].getAttribute("id") + "-left",
				anchor: "Left",
				maxConnections: -1
			});

			instance.addEndpoint(windows[i], {
				uuid: windows[i].getAttribute("id") + "-top",
				anchor: "Top",
				maxConnections: -1
			});
			instance.addEndpoint(windows[i], {
				uuid: windows[i].getAttribute("id") + "-bottom",
				anchor: "Bottom",
				maxConnections: -1
			});
		}

		var mdsUrlPost = 'http://10.238.255.103:32065/FactoryModelService/icNodetopMains/';// + Z6MP7010
		var mdsUrlPostr = '/icNodetopDtls?$sCodeList=';
    var mdsUrlPostl = '/icNodetopDtls?$tCodeList=';
    var nodeCode = localStorage.getItem('deviceNodeCode');
		//获取左侧装置
		$.ajax({
			url: mdsUrlPost + nodeCode.substr(0,8) + mdsUrlPostl + nodeCode,
			async: false,
			type: 'get',
			success: function(result, status, xhr) {

				result = JSON.stringify(result).replace(/\</g, "&lt;").replace(/\>/g, "&gt;");
				var data = $.ET.toObjectArr(result);
				// 左侧装置

				for (var x = 0; x < 3; x++) {
					if (x < data.length) {
            $('#chartWindowL' + (x + 1)).children('b').text(data[x]['sNodeCode']);
						instance.connect({ uuids: ['chartWindowL'+(x + 1)+"-right","chartWindowT1-left"], paintStyle:{lineWidth:10,stroke:'black',outlineColor:"white",outlineWidth:10}, overlays:[[ "Arrow", {location:1 }] ]});
					} else {
						$('#chartWindowL' + (x+1)).hide();
					}
				}

			}
		})

		// 获取右侧
		$.ajax({
			url: mdsUrlPost + nodeCode.substr(0,8) + mdsUrlPostr + nodeCode,
			async: false,
			type: 'get',
			success: function(result, status, xhr) {

				result = JSON.stringify(result).replace(/\</g, "&lt;").replace(/\>/g, "&gt;");
				var data = $.ET.toObjectArr(result);
				console.log(data)
				// 右侧装置

				for (var y = 0; y < 3; y++) {
					if (y < data.length) {
						$('#chartWindowR' + (y + 1)).children('b').text(data[y]['tNodeCode']);
						instance.connect({ uuids: ["chartWindowT1-right",'chartWindowR'+(y + 1)+"-left"], paintStyle:{lineWidth:10,stroke:'black',outlineColor:"white",outlineWidth:10},overlays:[[ "Arrow", {location:1 }] ]});
					} else {
						$('#chartWindowR' + (y+1)).hide();
					}
				}
			}
		})

// 		 $.ajax({
// 		        url: mdsUrlPost + 'factory/:factoryId/material/:materialId/rts',
// 		        async: false,
// 		        type: 'get',
// 		        success: function (result,status,xhr) {
//
// 			          result = JSON.stringify(result).replace(/\</g,"&lt;").replace(/\>/g,"&gt;");
//           			  var data = $.ET.toObjectArr(result);
//           			  console.log(data)
//           			  for(var i = 0;i<data.length;i++){
//           			  	var x = $('[data-id='+data[i].sourceType+''+data[i].source+']').attr('id')
//           			  	var y = $('[data-id='+data[i].targetType+''+data[i].target+']').attr('id')
//           			  	console.log($('[data-id='+data[i].sourceType+''+data[i].source+']').data('entername'))
//           			  	var label = $('[data-id='+data[i].sourceType+''+data[i].source+']').data('sidename')
//           			  	instance.connect({ uuids: [x+"-right", y+"-left"], overlays:[[ "Label", { label:label, location:0.1, id:"myLabel" } ],[ "Arrow", {location:1 },arrowCommon] ]});
//           			  	if($('[data-id='+data[i].sourceType+''+data[i].source+']').data('entername')){
// //        			  		var LeftOpa = $('[data-id='+data[i].sourceType+''+data[i].source+']').attr('id')
// 							var enterN = $('[data-id='+data[i].sourceType+''+data[i].source+']').data('entername')
// 							var leftId = x+'_dis';
//           			  		instance.connect({ uuids: [leftId+"-right", x+"-left"],anchors:[ [ 1, 1, 0, 1 ]],overlays:[[ "Label", { label:enterN, location:0.1, id:"myLabel" } ],[ "Arrow", {location:1},arrowCommon ] ]});
//           			  	}
//           			  }
// 		        }
// 		    })
		// instance.connect({ uuids: ["chartWindowL3-right", "chartWindowT1-left"],paintStyle:{lineWidth:10,stroke:'white',outlineColor:"white",outlineWidth:10}, overlays:["Arrow"]});
		// instance.connect({ uuids: ["chartWindowT1-right", "chartWindowR2-left"],paintStyle:{lineWidth:10,stroke:'white',outlineColor:"white",outlineWidth:10}, overlays:["Arrow"]});
		// instance.connect({ uuids: ["chartWindowJIN-right", "chartWindowT3-left"],paintStyle:{lineWidth:10,stroke:'#848789',outlineColor:"red",outlineWidth:10}, overlays:["Arrow",[ "Label", { label:"", location:0.1, id:"myLabel9" } ] ]});
		// instance.connect({ uuids: ["chartWindowT2-right", "chartWindowCHU-left"],paintStyle:{lineWidth:10,stroke:'#848789',outlineColor:"red",outlineWidth:10}, overlays:["Arrow",[ "Label", { label:"罐付出厂 34t/h", location:0.9, id:"myLabel10" } ] ]});
		// instance.connect({ uuids: ["chartWindowL1-right", "chartWindowCHU-left"],paintStyle:{lineWidth:10,stroke:'#848789',outlineColor:"red",outlineWidth :10}, overlays:["Arrow",[ "Label", { label:"装置付出厂 10t/h", location:0.7, id:"myLabel11" } ] ]});

		instance.draggable(windows);
	});

	jsPlumb.fire("jsPlumbDemoLoaded", instance);
});
