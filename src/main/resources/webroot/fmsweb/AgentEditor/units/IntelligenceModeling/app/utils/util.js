/**
 * 车间下级一共可以有7个区域
 * id | 类别  | name
 * 1  | 装置  |  plants
 * 2  | 罐区  | tankAreas
 * 3  | 仓库  | warehouses
 * 4  | 装卸台 | loadingDocks
 * 5  | 管网  | pipeNetworks
 * 6  | 办公区 | administrations
 * 7  | 生活区 | communities
 *
 1	侧线
 2	罐
 3	料仓
 4	库位
 5	进出厂点
 6	采样点
 7	排放口
 8	设备
 9	管段
 10	阀门
 11	盲板
 12	三通
 */
function area2node(areaTypeCode) {
  var rtn = [];
  switch(areaTypeCode) {
    case 'plants':
      rtn = [
        {nodeId:1, nodeNameCN:'侧线', nodeNameEN:'sideLines'},
        {nodeId:3, nodeNameCN:'料仓', nodeNameEN:'silos'},
        {nodeId:7, nodeNameCN:'排放口', nodeNameEN:'outlets'},
        {nodeId:8, nodeNameCN:'设备', nodeNameEN:'equipments'},
        {nodeId:6, nodeNameCN:'采样点', nodeNameEN:'samplePoints'},
        {nodeId:10, nodeNameCN:'阀门', nodeNameEN:'valves'},
        {nodeId:11, nodeNameCN:'盲板', nodeNameEN:'plates'},
        {nodeId:12, nodeNameCN:'三通', nodeNameEN:'tees'},  
        {nodeId:12, nodeNameCN:'生产单元', nodeNameEN:'cell'}  
      ];
      break;
    case 'tankAreas':
      rtn = [
        {nodeId:2, nodeNameCN:'罐', nodeNameEN:'tanks'},
        {nodeId:7, nodeNameCN:'排放口', nodeNameEN:'outlets'},
        {nodeId:8, nodeNameCN:'设备', nodeNameEN:'equipments'},
        {nodeId:9, nodeNameCN:'管段', nodeNameEN:'tubulations'},
        {nodeId:6, nodeNameCN:'采样点', nodeNameEN:'samplePoints'},
        {nodeId:10, nodeNameCN:'阀门', nodeNameEN:'valves'},
        {nodeId:11, nodeNameCN:'盲板', nodeNameEN:'plates'},
        {nodeId:12, nodeNameCN:'三通', nodeNameEN:'tees'} 
      ];
      break;
    case 'warehouses':
      rtn = [
        {nodeId:6, nodeNameCN:'采样点', nodeNameEN:'samplePoints'},
        {nodeId:4, nodeNameCN:'库位', nodeNameEN:'stocks'},
        {nodeId:10, nodeNameCN:'阀门', nodeNameEN:'valves'},
         {nodeId:12, nodeNameCN:'三通', nodeNameEN:'tees'} ,
        {nodeId:7, nodeNameCN:'排放口', nodeNameEN:'outlets'},
        {nodeId:9, nodeNameCN:'管段', nodeNameEN:'tubulations'},
        {nodeId:11, nodeNameCN:'盲板', nodeNameEN:'plates'},
        {nodeId:8, nodeNameCN:'设备', nodeNameEN:'equipments'}
      ];
      break;
    case 'loadingDocks':
      rtn = [
        {nodeId:5, nodeNameCN:'进出厂点', nodeNameEN:'edgePoints'},
        {nodeId:7, nodeNameCN:'排放口', nodeNameEN:'outlets'},
        {nodeId:9, nodeNameCN:'管段', nodeNameEN:'tubulations'},
        {nodeId:12, nodeNameCN:'三通', nodeNameEN:'tees'} ,
        {nodeId:11, nodeNameCN:'盲板', nodeNameEN:'plates'},
        {nodeId:10, nodeNameCN:'阀门', nodeNameEN:'valves'},
        {nodeId:8, nodeNameCN:'设备', nodeNameEN:'equipments'}
      ];
      break;
    case 'pipeNetworks':
      rtn = [
        {nodeId:7, nodeNameCN:'排放口', nodeNameEN:'outlets'},
        {nodeId:6, nodeNameCN:'采样点', nodeNameEN:'samplePoints'},
         {nodeId:12, nodeNameCN:'三通', nodeNameEN:'tees'} ,
        {nodeId:9, nodeNameCN:'管段', nodeNameEN:'tubulations'},
         {nodeId:11, nodeNameCN:'盲板', nodeNameEN:'plates'}
        
      ];
      break;
    case ('administrations' || 'communities'):
      rtn = [
        {nodeId:6, nodeNameCN:'采样点', nodeNameEN:'samplePoints'},
        {nodeId:8, nodeNameCN:'设备', nodeNameEN:'equipments'},
        {nodeId:10, nodeNameCN:'阀门', nodeNameEN:'valves'},
        {nodeId:9, nodeNameCN:'管段', nodeNameEN:'tubulations'},
        {nodeId:12, nodeNameCN:'三通', nodeNameEN:'tees'} ,
        {nodeId:11, nodeNameCN:'盲板', nodeNameEN:'plates'}

      ];
      break;
  }
//rtn.push({nodeId:6, nodeNameCN:'采样点', nodeNameEN:'samplePoints'});
////rtn.push({nodeId:9, nodeNameCN:'管段', nodeNameEN:'tubulations'});
//rtn.push({nodeId:10, nodeNameCN:'阀门', nodeNameEN:'valves'});
//rtn.push({nodeId:11, nodeNameCN:'盲板', nodeNameEN:'plates'});
//rtn.push({nodeId:12, nodeNameCN:'三通', nodeNameEN:'tees'});
  return rtn;
}

