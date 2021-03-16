
//***********控制树************//
$(function () {
    $('.trees li:has(ul)').addClass('parent_li').find(' > span').attr('title', '关闭');
    $('.trees li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li').find(' > ul > li');
        if (children.is(":visible")){
            children.hide('fast');
            $(this).attr('title', '展开').find(' > i').addClass('icon-plus-signsf').removeClass('icon-minus-signsf');              
        } else {
            children.show('fast');
            $(this).attr('title', '关闭').find(' > i').addClass('icon-minus-signsf').removeClass('icon-plus-signsf');
        }
        e.stopPropagation();
    });
});
//***********控制树结束************//

//***********拖动树************//
//位号节点拖动事件
function allowDrop(ev){
	ev.preventDefault();
	var node;
}
function allowDrop2(ev){
	ev.preventDefault();
	var node;
}

function drag(ev){	
	ev.dataTransfer.setData("Texthtml",$(ev.target).parent().parent().html());			
	ev.dataTransfer.setData("Textid",$(ev.target).parent().parent().id);		
	node = $(ev.target);
	node.addClass('dragNode');
}
	
//输入
var colorIndex = 0;

function drop(ev){	
	
	//随机颜色数组
	var colorArr = ["#3D90CD","#42BCC8","#E26A73","#00BEF1","#8882BE","#F8B086"];
	if(colorIndex < colorArr.length){
		colorIndex++;
	}else{
		colorIndex = colorArr.length;
	}
	
	ev.preventDefault();
	var data=ev.dataTransfer.getData("Texthtml");
	var dataid=ev.dataTransfer.getData("Textid");
	str = node.text();

	if (str.substring(0,2) == node.siblings().text().substring(0,2)) {
    	if (node.hasClass('inputNode0')) {
		    //不执行事件(输入与输出是相互的)
    	} else {		
			if ($('#inputBox').children().length == 0) {
				$('#inputBox').append("<li><span class='circle' style='background:#3D90CD;'><i>CII-FFEE5501</i></span><span style='#3D90CD'>————></span></li>"
					+ "<li><span class='circle' style='background:#42BCC8;'><i>CII-FFEE5501</i></span><span style='color:#42BCC8'>————></span></li>"
					+ "<li><span class='circle' style='background:#E26A73;'><i>CII-FFEE6601</i></span><span style='color:#E26A73'>————></span></li>"
					+ "<li><span class='circle' style='background:#00BEF1;'><i>CII-FFEEff01</i></span><span style='color:#00BEF1'>————></span></li>"
					+ "<li><span class='circle' style='background:#F8B086;'><i>CII-FIIEE3301</i></span><span style='color:#F8B086'>————></span></li>"
					+ "<li><span class='circle' style='background:#8882BE;'><i>CII-FFPP3301</i></span><span style='color:#8882BE'>————></span></li>").show('slow');
				node.addClass('inputNode0');
			} else {
				$('.inputNode0').removeClass('inputNode0');
				$('#inputBox').children().remove();
				$('#inputBox').append("<li><span class='circle' style='background:#3D90CD;'><i>CII-FFEE5501</i></span><span style='#3D90CD'>————></span></li>"
					+ "<li><span class='circle' style='background:#42BCC8;'><i>CII-FFEE5501</i></span><span style='color:#42BCC8'>————></span></li>"
					+ "<li><span class='circle' style='background:#E26A73;'><i>CII-FFEE6601</i></span><span style='color:#E26A73'>————></span></li>"
					+ "<li><span class='circle' style='background:#00BEF1;'><i>CII-FFEEff01</i></span><span style='color:#00BEF1'>————></span></li>"
					+ "<li><span class='circle' style='background:#F8B086;'><i>CII-FIIEE3301</i></span><span style='color:#F8B086'>————></span></li>"
					+ "<li><span class='circle' style='background:#8882BE;'><i>CII-FFPP3301</i></span><span style='color:#8882BE'>————></span></li>").show('slow');
				node.addClass('inputNode0');
			}
    	}
    } else {	
    	if (node.hasClass('inputNode0')) {
    		//不执行事件(输入与输出是相互的)
    	} else{
    		$("#inputBox").append("<li><span class='circle' style='background:"+colorArr[colorIndex]+";'><i>CII-FFPP3301</i></span><span style='color:"+colorArr[colorIndex]+"'>————></span></li>").show('slow');
		    node.addClass('inputNode0');
    	}
	}  
	
	node = '';
	stepBox01();stepBox02();stepBox03();stepBox04();stepBox05();demoDele();
}
	
