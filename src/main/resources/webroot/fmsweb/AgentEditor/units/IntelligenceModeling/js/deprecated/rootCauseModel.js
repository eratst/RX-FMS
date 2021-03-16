//***********拖动树************//
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

//输入
function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("Texthtml");
    var dataid = ev.dataTransfer.getData("Textid");
    str = node.text();

    if ($('#inputBox').children().length == 0) {
        $('#inputBox').append("<li><span class='circle' style='background:gold;'></span><span style='gold'>——————></span></li>"
            + "<li><span class='circle' style='background:green;'></span><span style='color:green'>——————></span></li>"
            + "<li><span class='circle' style='background:blueviolet;'></span><span style='color:blueviolet'>——————></span></li>"
            + "<li><span class='circle' style='background:hotpink;'></span><span style='color:hotpink'>——————></span></li>"
            + "<li><span class='circle' style='background:red;'></span><span style='color:red'>——————></span></li>"
            + "<li><span class='circle' style='background:orange;'></span><span style='color:orange'>——————></span></li>");
        node.addClass('inputNode0');
    } else {
        $('.inputNode0').removeClass('inputNode0');
        $('#inputBox').children().remove();
        $('#inputBox').append("<li><span class='circle' style='background:gold;'></span><span style='gold'>——————></span></li>"
            + "<li><span class='circle' style='background:green;'></span><span style='color:green'>——————></span></li>"
            + "<li><span class='circle' style='background:blueviolet;'></span><span style='color:blueviolet'>——————></span></li>"
            + "<li><span class='circle' style='background:hotpink;'></span><span style='color:hotpink'>——————></span></li>"
            + "<li><span class='circle' style='background:red;'></span><span style='color:red'>——————></span></li>"
            + "<li><span class='circle' style='background:orange;'></span><span style='color:orange'>——————></span></li>");
        node.addClass('inputNode0');
    }

    node = '';

}

//输出
function drop2(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("Texthtml");
    var dataid = ev.dataTransfer.getData("Textid");
    str = node.text();

    var myChart = echarts.init(document.getElementById('main'));
    option = {
        tooltip: {
            formatter: function (x) {
                return x.data.des;
            }
        },
        series: [
            {
                type: 'graph',
                layout: 'force',
                symbolSize: 30,
                roam: true,
                edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [4, 10],
                edgeLabel: {
                    normal: {
                        textStyle: {
                            fontSize: 12
                        }
                    }
                },
                force: {
                    repulsion: 50,//子节点之间的间距
                    edgeLength: [10, 30]//连线的长度
                },
                draggable: true,
                itemStyle: {
                    normal: {
                        color: '#4b565b'
                    }
                },
                lineStyle: {
                    normal: {
                        width: 2,
                        color: '#4b565b'
                    }
                },
                edgeLabel: {
                    normal: {
                        show: true,
                        formatter: function (x) {
                            return x.data.name;
                        }
                    }
                },
                label: {
                    normal: {
                        show: true,
                        textStyle: {}
                    }
                },
                data: [
                    {
                        name: 'A',
                        des: 'A',
//                          symbolSize: 100,
                        itemStyle: {
                            normal: {
                                color: '#3D90CD'
                            }
                        }
                    }, {
                        name: 'B',
                        des: 'B',
                        itemStyle: {
                            normal: {
                                color: '#3D90CD'
                            }
                        }
                    }, {
                        name: 'C',
                        des: 'C',
//                          symbolSize: 100
                    }, {
                        name: 'D',
                        des: 'D',
                        itemStyle: {
                            normal: {
                                color: '#E26A73'
                            }
                        }
                    }, {
                        name: 'E',
                        des: 'E',
                        itemStyle: {
                            normal: {
                                color: '#00BEF1'
                            }
                        }
                    }
                ],
                links: [
                    {
                        source: 'A',
                        target: 'C',
                        // name: "同学"
                    }, {
                        source: 'B',
                        target: 'C',
                        name: "朋友"
                    }, {
                        source: 'D',
                        target: 'E',
                        name: "夫妻"
                    }, {
                        source: 'A',
                        target: 'E',
                        name: "夫妻"
                    }, {
                        source: 'B',
                        target: 'D',
                        name: "举报"
                    }
                ]
            }
        ]
    };
    myChart.setOption(option);

    node = '';

}

