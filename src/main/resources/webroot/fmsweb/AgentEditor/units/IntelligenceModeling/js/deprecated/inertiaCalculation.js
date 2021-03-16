/*
 * 
 * @author dan.gao 2017/9/14
 * 
*/
var ohtml = localStorage.getItem('ohtml');
//位号节点拖动事件
function allowDropGuan(ev){
	ev.preventDefault();
	var node;
}

function drag(ev){	
	ev.dataTransfer.setData("Texthtml",$(ev.target).parent().parent().html());			
	ev.dataTransfer.setData("Textid",$(ev.target).parent().parent().id);		
	node = $(ev.target);
	node.addClass('dragNode');
}

function dropGuan(ev){	
	//随机颜色数组
//	var colorIndex = 0;
//	var colorArr = ["#3D90CD","#42BCC8","#E26A73","#00BEF1","#8882BE","#F8B086"];
//	if(colorIndex < colorArr.length){
//		colorIndex++;
//	}else{
//		colorIndex = colorArr.length;
//	}
	
	ev.preventDefault();
	var data=ev.dataTransfer.getData("Texthtml");
	var dataid=ev.dataTransfer.getData("Textid");
	str = node.text();

	$("#dropAreaUl").append("<li><i style='color:red'>CII-FP2541</i></li>");
	
	node = '';
}

//参数tab切换
$('.paramsLeft ul').find('li').not(':first').on('click',function(){
	$('.paramsRight').children().empty();
	$(this).css('color','#2CABE3').siblings().css('color','#000');
	var dataArr = ['度量指标','温度','压力','流量','体积','密度','质量'];
	for (var i = 0; i < dataArr.length; i++) {
		$('.paramsRight').append('<ul style="display: none;">'
									+'<li draggable="true" ondragstart="drag(event)">'+dataArr[i]+'</li>'
							    +'</ul>');
        $('.paramsRight').find('ul').show(400);
        $('.paramsRight').find('ul').find('li').eq(0).css('font-weight','bold');
        $('.paramsRight').find('ul').find('li').not(':first').css('color','#2CABE3');
	}
})

$('#formulaTop').find('button').on('click',function(){
	var html0 = $(this).html();
	$("#dropAreaUl").append("<li style='font-weight:bold;font-size:3.0rem'>"+html0+"</li>");
})

$('#clearBtn').on('click',function(){
	$("#dropAreaUl").empty();
})

$('#backBtn').on('click',function(){
	$("#dropAreaUl").children().last().remove();
})

//罐区计算 保存操作
$('#saveBtnGuan').on('click',function(){
	var input0 = $('.contentRightTop').find('table').find('td').find('input').val();
	if(input0 == '' || input0 == ' ' || input0 == null){
		alert('请补全基本信息中的表单项！');
	}else{
		// if ($('#dropAreaUl').children().length == 0) {
		// 	alert('请拖拽位号和运算符号！！！');
		// } 

		// else
		{
			// if ($('#dropAreaUl').children().length <= 2) {
			// 	alert('请拖拽一个正确的公式！！！');
			// } 
			// else
			{
				var treeObjGuan = zs_getUrlParamObj();
				console.log(treeObjGuan);
				//罐区计算页面  取值  上半区
				var appCodeGuan = $('#agentCodeInput').val();
				var agentNameGuan = $('#agentNameInput').val();
				var calcuRateTextGuan = Number($('#calcuRateInput').val());
				var timeBasicGuan = $('#hourlyBasis option:selected').text();
				if (timeBasicGuan == '分钟') {
					calcuRateTextGuan = calcuRateTextGuan * 60000;
				}else if (timeBasicGuan == '小时') {
					calcuRateTextGuan = calcuRateTextGuan * 3600000;

				}

				//整理成毫秒
				//下半区
				var standDensityInputTextGuan = $('#standDensityInput').val();//标准密度公式
				var warterVpfInputInputTextGuan = $('#warterVpfInput').val();//水尺VPF公式
				var tankVolumeInputInputTextGuan = $('#tankVolumeInput').val();//罐体积公式

				var vifInputTextGuan = $('#vifInput').val(); //VTF公式：
				var CoeLiquefacProInputTextGuan = $('#CoeLiquefacProInput').val();//液化产品系数公式
				var correctionInputTextGuan = $('#correctionInput').val();//修正检尺公式
				var vcfInputTextGuan = $('#vcfInput').val();//VCF公式
				var tankFormulaInputTextGuan = $('#tankFormulaInput').val();//罐公式
				var totalVpfInputTextGuan = $('#totalVpfInput').val();//总尺VPF公式
				var vcfDecimalInputTextGuan = $('#vcfDecimalInput').val();//VCF小数位
				var tankStanVolumeInputTextGuan = $('#tankStanVolumeInput').val();//罐标准体积公式
				var sphericalTankGasInputTextGuan = $('#sphericalTankGasInput').val();//球罐气体公式 
				var tankAirDensInputTextGuan = $('#tankAirDensInput').val();//罐空气密度式
				//拖拽数组
				var dragArr = [
					{"codeId":"a","codeName":"RTDB"},
					{"codeId":"b","codeName":"RTDB"}

				];
				dragArr = JSON.stringify(dragArr);
				var tmp = {
					'std_den': standDensityInputTextGuan,
					'vtf': vifInputTextGuan,
					'mod_chk_form': correctionInputTextGuan,
					'totl_chk_vpf': totalVpfInputTextGuan,
					'wtr_chk_vpf': warterVpfInputInputTextGuan,
					'cuba_temp_coef': CoeLiquefacProInputTextGuan,
					'vcf': vcfInputTextGuan,
					'vcf_dec_fra_dgt': vcfDecimalInputTextGuan,
					'tnk_form': tankFormulaInputTextGuan,
					'tnk_air_den': tankAirDensInputTextGuan,
					'tnk_form_mode': tankVolumeInputInputTextGuan,
					'std_cuba_form': tankStanVolumeInputTextGuan,
					'tnk_gas_form': sphericalTankGasInputTextGuan,
					'appCode': appCodeGuan,
					'agentName': agentNameGuan,
					'agentType': 'tankVolume',//罐量计算
					'treeId': treeObjGuan.needId,//, //treeId
					'treeType': treeObjGuan.guanTreeType,//, //treeType
					'frequency': calcuRateTextGuan, //频率
					'assemble':dragArr //拖拽数组
				};
				var cJsonStr = $.ET.toCollectionJson(tmp);
				console.log(JSON.stringify(cJsonStr));		
				
				$.ajax({
					type: "POST",
			        url: app.SERVICE_ROOT + "TankCfg/iPTankCfg/andAgnetlet",
					data: JSON.stringify(cJsonStr),
					dataType: "json",
					contentType: "application/json;charset=utf-8",
					async: true,
					success: function(result){
						console.log(result);					
						if (result) {
//							if (!result.collection.error) {
								alert('保存成功！！！');
//							} else{
//								alert(result.collection.error.message);
//							}				
						} else{
							return;
						}				
					},
					error: function(XMLHttpRequest,textStatus,errorThrow){
						alert('网络出错啦');
					}
				})
		    }
		}		
	}
	
	setTimeout(function(){
		
	},2000);
})

$(function(e){
	var ohtml = JSON.parse(localStorage.getItem('otreeObj'));
	$(".treeBoxBig").html("").html(ohtml.content);
});