//输出
var colorIndex2 = 0;

function drop2(ev){
	
	var colorArr2 = ["#3D90CD","#42BCC8","#E26A73","#00BEF1","#8882BE","#F8B086"];
	if(colorIndex2 < colorArr2.length){
		colorIndex2++;
	}else{
		colorIndex2 = colorArr2.length;
	}
	
	ev.preventDefault();
	var data=ev.dataTransfer.getData("Texthtml");
	var dataid=ev.dataTransfer.getData("Textid");			
//	str = node.text();
	
	if (node.text().substring(0,2) == node.siblings().text().substring(0,2)) {
		if (node.hasClass('inputNode0')) {
			//不执行事件(输入与输出是相互的)
		} else{
			if ($('#outputBox').children().length == 0) {
				$('#outputBox').append("<li><span class='circle' style='background:#3D90CD;'><i>CII-FFEE5501</i></span></li>"
					+ "<li><span class='circle' style='background:#42BCC8;'><i>CII-FFEE5501</i></span></li>"
					+ "<li><span class='circle' style='background:#E26A73;'><i>CII-FFEE6601</i></span></li>"
					+ "<li><span class='circle' style='background:#00BEF1;'><i>CII-FFEEff01</i></span></li>"
					+ "<li><span class='circle' style='background:#F8B086;'><i>CII-FIIEE3301</i></span></li>"
					+ "<li><span class='circle' style='background:#8882BE;'><i>CII-FFPP3301</i></span></li>");
				node.addClass('outputNode0');
			} else{
				$('.outputNode0').removeClass('outputNode0');
				$('#outputBox').children().remove();
				$('#outputBox').append("<li><span class='circle' style='background:#3D90CD;'><i>CII-FFEE5501</i></span></li>"
					+ "<li><span class='circle' style='background:#42BCC8;'><i>CII-FFEE5501</i></span></li>"
					+ "<li><span class='circle' style='background:#E26A73;'><i>CII-FFEE6601</i></span></li>"
					+ "<li><span class='circle' style='background:#00BEF1;'><i>CII-FFEEff01</i></span></li>"
					+ "<li><span class='circle' style='background:#F8B086;'><i>CII-FIIEE3301</i></span></li>"
					+ "<li><span class='circle' style='background:#8882BE;'><i>CII-FFPP3301</i></span></li>");
			}
		}
	} else {	
		if (node.hasClass('inputNode0')) {
			//不执行事件(输入与输出是相互的)
		} else {
			$("#outputBox").append("<li><span class='circle' style='background:"+colorArr2[colorIndex2]+";'><i>CII-FFPP3301</i></span><span style='color:"+colorArr2[colorIndex2]+"'>————></span></li>").show('slow');
		    node.addClass('inputNode0');
		}		
	}
	
	node = '';
	stepBox01();stepBox02();stepBox03();stepBox04();stepBox05();demoDele();
}   
	
////////////////////////////////////////////////

    //获取首页点击的装置，使本页面的树展开到具体的装置
	var installHtml2 = localStorage.getItem("installType");
//	console.log(installHtml2);
	//获取到装置那一级
	var device = $(".device");
