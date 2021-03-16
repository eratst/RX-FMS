/**
 * 该对象从Controller获取一个json object，编译成ui-grid需要的内容
 * @param gridDef 定义每一列的属性
 * @constructor
 */
//var GridFormatter = function(gridDef) {
	//if (!(gridDef["modelName"] && gridDef["attribute"] && gridDef["apiUrl"])){
	//  //console.log("It's a completed object");
	//} else {
	//  return;
	//}
	//
	//this.modelName = gridDef["modelName"];
	//this.attribute = gridDef["attribute"];
	//this.url = gridDef["apiUrl"];
	//this.getJsonFormat();
//};



//根据mTable.type 给table.value排序
//将restful转为array
//是否可以 

/**
 * 
 * @param {Object} mTable
 */


var GridFormatter = function(tableType,tableValue,alterBasicKey) {
	if (!(tableType&& tableValue && alterBasicKey)){
	 console.log("It's not a completed object");	 
	} 
	this.tableType=tableType;
	this.alterBasicKey=alterBasicKey;
	this.alterKeyIndex=this.getAlterKeyIndex();
	this.tableValue=cj.Parse(tableValue,true);
}
/**
 * 
 * @param {Object} tableValue 原始tableValue.collection.items
 */
GridFormatter.prototype.getFormatTableValue = function(items) {
	var tableValue = [];
	//遍历表头属性
	var tableTypeKeys=this.getKeys();
	//检查长度是否一致，检查字段是否对应
	this.check(tableTypeKeys, items[0].data);

	for(var i = 0; i < items.length; i++) {		
		tableValue[i] = [];
		// 	console.log(i)
		for(var j = 0; j < items[i].data.length; j++) {
			
			var sortNumber = this.judgekeyIndex(items[i].data[j].name, tableTypeKeys);
			tableValue[i][sortNumber] = items[i].data[j].value;
			

		}
	}
	return tableValue;

}


GridFormatter.prototype.judgekeyIndex = function(key, tableTypeKeys) {
	for(var i = 0; i < tableTypeKeys.length; i++) {
		if(key == tableTypeKeys[i]) {
			return i;
		}
	}

}

GridFormatter.prototype.getKeyIndex = function(keyArg) {
	var i=0;
	console.log(this.tableType)
	for(var key in this.tableType) {
		if(key === keyArg) {
			return i;
		}
		i++;
	}

}




/**
 * 检查type，value字段是否对应的方法
 * @param {Object} keys ：type中的keys
 * @param {Object} value ：data数据
 */
GridFormatter.prototype.check = function(keys, value) {

	if(keys.length != value.length) {
		console.log("前后端字段长度不一致"+keys.length+"<>"+value.length);
		
	}
	//判断当前keys[]是否在value中有对应值
	for(var i = 0; i < keys.length; i++) {
		var tag = false;
		for(var j = 0; j < value.length; j++) {
			if(keys[i] == value[j].name) {
				tag = true;
				
			}
		}
		if(tag==false){
			console.log("重要1-------->type数组从0开始第"+i+"个元素:"+keys[i]+" 在value里找不到对应");
		}
	}
	//判断当前value在head中是否有对应值
	for(var i = 0; i < value.length; i++) {
		var tag = false;
		for(var j = 0; j < keys.length; j++) {
			
			if(value[i].name == keys[j]) {
				tag = true;
			}
		}
		if(tag==false){
			console.log("重要-------->type数组从0开始第"+i+"个元素:"+value[i].name+" 在value里找不到对应");
		}
	}

//	console.log("check 结束");
}

/**
 * 向ui-grid返回该表的格式
 * @return {Array} 给ui-grid的columnDefs参数
 */

//GridFormatter.prototype.getGridFormat = function(){
//var columnDefs = [];
//this.attribute.forEach(function(elem){
//  var theColDef = {
//    name:elem["name"],
//    displayName:elem["display"],
//    width: elem["width"] || "10%" //在这里加个正则验证width是不是百分数？
//  };
//  if(elem["type"] === "option") {
//    //TODO 考虑把这个拎出来当作service注入到angular里
//    var xhr = new XMLHttpRequest();
//    //xhr.open("GET", "model/" + elem["option"] + ".json");
//    xhr.open("GET", "model/" + elem["option"] + ".json");
//    xhr.responseType = "json";
//    //xhr.addEventListener("load", function());
//    xhr.send();
//  } else {
//    theColDef.type = elem["type"];
//  }
////  /console.log(theColDef);
//  columnDefs.push(theColDef);
//});
//return columnDefs;
//};

//构造mTable Json
GridFormatter.prototype.getJsonFormat = function() {
	var mTable = {
		"tableType": [],
		"tableValue": []
	};
	mTable.tableType = this.getGridFormat();
	for(i = 0; i < 200; i++) {
		mTable.tableValue = this.getBackJson();
	}
	console.log(mTable);

}
/***
 * 获取tableType主键
 */
GridFormatter.prototype.getKeys = function() {
	var keys = [];
	var i=0;
	for(var key in this.tableType) {
		if(this.tableType.hasOwnProperty(key)) {
			keys[i] = key;
		}
		i++;

	}
	return keys;

}

GridFormatter.prototype.getAlterKeyIndex = function() {
	var keys = [];
	var i=0;
	for(var key in this.tableType) {
		if(this.alterBasicKey===key){
			return i;
		}
		i++;

	}
	return keys;

}