////////////////////////////////////////////////
//点击装置下的某个设备显示具体的设备
$('.jQ_treeBox ul li ul li ul li ul li ul li').find('span').click(function () {
    $('.contentLeft .params .paramsLeft').children("ul").remove();
    // $('.contentLeft .params .paramsRight').children("ul").remove();
    var index0 = $('.paramsLeft ul li').index();
    $('.paramsRight ul li').eq(index0).addClass('showLi').siblings('li').removeClass('showLi');
    $(this).parent('li').append("<i class='glyphicon glyphicon-ok posiAbsolu treeTure'></i>");
    if ($(this).html() == "管段") {
        $('.contentLeft .params .paramsLeft').append("<ul style='display: none;'><li class='tab' draggable='true' ondragstart='drag(event)'>管段0</li><li draggable='true' ondragstart='drag(event)'>管段1</li><li draggable='true' ondragstart='drag(event)'>管段2</li><li draggable='true' ondragstart='drag(event)'>管段3</li><li draggable='true' ondragstart='drag(event)'>管段4</li><li draggable='true' ondragstart='drag(event)'>管段5</li> <li class='tab' draggable='true' ondragstart='drag(event)'>管段6</li></ul>");
    } else if ($(this).html() == "设备") {
        $('.contentLeft .params .paramsLeft').append("<ul style='display: none;'><li class='tab' draggable='true' ondragstart='drag(event)'>设备0</li><li draggable='true' ondragstart='drag(event)'>设备1</li><li draggable='true' ondragstart='drag(event)'>设备2</li><li draggable='true' ondragstart='drag(event)'>设备3</li><li draggable='true' ondragstart='drag(event)'>设备4</li><li draggable='true' ondragstart='drag(event)'>设备5</li> <li class='tab' draggable='true' ondragstart='drag(event)'>设备6</li></ul>");
    } else if ($(this).html() == "阀门") {
        $('.contentLeft .params .paramsLeft').append("<ul style='display: none;'><li class='tab' draggable='true' ondragstart='drag(event)'>阀门0</li><li draggable='true' ondragstart='drag(event)'>阀门1</li><li draggable='true' ondragstart='drag(event)'>阀门2</li><li draggable='true' ondragstart='drag(event)'>阀门3</li><li draggable='true' ondragstart='drag(event)'>阀门4</li><li draggable='true' ondragstart='drag(event)'>阀门5</li> <li class='tab' draggable='true' ondragstart='drag(event)'>阀门6</li></ul>");
    } else if ($(this).html() == "三通") {
        $('.contentLeft .params .paramsLeft').append("<ul style='display: none;'><li class='tab' draggable='true' ondragstart='drag(event)'>三通0</li><li draggable='true' ondragstart='drag(event)'>三通1</li><li draggable='true' ondragstart='drag(event)'>三通2</li><li draggable='true' ondragstart='drag(event)'>三通3</li><li draggable='true' ondragstart='drag(event)'>三通4</li><li draggable='true' ondragstart='drag(event)'>三通5</li> <li class='tab' draggable='true' ondragstart='drag(event)'>三通6</li></ul>");
    } else if ($(this).html() == "盲板") {
        $('.contentLeft .params .paramsLeft').append("<ul style='display: none;'><li class='tab' draggable='true' ondragstart='drag(event)'>盲板0</li><li draggable='true' ondragstart='drag(event)'>盲板1</li><li draggable='true' ondragstart='drag(event)'>盲板2</li><li draggable='true' ondragstart='drag(event)'>盲板3</li><li draggable='true' ondragstart='drag(event)'>盲板4</li><li draggable='true' ondragstart='drag(event)'>盲板5</li> <li class='tab' draggable='true' ondragstart='drag(event)'>盲板6</li></ul>");
    } else if ($(this).html() == "采样点") {
        $('.contentLeft .params .paramsLeft').append("<ul style='display: none;'><li class='tab' draggable='true' ondragstart='drag(event)'>采样点0</li><li draggable='true' ondragstart='drag(event)'>采样点1</li><li draggable='true' ondragstart='drag(event)'>采样点2</li><li draggable='true' ondragstart='drag(event)'>采样点3</li><li draggable='true' ondragstart='drag(event)'>采样点4</li><li draggable='true' ondragstart='drag(event)'>采样点5</li> <li class='tab' draggable='true' ondragstart='drag(event)'>采样点6</li></ul>");
    }
    $('.contentLeft .params .paramsLeft ul').show('slow');
    //参数
    $('.paramsLeft ul li').click(function () {
        $(this).css({'color': 'black'}).siblings('li').css({'color': 'rgb(59, 144, 206)'});
        $('.paramsRight ul').show('slow');
        var index0 = $(this).index();
        $('.paramsRight ul li').eq(index0).addClass('showLi').siblings('li').removeClass('showLi');
    })


})
//////////////////////////////////////////
//时间格式转换成数字格式
function getparam(str) {
    var arr = str.split(' ');
    var arr01 = arr[0].split("-");
    var arr02 = arr[1].split(":");
    var newArr = arr01.concat(arr02);
    var newStr = newArr.join('');
    return newStr;
}