//	console.log(device);
	for(var i = 0; i < device.length; i++){		
	    if(device.eq(i).html() == installHtml2){
	    	console.log(device.eq(i).html());
	        device.eq(i).css("background","red");
	        device.eq(i).parent().parent().css({"display":"block"});
	        device.eq(i).parent().append("<i class='glyphicon glyphicon-ok posiAbsolu treeTure'></i>")
	        device.eq(i).parent().parent().parent().children("span").eq(i).css("background","red");
	        device.eq(i).parent().parent().parent().parent().parent().children("span").eq(i).css("background","red");
	        //显示参数左侧
	        $('.contentLeft .params .paramsLeft').children("div").remove();
	        $(this).parent('li').append("<i class='glyphicon glyphicon-ok posiAbsolu treeTure'></i>");
		    $('.contentLeft .params .paramsLeft').append("<div style='display: none;'>"
	      	                                                +"<span>管段</span>"
															+"<span>设备</span>"
															+"<span>阀门</span>"
															+"<span>三通</span>"
															+"<span>盲板</span>"
															+"<span>采样点</span>"
														+"</div>");
			$('.contentLeft .params .paramsLeft').find('div').show('slow');
			
	        $('.contentLeft .params .paramsLeft div span').click(function(){
		    	$('.contentLeft .params .paramsRight').children().remove();
		    	$(this).css({'color':'black'}).siblings().css({'color':'rgb(59, 144, 206)'});
		    	var html0 = $(this).html();
			    $('.contentLeft .params .paramsRight').append("<ul style='display: none;'><li draggable='true' ondragstart='drag(event)'>"+html0+"列表</li><li draggable='true' ondragstart='drag(event)'>"+html0+"1</li><li draggable='true' ondragstart='drag(event)'>"+html0+"2</li><li draggable='true' ondragstart='drag(event)'>"+html0+"3</li><li draggable='true' ondragstart='drag(event)'>"+html0+"4</li><li class='tab' draggable='true' ondragstart='drag(event)'>"+html0+"5</li></ul>");	
			    $('.contentLeft .params .paramsRight').find('ul').show('slow');
			    $('.contentLeft .params .paramsRight ul').find('li').css({'color':'black'});
			    $('.contentLeft .params .paramsRight ul').find('li:first-child').css({'font-weight':'bold'});
	    	})
	    }
	}

    //点击装置
    $('.device').click(function(){
		$('.contentLeft .params .paramsLeft').children("ul").remove();
		$('.contentLeft .params .paramsLeft').children('div').remove();
		$('.contentLeft .params .paramsRight').children().remove();
		$("#inputBox").children().remove();
		$("#outputBox").children().remove();
		$(this).parent('li').append("<i class='glyphicon glyphicon-ok posiAbsolu treeTure'></i>");
      	$('.contentLeft .params .paramsLeft').append("<div style='display: none;'>"
      	                                                +"<span>管段</span>"
														+"<span>设备</span>"
														+"<span>阀门</span>"
														+"<span>三通</span>"
														+"<span>盲板</span>"
														+"<span>采样点</span>"
													+"</div>");
		$('.contentLeft .params .paramsLeft').find('div').show('slow');
		
		//点击参数左边所有的span												
	    $('.contentLeft .params .paramsLeft div span').click(function(){
	    	$('.contentLeft .params .paramsRight').children().remove();
	    	$(this).css({'color':'black'}).siblings().css({'color':'rgb(59, 144, 206)'});
	    	var html0 = $(this).html();
		    $('.contentLeft .params .paramsRight').append("<ul style='display: none;'><li draggable='true' ondragstart='drag(event)'>"+html0+"列表</li><li draggable='true' ondragstart='drag(event)'>"+html0+"1</li><li draggable='true' ondragstart='drag(event)'>"+html0+"2</li><li draggable='true' ondragstart='drag(event)'>"+html0+"3</li><li draggable='true' ondragstart='drag(event)'>"+html0+"4</li><li class='tab' draggable='true' ondragstart='drag(event)'>"+html0+"5</li></ul>");	
		    $('.contentLeft .params .paramsRight').find('ul').show('slow');
		    $('.contentLeft .params .paramsRight ul').find('li').css({'color':'black'});
		    $('.contentLeft .params .paramsRight ul').find('li:first-child').css({'font-weight':'bold'});
    	})
    })

    //点击装置下所有的span
    $('.jQ_treeBox ul li ul li ul li ul li ul li').find('span').click(function () {
		$('.contentLeft .params .paramsLeft').children('div').remove();
		$('.contentLeft .params .paramsLeft').children('ul').remove();
		$('.contentLeft .params .paramsRight').children().remove();	
		$("#inputBox").children().remove();
		$("#outputBox").children().remove();
		$(this).parent('li').append("<i class='glyphicon glyphicon-ok posiAbsolu treeTure'></i>");
		var html2 = $(this).html();
	    $('.contentLeft .params .paramsLeft').append("<ul style='display: none;'><li style='font-weight:bold'>"+html2+"列表</li><li>"+html2+"1</li><li>"+html2+"2</li><li>"+html2+"3</li><li>"+html2+"4</li><li>"+html2+"5</li></ul>");
	    $('.contentLeft .params .paramsLeft').find('ul').show('slow');
	    
	    //点击参数左边所有的li
	    $('.contentLeft .params .paramsLeft ul li').not(':first-child').click(function(){
	    	$('.contentLeft .params .paramsRight').children().remove();
	    	$(this).css({'color':'black'}).siblings().css({'color':'rgb(59, 144, 206)'});
	    	$('.contentLeft .params .paramsRight').append("<ul style='display: none;'><li style='font-weight:bold'>度量指标</li><li draggable='true' ondragstart='drag(event)'>温度</li><li draggable='true' ondragstart='drag(event)'>压力</li><li draggable='true' ondragstart='drag(event)'>高度</li><li draggable='true' ondragstart='drag(event)'>热值</li><li draggable='true' ondragstart='drag(event)'>密度</li><li draggable='true' ondragstart='drag(event)'>水分</li><li draggable='true' ondragstart='drag(event)'>色度</li></ul>");
	    	$('.contentLeft .params .paramsRight').find('ul').show('slow');
	    	$('.contentLeft .params .paramsRight ul').find('li').css({'color':'black'});	    	
	    });
	})

