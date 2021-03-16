var divCount=$('div[class="window"]').length;
$('.btnClass').on('click',function(){
	var stepNum=$('#stepNum').val();
	if(stepNum==''||typeof(stepNum)=='undefined'){
		$.zxxbox.remind("请输入Step ！");
		return false;
	}
	if(stepNum>divCount){
		$.zxxbox.remind("Step输入有误，请重新输入 ！");
		return false;
	}
	initStep();
	for(var i=1;i<=stepNum;i++){
		$('#chartWindow'+i).css('background-color','#88c4e0');
	}
	
});
function initStep(){
	for(var i=1;i<=divCount;i++){
		$('#chartWindow'+i).css('background-color','#cecece');
	}
}



jsPlumb.ready(function () {

    var color = "#cecece";

    var instance = jsPlumb.getInstance({
        // notice the 'curviness' argument to this Bezier curve.  the curves on this page are far smoother
        // than the curves on the first demo, which use the default curviness value.
        Connector: [ "Flowchart", { curviness:50 } ],
        DragOptions: { cursor: "pointer", zIndex: 2000 },
        PaintStyle: { stroke: color, strokeWidth: 2 },
        EndpointStyle: { radius: 1, fill: color },
        HoverPaintStyle: {stroke: "#ec9f2e" },
        EndpointHoverStyle: {fill: "#ec9f2e" },
        Container: "canvas"
    });

    // suspend drawing and initialise.
    instance.batch(function () {
        // declare some common values:
        var arrowCommon = { foldback: 1.0, fill: color, width: 10 },
        // use three-arg spec to create two different arrows with the common values:
            overlays = [
                [ "Arrow", { location: 1.0 }, arrowCommon ]
            ];

        // add endpoints, giving them a UUID.
        // you DO NOT NEED to use this method. You can use your library's selector method.
        // the jsPlumb demos use it so that the code can be shared between all three libraries.
        var windows = jsPlumb.getSelector(".chart-demo .window,.window3,.window2");
        for (var i = 0; i < windows.length; i++) {
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

        instance.connect({uuids: ["chartWindow1-right", "chartWindow2-left" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow2-right", "chartWindow3-left" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow3-right", "chartWindow4-left" ], overlays: overlays, detachable: true, reattach: true});
        instance.connect({uuids: ["chartWindow4-right", "chartWindow5-left" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow4-right", "chartWindow6-left" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow5-right", "chartWindow8-left" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow6-bottom", "chartWindow9-left" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow7-right", "chartWindow8-left" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow9-top", "chartWindow7-bottom" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow8-right", "chartWindow10-right" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow9-right", "chartWindow10-right" ], overlays: overlays});
        instance.connect({uuids: ["chartWindow10-top", "chartWindow11-bottom" ], overlays: overlays});

        instance.draggable(windows);
    });

    jsPlumb.fire("jsPlumbDemoLoaded", instance);
});