/**
 * @param areaTypeId
 * @return rtn
 */
function area2ImgUrls(areaTypeCode){
  var rtn = area2node(areaTypeCode);
  for(var i = 0; i < rtn.length; i++){
    rtn[i]['imgUrl'] = '../img/area_node/icon_' + rtn[i]['nodeId'] + '.png';
  }
  return rtn;
}

function genNodeAddPopup(deviceName){
  var rtn = [];
  switch(deviceName){
    case "侧线":
   	  rtn = [
        {position:10,required:true,inputName:"侧线进出类型",inputType:"num",attributeName:"slineInOutTypeId"},
        {position:11,required:true,inputName:"侧线业务类型",inputType:"num",attributeName:"sidelineTypeId"},
        {position:12,required:true,inputName:"物料类型id",inputType:"num",attributeName:"slineMtrlTypeId"},
        {position:12,required:true,inputName:"物料名称",inputType:"num",attributeName:"mtrlId"},
      ];
      break;
    case "罐":
      rtn = [
        {position:10,required:true,inputName:"罐类型",inputType:"option",attributeName:"tankTypeId",options:{1:'卧罐',2:'球罐',3:'立罐',4:'长输管线',7:'其他',8:'包装装置',10:'辅料罐'},optionKeys:[1,2,3,4,7,8,10]},
        {position:11,required:false,inputName:"浮盘重",inputType:"num",attributeName:"fltPlatWgt"},
        {position:12,required:false,inputName:"罐高",inputType:"num",attributeName:"tankHgt"},
        {position:13,required:false,inputName:"是否保温罐",inputType:"bool",attributeName:"htPretTank"},
        {position:14,required:false,inputName:"浮盘前高",inputType:"num",attributeName:"fltPlatPerhgt"},
        {position:15,required:false,inputName:"罐公称容积",inputType:"num",attributeName:"tankTotlCuba"},
        {position:16,required:false,inputName:"浮顶最低点",inputType:"num",attributeName:"fltTipLst"},
        {position:17,required:false,inputName:"罐安全高度",inputType:"num",attributeName:"maxTankHgt"},
        {position:18,required:false,inputName:"罐底高度",inputType:"num",attributeName:"minTankHgt"},
        {position:19,required:false,inputName:"安全罐量上限",inputType:"num",attributeName:"maxTankStoarge"},
        {position:20,required:false,inputName:"安全罐量下限",inputType:"num",attributeName:"minTankStoarge"}
      ];
      break;
    case "三通":
      rtn = [
      ];
      break;
     case "管段":
      rtn = [
         {position:10,required:true,inputName:"货架号",inputType:"num",attributeName:"rackCode"},
         {position:10,required:true,inputName:"物料名称",inputType:"num",attributeName:"mtrlName"},
      ];
      break;
     case "库位":
      rtn = [
      	 {position:10,required:true,inputName:"货架号",inputType:"num",attributeName:"rackCode"},
      	 {position:11,required:true,inputName:"层号",inputType:"num",attributeName:"rackfloorCode"},
      	 {position:12,required:true,inputName:"位号",inputType:"num",attributeName:"racklocationCode"},
      ];
      break;
     case "采样点":
      rtn = [
      ];
      break;
    case "料仓":
      rtn = [
      	 {position:10,required:true,inputName:"料仓业务类型",inputType:"num",attributeName:"siloTypeId"},
      	 {position:11,required:true,inputName:"料仓公称容积",inputType:"num",attributeName:"cubage"},
      	 {position:12,required:true,inputName:"料仓高度",inputType:"num",attributeName:"siloHgt"},
      	 {position:13,required:true,inputName:"料仓上限高度",inputType:"num",attributeName:"maxSiloHgt"},
      	 {position:14,required:true,inputName:"料仓下限高度",inputType:"num",attributeName:"minSiloHgt"},
      	 {position:15,required:true,inputName:"安全料仓量上限",inputType:"num",attributeName:"maxSiloStoarge"},
      	 {position:16,required:true,inputName:"安全料仓量下限",inputType:"num",attributeName:"minSiloStoarge"},
      ];
      break;
    case "进出厂点":
    	rtn = [
        {position:10,required:true,inputName:"运输类型ID",inputType:"num",attributeName:"transTypeId"},
        {position:11,required:true,inputName:"进出类型ID",inputType:"num",attributeName:"inOutTypeId"},
      ];
      break;
     case "盲板":
    	rtn = [
        {position:10,required:true,inputName:"直径",inputType:"num",attributeName:"diameter"},
        {position:11,required:true,inputName:"厚度",inputType:"num",attributeName:"thickness"},
      ];
      break;
     case "设备":
    	rtn = [
        {position:10,required:true,inputName:"设备管理码",inputType:"num",attributeName:"equMgrCode"},
        {position:11,required:true,inputName:"设备工艺类型ID",inputType:"num",attributeName:"technicId"},
        {position:12,required:true,inputName:"设备名称",inputType:"num",attributeName:"technicName"},
      ];
      break;
      case "排放口":
    	rtn = [
        {position:10,required:true,inputName:"标志牌类别id",inputType:"num",attributeName:"signboardTypeId"},
        {position:11,required:true,inputName:"标志牌类别名称",inputType:"num",attributeName:"signboardTypeName"},
      ];
      break;
      case "阀门":
    	rtn = [
      ];
      break;
    default:
      break;
  }
  rtn.push({position:1,required:true,inputName:"节点类型ID",inputType:"",attributeName:"nodeTypeId"});
  rtn.push({position:2,required:true,inputName:"编码",inputType:"",attributeName:"nodeCode"});
  rtn.push({position:3,required:true,inputName:"简称",inputType:"",attributeName:"nodeAlias"});
  rtn.push({position:4,required:true,inputName:"名称",inputType:"",attributeName:"nodeName"});

  rtn.push({position:31,required:false,inputName:"位置经度",inputType:"num",attributeName:"nodeLatitude"});
  rtn.push({position:32,required:false,inputName:"位置纬度",inputType:"num",attributeName:"nodeAltitude"});
  rtn.push({position:33,required:false,inputName:"位置海拔",inputType:"num",attributeName:"nodeLongitude"});
  return rtn;
}