////////////////////////////////////////////////

	//创建收率预测模型（第一步）
	$('.jQ_stepOneA').click(function(){
		var nameInput = $(this).parent().prev().find('.jQ_nameInput').val();
		var inputLi = 0;
		var outputLi = 0;
		if($('#inputBox').find('li').length > 0){
			inputLi = 1;
		}
		if($('#outputBox').find('li').length > 0){
			outputLi = 1;
		}
		if(inputLi == 0){
			alert("请配置输入输出项！");
		}else{
			if(nameInput == ''|| nameInput == null){
				alert('请补全表单项！');
			}else{
				var timer1 = setInterval(function(){
					clearInterval(timer1);
					$('.jQ_stepTwoA').parent().next('.stepMask').slideUp();							
				},1000);
		    }
	    }
	})
	
///////////////////////////////////////////////////

	//时间格式转换成数字格式
	function getparam(str){
	    var arr = str.split(' ');
	    var arr01 = arr[0].split("-");
	    var arr02 = arr[1].split(":");
	    var newArr = arr01.concat(arr02);
	    var newStr = newArr.join('');
	    return newStr;
	}
	
	//采集数据（第二步）
	$('.jQ_stepTwoA').click(function(){
		var startTime = $(this).parent().prev().find('.startTime1').val();
		var endTime = $(this).parent().prev().find('.endTime1').val();
		var stepLong = $(this).parent().prev().find('.stepLong').val();
		
		if(stepLong == null || stepLong == '' || stepLong == ' ' || endTime == null || startTime == null){
		    alert('请补全表单！');
		}else{			
			var time2 = setInterval(function(){							
				clearInterval(time2);
				$('.jQ_stepThreeA').parent().next('.stepMask').slideUp();
			},1000);						
	    }
	})
	
///////////////////////////////////////////////////   
	
	//工况识别（第三步）
	var numbers;
	$('.jQ_stepThreeA').click(function(){
		var stage = $(this).parent().prev().find('.stage').val();
		if(stage == null || stage == '' || stage == ' '){
		    alert('请补全表单项！');
		}else{
			numbers = parseInt(stage);
            $('.jQstage_stepThree').children().remove();
			var demoBox = $('.jQstage_stepThree').find('.jQ_dragCon');
			var demos = 1;
			for(var i = 0; i < demoBox.length; i++){
				if($(demoBox[i]).find('table').length == 0){
					demos = 0;
				}else{}
			}
			if(demos == 1){							
				setTimeout(function(){
					for(var i = 0; i < numbers; i++){
						$('.jQstage_stepThree').append("<div class='demoDragBCon jQ_dragCon demoId' id='demo"+i+"'></div>");
					};
					var timer3 = setInterval(function(){
						clearInterval(timer3);
						$('.jQ_stepForeA').parent().next('.stepMask').slideUp();
					},1000);
				},1000);
			}else{
				alert('请配置完成函数项！');
			}
		}					
	})
	