//拖拽函数
jQuery(function ($) {
    //拖放开始:获取id放入dataTransfer
    $("ul").on("dragstart", ".movableItem", function (e) {
        e.originalEvent.dataTransfer.setData("obj_add", e.target.id);
    });

    //允许放入
    $(".jQ_demoBox_b ").on("dragover", ".jQ_dragCon", function (e) {
        e.originalEvent.preventDefault();
    })

    //放下事件
    $(".jQ_demoBox_b ").on("drop", ".jQ_dragCon", function (e) {
        var id = e.originalEvent.dataTransfer.getData("obj_add");
        //给新加入的元素添加拖放事件
        $("#" + id).on("dragstart", function (e) {
            e.originalEvent.dataTransfer.setData("obj_remove", e.target.id);
        });

        var demoSma = $("#" + id).html();
        if ($(this).children('table').length > 0) {
            $(this).children('table').remove();
            var demoId0 = $(this).attr('id');
//				alert(demoId0);
            $(this).append("<table>"
                + "<tr style='height:60px'>"
                + "<td width='20%' style='height:60px'>算法名称</td>"
                + "<td width='30%' style='height:60px'>"
                + " <input type='' value='根原因算法' style='width:120px;height:60px' >"
                + "</td>"
                + "<td  width='20%' style='height:60px'>算法id</td>"
                + "<td width='30%' style='height:60px'>"
                + "<input type=' ' name=' ' value='60' style='width:120px;height:60px'>"
                + "</td>"
                + "</tr>"
                + "<tr style='height:60px'>"
                + "<td width='20%' style='height:60px'>agentCode</td>"
                + "<td colspan='1' style='height:60px'>"
                + "<input type=' ' name=' ' value='10' style='width:120px;height:60px'>"
                + "</td>"
                + "</tr>"
                + "</table>")
        }
    })
});