//待重写
function genMsrAddPopup(){
  return [
    {position:1,required:true,inputName:"度量指标ID",inputType:"",attributeName:"idxId"},
    {position:2,required:true,inputName:"度量指标编码",inputType:"",attributeName:"idxCode"},
    {position:3,required:true,inputName:"度量指标名称",inputType:"",attributeName:"idxName"},
    {position:4,required:true,inputName:"度量指标简称",inputType:"",attributeName:"idxAlias"},
    {position:5,required:true,inputName:"度量指标公式",inputType:"",attributeName:"idxFormula"}
  ];
}
function genMsrPopup(){
  return [
    {position:1,required:true,inputName:"度量指标类型",inputType:"",attributeName:"idxTypeName"},
    {position:2,required:true,inputName:"度量指标编码",inputType:"",attributeName:"idxCode"},
    {position:3,required:true,inputName:"度量指标名称",inputType:"",attributeName:"idxName"},
    {position:4,required:true,inputName:"度量指标简称",inputType:"",attributeName:"idxAlias"},
    {position:5,required:true,inputName:"度量指标公式",inputType:"",attributeName:"idxFormula"}
  ];
}
//获取组织机构的信息
function OrgPopup(){
  return [
    {position:1,required:true,inputName:"组织机构名称",inputType:"",attributeName:"orgName",flag:3},
    {position:2,required:true,inputName:"组织机构简称",inputType:"",attributeName:"orgAlias",flag:3},
    {position:3,required:true,inputName:"组织机构编码",inputType:"",attributeName:"orgCode",flag:3},
    {position:4,required:true,inputName:"组织机构类型ID",inputType:"",attributeName:"orgTypeId",flag:3},
    {position:5,required:true,inputName:"业务域名称",inputType:"",attributeName:"bizName",flag:3},
    {position:6,required:true,inputName:"业务域简称",inputType:"",attributeName:"bizAlias",flag:3},
    {position:7,required:true,inputName:"业务域编码",inputType:"",attributeName:"bizCode",flag:3}
  ];
};
function OrgUpdatePopup(){
  return [
    {position:1,required:true,inputName:"组织机构名称",inputType:"",attributeName:"orgName",flag:3},
    {position:2,required:true,inputName:"组织机构简称",inputType:"",attributeName:"orgAlias",flag:3},
    {position:3,required:true,inputName:"组织机构编码",inputType:"",attributeName:"orgCode",flag:3},
//  {position:4,required:true,inputName:"组织机构类型",inputType:"",attributeName:"orgType",flag:3},
//  {position:5,required:true,inputName:"业务域名称",inputType:"",attributeName:"bizName",flag:3},
//  {position:6,required:true,inputName:"业务域简称",inputType:"",attributeName:"bizAlias",flag:3},
//  {position:7,required:true,inputName:"业务域编码",inputType:"",attributeName:"bizCode",flag:3}
  ];
};
//-----------------------------------------------------------------------
//1	侧线
// 2	罐
// 3	料仓
// 4	库位
// 5	进出厂点
// 6	采样点
// 7	排放口
// 8	设备
// 9	管段
// 10	阀门
// 11	盲板
// 12	三通