//////////////////////////////////////////////////

	//训练模式（第四步）
	$('.jQ_stepForeA').click(function(){
		var demoBox = $('.jQstage_stepThree').find('.jQ_dragCon');
		var demos = 1;
		for(var i = 0; i < demoBox.length; i++){
			if($(demoBox[i]).find('table').length == 0){
				demos = 0;
			}else{}
		}
		if(demos == 1){
//			进度条函数
			var pro = 0,
			    progress = $('.progress-bar'),
			    percent = $('.percent');					    					    					
				
			var timer4 = setInterval(function(){             				
				progress.css({'width': pro + "%"});
				percent.html(pro + "%");
				pro += 1;
				if(pro > 100){
					clearInterval(timer4);
					$('.jQ_stepFiveA').parent().next('.stepMask').slideUp();
				}
			},25);
		}else{
			alert('请配置完成函数项!');
		}
	})
	
////////////////////////////////////////

    //随机数函数
    function fRandomBy(under, over){ 
	   switch(arguments.length){ 
	     case 1: return parseInt(Math.random()*under+1); 
	     case 2: return parseInt(Math.random()*(over-under+1) + under); 
	     default: return 0; 
	   } 
	}
	
	//验证（第五步）
	$('.jQ_stepFiveA').click(function(){
		var startTime = $(this).parent().prev().find('.startTime2').val();
		var endTime = $(this).parent().prev().find('.endTime2').val();
		var stepLong = $(this).parent().prev().find('.stepLong').val();
		if(stepLong == null || stepLong == '' || stepLong == ' '|| endTime == null || startTime == null){
		    alert('请补全表单！');
		}else{		
			var time2 = setInterval(function(){							
				clearInterval(time2);
				$('.jQ_stepSixA').parent().next('.stepMask').slideUp();
				setTimeout(function(){	
					//平均准确率
					$('.jQ_averages').text(fRandomBy(90,98) + '%');
			        $('.jQ_averageBox').slideDown();						
				},1000);
			},1000);					
		}
	})
	
	//测试报告
	$('.jQ_ceshibaogao').click(function(){
		$('.jQ_popupBox').slideDown();
		$('.jQ_popups').children().remove();
		for(var i = 0; i < numbers; i++){
			$('.jQ_popups').append("<div class='lines'>"
							+"<span class='lineTitle'>分类"+(i+1)+"</span>"
							+"<div class='lineIn'></div>"
							+"<span class='lineBai'>100%</span>"
						+"</div>")
		}
	})
	$('.jQ_popupSure').click(function(){
		$(this).parents('.jQ_popupBox').slideUp();
	})
	
    //保存
	$('.saveBtn').click(function(){
		//模态框显示
		$('#myModal').modal('show');
		$('.btn-hide').on('click',function(){
			//隐藏
			$('#myModal').modal('hide');
		});
		$('#myModal').modal({
			show:false,
			//按下esc隐藏
			keyboard:true
		});					
    })	
    
    //保存成功
    $('.sureSaveButton').click(function(){
    	setTimeout(function(){
    		alert('保存成功！！！');
    	},1000);
    })
			
	//分步页面重置
	//第一步
	function stepBox01(){
		$('.stepBox01').find('.jQ_nameInput').val('');
	}
	//第二步
	function stepBox02(){
		$('.stepBox02').find('.startTime1').val('');
		$('.stepBox02').find('.endTime1').val('');
		$('.stepBox02').find('.stepLong').val('');
		$('.stepBox02').find('.stepMask').slideDown();
	}
	//第三步
	function stepBox03(){
		$('.stepBox03').find('.stage').val('');
		$('.stepBox03').find('.stepMask').slideDown();
//		$('.stepBox03').find('.jQ_sureThreeA').slideDown();
//		$('.stepBox03').find('.jQ_stepThreeA').slideUp();
	}
	//第四步
	function stepBox04(){
		$('.stepBox04').find('.jQ_ProgressBox').children().remove();
		$('.stepBox04').find('.stepMask').slideDown();
//		$('.stepBox04').find('.jQ_ksxlForeA').slideDown();
//		$('.stepBox04').find('.jQ_stepForeA').slideUp();
	}
	//第五步
	function stepBox05(){
		$('.stepBox05').find('.startTime2').val('');
		$('.stepBox05').find('.endTime2').val('');
		$('.stepBox05').find('.stepLong').val('');
		$('.stepBox05').find('.jQ_averageBox').slideUp();
		$('.stepBox05').find('.stepMask').slideDown();
	}
	
	//函数区域重置
	function demoDele(){
		$('.jQstage_stepThree').children().remove();
	}
	//第一步点击重置
	$('.stepBox01').find('input').change(function(){
		stepBox02();stepBox03();stepBox04();stepBox05();demoDele();
	})
	//第二步点击重置
	$('.stepBox02').find('input').change(function(){
		stepBox03();stepBox04();stepBox05();demoDele();
	})
	$('.stepBox02').find('.startTime1').click(function(){
		stepBox03();stepBox04();stepBox05();demoDele();
	})
	$('.stepBox02').find('.endTime1').click(function(){
		stepBox03();stepBox04();stepBox05();demoDele();
	})
	//第三步点击重置
	$('.stepBox03').find('input').change(function(){
		stepBox04();stepBox05();demoDele();
//		$('.stepBox03').find('.jQ_sureThreeA').slideDown();
//		$('.stepBox03').find('.jQ_stepThreeA').slideUp();
	})
	//第五步点击重置
	$('.stepBox05').find('input').change(function(){
		$('.stepBox05').find('.jQ_averageBox').slideUp();
	})
	$('.stepBox05').find('.startTime2').click(function(){
		$('.stepBox05').find('.jQ_averageBox').slideUp();
	})
	$('.stepBox05').find('.endTime2').click(function(){
		$('.stepBox05').find('.jQ_averageBox').slideUp();
	})
	
	$('ul').on('click','.nodeCloseLi',function(){
		$(this).parent('li').remove();
		stepBox01();stepBox02();stepBox03();stepBox04();stepBox05();demoDele();
	})
	
	//change事件函数
	$("input[name='learning_rate']").change(function(){
		console.log(1);
		var val = $("input[name='learning_rate']").val();
		console.log(val);
		$('.valve').html(val);
	})
	
