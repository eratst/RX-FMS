/*
 * 
 * @author chao.dong 2017/11/01
 * 
 */
var ohtml = localStorage.getItem('ohtml');
//console.log(ohtml);

//位号节点拖动事件
function allowDrop(ev) {
	ev.preventDefault();
	var node;
}

function drag(ev) {
	ev.dataTransfer.setData("Texthtml", $(ev.target).parent().parent().html());
	ev.dataTransfer.setData("Textid", $(ev.target).parent().parent().id);
	node = $(ev.target);
	node.addClass('dragNode');
}

function drop(ev) {
	//随机颜色数组
	//	var colorIndex = 0;
	//	var colorArr = ["#3D90CD","#42BCC8","#E26A73","#00BEF1","#8882BE","#F8B086"];
	//	if(colorIndex < colorArr.length){
	//		colorIndex++;
	//	}else{
	//		colorIndex = colorArr.length;
	//	}
	ev.preventDefault();
	var data = ev.dataTransfer.getData("Texthtml");
	var dataid = ev.dataTransfer.getData("Textid");
	var str = node.data('itemnno');
	var textT = node.data('textname') + '.' + node.text();
	if (ev.target.nodeName == 'DIV') {
		$(ev.target).append("<i style='color:red' data-sourcedatatype='" + node.data('sourcedatatype') + "' data-itempoint='" + str + "' contenteditable='false'>" + str + "</i>")
			//		$(ev.target).text(tag)
		$('#dropAreaUl').scrollTo('100%');
	} else {
		//		$("#dropAreaUl").append("<li><p>"+textT+"</p><i style='color:red' data-sourcedatatype='" + node.data('sourcedatatype') + "' data-itempoint='" + str + "' contenteditable='false'>" + str + "</i></li>");
		$("#dropAreaUl").append("<li><i style='color:red' data-sourcedatatype='" + node.data('sourcedatatype') + "' data-itempoint='" + str + "' contenteditable='false'><b>'</b>" + str + "<b>'</b></i></li>");
		var  div  =  document.getElementById('dropAreaUl'); 
		$("#dropAreaUl").animate({
			scrollLeft: div.scrollWidth + "px"
		}, 1000)
	}

	node = '';
}

