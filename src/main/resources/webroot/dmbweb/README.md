# 工厂模型控制台前端

## 1 概述

该项目旨在帮助维护人员高效且直观地对智能工厂数据进行维护。

## 2 功能

2.1 【增】
   a. 单个数据增加
   b. 批量数据增加（即【导入】功能。可导入`.xls .xlsx`等文件）
2.2 【删】
   a. 单个数据删除
   b. 批量数据删除
2.3 【改】
   a. 单个数据修改
   b. 批量数据修改（即【导入】功能。可导入`.xls .xlsx`等文件）
2.4 【查】
   a. 所有数据展示
   b. 模糊搜索（多条件高级搜索）
2.4 【导出】当前表所有数据导出
2.5 【导出模板】

## 3 页面

由于所有表结构统一，因此页面设计基于同一个组件，页面风格一致。初始化页面左侧菜单显示所有表列表。右侧显示对应表数据以及功能按钮。默认显示 【罐】表。

## 4 操作
详见操作手册

#### 5 代码
所有的前端代码存在于dmbweb/app内，文件结构为：
   components------------为【废弃】文件夹，目前不用
   lib-------------------存放angular框架js以及插件组件相关js文件----------------------修改代码一般不用动此文件夹
   model-----------------存放所有表的配置json以及左侧菜单栏配置json-------------------后面详细介绍
   style-----------------存放bootstrap样式文件
   uigrid----------------为【主要】文件，所有的功能以及页面，模态框展示都在此文件夹-----后面详细介绍
   viewTableSelector-----为左侧菜单栏列表配置文件夹
   app.css---------------为本地修改的样式
   app.js----------------定义路由，以及封装一些方法
   index.html------------入口文件
   
5.1 model文件夹
文件分两类：
    a.tableIndex.json,存储所有表的信息，包括：
  		  所有表分为：字典Dict||主模型Model||组态关系Rel||计量模型Msr||用户管理Use||系统管理Sys六大类
   		"tableName":表名
		"nameAlias": 表简称
		"tableFormat": 表对应的json配置文件
		"parent":
		"isShowSearch": 是否展示查询条件
		"desType": 该表新增及编辑模态框中描述属性的文本框长度
		"authPropertyValue": 权限
		"url": 该表调取后台数据的url
		"hide": 是否在菜单栏显示该表
		
	b.每个表详细字段的JSON描述文件（`model/x.x.x.json`），包括：	
		"display":表头显示的属性名称
		"type": 属性类型
		"required": 新增&编辑模态框是否必填，为true显示红色“*”
		"hide": 是否显示该属性
		"inOut": 是否导出
		"treeIsUpdate":是否可编辑（仅存在于显示标准组织机构树的属性中）
		"width": 宽度
		"class": 对齐方式
		"orgTree":根据选中的组织机构查询区域（仅存在于节点表）
		"parTree":选择上级组织机构（仅存在于选择标准组织机构树的表）
		"chilTree":根据组织机构（仅存在于选择标准组织机构树的表）
		"proSearch":该属性在查询区是否显示，是否可编辑
		"proUpdate":该属性在编辑模态框是否显示，是否可编辑
		"proAdd":该属性在新增模态框是否显示，是否可编辑
		"parent": 关联其他表
		"valueShowLine": 进出类型
		"valueShowSt": 是否启用
		"valueShowSex": 性别
		"valueShowOrg": 组织机构类型
		"valueShowTank": 显示罐类型
		"valueShowData": 源数据类型
		"searchInput":查询区关联查询
5.2 uiGrid文件夹
    文件名                                                         文件描述
    action.html                          	显示顶部按钮区和查询区
    conditionalQuery.html				 	弹窗。当查询区属性为关联查询时点击“放大镜”即可显示
    formulaEdit.html						弹窗。公式编辑器，基础配置和单罐配置表
    multiInsertOumOrg.html               	弹窗。OUM机构单元和OUM用户表引入
    multiInsertTeam.html				 	弹窗。用户表多条加入班组
    operation.html                       	表格功能按钮，删除&&编辑
    operationDel.html                		表格功能按钮，删除
    operationDetail.html 					表格功能按钮，详情
    operationUpe.html 						表格功能按钮，编辑
    org_tree_std.html						弹窗。标准组织机构树
    org_tree.html							弹窗。组织机构树（可选择业务域）
    parentSearch.html						弹窗。新增或者编辑弹窗关联查询
    rowCheckBox.html						表格功能按钮，行内多选框
    titleCheckBox.html						表格功能按钮，表头多选框
    titleOperation.html						表格功能按钮，表头文本提示
    uiGrid_ParentModelSearch.js				仅用于parentSearch.html查询关联表格数据
    uiGrid.html								uiGrid表格
    uiGrid.js								主要的控制文件
    user_model_add.html						弹窗。用户表新增弹窗的数据查询
    user_model_update.html					弹窗。用户表编辑弹窗的数据查询
    window_add.html							新增弹窗，用于所有表格
    window_delete.html						删除弹窗，用于所有表格
    window_import.html						导入弹窗，用于所有表格
    window_insert.html						弹窗。机构单元引入
    window_search.html						查询弹窗，用于所有表格（已弃用）
    window_update.html						修改弹窗，用于所有表格
#### 6 其他
6.1 文本框类型
    所有模态框的表单中，文本框类型主要包括以下：
	    1.普通文本输入框
	    2.下拉选择框（一般为关联的字典表或者其他boolean类型的属性）
	    3.查询框（一般为关联的模型表）
	    4.时间选择框
	    5.文件上传框
	    6.组织机构树选择框
	    7.公式编辑框
	    8.用户查询框
6.2 标识
        isRecursive：是否包含下级，否0，是1
		isPlatformRent：是否标准组织结构树机构树，否0，是1
		ofMeasindexType：度量指标表区分指标所属类型，节点0，区域1
		markOfVirtual ：能源节点表区分是否虚拟装置，装置0，虚拟装置1
		ofFms：装置界区与装置表区分是否虚拟装置，装置1，虚拟装置0
		isDeploy：是否引入，否0,是1
		leaf：节点物料关联表模态框里查询节点和物料
		notNodeTypeCode：采样点与节点对象关联表，模态框里查询节点
		inUse：是否启用，否0，是1
		dataStatus：是否启用，否0，是1