//***********控制树************//
$(function () {
    $('.trees li:has(ul)').addClass('parent_li').find(' > span').attr('title', '关闭');
    $('.trees li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li').find(' > ul > li');
        if (children.is(":visible")) {
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
function allowDrop(ev) {
    ev.preventDefault();
    var node;
}
function allowDrop2(ev) {
    ev.preventDefault();
    var node;
}

function drag(ev) {
    ev.dataTransfer.setData("Texthtml", $(ev.target).parent().parent().html());
    ev.dataTransfer.setData("Textid", $(ev.target).parent().parent().id);
    node = $(ev.target);
    console.log(node);
    node.addClass('dragNode');
}
colorIndex=0;
//输入
function drop(ev) {
    var colorArr=["#3D90CD","#42BCC8","#E26A73","#00BEF1","#8882BE","#F8B086"];
    if(colorIndex<colorArr.length){
        colorIndex++;
    }else{
        colorIndex=colorArr.length;
    }
    // $('#inputBox').children().remove();
    ev.preventDefault();
    var data = ev.dataTransfer.getData("Texthtml");
    var dataid = ev.dataTransfer.getData("Textid");
    str = node.text();
    console.log(node.parents(".agentCodeType"));
    if (node.parent().parent().hasClass("agentCodeType")) {
        if ($('#inputBox').children().length == 0) {
            $('#inputBox').append("<li class='agentCodeLi'><span class='circle' style='background:#3D90CD;'><i>CII-FFEE3301</i></span><span style='#3D90CD'>——————></span></li>"
                + "<li  class='agentCodeLi'><span class='circle' style='background:#42BCC8;'><i>CII-FFEE3301</i></span><span style='color:#42BCC8'>——————></span></li>"
                + "<li  class='agentCodeLi'><span class='circle' style='background:#E26A73;'><i>CII-FFEE3301</i></span><span style='color:#E26A73'>——————></span></li>"
                + "<li  class='agentCodeLi'><span class='circle' style='background:#00BEF1;'><i>CII-FFEE3301</i></span><span style='color:#00BEF1'>——————></span></li>"
                + "<li class='agentCodeLi'><span class='circle' style='background:#F8B086;'><i>CII-FFEE3301</i></span><span style='color:#F8B086'>——————></span></li>"
                + "<li class='agentCodeLi'><span class='circle' style='background:#8882BE;'><i>CII-FFEE3301</i></span><span style='color:#8882BE'>——————></span></li>");
            node.addClass('inputNode0');
        } else {
            $('.inputNode0').removeClass('inputNode0');
            $('#inputBox').children().remove();
            $('#inputBox').append("<li class='agentCodeLi'><span class='circle' style='background:#3D90CD;'><i>CII-FFEE5501</i></span><span style='#3D90CD'>——————></span></li>"
                + "<li class='agentCodeLi'><span class='circle' style='background:#42BCC8;'><i>CII-FFEE5501</i></span><span style='color:#42BCC8'>——————></span></li>"
                + "<li class='agentCodeLi'><span class='circle' style='background:#E26A73;'><i>CII-FFEE6601</i></span><span style='color:#E26A73'>——————></span></li>"
                + "<li class='agentCodeLi'><span class='circle' style='background:#00BEF1;'><i>CII-FFEEff01</i></span><span style='color:#00BEF1'>——————></span></li>"
                + "<li class='agentCodeLi'><span class='circle' style='background:#F8B086;'><i>CII-FIIEE3301</i></span><span style='color:#F8B086'>——————></span></li>"
                + "<li class='agentCodeLi'><span class='circle' style='background:#8882BE;'><i>CII-FFPP3301</i></span><span style='color:#8882BE'>——————></span></li>");
        }
    } else {
        $('#inputBox').children(".agentCodeLi").remove();
        $("#inputBox").append("<li><span class='circle' style='background:"+colorArr[colorIndex]+";'><i>CII-FFGE3401</i></span><span style='color:"+colorArr[colorIndex]+"'>——————></span></li>");
        node.addClass('inputNode0');
    }
    node = '';
    // stepBox01();stepBox02();stepBox03();stepBox04();stepBox05();demoDele();
}

//输出

////////////////////////////////////////////////
//树


//点击具体设备显示度量指标
$('.paramsLeft ul li').click(function () {
    $(this).css({'color': 'black'}).siblings('li').css({'color': 'rgb(59, 144, 206)'});
    $('.paramsRight ul').show('slow');
    var index0 = $(this).index();
    $('.paramsRight ul li').eq(index0).addClass('showLi').siblings('li').removeClass('showLi');
})


// 点击测试按钮小球连线
$(".buttonCeshi_c").click(function () {
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        tooltip: {
            formatter: function (x) {
                return x.data.des;
            }
        },
        series: [
            {
                type: 'graph',
                layout: 'force',
                symbolSize: 30,
                // roam: true,
                // edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [4, 10],
                edgeLabel: {
                    normal: {
                        textStyle: {
                            fontSize: 12
                        }
                    }
                },
                force: {
                    repulsion: 100,//子节点之间的间距
                    edgeLength: [10, 250]//连线的长度
                },
                // draggable: true,
                itemStyle: {
                    normal: {
                        color: '#4b565b'
                    }
                },
                lineStyle: {
                    normal: {
                        width: 2,
                        color: '#4b565b'
                    }
                },
                edgeLabel: {
                    normal: {
                        // show: true,//在小球上显示箭头和关系
                        formatter: function (x) {
                            return x.data.name;
                        }
                    }
                },
                label: {
                    normal: {
                        show: true,//在小球上显示字
                        textStyle: {}
                    }
                },
                data: [
                    {
                        name: 'A',
                        des: 'A',
                        // symbolSize: 100,
                        itemStyle: {
                            normal: {
                                color: '#3D90CD'
                            }
                        }
                    }, {
                        name: 'B',
                        des: 'B',
                        itemStyle: {
                            normal: {
                                color: '#42BCC8'
                            }
                        }
                    }, {
                        name: 'C',
                        des: 'C',
//                          symbolSize: 100
                    }, {
                        name: 'D',
                        des: 'D',
                        itemStyle: {
                            normal: {
                                color: '#E26A73'
                            }
                        }
                    }, {
                        name: 'E',
                        des: 'E',
                        itemStyle: {
                            normal: {
                                color: '#E26A73'
                            }
                        }
                    },
                    {
                        name: 'F',
                        des: 'F',
                        itemStyle: {
                            normal: {
                                color: '#E26A73'
                            }
                        }
                    },
                    {
                        name: 'G',
                        des: 'G',
                        itemStyle: {
                            normal: {
                                color: '#E26A73'
                            }
                        }
                    },
                    {
                        name: 'H',
                        des: 'H',
                        itemStyle: {
                            normal: {
                                color: '#00BEF1'
                            }
                        }
                    },
                    {
                        name: 'M',
                        des: 'M',
                        itemStyle: {
                            normal: {
                                color: '#F8B086'
                            }
                        }
                    }

                ],
                links: [
                    {
                        source: 'A',
                        target: 'G',
                        // name: "同学"
                    }, {
                        source: 'F',
                        target: 'E',
                        // name: "朋友"
                    }, {
                        source: 'D',
                        target: 'E',
                        // name: "夫妻"
                    }, {
                        source: 'A',
                        target: 'E',
                        // name: "夫妻"
                    }, {
                        source: 'B',
                        target: 'D',
                        // name: "举报"
                    },
                    {
                        source: 'D',
                        target: 'E',
                        // name: "同学"
                    },
                    {
                        source: 'F',
                        target: 'C',
                        // name: "同学"
                    },
                    {
                        source: 'M',
                        target: 'B',
                        // name: "同学"
                    },
                    {
                        source: 'A',
                        target: 'H',
                        // name: "同学"
                    },
                    {
                        source: 'D',
                        target: 'M',
                        // name: "同学"
                    },
                ]
            }
        ]
    };
    myChart.setOption(option);
    $("#main").show();
})
//////////////////////////////////////////

//算法
//	$.ajax({
//		type: "GET",
//      url: http0 + "/ICT/agentAlgorithms?agentType=3",
//		data: {},
//		dataType: "json",
//		contentType: "application/json;charset=utf-8",
//		async: true,
//		success: function(result){
////			console.log(result);
//			var info = $.ET.toObjectArr(result);
////			console.log(info);
//			$(info).each(function(i,item){
////				console.log(item);
////				console.log(i);//i代表数据的索引
//				$('.ul_movable').append('<li class="movable movableItem" draggable="true" id="' +item.id+ '" value="' +item.id+ '">' +item.algorithmName+ '</li>');
//			});
//		},
//		error: function(XMLHttpRequest, textStatus, errorThrown) {
//			alert('网络出错啦');
//		}
//	})

////////////////////////////////////////////////


///////////////////////////////////////////////////
//时间格式转换成数字格式
function getparam(str) {
    var arr = str.split(' ');
    var arr01 = arr[0].split("-");
    var arr02 = arr[1].split(":");
    var newArr = arr01.concat(arr02);
    var newStr = newArr.join('');
    return newStr;
}


//拖拽函数
jQuery(function ($) {
    //拖放开始:获取id放入dataTransfer
    $("ul").on("dragstart", ".movableItem", function (e) {
        console.log(e.target);
        $('.jQstage_stepThree .addDemoDragBCon').css("display", "block");

        e.originalEvent.dataTransfer.setData("obj_add", e.target.id);
    });

    $(".agentCodeType").on("dragstart", function (e) {
        $(".inputBoxDrag").on("drop", function (e) {
            console.log(e.target);
        })
    })
    $(".measuresMetric").on("dragstart", function (e) {

    })


    //允许放入
    $(".jQ_demoBox_b ").on("dragover", ".jQ_dragCon", function (e) {
        e.originalEvent.preventDefault();

        // $('.jQstage_stepThree .addDemoDragBCon').css("display","block");
    })


    //放下事件
    $(".jQ_demoBox_b ").on("drop", ".jQ_dragCon", function (e) {
        sizeDemo();
        var id = e.originalEvent.dataTransfer.getData("obj_add");
        console.log(id);
        // dependencyMovable
        var demoSma = $("#" + id).html();
        $(this).children('table').remove();
        if (demoSma == "根原因算法") {
            $(this).find("span").remove();
            var demoId0 = $(this).attr('id');
            console.log($(this));
            $(this).append("<span style='font-size:20px'>"+demoSma+"</span>")
//				alert(demoId0);
            sizeDemo();

        }



    })
});