//	$('.range').onchange = function(){
//		console.log(1);
//		var val = $('.range').val();
//		console.log(val);
//		$('.valve').html(val);
//	}
				
//	$('.jQ_demoBox_b').addEventListener('change','.range',function(){
//		alert(111);
//		var parents = $('.range').parent('td').parent('tr').parent('table').parent();
//  	var val = parents.find('input.range').val();
//  	console.log(val);
//  	parents.find('span.value').html(val);
//	})

////////////////////////////////////////
    
//拖拽函数
jQuery(function($){              
    //拖放开始:获取id放入dataTransfer  
    $("ul").on("dragstart",".movableItem",function(e){  
        e.originalEvent.dataTransfer.setData("obj_add",e.target.id);  
    });
      
    //允许放入
    $(".jQ_demoBox_b ").on("dragover",".jQ_dragCon",function(e){  
        e.originalEvent.preventDefault();  
    })

    //放下事件
    $(".jQ_demoBox_b ").on("drop",".jQ_dragCon",function(e){  
        var id = e.originalEvent.dataTransfer.getData("obj_add");
        //给新加入的元素添加拖放事件
        $("#"+id).on("dragstart",function(e){
            e.originalEvent.dataTransfer.setData("obj_remove",e.target.id);
        });
        
		var demoSma = $("#"+id).html();
		if($(this).children('table').length >0){
			$(this).children('table').remove();
			if(demoSma == "神经网络预测算法"){
				var demoId0 = $(this).attr('id');
				$(this).append("<table>"
									+"<tr>"
										+"<td width='20%'>计算类型</td>"
										+"<td width='30%'>"
											+"<select name='calcType'>"
												+"<option>train</option>" 
											+"</select>"
										+"</td>"
										+"<td width='20%'>节点数</td>"
										+"<td width='30%'>"
											+"<input type='text' name='nodes' value='60'>"
										+"</td>"
									+"</tr>"
									+"<tr>"
										+"<td width='20%'>学习率</td>"
										+"<td width='30%'>"
										    +"<span class='value' style='display: inline-block;'>0</span>"
											+"<input class='range' type='range' name='learning_rate' step='0.001'>"
											+"<span style='display: inline-block;'>0.01</span>"
										+"</td>"
										+"<td width='20%'>优化器</td>"
										+"<td width='30%'>"
											+"<select name='optimizer'>"
												+"<option>sgd</option>" 
											+"</select>"
										+"</td>"
									+"</tr>"
									+"<tr>"
										+"<td width='20%'>激活函数</td>"
										+"<td width='30%'>"
											+"<select name='act'>"
												+"<option>sigmoid</option>" 
											+"</select>"
										+"</td>"
										+"<td width='20%'>迭代次数</td>"
										+"<td width='30%'>"
											+"<input type='text' name='n_epoch' value='10'>"
										+"</td>"
									+"</tr>" 
								+"</table>");				
			    
				//进度条
                $('.jQ_ProgressBox').children("#progressBar"+demoId0).remove();
	            $('.jQ_ProgressBox').append("<div id='progressBar"+demoId0+"'>"
										+"<div class='progress progress_change' style='width:80%;display:inline-block;float:none,margin-top:5px'>"
											+"<div class='progress-bar' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: 0%;'>"
//													+"<span class='sr-only'>10% 完成</span>"
											+"</div>"
										+"</div>"
										+"<div class='percent' style='display:inline-block;width:auto;vertical-align:top;margin-left:5px'>0%</div>"
										+"</div>");												
			}else{
				var demoId0 = $(this).attr('id');
				$(this).append("<table>"
									+"<tr>"
										+"<td width='20%'>计算类型</td>"
										+"<td width='30%'>"
											+"<select name='calcType'>"
												+"<option>train</option>" 
											+"</select>"
										+"</td>"
										+"<td width='20%'>聚类数量</td>"
										+"<td width='30%'>"
											+"<input type='number' name='n_clusters' value='60'>"
										+"</td>"
									+"</tr>"
									+"<tr>"
										+"<td width='20%'>初始化种子数</td>"
										+"<td colspan='1'>"
											+"<input type='number' name='n_int' value='10'>"
										+"</td>" 
									+"</tr>" 
								+"</table>")
			}
			stepBox05();
		}else{
			if(demoSma == "神经网络预测算法"){
				var demoId0 = $(this).attr('id');
				$(this).append("<table>"
									+"<tr>"
										+"<td width='20%'>计算类型</td>"
										+"<td width='30%'>"
											+"<select name='calcType'>"
												+"<option>train</option>" 
											+"</select>"
										+"</td>"
										+"<td width='20%'>节点数</td>"
										+"<td width='30%'>"
											+"<input type='text' name='nodes' value='60'>"
										+"</td>"
									+"</tr>"
									+"<tr>"
										+"<td width='20%'>学习率</td>"
										+"<td width='30%'>"
										    +"<span class='value' style='display: inline-block;'>0</span>"
											+"<input class='range' type='range' name='learning_rate' step='0.001'>"
											+"<span style='display: inline-block;'>0.01</span>"
										+"</td>"
										+"<td width='20%'>优化器</td>"
										+"<td width='30%'>"
											+"<select name='optimizer'>"
												+"<option>sgd</option>" 
											+"</select>"
										+"</td>"
									+"</tr>"
									+"<tr>"
										+"<td width='20%'>激活函数</td>"
										+"<td width='30%'>"
											+"<select name='act'>"
												+"<option>sigmoid</option>" 
											+"</select>"
										+"</td>"
										+"<td width='20%'>迭代次数</td>"
										+"<td width='30%'>"
											+"<input type='text' name='n_epoch' value='10'>"
										+"</td>"
									+"</tr>" 
								+"</table>")
				
				//进度条
	            $('.jQ_ProgressBox').append("<div id='progressBar"+demoId0+"'>"
										+"<div class='progress progress_change' style='width:80%;display:inline-block;float:none'>"
											+"<div class='progress-bar' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: 0%;'>"
//													+"<span class='sr-only'>10% 完成</span>"
											+"</div>"
										+"</div>"
										+"<div class='percent' style='display:inline-block;width:auto;vertical-align:top'>0%</div>"
										+"</div>");												
			}else{
				var demoId0 = $(this).attr('id');
				$(this).append("<table>"
									+"<tr>"
										+"<td width='20%'>计算类型</td>"
										+"<td width='30%'>"
											+"<select name='calcType'>"
												+"<option>train</option>" 
											+"</select>"
										+"</td>"
										+"<td width='20%'>聚类数量</td>"
										+"<td width='30%'>"
											+"<input type='number' name='n_clusters' value='60'>"
										+"</td>"
									+"</tr>"
									+"<tr>"
										+"<td width='20%'>初始化种子数</td>"
										+"<td colspan='1'>"
											+"<input type='number' name='n_int' value='10'>"
										+"</td>" 
									+"</tr>" 
								+"</table>")
			}
			stepBox05();
		}
    })  
});  
