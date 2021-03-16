/*$(function(){
	$.ajax({
		type: "post",
		dataType: "json",
		async: "false",
		url: "jsplumb.json",
		data: {},
		complete: function() {
		},
		success: function(data) {
			if(typeof(data.DATA) != "undefined") {
				
			}
			console.log(data.DATA);
		},
		error: function(xhr, err) {
			$.zxxbox.remind("请求数据发生错误,请联系管理员" + err);
			return false;
		}
	});
});*/
var data = {
	"DATA": [
		{ "name": "采购订单", "left": "1em", "top": "10em" },
		{ "name": "盖章", "left": "12em", "top": "10em" },
		{ "name": "签署", "left": "23em", "top": "10em" },
		{ "name": "资金计划(1/2)", "left": "34em", "top": "10em" },
		{ "name": "预付款", "left": "46em", "top": "7em" },
		{ "name": "贷款结算(2/3)", "left": "46em", "top": "13em" },
		{ "name": "贷款付款申请", "left": "56em", "top": "11em" },
		{ "name": "付款财务记账", "left": "70em", "top": "7em" },
		{ "name": "结算财务记账", "left": "70em", "top": "17em" },
		{ "name": "供应商评分", "left": "12em", "top": "20em" },
		{ "name": "合同关闭", "left": "12em", "top": "15em" }
	],
	"CONNECT": [
		{ "start": "1", "end": "2", "startAnchor": "right", "endAnchor": "left" },
		{ "start": "2", "end": "3", "startAnchor": "right", "endAnchor": "left" },
		{ "start": "3", "end": "4", "startAnchor": "right", "endAnchor": "left" },
		{ "start": "4", "end": "5", "startAnchor": "right", "endAnchor": "left" },
		{ "start": "4", "end": "6", "startAnchor": "right", "endAnchor": "left" },
		{ "start": "5", "end": "8", "startAnchor": "right", "endAnchor": "left" },
		{ "start": "6", "end": "9", "startAnchor": "bottom", "endAnchor": "left" },
		{ "start": "7", "end": "8", "startAnchor": "right", "endAnchor": "left" },
		{ "start": "9", "end": "7", "startAnchor": "top", "endAnchor": "bottom" },
		{ "start": "8", "end": "10", "startAnchor": "right", "endAnchor": "right" },
		{ "start": "9", "end": "10", "startAnchor": "right", "endAnchor": "right" },
		{ "start": "10", "end": "11", "startAnchor": "top", "endAnchor": "bottom" }
	]
}

/********************显示JSPlumb************************/
//只显示一次
var boo = false;
//模块个数
var divCount=0;
$('#showJSPlumb').on('click', function() {
	if(!boo) {
		boo=true;
		var divData = data.DATA;
		var divConn = data.CONNECT;

		$.each(divData, function(index, val) {
			var idIndex = index + 1;
			$('.chart-demo').append('<div class="window" id="chartWindow' + idIndex + '">' + val.name + '</div>');
			$('#chartWindow' + idIndex).css({ "left": val.left, "top": val.top });
		});
		divCount = $('div[class="window"]').length;
		jsPlumb.ready(function() {

			var color = "#cecece";

			var instance = jsPlumb.getInstance({
				// notice the 'curviness' argument to this Bezier curve.  the curves on this page are far smoother
				// than the curves on the first demo, which use the default curviness value.
				Connector: ["Flowchart", { curviness: 50 }],
				DragOptions: { cursor: "pointer", zIndex: 2000 },
				PaintStyle: { stroke: color, strokeWidth: 2 },
				EndpointStyle: { radius: 1, fill: color },
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

				$.each(divConn, function(index, val) {
					instance.connect({ uuids: ["chartWindow" + val.start + "-" + val.startAnchor, "chartWindow" + val.end + "-" + val.endAnchor], overlays: overlays });
				});

				instance.draggable(windows);
			});

			jsPlumb.fire("jsPlumbDemoLoaded", instance);
		});
	}
});

/*******************步骤变色*******************/

$('.btnClass').on('click', function() {
	var stepNum = $('#stepNum').val();
	if(stepNum == '' || typeof(stepNum) == 'undefined') {
		$.zxxbox.remind("请输入Step ！");
		return false;
	}
	if(stepNum > divCount) {
		$.zxxbox.remind("Step输入有误，请重新输入 ！");
		$('#stepNum').val('');
		return false;
	}
	initStep();
	for(var i = 1; i <= stepNum; i++) {
		$('#chartWindow' + i).css('background-color', '#88c4e0');
	}

});

function initStep() {
	for(var i = 1; i <= divCount; i++) {
		$('#chartWindow' + i).css('background-color', '#cecece');
	}
}