$(function (e) {

	//获取agentType treeid和treeType
	var agentValObj = zs_getUrlParamObj();
	var comTreeId = localStorage.getItem("treeId");
	var comTreeType = localStorage.getItem("treeType");
	console.log(comTreeId, comTreeType)

	$('.appCode').val(localStorage.getItem("agentLetType"))
		//agentType
		//	var ohtml = JSON.parse(localStorage.getItem('otreeObj'));
		//	$(".treeBoxBig").html("").html(ohtml.content);
	$('#tree').append(localStorage.getItem('treeHtml'))
	if (localStorage.getItem("treetext") == '罐量计算') {
		$('.contentLeft').hide();
		$('.contentRightBottom').hide();
		$('.contentRight').css('width', '98%')
		$('.contentRightTop').css('height', '100%')
		$('.panel-body table').css('height', '75%')
		$('<p class="rightP"><button id="returnBtn">返回</button><button id="saveBtn">保存</button></p>').appendTo('.panel-body')
		var htmlT = ''
	} else if (localStorage.getItem("treetext").indexOf('告警') > -1) {
		//	告警类型agent
		$('.add_Title_b span').text(localStorage.getItem("treetext"))
		$('.panel-body table').css("height", '75%')
		$('.contentRightTop').css('height', '36%')
		$('.contentRightBottom').css('height', 'calc(64% - 15px)')
		$('<p class="rightP"><button id="returnBtn">返回</button><button id="saveBtn">保存</button></p>').appendTo('.panel-body')
		if (localStorage.getItem("treetext") == '装置告警') {
			var htmlT = '<div class="row manage_tb"><table id="demo-table"></table></div>'
		} else {
			if (localStorage.getItem("treetext") == '超温告警') {
				var htmlT = '<div id="showTab" class="temAlert">'
			} else if (localStorage.getItem("treetext") == '超压告警') {
				var htmlT = '<div id="showTab" class="pressAlert">'
			} else {
				var htmlT = '<div id="showTab" class="tankAlert">'
			}
			htmlT += '<div class="topHM"><input class="supremum"/><h5>告警信息</h5><textarea class="SUmessage"></textarea></div>' +
				'<div class="topM"><input class="upperLimit"/><h5>告警信息</h5><textarea class="UPmessage"></textarea></div>' +
				'<div class="botM"><input class="lowerLimit"/><h5>告警信息</h5><textarea class="LOmessage"></textarea></div>' +
				'<div class="botHM"><input class="llLimit"/><h5>告警信息</h5><textarea class="LLmessage"></textarea></div></div>'
		}




		$('.contentLeft').hide()
		$('.contentRight').css('width', '98%')
		$("#conMsg").text('智能点 基本信息 -- ' + localStorage.getItem("treetext"))
		localStorage.setItem('alert', '')
	} else {
		$('.input-arg-model').hide()
		$('.params').css('height', 'calc(67% - 15px)').css('margin-top', '81%')
		$('.contentRightTop').css('width', '132.5%').css('margin-left', '-32.5%')
		var urlArea = app.SERVICE_ROOT + app.URL_MEASURE + '?Id=' + localStorage.getItem("treeId")
			//		creatPonitMeasure(urlArea,localStorage.getItem('listName'))
		var htmlT = '<div class="formulaTop" id="formulaTop">' +
			'<button>+</button>' +
			'<button>-</button>' +
			'<button>×</button>' +
			'<button>÷</button>' +
			'<button>(</button>' +
			'<button>)</button>' +
			'<button class="showTack">DIFF</button>' +
			'<button class="showTack">VAL</button>' +
			'<button class="showTack">VAR</button>' +
			'</div>' +
			'<div class="formulaMiddle">' +
			'<ul id="dropAreaUl" ondrop="drop(event)" ondragover="allowDrop(event)" contenteditable="true"></ul>' +
			'</div>' +
			'<div class="formulaBottom">' +
			'<button id="returnBtn">返回</button>' +
			'<button id="saveBtn">保存</button>' +
			'<button id="clearBtn">清空</button>' +
			'<button id="backBtn">撤销</button>' +
			'</div>'
		if (localStorage.getItem("treetext") == '设备运行分析') {

			var htmlT = '<div class="formulaTop" id="formulaTop">' +
				'<button>+</button>' +
				'<button>-</button>' +
				'<button>×</button>' +
				'<button>÷</button>' +
				'<button>(</button>' +
				'<button>)</button>' +
				'</div>' +
				'<div class="formulaMiddle">' +
				'<ul id="dropAreaUl" ondrop="drop(event)" ondragover="allowDrop(event)" contenteditable="true"></ul>' +
				'</div>' +
				'<div class="formulaBottom">' +
				'<button id="returnBtn">返回</button>' +
				'<button id="saveBtn">保存</button>' +
				'<button id="clearBtn">清空</button>' +
				'<button id="backBtn">撤销</button>' +
				'</div>'
		}
	}
	console.log(sessionStorage.getItem('1'))
	$('.contentRightBottom .panel-body').html(htmlT)
	$("#conMsg").text('智能点 基本信息 -- ' + localStorage.getItem("treetext"))
		//	如果已经选择了侧线列表中的一项 则需要将这部分信息添加到挂载点中
	if (localStorage.getItem('listName') == '') {
		$('#playPoint').text('挂载点：' + localStorage.getItem('pointList').replace(/,/g, '/'))
	} else {
		$('#playPoint').text('挂载点：' + localStorage.getItem('pointList').replace(/,/g, '/') + '/' + localStorage.getItem('listName'))
		var urlArea = app.SERVICE_ROOT + 'points?cateId=' + $('.sign').parent().data('id');
		creatTreeNode($('.sign'), urlArea, 3, 'true')

	}

	if (localStorage.getItem('agentCode') != '' && localStorage.getItem('edit') == 'true') {
		//		修改agentLet
		if (localStorage.getItem('agtype').indexOf('tank') > -1) {
			var urls = app.SERVICE_ROOT + 'TankVolume/NewTankVolume/' + localStorage.getItem('agentCode')
			ajaxGet(urls, function (data) {
				showEditMsg(data, '0')
			})

		} else if (localStorage.getItem('agtype').indexOf('Alarm') > -1) {
			var urls = app.SERVICE_ROOT + 'Alarm/' + localStorage.getItem('agentCode')
			ajaxGet(urls, function (data) {
				showEditMsg(data, '0')
			})

		} else {
			var urls = app.SERVICE_ROOT + 'TankVolume/line/' + localStorage.getItem('agentCode')
			ajaxGet(urls, function (data) {
				showEditMsg(data, '1')
			})
		}
	}
	//装置告警需要加载列表
	if (localStorage.getItem("treetext") == '装置告警') {
		searchC()
	}

	//	treeAdd()
	treeOpera()
	localStorage.setItem('valParam', '')
	$('#formulaTop').on('click', 'button', function () {
		var html0 = $(this).html();
		if (html0 == "END" || html0 == 'BEG') {
			html0 = "'" + html0 + "'";
		}
		$("#dropAreaUl").append("<li style='font-weight:bold;font-size:3.0rem'>" + html0 + "</li>");
		if ($(this).html() == 'VAL') {
			$('#formulaTop').append('<button class="showTack">BEG</button><button class="showTack">END</button>')
		} else if ($(this).html() == 'END' || $(this).html() == 'BEG') {
			localStorage.setItem('valParam', $(this).html())
			$('#formulaTop button:contains(END)').remove();
			$('#formulaTop button:contains(BEG)').remove();
		}
	})

	//清除
	$('#clearBtn').on('click', function () {
			$("#dropAreaUl").empty();
		})
		//回退
	$('#backBtn').on('click', function () {
		$("#dropAreaUl").children().last().remove();
	})

	$('.showTack').click(function () {
			$('.active').removeClass('active')
			$(this).addClass('active')
		})
		//	返回按钮
	$(document).on('click', '#returnBtn', function () {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		})
		//设备运行分析 保存操作
	$(document).on('click', '#saveBtn', function () {
		console.log($("#dropAreaUl").children().not('li').text());
		var input0 = $('.contentRightTop').find('table').find('td').find('input').val();
		if (input0 == '' || input0 == ' ' || input0 == null) {
			alert('请补全基本信息中的表单项！');
		} else {
			if ($('#dropAreaUl').text() == '' && localStorage.getItem("treetext") != '罐量计算' && localStorage.getItem("treetext").indexOf('告警') < 0) {
				alert('请拖拽位号和运算符号！！！');
			} else {

				//设备运行页面  取值
				var appCodeCom = $('#appcode').val();
				var agentNameCom = $('#agentName').val();
				var calcuRateTextCom = Number($('#modelType').val());
				//var timeBasicCom = $('#arithmeticFormula option:selected').text();

				//				侧线计算部分
				//获取拖拽公式
				//取出运算符
				var dropFormulaText = $('#dropAreaUl').find('li');
				var FormulaArr = [];
				//判断li里面是否含有子节点
				for (var i = 0; i < dropFormulaText.length; i++) {
					// console.log($(dropFormulaText.eq(i)).find('i').eq(0).innerHTML);
					if ($(dropFormulaText.eq(i)).find('i').length == 0) {
						//存在
						var val = $(dropFormulaText.eq(i)).text();
						console.log(val);
						//FormulaArr.push(val);
					}
				}
				var flag = true;
				if (localStorage.getItem("treetext") == '罐量计算') {
					var tmp = {
						'appCode': appCodeCom,
						'agentName': agentNameCom,
						'agentType': appCodeCom,
						'treeId': comTreeId,
						'treeType': comTreeType,
						'frequency': calcuRateTextCom,
						'enabled': $('#switch-state').bootstrapSwitch('state'),
						'descs': $('.descs').val(),
						'offset': $('#OffsetL').val(),
						'objCode': localStorage.getItem('objcode')
					};
				} else if (localStorage.getItem("treetext").indexOf('告警') > -1) {
					var tmp = {
						'appCode': appCodeCom,
						'agentName': agentNameCom,
						'agentType': appCodeCom,
						'treeId': comTreeId,
						'treeType': comTreeType,
						'frequency': calcuRateTextCom,
						'enabled': $('#switch-state').bootstrapSwitch('state'),
						'descs': $('.descs').val(),
						'offset': $('#OffsetL').val(),
						'objCode': localStorage.getItem('objcode'),

						'upperLimit': $(".topM input").val(),
						'supremum': $(".topHM input").val(),
						'lowerLimit': $(".botM input").val(),
						'llLimit': $(".botHM input").val(),
						'UPmessage': $(".topM textarea").val(),
						'SUmessage': $(".topHM textarea").val(),
						'LOmessage': $(".botM textarea").val(),
						'LLmessage': $(".botHM textarea").val(),
					};
				} else {
					//拼接公式
					var formulaStr = '';
					$('#dropAreaUl li').each(function () {
						if ($(this).find('i').length == 0) {
							formulaStr += $(this).text()
						} else {
							formulaStr += "'" + $(this).children('i').data('itempoint') + "'";
						}
					})
					console.log($('#dropAreaUl').text())
					formulaStr = $('#dropAreaUl').text()
					if (formulaStr.indexOf('DIFF') < 0 && formulaStr.indexOf('VAL') < 0 && formulaStr.indexOf('VAR') < 0 && localStorage.getItem("treetext") != '设备运行分析') {
						layer.msg('请输入正确的公式')
						flag = false;
					}

					var dragArrCom = [];
					//拿到参数
					var dropFormulaCanText = $('#dropAreaUl').find('i');
					dropFormulaCanText.each(function () {
						var data = {};
						data.codeName = $(this).data('itempoint');
						data.codeId = $(this).data('sourcedatatype');
						if ($(this).parent().prev().prev().text() != '') {
							data.algm = $(this).parent().prev().prev().text();
						}
						if (data.algm == "VAL") {
							var valparam = $(this).parent().next().text()
							data.valParam = valparam.substr(1, valparam.length - 2);
						}
						dragArrCom.push(data)
					})
					dragArrCom = JSON.stringify(dragArrCom);
					var tmp = {
						'appCode': appCodeCom,
						'agentName': agentNameCom,
						'agentType': appCodeCom,
						'treeId': comTreeId,
						'treeType': comTreeType,
						'arithmeticFormula': formulaStr,
						'frequency': calcuRateTextCom,
						'assemble': dragArrCom,
						'enabled': $('#switch-state').bootstrapSwitch('state'),
						'offset': $('#OffsetL').val(),
						'descs': $('.descs').val(),
						'objCode': localStorage.getItem('objcode')
					};
				}

				var arr = [];
				for (var p in tmp) {
					var obj = {};
					obj.name = p;
					obj.value = tmp[p];
					arr.push(obj);
				}
				var cJsonStr = {
					"collection": {
						"templates": [{
							"data": arr
						}]
					}
				}
				console.log(JSON.stringify(cJsonStr))
				if ($('.agentName').val() == '') {
					layer.msg('请填写名称')
					flag = false;
				} else if ($('.offset').val() == '') {
					layer.msg('请填写偏移量')
					flag = false;
				} else if ($('.frequency').val() == '') {
					layer.msg('请填写计算频率')
					flag = false;
				}

				if (flag) {
					if (localStorage.getItem("treetext") == '罐量计算') {
						if (localStorage.getItem('edit') == 'true') {
							var url = app.SERVICE_ROOT + 'TankVolume/NewTankVolume/' + localStorage.getItem('agentCode')
							editD(url, JSON.stringify(cJsonStr))
						} else {
							var url = app.SERVICE_ROOT + 'TankVolume/NewTankVolume';
							addD(url, JSON.stringify(cJsonStr))
						}
					} else if (localStorage.getItem("treetext") == '设备运行分析') {
						if (localStorage.getItem('edit') == 'true') {
							var url = app.SERVICE_ROOT + 'TankVolume/equipment/' + localStorage.getItem('agentCode')
							editD(url, JSON.stringify(cJsonStr))
						} else {
							var url = app.SERVICE_ROOT + 'TankVolume/equipment';
							addD(url, JSON.stringify(cJsonStr))
						}
					} else if (localStorage.getItem("treetext").indexOf('告警') > -1) {
						if (localStorage.getItem('edit') == 'true') {
							var url = app.SERVICE_ROOT + 'Alarm/' + localStorage.getItem('agentCode')
							editD(url, JSON.stringify(cJsonStr))
						} else {
							var url = app.SERVICE_ROOT + 'Alarm';
							addD(url, JSON.stringify(cJsonStr))
						}
					} else {
						if (localStorage.getItem('edit') == 'true') {
							var url = app.SERVICE_ROOT + 'TankVolume/line/' + localStorage.getItem('agentCode')
							editD(url, JSON.stringify(cJsonStr))
						} else {
							var url = app.SERVICE_ROOT + 'TankVolume/line';
							addD(url, JSON.stringify(cJsonStr))
						}

					}
				}
				//				}
			}
		}
	})
});
//回显位号和公式类型
function showEditMsg(data, flag) {
	//	console.log(data[0]['assemble'])
	for (var i in data[0]) {
		console.log(data[0].enabled, flags)
		var flags = data[0].enabled == 0 ? false : true;
		//		给启用按钮状态回显
		$('#switch-state').bootstrapSwitch('state', flags)
			//		console.log($('#switch-state').bootstrapSwitch('state'))
		$('.' + i).val(data[0][i])
		if (flag == '0') {
			//		罐

		} else {
			//		测线及其他
			if (i != 'assemble' && i != 'appCode') {
				var jsonD = JSON.parse(data[0]['assemble']);
				var str = data[0]['arithmeticFormula'];
				//				console.log(str, i)
				for (var x = 0; x < jsonD.length; x++) {
					console.log(jsonD[x].codeName)
						//					公式
					var reg = new RegExp("'" + jsonD[x].codeName + "'")
					var con = "<li><i style='color:red' data-sourcedatatype='" + jsonD[x].codeId + "' data-itempoint='" + jsonD[x].codeName + "' contenteditable='false'>'" + jsonD[x].codeName + "'</i></li>"
						//					计算类型
					str = str.replace(reg, con)
					var arr = ["DIFF", "VAL", "VAR", "'BEG'", "'END'", "[(]", "[)]", "[×]", "[÷]", "[+]"];
					for (var y = 0; y < arr.length; y++) {
						str = regFH(arr[y], str)
					}

				}
				$('#dropAreaUl').html(str)
			}
		}
	}

}
//加载装置告警数据
function searchC() {
	var url = app.SERVICE_ROOT + 'deviceWarnings/getConfiguration';
	var arr = [ {                  
			checkbox:  true              
		},
		{
			field: 'type',
			title: '类别',
			align: 'center',
			valign: 'middle',
			sortable: false

                }, {
			field: 'name',
			title: '名称',
			align: 'center',
			valign: 'middle'
                },
		{
			field: 'value',
			title: '值',
			align: 'center',
			valign: 'middle',
			editable: {
				type: 'text',
				title: '值',
				validate: function (v) {
					if (!v) return '值不能为空';

				}
			}
                }, {
			field: 'message',
			title: '告警信息',
			align: 'center',
			valign: 'middle',
			editable: {
				type: 'text',
				title: '告警信息',
				validate: function (v) {
					if (!v) return '告警信息不能为空';

				}
			}
                }, {
			field: 'param',
			title: '参数',
			align: 'center',
			valign: 'middle',
			editable: {
				type: 'text',
				title: '参数',
				validate: function (v) {
					if (!v) return '参数不能为空';

				}
			}
				}];


	ajaxGet(url, function (data) {
		GetData(data, arr)
	})
}
/**
 * 点击增加子叶节点及显示智能点列表
 */