function genNodeUpdataPopup(deviceName){
  var rtn = [];
  switch(deviceName){
    case 1:
   	  rtn = [
        {position:10,required:true,inputName:"侧线进出类型",inputType:"num",attributeName:"slineInOutTypeId"},
        {position:11,required:true,inputName:"侧线业务类型",inputType:"num",attributeName:"sidelineTypeId"},
        {position:11,required:true,inputName:"物料类型id",inputType:"num",attributeName:"slineMtrlTypeId"},
        {position:11,required:true,inputName:"物料名称",inputType:"num",attributeName:"mtrlId"},
      ];
      break;
    case 2:
      rtn = [
        {position:10,required:true,inputName:"罐类型",inputType:"option",attributeName:"tankTypeId",options:{1:'卧罐',2:'球罐',3:'立罐',4:'长输管线',7:'其他',8:'包装装置',10:'辅料罐'},optionKeys:[1,2,3,4,7,8,10]},
        {position:11,required:false,inputName:"浮盘重",inputType:"num",attributeName:"fltPlatWgt"},
        {position:12,required:false,inputName:"罐高",inputType:"num",attributeName:"tankHgt"},
        {position:13,required:false,inputName:"是否保温罐",inputType:"bool",attributeName:"htPretTank"},
        {position:14,required:false,inputName:"浮盘前高",inputType:"num",attributeName:"fltPlatPerhgt"},
        {position:15,required:false,inputName:"罐公称容积",inputType:"num",attributeName:"tankTotlCuba"},
        {position:16,required:false,inputName:"浮顶最低点",inputType:"num",attributeName:"fltTipLst"},
        {position:17,required:false,inputName:"罐安全高度",inputType:"num",attributeName:"maxTankHgt"},
        {position:18,required:false,inputName:"罐底高度",inputType:"num",attributeName:"minTankHgt"},
        {position:19,required:false,inputName:"安全罐量上限",inputType:"num",attributeName:"maxTankStoarge"},
        {position:20,required:false,inputName:"安全罐量下限",inputType:"num",attributeName:"minTankStoarge"}
      ];
      break;
    case 12:
      rtn = [
      ];
      break;
    case 4:
      rtn = [
      	 {position:10,required:true,inputName:"货架号",inputType:"num",attributeName:"rackCode"},
      	 {position:11,required:true,inputName:"层号",inputType:"num",attributeName:"rackfloorCode"},
      	 {position:12,required:true,inputName:"位号",inputType:"num",attributeName:"racklocationCode"},
      ];
      break;
    case 6:
      rtn = [
      	 {position:10,required:true,inputName:"业务类型id",inputType:"num",attributeName:"samplePointTypeId"},
      ];
      break;
    case 3:
      rtn = [
      	 {position:10,required:true,inputName:"料仓业务类型",inputType:"num",attributeName:"siloTypeId"},
      	 {position:10,required:true,inputName:"料仓公称容积",inputType:"num",attributeName:"cubage"},
      	 {position:10,required:true,inputName:"料仓高度",inputType:"num",attributeName:"siloHgt"},
      	 {position:10,required:true,inputName:"料仓上限高度",inputType:"num",attributeName:"maxSiloHgt"},
      	 {position:10,required:true,inputName:"料仓下限高度",inputType:"num",attributeName:"minSiloHgt"},
      	 {position:10,required:true,inputName:"安全料仓量上限",inputType:"num",attributeName:"maxSiloStoarge"},
      	 {position:10,required:true,inputName:"安全料仓量下限",inputType:"num",attributeName:"minSiloStoarge"},
      ];
      break;
    case 9:
      rtn = [
        {position:10,required:true,inputName:"物料名称",inputType:"num",attributeName:"mtrlName"},
      ];
      break;
    case 5:
    	rtn = [
        {position:10,required:true,inputName:"运输类型ID",inputType:"num",attributeName:"transTypeId"},
        {position:11,required:true,inputName:"进出类型ID",inputType:"num",attributeName:"inOutTypeId"},
      ];
      break;
    case 11:
       rtn = [
        {position:10,required:true,inputName:"直径",inputType:"num",attributeName:"diameter"},
        {position:11,required:true,inputName:"厚度",inputType:"num",attributeName:"thickness"},
      ];
      break;
    case 7:
       rtn = [
        {position:10,required:true,inputName:"标志牌类别id",inputType:"num",attributeName:"signboardTypeId"},
        {position:11,required:true,inputName:"标志牌类别名称",inputType:"num",attributeName:"signboardTypeName"},
      ];
      break;
    case 8:
       rtn = [
       {position:10,required:true,inputName:"设备管理码",inputType:"num",attributeName:"equMgrCode"},
        {position:11,required:true,inputName:"设备工艺类型ID",inputType:"num",attributeName:"technicId"},
        {position:12,required:true,inputName:"设备名称",inputType:"num",attributeName:"technicName"},
      ];
      break;
    case 10:
       rtn = [
      ];
      break;
    default:
      break;
  }
  rtn.push({position:1,required:true,inputName:"节点类型ID",inputType:"",attributeName:"nodeTypeId"});
  rtn.push({position:2,required:true,inputName:"编码",inputType:"",attributeName:"nodeCode"});
  rtn.push({position:3,required:true,inputName:"简称",inputType:"",attributeName:"nodeAlias"});
  rtn.push({position:4,required:true,inputName:"名称",inputType:"",attributeName:"nodeName"});

  rtn.push({position:31,required:false,inputName:"位置经度",inputType:"num",attributeName:"nodeLatitude"});
  rtn.push({position:32,required:false,inputName:"位置纬度",inputType:"num",attributeName:"nodeAltitude"});
  rtn.push({position:33,required:false,inputName:"位置海拔",inputType:"num",attributeName:"nodeLongitude"});
  return rtn;
}
//待重写
//function genMsrAddPopup(){
//return [
//  {position:1,required:true,inputName:"度量指标ID",inputType:"",attributeName:"nodeId"},
//  {position:2,required:true,inputName:"度量指标编码",inputType:"",attributeName:"nodeId"},
//  {position:3,required:true,inputName:"度量指标名称",inputType:"",attributeName:"nodeId"},
//  {position:4,required:true,inputName:"度量指标简称",inputType:"",attributeName:"nodeId"},
//  {position:5,required:true,inputName:"度量指标公式",inputType:"",attributeName:"nodeId"}
//];
//}






function genFakeMsrInfo(){
  return {
  hasInfo: true,
  idxAlias: "HG.GHU2_TT8802A",
  idxCode: "310002_HG.GHU2_TT8802A",
  idxFormula: "HG.GHU2_TT8802A",
  idxId: 1288,
  idxName: "HG.GHU2_TT8802A",
  idxTypeId: 4,
  idxTypeName: "温度",
  mntDate: "2017-12-15 14:07:59",
  mntUserId: "1",
  mntUserName: "hx",
  mtrlId: -1,
  nodeCode: "Z6MP70441006",
  nodeId: 310002,
  nodeTypeName: "tank"
  };
}

/**
elem = {
position : Number
required : bool
inputName : String
inputType : option | str | num | bool | undefined
options : [option]
value : $$placeholder
}
 1	侧线
 2	罐
 3	料仓
 4	库位
 5	进出厂点
 6	采样点
 7	排放口
 8	设备
 9	管段
 10	阀门
 11	盲板
 12	三通
 */
