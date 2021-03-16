/*
 * @author gyy
 * tab切换
 */
$(function(){
	
	$(".addBtn").click(function(){
		layer.open({
		  type: 2,
		  title: '新建智能点',
		  shadeClose: true,
		  shade: 0.6,
		  area: ['602px', '380px'],
		  content: 'addPoint.html'
		});
	})
})