function treeAdd() {
	$('#tree').on('click', 'li>span', function (e) {
		e.stopPropagation()
		$(".selectable").hide(500)
		$('.bgBlue').removeClass('bgBlue')
		$('.sign').removeClass('sign')
		$(this).addClass('sign')
		$(this).parents('li').each(function (value, thisP) {
			if ($(thisP).attr('id') != 'tree') {
				$(thisP).find('span').eq(0).addClass('bgBlue')
			}
		})

		var self = this;
		//区分层级来辨识使用api种类及打开智能点列表还是打开列表
		if ($(this).data('leval') == undefined) {
			var urlArea = app.SERVICE_ROOT + 'areas?orgUnitCode=' + $(this).parent().data('code');
			var leavl = '1';
		} else if ($(this).data('leval') == '1') {
			var urlArea = app.SERVICE_ROOT + 'pointCategories?areaId=' + $(this).parent().data('id');
			var leavl = '2';
		} else if ($(this).data('leval') == '2') {
			$(".selectable").show(500)
			$(".openDialog").hide(500)
			$(".selectable .left-heading h5").text($(self).text() + '列表')
			var leavl = '3';
			var urlArea = app.SERVICE_ROOT + 'points?cateId=' + $(this).parent().data('id');
			//				fenyef(urlArea)
			//				return;
		} else if ($(this).data('leval') == '3') {
			var urlArea = app.SERVICE_ROOT + app.URL_MEASURE + '?Id=' + $(this).parent().data('id');
			creatPonitMeasure(urlArea, $(self).text())
			return;
		}
		if (!$(self).parent().hasClass('parent_li')) {
			creatTreeNode(self, urlArea, leavl)
		}

		//					showInterMod(self,0,0)
		return;
	});
}

/**
 * 根据左侧生成右侧列表
 * 
 * @param  {Array}
 * @return {Object}
 */
function fenyef(url) {
	$("#mainCon").bootstrapTable('destroy');
	$("#mainCon").bootstrapTable({
		method:   "get",
		   //使用get请求到服务器获取数据  
		url:  url,
		contentType: "application/json;charset=utf-8",
		dataType: "json",
		data: {},
		cache: false,
		pagination:  true, //启动分页  
		//showColumns: true,//显示列
		pageSize:  5, //每页显示的记录数  
		pageNumber: 1, //当前第几页  
		pageList:  [10,  20,  50,  100], //记录数可选列表  
		sidePagination:   "server", //表示服务端请求  
		queryParamsType :   "undefined",
		queryParams:   function  queryParams(params)  {    //设置查询参数  	            		                
			var  param  =   {
				pageNumber: params.pageNumber,
				pageSize:  params.pageSize
			};
			return  param;
		},
		async: true,
		responseHandler: function (res) {
			var info1 = $.ET.toObjectArr(res);
			console.log(info1)
			$("#mainCon").html('');
			$(info1).each(function (index, item1) {
				$("#mainCon").append('<li data-type="' + item1.type + '" data-id="' + item1.id + '">' + item1.name + '</li>')
			})
			return {
				"total": $.ET.getPageInfo(res)[0] == undefined ? 0 : $.ET.getPageInfo(res)[0].recordCount, //总页数
				"rows": $.ET.toObjectArr(JSON.stringify(res).replace(/\</g, "&lt;").replace(/\>/g, "&gt;")) //数据
			};
		}
	})
}

/**
 * 生成右侧度量指标
 * params {object} self 需要绑定添加子节点的对象
 * params {string} id 需要绑定添加子节点的对象id
 */
function creatPonitMeasure(urlArea, text) {
	ajaxGet(urlArea, function (data) {
			console.log(data)
			$('.paramsRight').html('')
			for (var i = 0; i < data.length; i++) {
				$('.paramsRight').append('<ul style="display: none;">' +
					'<li draggable="true" data-textname="' + text + '" ondragstart="drag(event)" data-itemnNo="' + data[i].itemnNo + '" data-sourcedatatype="' + data[i].sourceDataType + '">' + data[i].shortName + '</li>' +
					'</ul>');
				$('.paramsRight').find('ul').show(400);
				$('.paramsRight').find('ul').find('li').eq(0).css('font-weight', 'bold');
				$('.paramsRight').find('ul').find('li').not(':first').css('color', '#2CABE3');
			}
			return false;
		})
		//实例
		//	var dataArr = ['度量指标','温度','压力','流量','体积','密度','质量'];

}
/**
 * 正则匹配特殊符号替换
 * params {object} self 需要绑定添加子节点的对象
 * params {string} id 需要绑定添加子节点的对象id
 */
function regFH(regh, str) {
	var reg1 = new RegExp(regh)
	if (regh.indexOf("[") > -1) {
		var con1 = '<li style="font-weight:bold;font-size:3.0rem">' + regh.substr(1, regh.length - 2) + '</li>'
	} else {
		var con1 = '<li style="font-weight:bold;font-size:3.0rem">' + regh + '</li>'
	}

	return str.replace(reg1, con1)
}